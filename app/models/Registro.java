package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;


@Entity
public class Registro extends Model {


	@Id
	public Long id;
	
	@Constraints.Required
	@OneToOne
	public Municipios municipios;
	
	@Constraints.Required
	@OneToOne
	public Parroquias parroquias;
	
	@Constraints.Required(message = "Este campo es requerido")
	public Integer cedula;
	
	@Constraints.Required
	public String nombres;
	
	@Constraints.Required
	public String apellidos;
	
	public String telefono;
	
	@Constraints.Required
	@Formats.DateTime(pattern = "yyyy-mm-dd")
	public Date fechaNac;
	
	@Constraints.Required
	@Formats.DateTime(pattern = "yyyy-mm-dd")
	public Date fechaReg;
	
	@Constraints.Required
	public String nacionalidad;
	
	@Constraints.Required
	public String estadoCivil;
	
	@Constraints.Required
	public String direccion;
	
	@Constraints.Required
	public String sexo;
	
	@Constraints.Required
	public Double ingresoMensual;
	
	@Constraints.Required
	public Integer hijos;
	
	
	
	
	
	
	
	
	
	
}
