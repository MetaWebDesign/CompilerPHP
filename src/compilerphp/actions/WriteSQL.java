package compilerphp.actions;

import java.io.FileWriter;
import java.util.List;

public class WriteSQL {
	
	private static String path="/home/leo/DataBase.sql";


	public static void write(SQL sql){
		List <Tabla> tablas = sql.getTablas();
		List <Atributo> atributos;
		List <View> views = sql.getViews();
		//ESCRITURA DE LA NUEVA SENTENCIA
		FileWriter fichero = null;
		try {
			
			
			fichero = new FileWriter(path);
			
			//ESCRIVO LAS TABLAS
			for(Tabla tabla : tablas) {
				// ESCRIVO EL NOMBRE DE LA TABLA
			    fichero.write("\n CREATE TABLE "+tabla.getNombre()+" (");
			    
			    //CAPTURO LOS ATRIBUTOS DE LA TABLA
			    atributos=tabla.getAtributos();
			    
			    //IMPRIMO LOS ATRIBUTOS
			    int coma=0;
			    for(Atributo atributo : atributos){
			    	//ESCRIVO LOS ATRIBUTOS
			    	if(coma !=0){
			    		fichero.write(", ");
			    	}
			    	
			    	fichero.write(atributo.getNombre());
			    	
			    	System.out.println("type: |"+atributo.getType()+"|");
			    	if (atributo.getType().equals("autoincremental")){
			    		System.out.println("si");
			    		fichero.write(" integer primary key not null");
			    	}
			    	if (!atributo.getType().equals("autoincremental")){
			    		System.out.println("no");
			    		fichero.write(" "+atributo.getType());
			    		if(atributo.getPrimaryKey() == true){
			    			fichero.write(" PRIMARY KEY");
			    		}
			    	}
			    	
			    	//IF RELATION (LLAVE FORANEA)
			    	//TABLA[RELATIONTABLA.GETNUM()]
			    			
			    	
			    	coma++;
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