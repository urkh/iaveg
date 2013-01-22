package controllers;

import models.Usuario;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.formLogin;

public class LoginU extends Controller {
  

    
    public static class Login {
        
        public String usuario;
        public String contrasena;
        
        public String validar() {
            if(Usuario.autenticar(usuario, contrasena) == null) {
                return "Usuario o contrasena invalida";
            }
            return null;
        }
        
    }


    public static Result login() {
        return ok(formLogin.render(form(Login.class)));
    }
    

    public static Result autenticar() {
        Form<Login> formCLogin = form(Login.class).bindFromRequest();
        
        if(formCLogin.hasErrors()) {
            return badRequest(formLogin.render(formCLogin));
        } else {
            session("usuario", formCLogin.get().usuario);
            return redirect(routes.CRegistro.index());
        }
    }


    public static Result desconectar() {
        session().clear();
        flash("success", "Has sido desconectado");
        return redirect(routes.LoginU.login());
    }
  
 
    


}
