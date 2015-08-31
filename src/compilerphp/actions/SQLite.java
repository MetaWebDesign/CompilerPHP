package compilerphp.actions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class SQLite{
	
	private static String fileSQL="sql.dat";
	
	//ESCRIBRE EN TEXTO PLANO LAS SENTENCIAS SQL
	public static void write(SQL sql, String path){
		System.out.println("Write XML, path: "+path);
		List <View> views = sql.getViews();
		List <Tabla> tablas = sql.getTablas();
		List <Atributo> atributos;
		List <ForeignKey> foreignKeys;
		//ESCRITURA DE LA NUEVA SENTENCIA
		FileWriter fichero = null;
		try {
			fichero = new FileWriter(path+"/"+fileSQL);
			
			//ESCRIVO LAS TABLAS
			for(Tabla tabla : tablas) {
				// ESCRIVO EL NOMBRE DE LA TABLA
			    fichero.write("\n CREATE TABLE "+tabla.getNombre()+" (");
			    
			    //CAPTURO LOS ATRIBUTOS DE LA TABLA
			    atributos=tabla.getAtributos();
			    foreignKeys=tabla.getForeignKeys();
			    
			    //IMPRIMO LOS ATRIBUTOS
			    int coma=0;
			    for(Atributo atributo : atributos){
			    	//ESCRIVO LOS ATRIBUTOS
			    	if(coma !=0){
			    		fichero.write(", ");
			    	}
			    	
			    	fichero.write(atributo.getNombre());
			    	
			    	if (atributo.getType().equals("autoincremental")){
			    		fichero.write(" integer primary key not null");
			    	}
			    	if (!atributo.getType().equals("autoincremental")){
			    		fichero.write(" "+atributo.getType());
			    		if(atributo.getPrimaryKey() == true){
			    			fichero.write(" PRIMARY KEY");
			    		}
			    	}
			    	
			    	coma++;
			    }
			    
		    	//IMPRIMO LLAVES FORANEAS
		    	for(ForeignKey fk : foreignKeys){
		    		String tablaDestino=tablas.get(fk.getDestination()).getNombre();
		    		String atributoDestino=tablas.get(fk.getDestination()).getAtributos().get(fk.getAtributoDestination()).getNombre();
		    		String typeAtributo=tablas.get(fk.getDestination()).getAtributos().get(fk.getAtributoDestination()).getType();
		    		
		    		fichero.write(", "+fk.getNombre()+" ");
		    		if(typeAtributo.equals("autoincremental")){
		    			fichero.write("integer ,");
		    		}
		    		fichero.write(" FOREIGN KEY("+fk.getNombre()+") REFERENCES "+tablaDestino+"("+atributoDestino+")");
		    		/*
		    		System.out.println("Tabla :"+tablas.get(fk.getDestination()).getNombre());
		    		System.out.println("Atributo :"+tablas.get(fk.getDestination()).getAtributos().get(fk.getAtributoDestination()).getNombre());
		    		System.out.println("Typo :"+tablas.get(fk.getDestination()).getAtributos().get(fk.getAtributoDestination()).getType());
		    		*/
		    	}
			    
			    fichero.write(");");
	        }
			
			//ESCRIVO LAS VISTAS
			for(View view : views){
				fichero.write("\n CREATE VIEW "+view.getTabla()+view.getNombre()+"view as ");
				fichero.write("select * from "+view.getTabla());
				fichero.write(", ("+view.getFormula()+") as derived");
			}
			
			fichero.close();
		}catch (Exception ex){
			System.out.println("Mensaje de la excepción: " + ex.getMessage());
		}
	}
	
	//GENERO LA BASE DE DATOS EN BASE AL ARCHIVO SQL.DAT ESCRITO ANTEIORMENTE
	public static void createDB(SQL sql, String path, String file) throws IOException{
		//GENERO EL CODIGO SQL
		SQLite.write(sql, path);
		ExecuteShellComand obj = new ExecuteShellComand();
		//GENERO EN BASE AL COD SQL LA BASE DE DATOS SQLITE
		try {
			FileReader fr = new FileReader(path+"/"+fileSQL);
			BufferedReader br = new BufferedReader(fr);
			String sql_line;//LINEA DE LECTURA DEL ARCHIVO
			System.out.println("Generando la base de datos");
			//LECTURA
			while((sql_line = br.readLine()) != null) {
				//obj.executeCommand("sqlite3 "+path+"/"+file+".db "+"'"+line+"';");//UBICA EL ARCHIVO XML
				sqlite_execute(sql_line, path);
				System.out.println(sql_line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error al leer el archivo sql.dat");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//EXECUTA EL SQL CREADO
	public static void sqlite_execute(String sql, String path){
		   Connection c = null;
		    Statement stmt = null;
		    try {
		     // Class.forName("SQLite");
		      c = DriverManager.getConnection("jdbc:sqlite:"+path+"/test.db");
		      System.out.println("Opened database successfully");
		      stmt = c.createStatement();
		      stmt.executeUpdate(sql);
		      stmt.close();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		    System.out.println("Table created successfully");
  }
	
	public static void main(String[] args) throws IOException {
		sqlite_execute("Create table test2(nombre text);", "/home/leo/runtime-EclipseApplication/Elearning");
	}
	
}