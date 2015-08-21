package compilerphp.actions;

import java.io.FileWriter;
import java.util.List;

public class WriteSQL {
	
	private static String path="/home/leo/DataBase.sql";


	public static void write(SQL sql){
		List <Tabla> tablas = sql.getTablas();
		List <Atributo> atributos;
		
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
			    	coma++;
			    }
			    fichero.write(");");
	        }
			
			fichero.close();
		}catch (Exception ex){
			System.out.println("Mensaje de la excepción: " + ex.getMessage());
		}
	}
}