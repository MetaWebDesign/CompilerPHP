package scripts;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import clases.*;

public class DropForeignKeys {

	
	public DropForeignKeys(ProyectoM Proyecto){
	
		String sql = "";
		String constraint = "";
		
		
		System.out.println("----------------------------");
		System.out.println("Drop Foreign Keys");
		System.out.println("----------------------------\n");
		
		
		for(int i = 0; i < Proyecto.Clase.size(); i++){
			for(int j = 0; j < ((ClaseM) Proyecto.getClaseM(i)).ArrayConstraint.size(); j++){
				constraint = "";
				constraint = (String) ((ClaseM) Proyecto.getClaseM(i)).ArrayConstraint.get(j);
								
				sql += "ALTER TABLE " + ((ClaseM) Proyecto.getClaseM(i)).getNombreEnBD() + 
						" DROP CONSTRAINT " + constraint + ";\n\n";
			}
			
		}
		
				
		//System.out.println(sql);
		
		try {
			  File file = new File(Proyecto.getDirectorio()+"\\scripts\\DropForeignKeys.sql");
	          BufferedWriter output = new BufferedWriter(new FileWriter(file));
	          output.write(sql);
	          output.close();
	        } catch ( IOException e ) {
	           e.printStackTrace();
	        }
	}
	
	public boolean check_constraint(String constraint, List ArrayConstraint){
		
		boolean flag = true;
		
		for(int i=1; i< ArrayConstraint.size(); i++)
			if(ArrayConstraint.get(i).equals(constraint))
				flag = false;
		
		return flag;
	}
	
}
