package scripts;

import clases.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class Droptable {

	public Droptable(ProyectoM Proyecto){
		String sql="";
		
		System.out.println("----------------------------");
		System.out.println("Drop Table");
		System.out.println("----------------------------\n");
		
		for(int i=0; i< Proyecto.Clase.size(); i++)
			sql += "DROP TABLE "+((ClaseM) Proyecto.getClaseM(i)).getNombreEnBD() + ";\n\n";						
		
		//System.out.println(sql);
		
		try {
			  File file = new File(Proyecto.getDirectorio()+"\\scripts\\DropTable.sql");
	          BufferedWriter output = new BufferedWriter(new FileWriter(file));
	          output.write(sql);
	          output.close();
	        } catch ( IOException e ) {
	           e.printStackTrace();
	        }
	
	}
}
