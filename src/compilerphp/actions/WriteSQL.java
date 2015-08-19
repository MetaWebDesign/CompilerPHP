package compilerphp.actions;

import java.io.FileWriter;
import java.io.IOException;

public class WriteSQL {

	public static void genSQL() {

		String[] lineas = { "Uno", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete", "..." };

		/** FORMA 1 DE ESCRITURA **/
		FileWriter fichero = null;
		try {

			fichero = new FileWriter("/home/leo/DataBase.sql");

			// Escribimos linea a linea en el fichero
			for (String linea : lineas) {
				fichero.write(linea + "\n");
			}

			fichero.close();

		} catch (Exception ex) {
			System.out.println("Mensaje de la excepción: " + ex.getMessage());
		}
	}
	
	public static void put(String sentence){
		FileWriter fichero = null;
		try {
			fichero = new FileWriter("/home/leo/DataBase.sql");
			fichero.write(sentence + "\n");
			fichero.close();
		}catch (Exception ex) {
			System.out.println("Mensaje de la excepción: " + ex.getMessage());
		}
	}
	
	
	public static void main() throws IOException {
		genSQL();
	}
}