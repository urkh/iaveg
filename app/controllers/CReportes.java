package controllers;

import play.db.DB;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;



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


	public static Result buscarTorta() {
		Map<String,String[]> queryParameters = request().queryString();

		String fechaD = queryParameters.get("fechaD")[0];
		String fechaH = queryParameters.get("fechaH")[0];

		Connection con = DB.getConnection();
		PreparedStatement pstm = null;
		ResultSet res = null;
		
		Status resp = null;
		Integer apro = null;
		Integer repro = null;
		
		try{

			pstm = con.prepareStatement("SELECT count(id) AS apro FROM solicitud WHERE estado_sol='Aprobada' AND DATE(fecha_reg_sol) BETWEEN '"+fechaD+"' AND '"+fechaH+"'");
			res = pstm.executeQuery();

			if (res.next()){

				apro = res.getInt("apro");

		    }else{
		    	apro = 0;
		    }
			
			
		} catch (SQLException e) {
	    	e.printStackTrace();
	    } 



	   	try{

			pstm = con.prepareStatement("SELECT count(id) AS repro FROM solicitud WHERE estado_sol='No Aprobada'AND DATE(fecha_reg_sol) BETWEEN '"+fechaD+"' AND '"+fechaH+"'");
			
			res = pstm.executeQuery();
			
			if (res.next()){

				repro = res.getInt("repro");

		    }else{

		    	repro = 0;
		    }
			
			
		} catch (SQLException e) {
	    	e.printStackTrace();
	    } finally{
	    	  SQLUtils.closeQuietly(res);
	    	  SQLUtils.closeQuietly(pstm);
	    	  SQLUtils.closeQuietly(con);
	   	}	
		
		return resp=ok("[[\"Aprobadas\", "+apro+"], [\"No Aprobadas\", "+repro+"]]"); 
		
	}


}