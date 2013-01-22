package controllers;

import models.TipoSolicitudes;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.formTipoSolicitudes;
import views.html.listarTipoSol;


@Security.Authenticated(Seguridad.class)
public class CTSolicitud extends Controller {
	
	public static Result Inicio = redirect(routes.CTSolicitud.listar(0, "solicitud", "asc", ""));
	
	public static Result index(){
		return Inicio;
	}


	public static Result listar(int pagina, String ordenarPor, String orden, String filtro) {
		return ok(listarTipoSol.render(TipoSolicitudes.pagina(pagina, 10, ordenarPor, orden, filtro), ordenarPor, orden, filtro));
	}
	
	public static Result nuevo() {
		Form<TipoSolicitudes> formTSolicitudes = form(TipoSolicitudes.class);
		return ok(formTipoSolicitudes.render(formTSolicitudes));
	}


	public static Result editar(Long id){
		Form<TipoSolicitudes> formTSolicitudes = form(TipoSolicitudes.class).fill(TipoSolicitudes.buscar.byId(id));
		return ok(formTipoSolicitudes.render(formTSolicitudes));
	}
	
	public static Result actualizar(){
		Form<TipoSolicitudes> formTSolicitudes = form(TipoSolicitudes.class).bindFromRequest();

		String ids = formTSolicitudes.field("id").value();
		
		Long id = Long.parseLong(ids);
		formTSolicitudes.get().update(id);
		flash("exito", "Solicitud " + formTSolicitudes.get().solicitud + " actualizada con exito");
		return Inicio;
	}
	
	public static Result guardar(){
		
		Form<TipoSolicitudes> formTSolicitudes = form(TipoSolicitudes.class).bindFromRequest();		
		
		formTSolicitudes.get().save();
		flash("exito", "Solicitud " + formTSolicitudes.get().solicitud + " guardada exitosamente!");
		return Inicio;
		
	}
	

}
