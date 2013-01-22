package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.Registro;
import play.data.Form;
import play.data.DynamicForm;
import play.db.DB;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.formRegistros;
import views.html.listarRegistros;

@Security.Authenticated(Seguridad.class)
public class CRegistro extends Controller {
	
	
	public static Result Inicio = redirect(routes.CRegistro.listar(0, "cedula", "asc", ""));
	
	
	public static Result index(){
		return Inicio;
	}
	
	
	public static Result listar(int pagina, String ordenarPor, String orden, String filtro) {
		return ok(listarRegistros.render(Registro.pagina(pagina, 10, ordenarPor, orden, filtro), ordenarPor, orden, filtro));
	}
	
	
	public static Result nuevo() {
		Form<Registro> formCRegistro = form(Registro.class);
		return ok(formRegistros.render(formCRegistro));
	}
	
	
	public static Result editar(Long id){
		Form<Registro> formCRegistro = form(Registro.class).fill(Registro.buscar.byId(id));
		return ok(formRegistros.render(formCRegistro));
	}
	
	public static Result actualizar(){
		Form<Registro> formCRegistros = form(Registro.class).bindFromRequest();
		
		String ids = formCRegistros.field("id").value();
		
		Long id = Long.parseLong(ids);
		formCRegistros.get().update(id);
		
		flash("exito", "La Solicitud  ha sido actualizada con exito");
		return Inicio;
	}
	
	
	public static Result guardar(){
		
		Form<Registro> formCRegistro = form(Registro.class).bindFromRequest();
		DynamicForm formRegConyugue = form().bindFromRequest();
		
		formCRegistro.get().save();
		
		String cedulaCo = formRegConyugue.get("cedulaCo");
		
		
		if (cedulaCo!=null) {
			
			try {
				
				Connection con = DB.getConnection(); 

				//formCRegistro.get();
				Long maxId = (long) Registro.buscar.findRowCount();
				String nombreCo = formRegConyugue.get("nombreCo");
				String apellidoCo = formRegConyugue.get("apellidoCo");
				String fechaNacCo = formRegConyugue.get("fechaNacCo");
				String nacionalidadCo = formRegConyugue.get("nacionalidadCo");
				String sexoCo = formRegConyugue.get("sexoCo");
				
				PreparedStatement pstm = con.prepareStatement("INSERT INTO registro_conyugue (registro_id, cedula, nombres, apellidos, fecha_nac, nacionalidad, sexo) VALUES ('" +maxId+ "', '" + cedulaCo + "', '" + nombreCo + "', '" + apellidoCo + "', '" + fechaNacCo + "', '" + nacionalidadCo + "', '"+ sexoCo +"');");


				pstm.executeUpdate();

			} catch (SQLException e) {
				System.out.println(e);
			}
			
		}

		
		
		flash("exito", "El registro de solicitud se ha realizado exitosamente!");
		return Inicio;
			
	}
	


}
