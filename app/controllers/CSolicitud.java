package controllers;

import models.Solicitud;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.formSolicitudes;
import views.html.listarSolicitudes;


@Security.Authenticated(Seguridad.class)
public class CSolicitud extends Controller {
	
	public static Result Inicio = redirect(routes.CSolicitud.listar(0, "solicitud", "asc", ""));
	
	public static Result index(){
		return Inicio;
	}


	public static Result listar(int pagina, String ordenarPor, String orden, String filtro) {
		return ok(listarSolicitudes.render(Solicitud.pagina(pagina, 10, ordenarPor, orden, filtro), ordenarPor, orden, filtro));
	}
	
	public static Result nuevo() {
		Form<Solicitud> formCSolicitudes = form(Solicitud.class);
		return ok(formSolicitudes.render(formCSolicitudes));
	}


	public static Result editar(Long id){
		Form<Solicitud> formCSolicitudes = form(Solicitud.class).fill(Solicitud.buscar.byId(id));
		return ok(formSolicitudes.render(formCSolicitudes));
	}
	
	public static Result actualizar(){
		Form<Solicitud> formCSolicitudes = form(Solicitud.class).bindFromRequest();

		String ids = formCSolicitudes.field("id").value();
		
		Long id = Long.parseLong(ids);
		formCSolicitudes.get().update(id);
		flash("exito", "Solicitud " + formCSolicitudes.get().solicitud + " actualizada con exito");
		return Inicio;
	}
	
	public static Result guardar(){
		
		Form<Solicitud> formTSolicitudes = form(Solicitud.class).bindFromRequest();		
		
		formTSolicitudes.get().save();
		flash("exito", "Solicitud " + formTSolicitudes.get().solicitud + " guardada exitosamente!");
		return Inicio;
		
	}
	

}
