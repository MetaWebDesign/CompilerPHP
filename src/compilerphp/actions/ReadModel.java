package compilerphp.actions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import compilerphp.actions.WriteSQL;

public class ReadModel{
	

	
	public static void test(){
		//WriteSQL.put("SQL");
		WriteSQL.escribir();
	}
	

	
	public static void loadXML() throws IOException
	{
			SQL sql= new SQL();
			
			String substr;
			FileReader fr = new FileReader("/home/leo/default.metawebdesign");
			BufferedReader br = new BufferedReader(fr);
			String line;
			//LECTURA
			while((line = br.readLine()) != null) {
				
				Tabla t = new Tabla();
				Atributo a = new Atributo();
				
				int x_class=line.indexOf("class name="); //IDENTIFICA UNA CLASE
				int x_attribute=line.indexOf("<hasAttributes");//IDENTIFICA UN ATRIBUTO
				int x_attribute_name=line.indexOf("name=");//POSICION DEL NOMBRE DEL ATRIBUTO
				int x_relation=line.indexOf("hasRelationClass name=");//IDENTIFICA UNA RELACION
				int x_relation_to_class=line.indexOf("fromClass=");//IDENTIFICA LA OTRA CLASE EN LA RELACION
				
				//BUSQUEDA TABLA
				if(x_class != -1){
			        substr = line.substring(x_class+12, line.length()-2);
			        t.setNombre(substr);//AGREGO EL NOMBRE A LA TABLA
			        System.out.println(substr);
			    }
				
				//BUSQUEDA ATRIBUTO
				if(x_attribute != -1){
			        substr = line.substring(x_attribute_name+6, line.length()-3);
			        a = new Atributo(substr, false, false, "text");
			        System.out.println("->"+substr);
				}
				
				t.addAtributo(a);//AGREGO ATRIBUTOS A LA TABLA 
				
				//BUSQUEDA RELACION
				if(x_relation != -1){
					//NOMBRE EN LA RELACION
			        substr = line.substring(x_relation+23, x_relation_to_class-2);
			        System.out.println("R->"+substr);
			        
			        //OTRA TABLA INVOLUCRADA
			        substr = line.substring(x_relation_to_class+14, line.length()-3);
			        System.out.println("RC->"+substr);
				}
				
				sql.addTabla(t);//AGREGO LA TABLA A LAS SENTENCIAS SQL
			}
			fr.close();
			
	}
	
	public static void main(String[] args) throws IOException {
	    //cargarXml();
		loadXML();
	}
}