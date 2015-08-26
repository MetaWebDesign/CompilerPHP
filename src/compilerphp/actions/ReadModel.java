package compilerphp.actions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import compilerphp.actions.WriteSQL;

public class ReadModel{
	
	private static String path="/home/leo/elearning.metawebdesign";
	
	public static void loadXML() throws IOException
	{
			SQL sql= new SQL();
			Tabla t=  new Tabla();
			
			FileReader fr = new FileReader(path);//LECTURA DEL ARCHIVO DEL MODELO
			BufferedReader br = new BufferedReader(fr);
			String line;//LINEA DE LECTURA DEL ARCHIVO
			int cont_tabla=0;//CONTADOR DE TABLAS
			
			String tabla = null;//NOMBRE DE LA TABLA
			
			//LECTURA
			while((line = br.readLine()) != null) {
				
				//System.out.println(line);
				int x_class=line.indexOf("class name="); //IDENTIFICA UNA CLASE
				int x_attribute=line.indexOf("<hasAttributes");//IDENTIFICA UN ATRIBUTO
				int x_attribute_not_derived=line.indexOf("metawebdesign:NotDerived");
				int x_attribute_derived=line.indexOf("metawebdesign:Derived");
				int x_attribute_name=line.indexOf("name=");//POSICION DEL NOMBRE DEL ATRIBUTO
				int x_attribute_type=line.indexOf("dataType=");
				int x_attribute_pk=line.indexOf("primaryKey=");
				int x_attribute_formula=line.indexOf("formula=");
				int x_relation=line.indexOf("hasRelationClass name=");//IDENTIFICA UNA RELACION
				int x_relation_name=line.indexOf("name=");
				int x_relation_classA=line.indexOf("Attribute_Class_A=");
				int x_relation_classB=line.indexOf("Attribute_Class_B=");
				
				//BUSQUEDA TABLA
				if(x_class != -1){
					//AGREGO LA TABLA IDENTIFICADA ANTERIORMENTE
					if(cont_tabla !=0){
						sql.addTabla(t);//AGREGO LA TABLA IDENTIFICADA EN LOS CICLOS ANTERIORES A LAS SENTENCIAS SQL
					}
					
					//NUEVA TABLA
					t = new Tabla();
			        String substr = line.substring(x_class+12, line.length());
			        int stop=substr.indexOf("\"");//CRITERIO DE PARADA PARA EXTRACCION DEL DATO
			        tabla=substr.substring(0, stop);
			        t.setNombre(tabla);//AGREGO EL NOMBRE A LA TABLA
			        //System.out.println("Tabla nombre: "+tabla);
			        cont_tabla++;
			    }
				
				//BUSQUEDA ATRIBUTO NO DERIVADO
				if(x_attribute != -1 && x_attribute_not_derived != -1){
					//DATOS A EXTRAER DE LOS ATRIBUTOS
			        String substr_nombre = line.substring(x_attribute_name+6, line.length());
			        String substr_type=line.substring(x_attribute_type+10, line.length());
			        
			        //CRITERIO DE PARADA PARA EXTRACCION DEL DATO
			        int stop_nombre=substr_nombre.indexOf("\"");
			        int stop_type=substr_type.indexOf("\"");
			        
			        boolean pk=false;
			        
			        if(x_attribute_pk != -1){
			        	pk=true;
			        }
			        
			        String atributo_nombre=substr_nombre.substring(0, stop_nombre);
			        String atributo_type=substr_type.substring(0, stop_type);
			        
			        //System.out.println("Atributo nombre: "+atributo_nombre+" tipo: "+atributo_type);
			        Atributo a = new Atributo(atributo_nombre, pk, false, atributo_type);
			        t.addAtributo(a);//AGREGO ATRIBUTOS A LA TABLA 
				}
				
				//BUSQUEDA ATRIBUTO DERIVADO 
				/*
				 * Este atributo genera una vista en la Base de Datos
				 */
				if(x_attribute != -1 && x_attribute_derived != -1){
					//DATOS A EXTRAER DE LOS ATRIBUTOS
					String substr_nombre=line.substring(x_attribute_name+6,line.length());
					String substr_formula=line.substring(x_attribute_formula+9, line.length());
					
					//CRITERIO DE PARADA PARA EXTRACCION DEL DATO
			        int stop_nombre=substr_nombre.indexOf("\"");
			        int stop_formula=substr_formula.indexOf("\"");
			        
			        String atributo_nombre=substr_nombre.substring(0, stop_nombre);
			        String atributo_formula=substr_formula.substring(0, stop_formula);
			        View v = new View(atributo_nombre, atributo_formula, tabla );
			        sql.addView(v);
				}
				
				//BUSQUEDA RELACION
				/*
				if(x_relation != -1){
					//NOMBRE EN LA RELACION
			        //String substr = line.substring(x_relation+23, x_relation_to_class-2);
					 String substr_nombre = line.substring(x_relation_name+6, line.length());
				     String substr_tablaA=line.substring(x_relation_classA, line.length());
				     String substr_tablaA=line.substring(x_relation_classA, line.length());   
				     //CRITERIO DE PARADA PARA EXTRACCION DEL DATO
				     int stop_nombre=substr_nombre.indexOf("\""); 
				     int stop_tablaA=substr_tablaA.indexOf("@");
				}*/
				
				
			}
			fr.close();
			sql.addTabla(t);//AGREGO LA ULTIMA TABLA
			WriteSQL.write(sql);
			
	}
	
	public static void main(String[] args) throws IOException {
	    //loadTest();
		loadXML();
	}
}