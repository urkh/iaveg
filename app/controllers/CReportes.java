package controllers;

import play.db.DB;
import play.libs.Json;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.node.ObjectNode;

import views.html.reportes;
import views.html.estadisticas;

@Security.Authenticated(Seguridad.class)
public class CReportes extends Controller{



	public static Result reportes(){
		return ok(reportes.render());

	}



	public static Result estadisticas(){
		return ok(estadisticas.render());

	}


	public static Result buscarE() {
		Map<String,String[]> queryParameters = request().queryString();

		String cedulaRe = queryParameters.get("buscar")[0];
		Connection con = DB.getConnection(); 
		Status resp = null;
		
		
		try{
			PreparedStatement pstm = con.prepareStatement("SELECT count(id) AS apro FROM solicitud WHERE estado_sol='Aprobada'");
			ResultSet res = pstm.executeQuery();
			
			
			if (res.next()){
				
				
				System.out.println(cedulaRe);
				String apro = res.getString("apro");
				//String repro = res.getString("repro");

				//Map<String,String> d = new HashMap<String,String>();

				
				List d = Arrays.asList(apro, "9");
				
				
		        //d.put("apro", "9");
		        //d.put("repro", "6");
		       // d.put("registro", repro);

	
				 
				resp=ok(Json.toJson(d)); 

		    }else{
		    	
		    	resp=badRequest("No se encontraron resultados.");
		    
		    }
			
			
		} catch (SQLException e) {
	    	e.printStackTrace();
	    }
			
		
		return resp;
		
	}


}