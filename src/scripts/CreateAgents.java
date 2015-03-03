package scripts;

import clases.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class CreateAgents {
	
	public CreateAgents(ProyectoM Proyecto){
		
		String sql = "";
		String values = "";
		String tipoatributo = "";
		String table = "";
		
		//ARREGLAR
		String[] numeric = {"Autonumerico", "Int", "Nat", "Real"};
		String[] text = {"String", "Text"};
		String[] bool = {"Bool"};

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
		String date = sdf.format(new Date()); 
		
		
		System.out.println("----------------------------");
		System.out.println("Create Agents");
		System.out.println("----------------------------\n");
		
		for(int i = 0; i < Proyecto.Clase.size(); i++){
			for(int j = 0; j < Proyecto.ClaseActora.size(); j++){
				table = ((ClaseM) Proyecto.getClaseM(i)).getNombre();
				if( ((ClaseM) Proyecto.getClaseM(i)).getId().equals( Proyecto.ClaseActora.get(j) ) ){
					sql += "INSERT INTO " + table + "(";
					
					values = "VALUES (";
					
					for(int k=0; k< ((ClaseM) Proyecto.getClaseM(i)).Atributos.size(); k++ ){
						
						if(k>0){
							sql += ((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(k)).getNombre();						
						
						tipoatributo = ((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(k)).getTipoDato();
						
						for(int n = 0; n < numeric.length; n++){
							if(tipoatributo.equals(numeric[n]))
							values += "1";
						}
						
						for(int b = 0; b < bool.length; b++){
							if(tipoatributo.equals(bool[b]))
							values += "TRUE";
						}
						
						for(int s = 0; s < text.length; s++){
							if(tipoatributo.equals(text[s]))
							values += "'1'";
						}
						
						if(tipoatributo.equals("Date")){
							values += "'" + date + "'";
						}
						
						//END ATTRIBUTE?
				 		if(k+1 == ((ClaseM) Proyecto.getClaseM(i)).Atributos.size()){
				 			sql += ") ";
				 			values += ");\n";
				 		}
				 		else{
				 			sql += ", ";
				 			values += ", ";
				 		}
				 		
						}
				 		if(k == 0){				 							 			
				 			//ClaseActora
				 			for(int p = 0; p < Proyecto.ClaseActora.size(); p++){
				 				if( ((ClaseM) Proyecto.getClaseM(i)).getId().equals(Proyecto.ClaseActora.get(p)) ){
				 					sql += "PassWord, estadoobj, ";
				 					values += "'1', '" + ((ClaseM) Proyecto.getClaseM(i)).getNombre().substring(0, 6) + "0', ";
				 				}
				 			}				 			
				 		}	
				 					 		
					}
					
					sql += values;
					
				}				
			}
		}
		
		//System.out.println(sql);
		
		try {
			  File file = new File(Proyecto.getDirectorio()+"\\scripts\\CreateAgents.sql");
	          BufferedWriter output = new BufferedWriter(new FileWriter(file));
	          output.write(sql);
	          output.close();
	        } catch ( IOException e ) {
	           e.printStackTrace();
	        }
		
		
	}

}
