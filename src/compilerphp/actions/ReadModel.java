package compilerphp.actions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//import compilerphp.actions.WriteSQL;

public class ReadModel{
	
	public static void test(){
		//WriteSQL.put("SQL");
		WriteSQL.escribir();
	}
	 
	
	public static void loadXML() throws IOException
	{
			String substr;
			FileReader fr = new FileReader("/home/leo/default.metawebdesign");
			BufferedReader br = new BufferedReader(fr);
			String line;
			//LECTURA
			while((line = br.readLine()) != null) {
				//Busqueda de una Clase
				int x_class=line.indexOf("class name="); //POSICION DE UNA CLASE
				int x_attribute=line.indexOf("<hasAttributes");//POSICION DE UN ATRIBUTO
				int x_attribute_name=line.indexOf("name=");//POSICION DEL NOMBRE DEL ATRIBUTO
				if(x_class != -1){
			        substr = line.substring(x_class+12, line.length()-2);
			        System.out.println(substr);
			    }
				if(x_attribute != -1){
			        substr = line.substring(x_attribute_name+6, line.length()-3);
			        System.out.println("->"+substr);					
				}
			}
			fr.close();
			
	}
	
	public static void main(String[] args) throws IOException {
	    //cargarXml();
		loadXML();
	}
}