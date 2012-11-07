package models;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class Parroquias extends Model {

	@Id
	public Long id;
	
	@Constraints.Required
	public String parroquia;
	
	public static Finder<Long, Parroquias> buscar = new Finder<Long, Parroquias>(Long.class, Parroquias.class);
	
	public static Map<String, String> opciones(){
		LinkedHashMap<String, String> opciones = new LinkedHashMap<String, String>();
		for(Parroquias parroquias: Parroquias.buscar.orderBy("parroquia").findList()){
			opciones.put(parroquias.id.toString(), parroquias.parroquia);
		}
		return opciones;
	}
	

}
