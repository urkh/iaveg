package models;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Page;

import play.data.validation.Constraints;
import play.db.ebean.Model;


@Entity
public class TipoSolicitudes extends Model {
	
	@Id
	public Long id;
	
	@Constraints.Required
	public String solicitud;
	
	@Constraints.Required
	public String codigo;
	
	public static Finder<Long, TipoSolicitudes> buscar = new Finder<Long, TipoSolicitudes>(Long.class, TipoSolicitudes.class);


	public static Page<TipoSolicitudes> pagina(int pagina, int tamanoPagina, String ordenarPor, String ordenar, String filtrar) {
		return buscar.where().ilike("solicitud", "%" + filtrar + "%").orderBy(ordenarPor + " " + ordenar).findPagingList(tamanoPagina).getPage(pagina);
	}
	
	public static Map<String, String> opciones (){
		LinkedHashMap<String, String> opciones = new LinkedHashMap<String, String>();
		for(TipoSolicitudes tipoSolicitudes: TipoSolicitudes.buscar.orderBy("solicitud").findList()){
			opciones.put(tipoSolicitudes.id.toString(), tipoSolicitudes.solicitud);
		}
		return opciones;
	}
	

}
