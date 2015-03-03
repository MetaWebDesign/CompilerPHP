
package mapping;
import clases.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Mapping{
	private String serverName;
	private String dbName;
	private String userName;
	private String password;
	
	public ProyectoM Proyecto;
	
	
	public Mapping(ProyectoM Proyecto){
		String php = "<?php\n";
		String function = "";
		String sql = "";
		String params = "";
		String atributos = "";
		String formula = "";
		String tabla = "";
		String argobject = "";
		String id_class = "";
		//Listar(Proyecto);
		
		boolean objval = false;
		
		List Argumentos = new ArrayList<ArgumentoM>();
		List objetosval = new ArrayList<String>();
		
		System.out.println("----------------------------");
		System.out.println("Write");
		System.out.println("----------------------------\n");
		
		/*************
		 * postgresql
		 *************/
		php += "class postgresql {\n\tvar $DB;\n\tfunction connect() {\n" 
				+ "\t\t//FILL YOUR INFO IN DATABASE - USERNAME - PASSWORD\n"
				+ "\t\t$config = array(\"server\" => \"localhost\", \"database\" => \"DATABASE\", \"username\" => \"USERNAME\", "
				+ "\"password\" => \"PASSWORD\"); \n\t\t$connString = sprintf('host=%s dbname=%s user=%s password=%s', "
				+ "$config['server'], $config['database'], $config['username'], $config['password']);\n"
				+ "\t\tif ($this->DB = pg_pconnect($connString)) return TRUE;\n\t\treturn FALSE;\n\t}"
				+ "\n\tfunction login($username, $passwd, $table){\n\t\t$qs=\"SELECT usuario FROM $table WHERE usuario='$username' AND PassWord='$passwd'\";"
				+ "\n\t\t$result = pg_query($qs);\n\t\t$row = pg_fetch_array($result);\n\t\tif ($row['usuario'] != $username) {\n\t\t\treturn false;\n"
				+ "\t\t}\n\t\treturn true;}\n\n"
				+ "\n\tfunction close() {\n\t\tpg_close($this->DB);\n\t}\n\n}\n\n";
		
		for(int i=0; i< Proyecto.Clase.size(); i++){
			php += "class "+((ClaseM) Proyecto.getClaseM(i)).getNombre() + " extends postgresql {\n";
			
			System.out.println("\nclass "+((ClaseM) Proyecto.getClaseM(i)).getNombre()+".php\n");
			
			tabla = ((ClaseM) Proyecto.getClaseM(i)).getNombreEnBD();
			
			
			for(int j=0; j< ((ClaseM) Proyecto.getClaseM(i)).Atributos.size(); j++ ){
		 		if( !((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(j)).getTipo().equals("D") ){
		 			php += "\tvar $"+((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(j)).getNombre()+";\n";
		 			
		 			((ClaseM) Proyecto.getClaseM(i)).NombresAtributos.add( ((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(j)).getNombre() );
				}
			
			}	
	 		
			php +="\n\n";
			
			boolean check_agent = false;
			objval = false;
 			String argval = "";
 			String values = "";
 			String Alias = "";
 			Argumentos.clear();
 			objetosval.clear();
			
			//Function
			for(int s=0; s< ((ClaseM) Proyecto.getClaseM(i)).Servicios.size(); s++ ){
		 		//php += "\tfunction "+((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getNombre()+"(";
				Alias = ((ClaseM) Proyecto.getClaseM(i)).Alias.getAlias();
				function = "\tfunction "+((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getNombre()+"(";
		 		atributos = "";
		 		formula = "";
		 		sql = "\n\n\t\t$sql = \"INSERT INTO "+ ((ClaseM) Proyecto.getClaseM(i)).getNombreEnBD() + " (";
		 		
		 		objval=false;
		 		Argumentos.clear();
		 		
		 		//Arguments
		 		for(int t=0; t< ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).Argumento.size(); t++ ){
		 			
		 			//Search reference with the name of the object
		 			if( ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getArgumentoM(t)).getTipoArgumento().equals("O") ){		 				
		 				//php += getNamebyRef(((HerArgObjValM) ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getArgumentoM(t)).HerArgObjValM).getRefClaseM(), Proyecto) +" ";		 				
		 				argobject = ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getArgumentoM(t)).getNombre();
		 				//argval = "$" + ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getArgumentoM(t)).getNombre().replace("p_agr", "");
		 				
		 				argval = "$" + ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getArgumentoM(t)).getNombre();
		 				
		 				//estandarizar nombres p_agr p_evc
		 				
		 				argobject = argobject.replace("p_evc", "");
		 				
		 				argval = argval.replace("p_agr", "");
		 				argval = argval.replace("p_evc", "");
		 				
		 				objval = true;
		 				
		 				objetosval.add(argval);
		 			}
		 			else{
		 				
		 				if( !((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getArgumentoM(t)).getNombre().replace("p_atr", "").contains("id_") )
		 					function += "$"+((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getArgumentoM(t)).getNombre().replace("p_atr", "") + ", ";
		 				
		 			}
		 			
		 			
		 			
		 			Argumentos.add(((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getArgumentoM(t)));
		 			
		 			//ID IS SERIAL
		 			if(t>0){
		 				sql += getNamebyRef(((AliasM) ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getArgumentoM(t)).Alias).getRefInferencia(), Proyecto);
		 			}
		 			
		 			
		 			if((t+1) == ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).Argumento.size()){
		 				
		 				//add object in the end of function's arguments
		 				for(int o=0; o< objetosval.size(); o++){
		 					function += objetosval.get(o) + ", ";
		 				}
		 				objetosval.clear();
		 				
		 				
		 				if(function.substring(function.length()-2).equals(", ")){
	 					function = function.substring(0, function.length()-2);
	 					}
		 				
		 				//php += "){\n";
		 				function += "){\n";
		 				sql += ") VALUES (";
		 			}
		 			else{
		 				
		 				if(!objval){		 				
			 				//php +=", ";
			 				sql +=", ";
			 				objval = false;
		 				}
		 				
		 			}			 		
		 		}
		 		//php += atributos;
		 		function  += atributos;
		 		
		 		//CREATE
		 		if(((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getDTipoEvento().equals("C")){
		 			
		 			
		 			//TESTING NEW INSERT
			 		//php += "\n\n";
			 		//php += "\t\t$qs=\"INSERT INTO " + tabla + "(";
		 			
		 			function += "\n\n";
			 		function += "\t\t$qs=\"INSERT INTO " + tabla + "(";
		 			
			 		for(int a=0; a < ((ClaseM) Proyecto.getClaseM(i)).Insert.size(); a++){
			 			
			 			//ID IS SERIAL!
			 			if(a>0){
			 				function += ((ClaseM) Proyecto.getClaseM(i)).getInsert(a) + ", ";
			 			}
			 			
			 			boolean fkv = false;
			 			for(int a1=0; a1 < Argumentos.size(); a1++){
			 				
			 				String fkvalue = "";
			 				fkv = false;
			 				
			 				if( ((String) ((ClaseM) Proyecto.getClaseM(i)).getInsert(a)).contains("fk_") && ((String) ((ClaseM) Proyecto.getClaseM(i)).getInsert(a)).contains( ((String) ((ArgumentoM) Argumentos.get(a1)).getNombre().replace("p_agr", "")) )){
			 					fkvalue = ((String) ((ClaseM) Proyecto.getClaseM(i)).getInsert(a)).replace("fk_", "");
			 					
			 					
			 					
				 				fkvalue = fkvalue.substring(0, fkvalue.length()-2);
				 				
				 				//BUG local_ vehiculo_
				 				if(fkvalue.equals("local_"))
			 						fkvalue = "local";
				 				
				 				if(fkvalue.equals("vehiculo_"))
			 						fkvalue = "vehiculo";
				 				
				 				fkv = true;
				 				//System.out.println("AGREGAR FK: " + fkvalue + " - " + ((ArgumentoM) Argumentos.get(a1)).getNombre().replace("p_agr", "") + " CON: " + ((ClaseM) Proyecto.getClaseM(i)).getInsert(a));
				 				
			 				}
			 				
			 				
			 				
			 				if(((ClaseM) Proyecto.getClaseM(i)).getInsert(a).equals( ((ArgumentoM) Argumentos.get(a1)).getNombre().replace("p_atr", "") ) || fkvalue.equals(((ArgumentoM) Argumentos.get(a1)).getNombre().replace("p_agr", ""))){
			 					if(!fkv){
			 						if( ((ArgumentoM) Argumentos.get(a1)).HerArgDatoValM.getTipoDato().equals("Bool") )
			 							values += "\".$" + ((ClaseM) Proyecto.getClaseM(i)).getInsert(a) + ".\", ";
			 						else{
			 						
			 							if(a > 0){
			 								values += "'$" + ((ClaseM) Proyecto.getClaseM(i)).getInsert(a) + "', ";
			 							}			 							
			 						}
			 					}
			 					else{
			 						values += "'$" + fkvalue + "', ";
			 						//System.out.println("SE AGREGA FK: " + fkvalue);
			 					}
			 					
				 				if(!check_agent){	
				 					if(((ClaseM) Proyecto.getClaseM(i)).getIsAgent().equals("1") ){//Agents And Parameters PassWord & estadoobj
				 						values += "'$p_password', '" + ((ClaseM) Proyecto.getClaseM(i)).getNombre().substring(0, 6) + "0', ";
				 						check_agent = true;
				 					}
				 				}
			 				}
			 				
			 			}
			 		}
			 		
			 		values = values.substring(0, values.length()-2);
			 		
			 		function = function.substring(0, function.length()-2) + ") VALUES ( " + values + ")\";\n\n";
			 					 		
			 		function += "\t\t$result = pg_query($qs);\n\t\tif (!$result) {\n\t\t\t\n"
		 					+ "\t\t\t$errormessage = pg_errormessage($this->DB);\n\t\t\techo \"Error, hubo un error con los datos.\";\n\t\t\texit();\n\t\t}\n"
		 					+ "\t\techo \"" + Alias + " fue agregado exitosamente.\"; ";
		 		
		 			params = "";
		 			sql = "";
		 			
		 		}
		 		
		 		
		 		//DESTROY
		 		else if(((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getDTipoEvento().equals("D")){
		 			sql = "\t$qs = \"DELETE FROM " + tabla + " WHERE " + getNamebyRef(((ClaseM) Proyecto.getClaseM(i)).getRefAtributoFIM(), Proyecto) + " = '$" + argobject + "' \";\n";
		 			sql += "\t\t$result = pg_query($qs);\n\t\tif (!$result) {\n\t\t\t\n"
		 					+ "\t\t\t$errormessage = pg_errormessage($this->DB);\n\t\t\techo \"Error, hubo un error con los datos.\";\n\t\t\texit();\n\t\t}\n"
		 					+ "\t\techo \"" + Alias + " fue eliminado exitosamente.\"; ";
		 		}
		 		
		 		//Set password
		 		else if(((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getDTipoEvento().equals("P")){
		 			String passwd = "";
		 			
		 			//search attribute password 
		 			for(int a1=0; a1 < ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).Argumento.size(); a1++){
		 				
		 				if( ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getArgumentoM(a1)).getTipoImplicito().equals("P_PASSWORD") ){
		 					passwd = ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getArgumentoM(a1)).getNombre();
		 				}
		 			}
		 			
		 			
		 			sql = "\t$qs = \"UPDATE " + tabla + " SET password='$" + passwd + "' WHERE " + getNamebyRef(((ClaseM) Proyecto.getClaseM(i)).getRefAtributoFIM(), Proyecto) + " = '$" + argobject + "' \";\n";
		 			sql += "\t\t$result = pg_query($qs);\n\t\tif (!$result) {\n\t\t\t\n"
		 					+ "\t\t\t$errormessage = pg_errormessage($this->DB);\n\t\t\techo \"Error, hubo un error con los datos.\";\n\t\t\texit();\n\t\t}\n"
		 					+ "\t\techo \"" + Alias + " ha sido actualizado exitosamente.\"; ";
		 					 			
		 		}
		 		
		 		//Ins*
		 		else if(((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getNombre().contains("Ins")){
		 			
		 			String strwhere="";
		 			String M_Clase = argobject.replace("$", "");
		 			String from = "";
		 			String set = "";
		 			String and = "";
		 			
		 			boolean find=false;
		 			
		 			argval = "$p_this" + ((ClaseM) Proyecto.getClaseM(i)).getNombreEnBD();
		 			
		 			//IF THE RELATIONSHIP IS IN RELATIONDB
					 //GET THE ID EQUIAL IN FK OR ID 
					 for(int sqlw=0; sqlw< Proyecto.RelationDB.size(); sqlw++){
						 if( ((RelationDB) Proyecto.getRelation(sqlw)).getTables().equals(tabla+M_Clase) || ((RelationDB) Proyecto.getRelation(sqlw)).getTables().equals(M_Clase+tabla)){
						 		
							 
							 	strwhere += ((RelationDB) Proyecto.getRelation(sqlw)).getRelation();
						 		
							 	
							 	for(int t=0; t< Proyecto.Clase.size(); t++){
							 		if( ((ClaseM) Proyecto.getClaseM(t)).getNombreEnBD().equals( ((RelationDB) Proyecto.getRelation(sqlw)).getTableFK() ) ){
							 			for(int f=0; f< ((ClaseM) Proyecto.getClaseM(t)).ForeignKeys.size(); f++){
							 				if( ((String) ((ClaseM) Proyecto.getClaseM(t)).ForeignKeys.get(f)).contains(tabla) ){
							 					find = true;
							 					from = ((RelationDB) Proyecto.getRelation(sqlw)).getTableFK();
							 					set = ((String) ((ClaseM) Proyecto.getClaseM(t)).ForeignKeys.get(f));
								 					set = set.replace("INT NOT NULL", "");
							 						set = set.replace("INT NULL", "");
							 					
							 					set += "='" + argval + "'";
							 					and = getNamebyRef(((ClaseM) Proyecto.getClaseM(t)).getRefAtributoFIM(), Proyecto) + " = '$" + argobject ;
							 				}
							 			}
							 			
							 			if(!find){
							 				
							 				
							 				for(int f=0; f< ((ClaseM) Proyecto.getClaseM(i)).ForeignKeys.size(); f++){
							 					if( ((String) ((ClaseM) Proyecto.getClaseM(i)).ForeignKeys.get(f)).contains(argobject) ){
									 				from = tabla;
								 					set = ((String) ((ClaseM) Proyecto.getClaseM(i)).ForeignKeys.get(f));
								 						set = set.replace("INT NOT NULL", "");
								 						set = set.replace("INT NULL", "");
								 							
								 					set += "='$" + argobject + "'";
								 					and = getNamebyRef(((ClaseM) Proyecto.getClaseM(i)).getRefAtributoFIM(), Proyecto) + " = '" + argval ;
							 					}
							 				}
							 				
							 				
							 			}
							 		}
							 	}
							 	
							 	sql = "\t$qs = \"UPDATE " + from + " SET " + set + " WHERE " + and + "' \";\n";
						 }
					 }
		 			
		 			
					 if(set.equals("")){
						 sql = "\t//Didn't right in the model\n\n";
						 ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).setDont("1");
					 }
						 
					 else{
			 			sql += "\t\t$result = pg_query($qs);\n\t\tif (!$result) {\n\t\t\t\n"
			 					+ "\t\t\t$errormessage = pg_errormessage($this->DB);\n\t\t\techo \"Error, hubo un error con los datos.\";\n\t\t\texit();\n\t\t}\n"
			 					+ "\t\techo \"" + Alias + " ha sido actualizado exitosamente.\"; ";
					 }
		 			
		 		}
		 		
		 		//Edit*
		 		else if(((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getNombre().contains("edit")){
		 			
		 			String set = "";
		 			String where = getNamebyRef(((ClaseM) Proyecto.getClaseM(i)).getRefAtributoFIM(), Proyecto) + " = '" + argval + "'";
		 			
		 			for(int a=0; a < Argumentos.size(); a++){
		 				if( !((ArgumentoM) Argumentos.get(a)).getNombre().contains("p_this") ){
		 					set += ((ArgumentoM) Argumentos.get(a)).getNombre().replace("p_", "") + "='$" + ((ArgumentoM) Argumentos.get(a)).getNombre() + "', "; 
		 				} 
		 			}
		 			
		 			set = set.substring(0, set.length()-2);
		 			
		 			sql = "\t$qs = \"UPDATE " + tabla + " SET " + set + " WHERE " + where + "\";\n";
		 			sql += "\t\t$result = pg_query($qs);\n\t\tif (!$result) {\n\t\t\t\n"
		 					+ "\t\t\t$errormessage = pg_errormessage($this->DB);\n\t\t\techo \"Error, hubo un error con los datos.\";\n\t\t\texit();\n\t\t}\n"
		 					+ "\t\techo \"" + Alias + " ha sido actualizado exitosamente.\"; ";
		 			
		 		}
		 		
		 		//TRANSACCIONES
		 		else if(((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getTipo().equals("T")){
		 		
		 			for(int a=0; a < Argumentos.size(); a++){
		 				System.out.println( ((ArgumentoM) Argumentos.get(a)).getNombre() ); 
		 				 
		 			}
		 			
		 			System.out.println("Transaccion: " + ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).Formula.getFormulaString());
		 			
		 			sql = "\t//" + ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).Formula.getFormulaString();
		 			
		 			//CHANGE THIS -> $p_this+class
		 			sql = sql.replace("THIS", "p_this"+tabla );
		 			//CHANGE SYSTEM DATE
		 			sql = sql.replace("systemDate(  )", "date('Y-m-d')");
		 			//ADDING $ TO ARGUMENTS
		 			sql = sql.replace("(p", "($p");
		 			//ADDING $ TO , ARGUMENTS
		 			sql = sql.replace(", ", ", $");
		 			//ADDING $ TO .ARGUMENTS
		 			sql = sql.replace(".id", ".$id");
		 			
		 			sql +=";";
		 			
		 			String parser = ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).Formula.getFormulaString();
		 			
		 			
		 			String[] p = parser.split("\\.");
		 			
		 			for(int x = 0; x < p.length; x++){
		 				System.out.println(p[x]);
		 			}
		 			
		 			
		 		}
		 		
		 		//Evaluaciones - Eventos
		 		else if(((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getTipo().equals("E") && ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getDTipoEvento().equals("N")){
		 			
		 			String Formula = "";
		 			String set = "";
		 			String where = getNamebyRef(((ClaseM) Proyecto.getClaseM(i)).getRefAtributoFIM(), Proyecto) + " = '" + argval + "'";;
		 			
		 			for(int a1=0; a1 < Argumentos.size(); a1++){
		 				System.out.println( ((ArgumentoM) Argumentos.get(a1)).getNombre() );		 					
		 			}
		 			
	 				System.out.println("Servicio: " + ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getNombre());
	 				for(int e=0; e< ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).RefEvaluacion.size(); e++){
	 					
	 					System.out.println( ((String) ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getRefEvaluacion(e)) ); 
	 				
		 				for(int a=0; a < ((ClaseM) Proyecto.getClaseM(i)).Atributos.size(); a++ ){
		 					for(int a2=0; a2< ((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(a)).RefEvaluacion.size(); a2++ ){
		 						
		 						if( ((String) ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getRefEvaluacion(e)).equals( ((String) ((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(a)).getRefEvaluacion(a2)) ) ){
		 						 					 						
			 						for(int a3=0; a3< ((ClaseM) Proyecto.getClaseM(i)).Evaluaciones.size(); a3++){
				 						if( ((EvaluacionM) ((ClaseM) Proyecto.getClaseM(i)).getEvaluacionM(a3)).getId().equals( ((String) ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getRefEvaluacion(e)) ) ){
				 							
				 							Formula = ((EvaluacionM) ((ClaseM) Proyecto.getClaseM(i)).getEvaluacionM(a3)).Formula.getFormulaString();
				 											 							
				 							set = ((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(a)).getNombre() + "=" + Formula + ", ";
				 							
				 							System.out.println("\t AtributoM: " + ((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(a)).getNombre() + " - EvaluacionM: " + ((EvaluacionM) ((ClaseM) Proyecto.getClaseM(i)).getEvaluacionM(a3)).getId() + " - ModoficaAtributoM: " + set + " - Formula: " + Formula  );
				 						}
				 					}
		 						}
		 						
		 					}
		 						
		 				}
	 				
	 				}
	 				
	 				set = set.substring(0, set.length()-2);
	 				
	 				sql = "\t$qs = \"UPDATE " + tabla + " SET " + set + " WHERE " + where + "\";\n";
	 				sql += "\t\t$result = pg_query($qs);\n\t\tif (!$result) {\n\t\t\t\n"
		 					+ "\t\t\t$errormessage = pg_errormessage($this->DB);\n\t\t\techo \"Error, hubo un error con los datos.\";\n\t\t\texit();\n\t\t}\n"
		 					+ "\t\techo \"" + Alias + " ha sido actualizado exitosamente.\"; ";
		 			
		 		}
		 		
		 		else{
	 				sql = "";
	 			}
		 		
		 		//php += "\t" + sql + params;	
		 		function += "\t" + sql + params;
		 		
		 		//php +="\n\t}\n\n";		 	
		 		function +="\n\t}\n\n";
		 		
		 		sql = params = "";
		 		
		 		php += function;
		 		
	 		}
		 		
			php += "}\n\n";
			
		}
		
		php += "\n?>";
		
		
		try {
			  File file = new File(Proyecto.getDirectorio()+"\\lib\\class.php");
	          BufferedWriter output = new BufferedWriter(new FileWriter(file));
	          output.write(php);
	          output.close();
	        } catch ( IOException e ) {
	           e.printStackTrace();
	        }
		
		
	}
	
		
	public static String getNamebyRef(String ref, ProyectoM Proyecto){
		String Nombre = "";
		for(int i = 0; i < Proyecto.Clase.size(); i++){
			//Clases
			if(((ClaseM) Proyecto.getClaseM(i)).getId().equals(ref)){
				Nombre = ((ClaseM) Proyecto.getClaseM(i)).getNombre();
				break;
			}
			
			//Atributos
			for(int a=0; a< ((ClaseM) Proyecto.getClaseM(i)).Atributos.size(); a++ ){
				if(((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(a)).getId().equals(ref))
					Nombre = ((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(a)).getNombre();
			}
			
			for(int s=0; s< ((ClaseM) Proyecto.getClaseM(i)).Servicios.size(); s++ ){
				if(((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getId().equals(ref))
					Nombre = "//SERVICIO $this->" + ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getNombre();
			}
			
			if(!Nombre.isEmpty())
				break;			
		}
		
		if(Nombre.isEmpty())
			Nombre = ref;
		
		//BUG!!!!!
		if(ref.equals("Clas_1402616938496595UIInst_Auto"))
			Nombre = "ciudad";
		
		return Nombre;
	}

	/*******************
	 Listar
	 *******************/	
	public static void Listar(ProyectoM Proyecto){
		
		System.out.println("----------------------------");
		System.out.println("Listar:");
		//System.out.println("----------------------------\n");
		
		for(int i=0; i< Proyecto.Clase.size(); i++){
			 System.out.println("La Clase "+((ClaseM) Proyecto.getClaseM(i)).getNombre());
			 System.out.println("Alias " + ((ClaseM) Proyecto.getClaseM(i)).Alias.getAlias() + " RefInferencia " + ((ClaseM) Proyecto.getClaseM(i)).Alias.getRefInferencia());
			 System.out.println("Observacions " + ((ClaseM) Proyecto.getClaseM(i)).getObservacions() );
			 System.out.println("\tAtributo");
			 for(int j=0; j< ((ClaseM) Proyecto.getClaseM(i)).Atributos.size(); j++ ){
			 		System.out.println("\t\t"+j+" "+ ((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(j)).getNombre() );
			 		System.out.println("\t\t\t" + ((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(j)).Alias.getAlias() );
			 		System.out.println("\t\t\t" + ((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(j)).getObservacions() );
			 		
			 		for(int k=0; k< ((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(j)).RefRolEvaluaciones.size(); k++ )
			 			System.out.println("\t\t\t RefEvaluacion: " + ((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(j)).RefRolEvaluaciones.get(k) );
		 		
			 }
			 
			 System.out.println("\tEvaluacion "+((ClaseM) Proyecto.getClaseM(i)).Evaluaciones.size());
			 for(int l=0; l< ((ClaseM) Proyecto.getClaseM(i)).Evaluaciones.size(); l++){
		 			System.out.println("\t\t"+l+" "+ ((EvaluacionM) ((ClaseM) Proyecto.getClaseM(i)).getEvaluacionM(l)).getId());
		 			System.out.println("\t\t Formula: "+ ((FormulaM) ((EvaluacionM) ((ClaseM) Proyecto.getClaseM(i)).getEvaluacionM(l)).Formula).getFormulaString() );
		 		}
			 
			 System.out.println("\tServicio");
			 for(int s=0; s< ((ClaseM) Proyecto.getClaseM(i)).Servicios.size(); s++ ){
			 		System.out.println("\t\t"+s+" "+ ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getNombre() );
			 		
			 		System.out.println("\t\tArgumento");
			 		for(int t=0; t< ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).Argumento.size(); t++ ){
			 			System.out.println("\t\t\t"+t+" "+ ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getArgumentoM(t)).getId() + " " + ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getArgumentoM(t)).getNombre() );
			 			System.out.println("\t\t\t Alias "+t+" "+ ((AliasM) ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getArgumentoM(t)).Alias).getAlias() + " " + ((AliasM) ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getArgumentoM(t)).Alias).getRefInferencia() );
			 			System.out.println("\t\t\t Observacions "+t+" "+ ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(s)).getArgumentoM(t)).getObservacions() );
			 			
			 		}
			 }
			 
			 
			 
			 System.out.println("----------------------------\n");
		}
		
		System.out.println("----------------------------\n");
		System.out.println("Proyecto");
		System.out.println("----------------------------\n");
		
		
		
		for(int b=0; b< Proyecto.Vista.size(); b++){
			System.out.println("VistaM");
			System.out.println("\t" + ((VistaM) Proyecto.getVistaM(b)).getId());
			System.out.println("\t" + ((VistaM) Proyecto.getVistaM(b)).getNombre());			
			
			for(int b2=0; b2< ((VistaM) Proyecto.getVistaM(b)).NodoJerarquia.size(); b2++){
				System.out.println("\tNodoJerarquia");
				System.out.println("\t\t" + ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(b)).getNodoJerarquia(b2)).getId());
				System.out.println("\t\t" + ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(b)).getNodoJerarquia(b2)).getIdNodoJerarquia());
				System.out.println("\t\t Alias: " + ((AliasM) ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(b)).getNodoJerarquia(b2)).Alias).getAlias() );
				
				for(int b3=0; b3< ((NodoJerarquiaM) ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(b)).getNodoJerarquia(b2))).NodoJerarquia.size(); b3++){
					System.out.println("\t\tNodoJerarquia");
					System.out.println("\t\t\t" + ((NodoJerarquiaM) ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(b)).getNodoJerarquia(b2)).getNodoJerarquia(b3)).getId());
					System.out.println("\t\t\t" + ((NodoJerarquiaM) ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(b)).getNodoJerarquia(b2)).getNodoJerarquia(b3)).getIdNodoJerarquia());
					System.out.println("\t\t\t Alias: " + ((AliasM) ((NodoJerarquiaM) ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(b)).getNodoJerarquia(b2)).getNodoJerarquia(b3)).Alias).getAlias() );
					
					for(int b4=0; b4< ((NodoJerarquiaM) ((NodoJerarquiaM) ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(b)).getNodoJerarquia(b2))).getNodoJerarquia(b3)).NodoJerarquia.size(); b4++){
						System.out.println("\t\t\tNodoJerarquia");
						System.out.println("\t\t\t\t" + ((NodoJerarquiaM) ((NodoJerarquiaM) ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(b)).getNodoJerarquia(b2)).getNodoJerarquia(b3)).getNodoJerarquia(b4)).getId());
						System.out.println("\t\t\t\t" + ((NodoJerarquiaM) ((NodoJerarquiaM) ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(b)).getNodoJerarquia(b2)).getNodoJerarquia(b3)).getNodoJerarquia(b4)).getIdNodoJerarquia());
						System.out.println("\t\t\t\t Alias: " + ((AliasM) ((NodoJerarquiaM) ((NodoJerarquiaM) ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(b)).getNodoJerarquia(b2)).getNodoJerarquia(b3)).getNodoJerarquia(b4)).Alias).getAlias() );
					}
				}				
			}
		}

	}


	public String getServerName() {
		return this.serverName;
	}


	public void setServerName(String serverName) {
		this.serverName = serverName;
	}


	public String getDbName() {
		return this.dbName;
	}


	public void setDbName(String dbName) {
		this.dbName = dbName;
	}


	public String getUserName() {
		return this.userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return this.password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


}

