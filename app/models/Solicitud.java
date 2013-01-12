package models;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Page;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Solicitud extends Model {

	@Id
	public Long id;
	
	@Constraints.Required
	@ManyToOne
	public Registro registro;
	
	@Constraints.Required
	@ManyToOne
	public TipoSolicitudes solicitud;
	
	@Constraints.Required
	public String codigo;
	
	@Constraints.Required
	@Formats.DateTime(pattern = "yyyy-mm-dd")
	public Date fechaReg;
	
	@Constraints.Required
	public Boolean lph;
	
	@Constraints.Required
	public String tenencia;
	
	@Constraints.Required
	public String estadoSol;
	
	@Constraints.Required
	public Boolean docCompleta;
	
	@Constraints.Required
	public String observacion;
	
	
	
	public static Finder<Long, Solicitud> buscar = new Finder<Long, Solicitud>(Long.class, Solicitud.class);

	
	public static Page<Solicitud> pagina(int pagina, int tamanoPagina, String ordenarPor, String ordenar, String filtrar) {
		return buscar.where().ilike("solicitud", "%" + filtrar + "%").orderBy(ordenarPor + " " + ordenar).findPagingList(tamanoPagina).getPage(pagina);
	}
	

	
	
}
