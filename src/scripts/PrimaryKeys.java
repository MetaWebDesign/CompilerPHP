package scripts;

import clases.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PrimaryKeys{
	
	public PrimaryKeys(ProyectoM Proyecto){
		String sql = "";
		String pk = "";
				
		System.out.println("----------------------------");
		System.out.println("Primary Keys");
		System.out.println("----------------------------\n");
		
		for(int i=0; i< Proyecto.Clase.size(); i++){
			sql += "ALTER TABLE " + ((ClaseM) Proyecto.getClaseM(i)).getNombreEnBD() + " ADD CONSTRAINT pk_" 
					+ ((ClaseM) Proyecto.getClaseM(i)).getNombreEnBD() + " " ;
						
			pk = "PRIMARY KEY (" + ((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(0)).getNombre() + ");\n\n";
			
			
			sql += pk;
			
		}	
		
		//System.out.println(sql);
		

		try {
			  File file = new File(Proyecto.getDirectorio()+"\\scripts\\PrimaryKeys.sql");
	          BufferedWriter output = new BufferedWriter(new FileWriter(file));
	          output.write(sql);
	          output.close();
	        } catch ( IOException e ) {
	           e.printStackTrace();
	        }
		
	}
	
	
}