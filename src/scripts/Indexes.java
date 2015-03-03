package scripts;

import clases.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Indexes{
	
	public Indexes(ProyectoM Proyecto){
		String sql = "";
		String index = "";
		String foreignkey = "";
		List Indexes = new ArrayList<String>();
		int k = 1;
						
		System.out.println("----------------------------");
		System.out.println("Indexes");
		System.out.println("----------------------------\n");
		
		for(int i=0; i< Proyecto.Clase.size(); i++){
			
			for(int p = 0; p < ((ClaseM) Proyecto.getClaseM(i)).ForeignKeys.size(); p++ ){
				sql += "CREATE INDEX ";
				
				k = 1;
				index = "Idx_" + ((ClaseM) Proyecto.getClaseM(i)).getNombreEnBD() + k;
				while(!check_constraint(index, Indexes)){
					k++;
					index = "Idx_" + ((ClaseM) Proyecto.getClaseM(i)).getNombreEnBD() + k;
				}
				
				Indexes.add(index);
				
				foreignkey = (String) ((ClaseM) Proyecto.getClaseM(i)).ForeignKeys.get(p);
				
				foreignkey = foreignkey.replace(" INT NULL", "");
				foreignkey = foreignkey.replace(" INT NOT NULL", "");
				
				sql += index + " ON " + ((ClaseM) Proyecto.getClaseM(i)).getNombreEnBD() + "("
						+ foreignkey + ");\n\n";		
				
				((ClaseM) Proyecto.getClaseM(i)).Indexes.add(index);
			
			}
			
		}	
		
		//System.out.println(sql);
		

		try {
			  File file = new File(Proyecto.getDirectorio()+"\\scripts\\Indexes.sql");
	          BufferedWriter output = new BufferedWriter(new FileWriter(file));
	          output.write(sql);
	          output.close();
	        } catch ( IOException e ) {
	           e.printStackTrace();
	        }
		
	}
	
	public boolean check_constraint(String constraint, List ArrayConstraint){
		
		boolean flag = true;
		
		for(int i=0; i< ArrayConstraint.size(); i++)
			if(ArrayConstraint.get(i).equals(constraint))
				flag = false;
		
		return flag;
	}
	
	
}