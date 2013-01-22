package models;

import java.util.LinkedHashMap;
import java.util.Map;

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
	
	
	public static Map<String, String> opciones(){
		LinkedHashMap<String, String> opciones = new LinkedHashMap<String, String>();
		for (Parroquias parroquias: Parroquias.buscar.orderBy("parroquia").findList()){
			opciones.put(parroquias.id.toString(), parroquias.parroquia);
		}
		return opciones;
	}
	

}
