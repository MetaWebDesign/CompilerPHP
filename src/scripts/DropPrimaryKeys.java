package scripts;

import clases.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DropPrimaryKeys{
	
	public DropPrimaryKeys(ProyectoM Proyecto){
		String sql = "";
		String pk = "";
				
		System.out.println("----------------------------");
		System.out.println("Drop Primary Keys");
		System.out.println("----------------------------\n");
		
		for(int i=0; i< Proyecto.Clase.size(); i++){
			sql += "ALTER TABLE " + ((ClaseM) Proyecto.getClaseM(i)).getNombreEnBD() + " DROP CONSTRAINT pk_" 
					+ ((ClaseM) Proyecto.getClaseM(i)).getNombreEnBD() + ";\n\n";
			
		}	
		
		//System.out.println(sql);
		

		try {
			  File file = new File(Proyecto.getDirectorio()+"\\scripts\\DropPrimaryKeys.sql");
	          BufferedWriter output = new BufferedWriter(new FileWriter(file));
	          output.write(sql);
	          output.close();
	        } catch ( IOException e ) {
	           e.printStackTrace();
	        }
		
	}
	
	
}