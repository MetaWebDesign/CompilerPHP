package compilerphp.actions;

import java.io.FileWriter;
import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import org.sqlite.*;


public class SQLite{
	
	//ESCRIBRE EN TEXTO PLANO LAS SENTENCIAS SQL
	public static List<String> genSQL(SQL sql, String path){
		List <View> views = sql.getViews();
		List <Tabla> tablas = sql.getTablas();
		List <Atributo> atributos;
		List <ForeignKey> foreignKeys;
		List <String> dataBase = new ArrayList<String>();
		//ESCRITURA DE LA NUEVA SENTENCIA
			
			//ESCRIVO LAS TABLAS
			for(Tabla tabla : tablas) {
				String linea_sql="";
				// ESCRIVO EL NOMBRE DE LA TABLA
			    linea_sql=linea_sql+"CREATE TABLE "+tabla.getNombre()+" (";
			    //CAPTURO LOS ATRIBUTOS DE LA TABLA
			    atributos=tabla.getAtributos();
			    foreignKeys=tabla.getForeignKeys();
			    
			    //IMPRIMO LOS ATRIBUTOS
			    int coma=0;
			    for(Atributo atributo : atributos){
			    	//ESCRIVO LOS ATRIBUTOS
			    	if(coma !=0){
			    		linea_sql=linea_sql+", ";
			    	}
			    	
			    	linea_sql=linea_sql+atributo.getNombre();
			    	
			    	if (atributo.getType().equals("autoincremental")){
			    		linea_sql=linea_sql+" integer primary key not null";
			    	}
			    	if (!atributo.getType().equals("autoincremental")){
			    		linea_sql=linea_sql+" "+atributo.getType();
			    		if(atributo.getPrimaryKey() == true){
			    			linea_sql=linea_sql+" PRIMARY KEY";
			    		}
			    	}
			    	coma++;
			    }
			    
		    	//IMPRIMO LLAVES FORANEAS
			    coma=0;
		    	for(ForeignKey fk : foreignKeys){
		    		//String tablaDestino=tablas.get(fk.getDestination()).getNombre();
		    		//String atributoDestino=tablas.get(fk.getDestination()).getAtributos().get(fk.getAtributoDestination()).getNombre();
		    		String typeAtributo=tablas.get(fk.getDestination()).getAtributos().get(fk.getAtributoDestination()).getType();
	    			linea_sql=linea_sql+", "+fk.getNombre()+" ";
		    		if(typeAtributo.equals("autoincremental")){
		    			linea_sql=linea_sql+"integer";
		    		}
		    		else{
		    			linea_sql=linea_sql+typeAtributo;
		    		}
		    		coma++;
		    	}
		    	coma=0;
		    	for(ForeignKey fk : foreignKeys){
		    		String tablaDestino=tablas.get(fk.getDestination()).getNombre();
		    		String atributoDestino=tablas.get(fk.getDestination()).getAtributos().get(fk.getAtributoDestination()).getNombre();
		    		//String typeAtributo=tablas.get(fk.getDestination()).getAtributos().get(fk.getAtributoDestination()).getType();
		    		linea_sql=linea_sql+", FOREIGN KEY("+fk.getNombre()+") REFERENCES "+tablaDestino+"("+atributoDestino+")";
		    		coma++;
		    	}
		    	
		    	linea_sql=linea_sql+");";
		    	System.out.println(linea_sql);
		    	dataBase.add(linea_sql);
	        }
			
			//ESCRIVO LAS VISTAS
			for(View view : views){
				String vista="CREATE VIEW "+view.getTabla()+view.getNombre()+"view as ";
				vista=vista+"select * from "+view.getTabla();
				vista=vista+", ("+view.getFormula()+") as derived";
				dataBase.add(vista);
			}
		return dataBase;
	}
	
	//GENERO LA BASE DE DATOS EN BASE AL ARCHIVO SQL.DAT ESCRITO ANTEIORMENTE
	public static void createDB(SQL model, String path, String file) throws IOException{
		//GENERO EL CODIGO SQL
		List <String> dataBase=SQLite.genSQL(model, path);
		ExecuteShellComand obj= new ExecuteShellComand();
		int stop=file.indexOf(".");
		String nombreScriptBD=file.substring(0, stop);
		System.out.println("nombre archivo: "+nombreScriptBD+" stop :"+stop);
		//CREO LA CARPETA DEL PROYECTO
		obj.executeCommand("mkdir "+path+"/PHP");
		//ESCRITURA DEL SCRIPT BASH PARA LA CREACION DE LA BDD
		FileWriter fichero = null;
		fichero = new FileWriter(path+"/PHP/"+nombreScriptBD+".sh");
		for(String sql_line : dataBase){
			fichero.write("sqlite3 "+path+"/PHP/"+nombreScriptBD+".db \""+sql_line+"\"\n");
			System.out.println("sqlite3-> "+sql_line);
		}
		fichero.close();
		
		obj.executeCommand("chmod +x "+path+"/PHP/"+nombreScriptBD+".sh");
		obj.executeCommand("bash "+path+"/PHP/"+nombreScriptBD+".sh");
	}

	/*
	public static void main(String[] args) throws IOException {
		sqlite_execute("Create table test2(nombre text);", "/home/leo/runtime-EclipseApplication/Elearning");
	}*/
	
}