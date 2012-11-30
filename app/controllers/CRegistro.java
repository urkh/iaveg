package controllers;

import models.Registro;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.editarRegistros;
import views.html.formRegistros;
import views.html.listarRegistros;

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
		return ok(editarRegistros.render(id, formCRegistro));
	}
	
	public static Result actualizar(Long id){
		Form<Registro> formCRegistros = form(Registro.class).bindFromRequest();
		if(formCRegistros.hasErrors()){
			return badRequest(editarRegistros.render(id, formCRegistros));
		}
		
		formCRegistros.get().update(id);
		flash("exito", "La Solicitud  ha sido actualizada con exito");
		return Inicio;
	}
	
	
	public static Result guardar(){
		
		Form<Registro> formCRegistro = form(Registro.class).bindFromRequest();
		
		formCRegistro.get().save();
		flash("exito", "El registro de solicitud se ha realizado exitosamente!");
		return Inicio;
			
	}
	


}
