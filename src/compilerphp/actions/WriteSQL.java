package compilerphp.actions;

import java.io.FileWriter;
import java.util.List;

public class WriteSQL {
	
	private static String fileSQL="db.sql";
	

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
}