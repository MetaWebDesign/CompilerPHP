package scripts;

import clases.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DropIndexes{
	
	public DropIndexes(ProyectoM Proyecto){
		String sql = "";		
								
		System.out.println("----------------------------");
		System.out.println("Drop Indexes");
		System.out.println("----------------------------\n");
		
		for(int i=0; i< Proyecto.Clase.size(); i++){
			
			for(int p = 0; p < ((ClaseM) Proyecto.getClaseM(i)).Indexes.size(); p++ ){
				sql += "DROP INDEX " + ((ClaseM) Proyecto.getClaseM(i)).getNombreEnBD() + "." 
						+ ((ClaseM) Proyecto.getClaseM(i)).Indexes.get(p) + ";\n\n";
				
			}
		}	
		
		//System.out.println(sql);
		

		try {
			  File file = new File(Proyecto.getDirectorio()+"\\scripts\\DropIndexes.sql");
	          BufferedWriter output = new BufferedWriter(new FileWriter(file));
	          output.write(sql);
	          output.close();
	        } catch ( IOException e ) {
	           e.printStackTrace();
	        }
		
	}
	
	
	
}