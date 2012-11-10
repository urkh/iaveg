package controllers;

import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;



public class Seguridad extends Security.Authenticator {
    
    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("usuario");
    }
    
    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.LoginU.login());
    }
    

    
}