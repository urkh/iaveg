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

			pstm = con.prepareStatement("SELECT COUNT(CASE WHEN estado_sol='Aprobada' THEN estado_sol END) AS apro, COUNT(CASE WHEN estado_sol='No Aprobada' THEN estado_sol END) AS repro FROM solicitud WHERE DATE(fecha_reg_sol) BETWEEN '"+fechaD+"' AND '"+fechaH+"'");
			res = pstm.executeQuery();

			if (res.next()){

				apro = res.getInt("apro");
				repro = res.getInt("repro");

		    }else{
		    	apro = 0;
		    	repro = 0;
		    }
			
			
		} catch (SQLException e) {
	    	e.printStackTrace();
	    } finally {
	    	  SQLUtils.closeQuietly(res);
	    	  SQLUtils.closeQuietly(pstm);
	    	  SQLUtils.closeQuietly(con);
	   	}
		
		return resp=ok("[[\"Aprobadas\", "+apro+"], [\"No Aprobadas\", "+repro+"]]"); 
		
	}




	public static Result buscarLinea() {

		Map<String,String[]> queryParameters = request().queryString();
		String fechaD = queryParameters.get("fechaD")[0];
		String fechaH = queryParameters.get("fechaH")[0];

		Connection con = DB.getConnection();
		PreparedStatement pstm = null;
		ResultSet res = null;
		Status resp = null;
		
		String json = "";
		String json2 = "";
		String[] apro = new String[12];
		String[] repro = new String[12];

		try{
			
			pstm = con.prepareStatement("SELECT CASE  WHEN MONTH(fecha_reg_sol) = 1 THEN 'Enero' WHEN MONTH(fecha_reg_sol) = 2 THEN 'Febrero' WHEN MONTH(fecha_reg_sol) = 3 THEN 'Marzo' WHEN MONTH(fecha_reg_sol) = 4 THEN 'Abril' WHEN MONTH(fecha_reg_sol) = 5 THEN 'Mayo' WHEN MONTH(fecha_reg_sol) = 6 THEN 'Junio'  WHEN MONTH(fecha_reg_sol) = 7 THEN 'Julio'  WHEN MONTH(fecha_reg_sol) = 8 THEN 'Agosto' WHEN MONTH(fecha_reg_sol) = 9 THEN 'Septiembre'  WHEN MONTH(fecha_reg_sol) = 10 THEN 'Octubre' WHEN MONTH(fecha_reg_sol) = 11 THEN 'Noviembre'  WHEN MONTH(fecha_reg_sol) = 12 THEN 'Diciembre' END AS mes, COUNT(CASE WHEN estado_sol='Aprobada' THEN estado_sol END) AS apro, COUNT(CASE WHEN estado_sol='No Aprobada' THEN estado_sol END) AS repro  FROM solicitud WHERE fecha_reg_sol BETWEEN '"+fechaD+"' AND '"+fechaH+"' GROUP BY MONTH(fecha_reg_sol)");
			res = pstm.executeQuery();

			while (res.next()){

				
				if(res.getString(1).equals("Enero")){

					apro[0]=res.getString(2);
					repro[0]=res.getString(3);
					
				}else if(res.getString(1).equals("Febrero")){
					
					apro[1]=res.getString(2);
					repro[1]=res.getString(3);
					
					
				}else if(res.getString(1).equals("Marzo")){
					
					apro[2]=res.getString(2);
					repro[2]=res.getString(3);
					
					
				}else if(res.getString(1).equals("Abril")){
					
					apro[3]=res.getString(2);
					repro[3]=res.getString(3);
					
					
				}else if(res.getString(1).equals("Mayo")){
					
					apro[4]=res.getString(2);
					repro[4]=res.getString(3);
					
					
				}else if(res.getString(1).equals("Junio")){
					
					apro[5]=res.getString(2);
					repro[5]=res.getString(3);
					
					
				}else if(res.getString(1).equals("Julio")){
					
					apro[6]=res.getString(2);
					repro[6]=res.getString(3);
					
					
				}else if(res.getString(1).equals("Agosto")){
					
					apro[7]=res.getString(2);
					repro[7]=res.getString(3);
					
					
				}else if(res.getString(1).equals("Septiembre")){
					
					apro[8]=res.getString(2);
					repro[8]=res.getString(3);
					
					
				}else if(res.getString(1).equals("Octubre")){
					
					apro[9]=res.getString(2);
					repro[9]=res.getString(3);
					
					
				}else if(res.getString(1).equals("Noviembre")){
					
					apro[10]=res.getString(2);
					repro[10]=res.getString(3);
					
					
				}else if(res.getString(1).equals("Diciembre")){
					
					apro[11]=res.getString(2);
					repro[11]=res.getString(3);
					
				}

		    }
			
			
			
			for (int i = 0; i < apro.length; i++) {
				if(apro[i]==null){apro[i]="0";}
				if(repro[i]==null){repro[i]="0";}
				
				if(i==0){
					json=apro[i];
					json2=repro[i];
				}else{
					json=json+", "+apro[i];	
					json2=json2+", "+repro[i];	
				}
			}
			
			
			
		} catch (SQLException e) {
	    	e.printStackTrace();
	    } finally {
	    	  SQLUtils.closeQuietly(res);
	    	  SQLUtils.closeQuietly(pstm);
	    	  SQLUtils.closeQuietly(con);
	   	}
		
		return resp=ok("[{\"name\" : \"Aprobadas\", \"data\" : ["+json+"]}, {\"name\" : \"No Aprobadas\", \"data\" : ["+json2+"]}]"); 
		
	}


}