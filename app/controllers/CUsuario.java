package controllers;

import models.Usuario;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.formUsuarios;
import views.html.listarUsuarios;


@Security.Authenticated(Seguridad.class)
public class CUsuario extends Controller {
	
	public static Result Inicio = redirect(routes.CUsuario.listar(0, "usuario", "asc", ""));
	
	public static Result nuevo(){
		Form<Usuario> formCUsuario = form(Usuario.class);
		return ok(formUsuarios.render(formCUsuario));
	}
	
	public static Result listar(int pagina, String ordenarPor, String orden, String filtro){
		return ok(listarUsuarios.render(Usuario.pagina(pagina, 10, ordenarPor, orden, filtro), ordenarPor, orden, filtro));
		
	}
	
	
	
	public static Result guardar(){
		Form<Usuario> formCUsuario = form(Usuario.class).bindFromRequest();
		formCUsuario.get().save();
		return Inicio;
	}
	

}
