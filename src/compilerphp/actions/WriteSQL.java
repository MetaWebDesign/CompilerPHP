package compilerphp.actions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteSQL {
	
	private static String path="/home/leo/DataBase.sql";

	public static void escribir(){
	File f;
	f = new File(path);

	//Escritura
	try{
	FileWriter w = new FileWriter(f);
	BufferedWriter bw = new BufferedWriter(w);
	PrintWriter wr = new PrintWriter(bw);	
	wr.write("Esta es una linea de codigo");//escribimos en el archivo 
	wr.append(" - y aqui continua"); //concatenamos en el archivo sin borrar lo existente
	              //ahora cerramos los flujos de canales de datos, al cerrarlos el archivo quedará guardado con información escrita
	              //de no hacerlo no se escribirá nada en el archivo
	wr.close();
	bw.close();
	}catch(IOException e){};
	}
	
	
	public static void put(String sentence){
		//ESCRITURA DE LA NUEVA SENTENCIA
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
		escribir();
	}
}