package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Page;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

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
	@Formats.DateTime(pattern = "yyyy-mm-dd")
	public Date fechaRegSol;
	
	@Constraints.Required
	public Character lph;
	
	@Constraints.Required
	public String tenencia;
	
	@Constraints.Required
	public String estadoSol;
	
	@Constraints.Required
	public Character docCompleta;
	
	@Constraints.Required
	public String observacion;
	
	
	
	public static Finder<Long, Solicitud> buscar = new Finder<Long, Solicitud>(Long.class, Solicitud.class);
	
	public static Page<Solicitud> pagina(int pagina, int tamanoPagina, String ordenarPor, String ordenar, String filtrar) {
		return buscar.where().ilike("registro.cedula", "%" + filtrar + "%").orderBy(ordenarPor + " " + ordenar).fetch("solicitud").fetch("registro").findPagingList(tamanoPagina).getPage(pagina);
	}

	
	
}
