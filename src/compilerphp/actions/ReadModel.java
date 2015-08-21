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
		//WriteSQL.escribir();
	}
	

	
	public static void loadXML() throws IOException
	{
			SQL sql= new SQL();
			Tabla t=  new Tabla();
			
			String substr;
			FileReader fr = new FileReader("/home/leo/default.metawebdesign");
			BufferedReader br = new BufferedReader(fr);
			String line;
			int cont_tabla=0;
			
			//LECTURA
			while((line = br.readLine()) != null) {	
				int x_class=line.indexOf("class name="); //IDENTIFICA UNA CLASE
				int x_attribute=line.indexOf("<hasAttributes");//IDENTIFICA UN ATRIBUTO
				int x_attribute_name=line.indexOf("name=");//POSICION DEL NOMBRE DEL ATRIBUTO
				int x_relation=line.indexOf("hasRelationClass name=");//IDENTIFICA UNA RELACION
				int x_relation_to_class=line.indexOf("fromClass=");//IDENTIFICA LA OTRA CLASE EN LA RELACION
				
				//BUSQUEDA TABLA
				if(x_class != -1){
					//AGREGO LA TABLA IDENTIFICADA ANTERIORMENTE
					if(cont_tabla !=0){
						sql.addTabla(t);//AGREGO LA TABLA IDENTIFICADA EN LOS CICLOS ANTERIORES A LAS SENTENCIAS SQL
					}
					
					//NUEVA TABLA
					t = new Tabla();
			        substr = line.substring(x_class+12, line.length()-2);
			        t.setNombre(substr);//AGREGO EL NOMBRE A LA TABLA
			        cont_tabla++;
			    }
				
				//BUSQUEDA ATRIBUTO
				if(x_attribute != -1){
			        substr = line.substring(x_attribute_name+6, line.length()-3);
			        Atributo a = new Atributo(substr, false, false, "text");
			        t.addAtributo(a);//AGREGO ATRIBUTOS A LA TABLA 
				}
				//BUSQUEDA RELACION
				if(x_relation != -1){
					//NOMBRE EN LA RELACION
			        substr = line.substring(x_relation+23, x_relation_to_class-2);
			        
			        //OTRA TABLA INVOLUCRADA
			        substr = line.substring(x_relation_to_class+14, line.length()-3);
				}
				
				
			}
			fr.close();
			
			WriteSQL.write(sql);
			
	}
	
	public static void loadTest(){
		SQL sql= new SQL();
		Tabla t;
		Atributo a = new Atributo();
		
		t = new Tabla();
		t.setNombre("NOMBRE1_A");
		a = new Atributo("A1", false, false, "text");
        t.addAtributo(a);//AGREGO ATRIBUTOS A LA TABLA 
        a = new Atributo("A2", false, false, "text");
        t.addAtributo(a);//AGREGO ATRIBUTOS A LA TABLA
        
        sql.addTabla(t);
        
        t = new Tabla();
		t.setNombre("NOMBRE1_B");
		a = new Atributo("B1", false, false, "text");
        t.addAtributo(a);//AGREGO ATRIBUTOS A LA TABLA 
        a = new Atributo("B2", false, false, "text");
        t.addAtributo(a);//AGREGO ATRIBUTOS A LA TABLA 
        
        sql.addTabla(t);
        
        t = new Tabla();
		t.setNombre("NOMBRE1_C");
		a = new Atributo("C1", false, false, "text");
        t.addAtributo(a);//AGREGO ATRIBUTOS A LA TABLA 
        a = new Atributo("C2", false, false, "text");
        t.addAtributo(a);//AGREGO ATRIBUTOS A LA TABLA
        
        sql.addTabla(t);
        
        WriteSQL.write(sql);
	}
	
	public static void main(String[] args) throws IOException {
	    //loadTest();
		loadXML();
	}
}