package models;

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
	
	

}