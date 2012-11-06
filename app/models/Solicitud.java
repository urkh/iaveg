package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class Solicitud extends Model {

	@Id
	public Integer id;
	
	@Constraints.Required
	@ManyToOne
	public Registro registro;
	
	@Constraints.Required
	@ManyToOne
	public TipoSolicitudes solicitud;
	
	@Constraints.Required
	public String codigo;
	
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
	
	
}
