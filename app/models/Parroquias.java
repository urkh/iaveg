package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import com.avaje.ebean.Page;

@Entity
public class Parroquias extends Model {

	@Id
	public Long id;
	
	@Constraints.Required
	@ManyToOne
	public Municipios municipios;
	
	@Constraints.Required
	public String parroquia;
	
	
	public static Finder<Long, Parroquias> buscar = new Finder<Long, Parroquias>(Long.class, Parroquias.class);

	
	public static Page<Parroquias> pagina(int pagina, int tamanoPagina, String ordenarPor, String ordenar, String filtrar) {
		return buscar.where().ilike("parroquia", "%" + filtrar + "%").orderBy(ordenarPor + " " + ordenar).fetch("municipios").findPagingList(tamanoPagina).getPage(pagina);
	}
	

}
