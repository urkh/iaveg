package controllers;

import models.Registro;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
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
	
	
	public static Result guardar(){
		
		Form<Registro> formCRegistro = form(Registro.class).bindFromRequest();
		
		if (formCRegistro.hasErrors()) {
			return badRequest(formRegistros.render(formCRegistro));
		}
		
		formCRegistro.get().save();
		flash("exito", "El registro se ha realizado exitosamente!");
		return Inicio;
			
	}
	


}
