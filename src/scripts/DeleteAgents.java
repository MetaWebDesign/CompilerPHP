package scripts;

import clases.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class DeleteAgents {
	
	public DeleteAgents(ProyectoM Proyecto){
		
		String sql = "";
		String table = "";
				
		System.out.println("----------------------------");
		System.out.println("Delete Agents");
		System.out.println("----------------------------\n");
		
		for(int i = 0; i < Proyecto.Clase.size(); i++){
			for(int j = 0; j < Proyecto.ClaseActora.size(); j++){
				table = ((ClaseM) Proyecto.getClaseM(i)).getNombre();
				if( ((ClaseM) Proyecto.getClaseM(i)).getId().equals( Proyecto.ClaseActora.get(j) ) ){
					sql += "DELETE " + table + " WHERE (" +
							((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(0)).getNombre() +
							" = 1 );\n";			
				}				
			}
		}
		
		//System.out.println(sql);
		
		try {
			  File file = new File(Proyecto.getDirectorio()+"\\scripts\\DeleteAgents.sql");
	          BufferedWriter output = new BufferedWriter(new FileWriter(file));
	          output.write(sql);
	          output.close();
	        } catch ( IOException e ) {
	           e.printStackTrace();
	        }
		
		
	}

}
