package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;


@Entity 
@Table(name="usuarios")
public class Usuario extends Model {

    @Id
    @Constraints.Required
    @Formats.NonEmpty
    public String usuario;
    
    @Constraints.Required
    public String nombre;
    
    @Constraints.Required
    public String contrasena;
    

    
    public static Finder<String, Usuario> buscar = new Finder<String, Usuario>(String.class, Usuario.class);
    
    
    public static List<Usuario> buscarTodo() {
        return buscar.all();
    }


    public static Usuario buscarUsuario(String usuario) {
        return buscar.where().eq("usuario", usuario).findUnique();
    }
    

    public static Usuario authenticate(String usuario, String contrasena) {
        return buscar.where().eq("usuario", usuario).eq("contrasena", contrasena).findUnique();
    }
    
    
    public String toString() {
        return "Usuario(" + usuario + ")";
    }

}
