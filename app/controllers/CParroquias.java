package controllers;


import models.Parroquias;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.formParroquias;
import views.html.listarParroquias;

@Security.Authenticated(Seguridad.class)
public class CParroquias extends Controller {
	
	
	public static Result Inicio = redirect(routes.CParroquias.listar(0, "parroquia", "asc", ""));
	
	
	public static Result index(){
		return Inicio;
	}
	
	
	public static Result listar(int pagina, String ordenarPor, String orden, String filtro) {
		return ok(listarParroquias.render(Parroquias.pagina(pagina, 10, ordenarPor, orden, filtro), ordenarPor, orden, filtro));
	}
	
	public static Result editar(Long id){
		Form<Parroquias> formCParroquias = form(Parroquias.class).fill(Parroquias.buscar.byId(id));
		return ok(formParroquias.render(formCParroquias));
	}
	
	public static Result actualizar(){
		Form<Parroquias> formCParroquias = form(Parroquias.class).bindFromRequest();

		String ids = formCParroquias.field("id").value();
		
		Long id = Long.parseLong(ids);
		formCParroquias.get().update(id);
		flash("exito", "La Parroquia " + formCParroquias.get().parroquia + " ha sido actualizada con exito");
		return Inicio;
	}
	
	
	public static Result nuevo() {
		Form<Parroquias> formCParroquias = form(Parroquias.class);
		return ok(formParroquias.render(formCParroquias));
	}
	
	
	public static Result guardar(){
		
		Form<Parroquias> formCParroquias = form(Parroquias.class).bindFromRequest();
		
		formCParroquias.get().save();
		
		flash("exito", "Parroquia " + formCParroquias.get().parroquia + "guardada exitosamente!");
		return Inicio;
			
	}
	
}
