package models;

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

}
