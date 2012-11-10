package models;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints;
import play.db.ebean.Model;


@Entity
public class TipoSolicitudes extends Model {
	
	@Id
	public Integer id;
	
	@Constraints.Required
	public String solicitud;
	
	@Constraints.Required
	public String codigo;
	
	public static Finder<Long, TipoSolicitudes> buscar = new Finder<Long, TipoSolicitudes>(Long.class, TipoSolicitudes.class);
	
	public static Map<String, String> opciones (){
		LinkedHashMap<String, String> opciones = new LinkedHashMap<String, String>();
		for(TipoSolicitudes tipoSolicitudes: TipoSolicitudes.buscar.orderBy("solicitud").findList()){
			opciones.put(tipoSolicitudes.id.toString(), tipoSolicitudes.solicitud);
		}
		return opciones;
	}
	

}
