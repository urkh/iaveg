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


/*    public static boolean isMemberOf(Long project) {
        return Project.isMember(
            project,
            Context.current().request().username()
        );
    }
    
    public static boolean isOwnerOf(Long task) {
        return Task.isOwner(
            task,
            Context.current().request().username()
        );
    }*/
    

    
}
