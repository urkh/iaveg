package controllers;

import models.Usuario;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.listarUsuarios;
import views.html.formUsuario;


@Security.Authenticated(Seguridad.class)
public class CUsuario extends Controller {
	
	public static Result Inicio = redirect(routes.CUsuario.listar(0, "usuario", "asc", ""));
	
	
	public static Result listar(int pagina, String ordenarPor, String orden, String filtro){
		return ok(listarUsuarios.render(Usuario.pagina(pagina, 10, ordenarPor, orden, filtro), ordenarPor, orden, filtro));
		
	}
	
	public static Result nuevo() {
		Form<Usuario> formCUsuario = form(Usuario.class);
		return ok(formUsuario.render(formCUsuario));
	}
	
	public static Result editar(Long id){
		Form<Usuario> formCUsuario = form(Usuario.class).fill(Usuario.buscar2.byId(id));
		return ok(formUsuario.render(formCUsuario));
	}
	
	public static Result actualizar(){
		Form<Usuario> formCUsuario = form(Usuario.class).bindFromRequest();
		
		String ids = formCUsuario.field("id").value();
		
		Long id = Long.parseLong(ids);
		formCUsuario.get().update(id);
		
		flash("exito", "El usuario " + formCUsuario.get().usuario + " ha sido actualizada con exito");
		
		return Inicio;
	}
	
	
	public static Result guardar(){
		Form<Usuario> formCUsuario = form(Usuario.class).bindFromRequest();
		formCUsuario.get().save();
		return Inicio;
	}
	
	
	public static Result borrar(String usuario) {
		Usuario.buscar.ref(usuario).delete();
		flash("exito", "El usuario ha sido borrado exitosamente!");
		return Inicio;
	}
	

}
