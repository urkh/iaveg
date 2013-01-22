package models;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class Municipios extends Model {
	
	@Id
	public Long id;
	
	@Constraints.Required
	public String municipio;
	 
	public static Finder<Long, Municipios> buscar = new Finder<Long, Municipios>(Long.class, Municipios.class);
	
	public static Map<String, String> opciones(){
		LinkedHashMap<String, String> opciones = new LinkedHashMap<String, String>();
		for (Municipios municipios: Municipios.buscar.orderBy("municipio").findList()){
			opciones.put(municipios.id.toString(), municipios.municipio);
		}
		return opciones;
	}
	
	
	

}
