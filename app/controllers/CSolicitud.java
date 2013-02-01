package controllers;

import models.Solicitud;
import play.data.DynamicForm;
import play.data.Form;
import play.db.DB;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import util.pdf.PDF;
import views.html.formSolicitudes;
import views.html.listarSolicitudes;
import views.html.comprobante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Security.Authenticated(Seguridad.class)
public class CSolicitud extends Controller {
	
	public static Result Inicio = redirect(routes.CSolicitud.listar(0, "cedula", "asc", ""));
	
	public static Result index(){
		return Inicio;
	}


	public static Result listar(int pagina, String ordenarPor, String orden, String filtro) {
		return ok(listarSolicitudes.render(Solicitud.pagina(pagina, 10, ordenarPor, orden, filtro), ordenarPor, orden, filtro));
	}
	
	
	
	
	public static Result comprobante(Long id) {

		Connection con = DB.getConnection();
		String lph = null;
		String fechaRegSol = null;
		String tenencia = null;
		String estadoSol = null;
		String docCompleta = null;
		String observacion = null;
		String cedula = null;
		String nombre = null;
		String apellido = null;
		String telefono = null;
		String nacionalidad = null;
		String direccion = null;
		String sexo = null;
		String ingresoMensual = null;
		String hijos = null;
		String solicitud = null;

		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		
		
		try{
			PreparedStatement pstm = con.prepareStatement("SELECT so.fecha_reg_sol, so.lph, so.tenencia, so.estado_sol, so.doc_completa, so.observacion, re.cedula, re.nombre, re.apellido, re.telefono, re.nacionalidad, re.direccion, re.sexo, re.ingreso_mensual, re.hijos, tso.solicitud FROM solicitud so, registro re, tipo_solicitudes tso WHERE so.registro_id=re.id AND tso.id=so.solicitud_id AND so.id='" + id + "'");

			ResultSet res = pstm.executeQuery();
			
			if(res.next()){
				
				 fechaRegSol = df.format(res.getDate("fecha_reg_sol"));
				 lph = res.getString("lph");
				 tenencia = res.getString("tenencia");
				 estadoSol = res.getString("estado_sol");
				 docCompleta = res.getString("doc_completa");
				 observacion = res.getString("observacion");
				 cedula = String.valueOf(res.getInt("cedula"));
				 nombre = res.getString("nombre");
				 apellido = res.getString("apellido");
				 telefono = String.valueOf(res.getInt("telefono"));
				 nacionalidad = res.getString("nacionalidad");
				 direccion = res.getString("direccion");
				 sexo = res.getString("sexo");
				 ingresoMensual = String.valueOf(res.getDouble("ingreso_mensual"));
				 hijos = String.valueOf(res.getInt("hijos"));
				 solicitud = res.getString("solicitud");


				 

			    
			}

	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
		
		return PDF.ok(comprobante.render(lph, fechaRegSol, tenencia, estadoSol, docCompleta, observacion, cedula, nombre, apellido, telefono, nacionalidad, direccion, sexo, ingresoMensual, hijos, solicitud));
	}

	
	
	
	
	public static Result buscarCedula() {
		Map<String,String[]> queryParameters = request().queryString();

		String cedulaRe = queryParameters.get("busqueda")[0];
		Connection con = DB.getConnection(); 
		Status resp = null;
		
		try{
			PreparedStatement pstm = con.prepareStatement("SELECT id, cedula, nombre, apellido FROM registro WHERE cedula='" + cedulaRe + "' ");

			ResultSet res = pstm.executeQuery();
			if (res.next()){
				
				String idRegistro = res.getString("id");
				String cedula = res.getString("cedula");
				String nombre = res.getString("nombre");
				String apellido = res.getString("apellido");
				
				Map<String,String> d = new HashMap<String,String>();
						
			
		        d.put("registro",idRegistro);
		        d.put("cedula",cedula);
		        d.put("nombre",nombre);
		        d.put("apellido",apellido);       
		    
				
		        resp=ok(Json.toJson(d)); 

		    }else{
		    	
		    	resp=badRequest("Cedula no encontrada");
		    
		    }
		

	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
		
		
		return resp;
		
	}
	
	
	
	public static Result nuevo() {
		Form<Solicitud> formCSolicitudes = form(Solicitud.class);
		return ok(formSolicitudes.render(formCSolicitudes));
	}


	public static Result editar(Long id){
		Form<Solicitud> formCSolicitudes = form(Solicitud.class).fill(Solicitud.buscar.byId(id));
		return ok(formSolicitudes.render(formCSolicitudes));
	}
	
	
	
	
	public static Result actualizar(){
	
		DynamicForm formActSol = form().bindFromRequest();
		
		try {
				
			String ids = formActSol.get("id");
			String tipoSolicitudes = formActSol.get("solicitud.id");
			String lph = formActSol.get("lph");
			String tenencia = formActSol.get("tenencia");
			String estadoSol = formActSol.get("estadoSol");
			String docCompleta = formActSol.get("docCompleta");
			String observacion = formActSol.get("observacion");
			
			Connection con = DB.getConnection(); 
			Long id = Long.parseLong(ids);

			
			PreparedStatement pstm = con.prepareStatement("UPDATE solicitud SET solicitud_id='" +tipoSolicitudes+ "', lph='" +lph+ "', tenencia='" +tenencia+ "', estado_sol='" +estadoSol+ "', doc_completa='"+docCompleta+"', observacion='"+observacion+"' WHERE id='"+id+"';");
			pstm.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
		}

		flash("exito", "Solicitud actualizada con exito");
		return Inicio;
	}
	
	
	
	
	public static Result guardar(){
		
		DynamicForm formRegSol = form().bindFromRequest();
				
			
			try {
				
				String idRegistro = formRegSol.get("registro");
				String tipoSolicitudes = formRegSol.get("solicitud.id");
				String lph = formRegSol.get("lph");
				String tenencia = formRegSol.get("tenencia");
				String estadoSol = formRegSol.get("estadoSol");
				String docCompleta = formRegSol.get("docCompleta");
				String observacion = formRegSol.get("observacion");
				
				
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
   				Date fechaDate = new Date();
   				String fecha = df.format(fechaDate);
							

				Connection con = DB.getConnection(); 
				PreparedStatement pstm = con.prepareStatement("INSERT INTO solicitud (registro_id, solicitud_id, lph, tenencia, estado_sol, doc_completa, observacion, fecha_reg_sol) VALUES ('" +idRegistro+ "', '" +tipoSolicitudes+ "', '" +lph+ "', '" +tenencia+ "', '" +estadoSol+ "', '"+docCompleta+"', '"+observacion+"', '"+fecha+"');");
				pstm.executeUpdate();

			} catch (SQLException e) {
				System.out.println(e);
			}
			
		
		flash("exito", "La solicitud ha sido guardada exitosamente!");
		return Inicio;
		
	}
	

}
