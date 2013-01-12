package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import com.avaje.ebean.Page;


@Entity 
@Table(name="usuarios")
public class Usuario extends Model {

    @Id
    public Long id;
    
    @Constraints.Required
    @Formats.NonEmpty
    public String usuario;
    
    @Constraints.Required
    public String contrasena;
    
    @Constraints.Required
    public Integer cedula;
    
    @Constraints.Required
    public String nombre;
    
    @Constraints.Required
    public String apellido;
    
    @Constraints.Required
    public String sexo;
    
    public String tipo;
    
    public String theme;
    
   
    

    
    public static Finder<String, Usuario> buscar = new Finder<String, Usuario>(String.class, Usuario.class);
    public static Finder<Long, Usuario> buscar2 = new Finder<Long, Usuario>(Long.class, Usuario.class);
    
    
    public static List<Usuario> buscarTodo() {
        return buscar.all();
    }


    public static Usuario buscarUsuario(String usuario) {
        return buscar.where().eq("usuario", usuario).findUnique();
    }
    

    public static Usuario autenticar(String usuario, String contrasena) {
        return buscar.where().eq("usuario", usuario).eq("contrasena", contrasena).findUnique();
    }
    
    
    public static Page<Usuario> pagina(int pagina, int tamanoPagina, String ordenarPor, String ordenar, String filtrar) {
		return buscar2.where().ilike("usuario", "%" + filtrar + "%").orderBy(ordenarPor + " " + ordenar).findPagingList(tamanoPagina).getPage(pagina);
	}
    
    
    public String toString() {
        return "Usuario(" + usuario + ")";
    }

}
