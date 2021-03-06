package controllers;

import models.Municipios;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.formMunicipios;

@Security.Authenticated(Seguridad.class)
public class CMunicipios extends Controller {
	
	public static Result Inicio = redirect(routes.CParroquias.listar(0, "parroquia", "asc", ""));

	public static Result index() {
		return Inicio;
	}
	
	public static Result nuevo() {
		Form<Municipios> formCMunicipios = form(Municipios.class);
		return ok(formMunicipios.render(formCMunicipios));
	}
	
	public static Result guardar(){
		
		Form<Municipios> formCMunicipios = form(Municipios.class).bindFromRequest();		
		
		formCMunicipios.get().save();
		flash("exito", "Municipio " + formCMunicipios.get().municipio + " guardado exitosamente!");
		return Inicio;
		
	}
	

}
