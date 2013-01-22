package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class RegistroConyugue extends Model {
	
	@Id
	public Long id;
	
	@Constraints.Required
	@OneToOne
	public Registro registro;
	
	@Constraints.Required
	public Integer cedula;
	
	@Constraints.Required
	public String nombres;
	
	@Constraints.Required
	public String apellidos;
	
	@Constraints.Required
	@Formats.DateTime(pattern = "yyyy-mm-dd")
	public Date fechaNac;
	
	@Constraints.Required
	public String nacionalidad;
	
	@Constraints.Required
	public String sexo;
	
	
	

}
