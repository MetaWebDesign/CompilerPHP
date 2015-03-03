package scripts;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import clases.*;

public class ForeignKeys {

	
	public ForeignKeys(ProyectoM Proyecto){
	
		String sql = "";
		String constraint = "";
		String foreignkey = "";
		String table = "";
		boolean fk = false;
		List ArrayConstraint = new ArrayList<String>();
		List Arrayforeignkey = new ArrayList<String>();
		int i = 1;
		
				
		System.out.println("----------------------------");
		System.out.println("Foreign Keys");
		System.out.println("----------------------------\n");
		
		
		for(int a=0; a< Proyecto.Agregacion.size(); a++){
			
			sql += "ALTER TABLE ";
			constraint = "";
			foreignkey = "";
			table = "";
			fk = false;
			i = 1;
			
			if( ((AgregacionM) Proyecto.getAgregacionM(a)).getEsDinamica().equals("1") ){ //DINAMICA
				
				if( ((AgregacionM) Proyecto.getAgregacionM(a)).getMaxCompuesta().equals("M") ){		//x:M x:x		
					table = ((AgregacionM) Proyecto.getAgregacionM(a)).getRCompuesta();
					sql += ((AgregacionM) Proyecto.getAgregacionM(a)).getRCompuesta() + " ";
					
					constraint = "fkc_" + table + i;
					while (!check_constraint(constraint, ArrayConstraint)){
						constraint = "fkc_" + table + i;
						i++;
					}
					
					
					ArrayConstraint.add(constraint);
					
					i = 1;
					
					foreignkey = "fk_" + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + "_" + i;
										
					Arrayforeignkey.add(foreignkey);
					
					Proyecto.addRelation(table + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente(), table + "." + foreignkey + "=" + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + "." + gettableid(((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente(),Proyecto), table);
					//System.out.println("TABLA " + table + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + "    " + table + "." + foreignkey + "=" + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + "." + gettableid(((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente(),Proyecto) );
					
					sql += "ADD CONSTRAINT " + constraint + " FOREIGN KEY (" + foreignkey + ") REFERENCES " + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + ";\n\n";
					
					foreignkey += " INT NOT NULL";
					
				}					
				else if ( ((AgregacionM) Proyecto.getAgregacionM(a)).getMaxComponente().equals("M") ){ // x:x x:M
					table = ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente();
					sql += ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + " ";
				
					constraint = "fkc_" + table + i;
					while (!check_constraint(constraint, ArrayConstraint)){
						constraint = "fkc_" + table + i;
						i++;
					}
					
					ArrayConstraint.add(constraint);
					
					i = 1;
					
					foreignkey = "fk_" + ((AgregacionM) Proyecto.getAgregacionM(a)).getRCompuesta() + "_" + i;
										
					Arrayforeignkey.add(foreignkey);
					
					Proyecto.addRelation(table + ((AgregacionM) Proyecto.getAgregacionM(a)).getRCompuesta(), table + "." + gettableid(table,Proyecto) + "=" + ((AgregacionM) Proyecto.getAgregacionM(a)).getRCompuesta() + "." + foreignkey, ((AgregacionM) Proyecto.getAgregacionM(a)).getRCompuesta());
					//System.out.println("TABLA " + table + ((AgregacionM) Proyecto.getAgregacionM(a)).getRCompuesta() + "    " + table + "." + gettableid(table,Proyecto) + "=" + ((AgregacionM) Proyecto.getAgregacionM(a)).getRCompuesta() + "." + foreignkey );
					
					sql += "ADD CONSTRAINT " + constraint + " FOREIGN KEY (" + foreignkey + ") REFERENCES " + ((AgregacionM) Proyecto.getAgregacionM(a)).getRCompuesta() + ";\n\n";
					
					foreignkey += " INT NOT NULL";
					
				}
				else{ //x:1 x:1
					table = ((AgregacionM) Proyecto.getAgregacionM(a)).getRCompuesta();
					sql += ((AgregacionM) Proyecto.getAgregacionM(a)).getRCompuesta() + " ";
					
					constraint = "fkc_" + table + i;
					while (!check_constraint(constraint, ArrayConstraint)){
						constraint = "fkc_" + table + i;
						i++;
					}
					
					ArrayConstraint.add(constraint);
					
					i = 1;
					
					foreignkey = "fk_" + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + "_" + i;
					
					Proyecto.addRelation(table + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente(), table + "." + foreignkey + "=" + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + "." + gettableid(((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente(),Proyecto), table);
					//System.out.println("TABLA " + table + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + "    " + table + "." + foreignkey + "=" + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + "." + gettableid(((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente(),Proyecto) );
					
					sql += "ADD CONSTRAINT " + constraint + " FOREIGN KEY (" + foreignkey + ") REFERENCES " + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + ";\n\n";
					
					foreignkey += " INT NULL";
				
				}
			}
			else{ //ESTATICA
				if( ((AgregacionM) Proyecto.getAgregacionM(a)).getMaxCompuesta().equals("M") ){		//x:M x:x
					table = ((AgregacionM) Proyecto.getAgregacionM(a)).getRCompuesta();
					sql += ((AgregacionM) Proyecto.getAgregacionM(a)).getRCompuesta() + " ";
									
					constraint = "fkc_" + table + i;
					while (!check_constraint(constraint, ArrayConstraint)){
						constraint = "fkc_" + table + i;
						i++;
					}
					
					ArrayConstraint.add(constraint);
					
					i = 1;
					
					foreignkey = "fk_" + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + "_" + i;				
					
					Proyecto.addRelation(table + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente(), table + "." + foreignkey + "=" + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + "." + gettableid(((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente(),Proyecto), table);
					//System.out.println("TABLA " + table + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + "    " + table + "." + foreignkey + "=" + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + "." + gettableid(((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente(),Proyecto) );
					
					sql += "ADD CONSTRAINT " + constraint + " FOREIGN KEY (" + foreignkey + ") REFERENCES " + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + ";\n\n";
					
					foreignkey += " INT NOT NULL";
				}
				else if ( ((AgregacionM) Proyecto.getAgregacionM(a)).getMaxComponente().equals("M") ){ // x:x x:M
					table = ((AgregacionM) Proyecto.getAgregacionM(a)).getRCompuesta();
					sql += ((AgregacionM) Proyecto.getAgregacionM(a)).getRCompuesta() + " ";
									
					constraint = "fkc_" + table + i;
					while (!check_constraint(constraint, ArrayConstraint)){
						constraint = "fkc_" + table + i;
						i++;
					}
					
					ArrayConstraint.add(constraint);
					
					i = 1;
					
					foreignkey = "fk_" + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + "_" + i;				
					
					Proyecto.addRelation(table + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente(), table + "." + gettableid(table,Proyecto) + "=" + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + "." + foreignkey, ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente());
					//System.out.println("TABLA " + table + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + "    " + table + "." + gettableid(table,Proyecto) + "=" + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + "." + foreignkey );
					
					sql += "ADD CONSTRAINT " + constraint + " FOREIGN KEY (" + foreignkey + ") REFERENCES " + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + ";\n\n";
					
					foreignkey += " INT NOT NULL";
					
				}
				
				else{ //x:1 x:1
					table = ((AgregacionM) Proyecto.getAgregacionM(a)).getRCompuesta();
					sql += ((AgregacionM) Proyecto.getAgregacionM(a)).getRCompuesta() + " ";
					
					constraint = "fkc_" + table + i;
					while (!check_constraint(constraint, ArrayConstraint)){
						constraint = "fkc_" + table + i;
						i++;
					}
					
					ArrayConstraint.add(constraint);
					
					i = 1;
					
					foreignkey = "fk_" + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + "_" + i;
					
					Proyecto.addRelation(table + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente(), table + "." + foreignkey + "=" + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + "." + gettableid(((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente(),Proyecto), table );
					//System.out.println("TABLAS " + table + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + "    " + table + "." + foreignkey + "=" + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + "." + gettableid(((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente(),Proyecto) );
					
					sql += "ADD CONSTRAINT " + constraint + " FOREIGN KEY (" + foreignkey + ") REFERENCES " + ((AgregacionM) Proyecto.getAgregacionM(a)).getRComponente() + ";\n\n";
					
					foreignkey += " INT NULL";
				
				}
				
			}
			
			for(int f = 0; f < Proyecto.Clase.size() ; f++){
				if( ((ClaseM) Proyecto.getClaseM(f)).getNombreEnBD().equals(table) ){
					((ClaseM) Proyecto.getClaseM(f)).ForeignKeys.add(foreignkey);
					((ClaseM) Proyecto.getClaseM(f)).ArrayConstraint.add(constraint);
					fk = true;
				}
			}
			
		}
		
				
		//System.out.println(sql);
		
		try {
			  File file = new File(Proyecto.getDirectorio()+"\\scripts\\ForeignKeys.sql");
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
	
	public String gettableid(String table, ProyectoM Proyecto){
		String nombre = "";
		boolean find = false;
		for(int a1=0; a1 < Proyecto.Clase.size(); a1++){
			if( ((ClaseM) Proyecto.getClaseM(a1)).getNombre().equals(table) ){
				nombre = ((AtributoM) ((ClaseM) Proyecto.getClaseM(a1)).getAtributoM(0)).getNombre();
				find = true;
				break;
			}
				
		}
		
		
		return nombre;
	}
	
}
