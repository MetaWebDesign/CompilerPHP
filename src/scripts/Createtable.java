package scripts;

import clases.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Createtable{
	
	public Createtable(ProyectoM Proyecto){
		String sql = ""; 
		String fk = "";
				
		System.out.println("----------------------------");
		System.out.println("Create Table");
		System.out.println("----------------------------\n");
		
		for(int i=0; i< Proyecto.Clase.size(); i++){
			sql += "\nCREATE TABLE "+((ClaseM) Proyecto.getClaseM(i)).getNombreEnBD() + " (";
						
			//Atributos
			for(int j=0; j< ((ClaseM) Proyecto.getClaseM(i)).Atributos.size(); j++ ){
				if( !((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(j)).getTipo().equals("D") ){
			 		sql += ((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(j)).getNombre();
			 		((ClaseM) Proyecto.getClaseM(i)).Insert.add(((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(j)).getNombre());
			 		
			 		if(((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(j)).getTipoDato().equals("Autonumerico")  && j>0)
			 			sql += " INT";
			 		
			 		if(((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(j)).getTipoDato().equals("Int"))
			 			sql += " INT";
			 		
			 		if(((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(j)).getTipoDato().equals("Nat"))
			 			sql += " INT";
			 		
			 		if(((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(j)).getTipoDato().equals("Real"))
			 			sql += " FLOAT";
			 		
			 		if(((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(j)).getTipoDato().equals("Bool"))
			 			sql += " BOOLEAN";
			 		
			 		if(((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(j)).getTipoDato().equals("String")){
			 			sql += " VARCHAR";
			 			sql += "("+((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(j)).getTamano()+")";
			 		}
			 		
			 		if(((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(j)).getTipoDato().equals("Text"))
			 			sql += " TEXT";
			 		
			 		if(((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(j)).getTipoDato().equals("Date"))
			 			sql += " DATE";
			 		
			 		if(!((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(j)).getAdmiteNulos() && j>0)
			 			sql += " NOT NULL";
			 		
			 		if(j==0)
			 			sql += " SERIAL";
			 		
			 		sql += ", ";
			 		
			 		
			 		if(j == 0){ //ADD FOREIGN KEYS
			 						 			
			 			//ClaseActora
			 			for(int p = 0; p < Proyecto.ClaseActora.size(); p++){
			 				if( ((ClaseM) Proyecto.getClaseM(i)).getId().equals(Proyecto.ClaseActora.get(p)) ){
			 					sql += "PassWord VARCHAR(200) NOT NULL, estadoobj CHAR(15) NOT NULL, ";
			 					((ClaseM) Proyecto.getClaseM(i)).Insert.add("PassWord");
			 					((ClaseM) Proyecto.getClaseM(i)).Insert.add("estadoobj");
			 					
			 					//IS AGENT
			 					((ClaseM) Proyecto.getClaseM(i)).setIsAgent("1");
			 				}
			 			}
			 			
			 			for(int f = 0; f < ((ClaseM) Proyecto.getClaseM(i)).ForeignKeys.size(); f++){
			 				sql += ((ClaseM) Proyecto.getClaseM(i)).ForeignKeys.get(f) + ", ";
			 				
			 				fk = ((String) ((ClaseM) Proyecto.getClaseM(i)).ForeignKeys.get(f)).replace(" INT NOT NULL", "");
			 				
			 				fk = fk.replace("INT NULL", "");
			 				
			 				((ClaseM) Proyecto.getClaseM(i)).Insert.add( fk );
			 				
			 			}
			 		}
				
				} 			
			} sql = sql.substring(0, sql.length()-2); sql += ");\n";
		}	
		
		//System.out.println(sql);
		
		
		
		try {
			  File file = new File(Proyecto.getDirectorio()+"\\scripts\\CreateTable.sql");
	          BufferedWriter output = new BufferedWriter(new FileWriter(file));
	          output.write(sql);
	          output.close();
	        } catch ( IOException e ) {
	           e.printStackTrace();
	        }
		
	}
	
	
}