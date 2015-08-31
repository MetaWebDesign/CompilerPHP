package compilerphp.actions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

//import compilerphp.actions.WriteSQL;

public class ReadModel{
	
	private static String path;
	private static String file;
	
	/*
	public static String getCurrentFile(String currentDirectory){
		String fileModel = null;
		File folder = new File(currentDirectory);
		File[] listOfFiles = folder.listFiles();
	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	        //System.out.println("File " + listOfFiles[i].getName());
	        if(listOfFiles[i].getName().indexOf("metawebdesign")!=-1){
	        	int stop=listOfFiles[i].getName().indexOf(".");
	        	fileModel=listOfFiles[i].getName().substring(0, stop)+"metawebdesign";
	        }
	      } 
	    }
		return fileModel;
	}*/
	
	
	public static String getCurrentDirectory(){
		ExecuteShellComand obj = new ExecuteShellComand();
		String output = obj.executeCommand("locate *.metawebdesign");//UBICA EL ARCHIVO XML
		Path p = Paths.get(output);
		/*
		System.out.format("toString: %s%n", p.toString());
		System.out.format("getFileName: %s%n", p.getFileName());
		System.out.format("getName(0): %s%n", p.getName(0));
		System.out.format("getNameCount: %d%n", p.getNameCount());	
		System.out.format("subpath(0,2): %s%n", p.subpath(0,2));
		System.out.format("getParent: %s%n", p.getParent());
		System.out.format("getRoot: %s%n", p.getRoot());
		*/
		String currentDirectory=p.getParent().toString();//IDENTIFICA LA RUTA DEL XML
		String currentFile=p.getFileName().toString();
		//System.out.println(currentDirectory);
		setFile(currentFile.substring(0, currentFile.length()-1 ));//IDENTIFICA EL NOMBRE DEL XML
		//System.out.println(file);
        return currentDirectory;
	}

	public static void setFile(String f){
		file=f;
	}
	
	public static void setPath(String p){
		path=p;
	}
	
	
	
	public static void loadXML() throws IOException
	{
			SQL sql= new SQL();
			Tabla t=  new Tabla();
			
			setPath(getCurrentDirectory());
			System.out.println("Load XML");
			FileReader fr = new FileReader(path+"/"+file);//LECTURA DEL ARCHIVO DEL MODELO
			BufferedReader br = new BufferedReader(fr);
			String line;//LINEA DE LECTURA DEL ARCHIVO
			int cont_tabla=0;//CONTADOR DE TABLAS
			
			String tabla = null;//NOMBRE DE LA TABLA
			
			//LECTURA
			while((line = br.readLine()) != null) {
				
				//System.out.println(line);
				int x_class=line.indexOf("class name="); //IDENTIFICA UNA CLASE
				int x_attribute=line.indexOf("<hasAttributes");//IDENTIFICA UN ATRIBUTO
				int x_attribute_not_derived=line.indexOf("metawebdesign:NotDerived");//IDENTIFICA UN ATRIBUTO NO DERIVADO
				int x_attribute_derived=line.indexOf("metawebdesign:Derived");//IDENTIFICA UN ATRIBUTO DERIVADO
				int x_attribute_name=line.indexOf("name=");//POSICION DEL NOMBRE DEL ATRIBUTO
				int x_attribute_type=line.indexOf("dataType=");//IDENTIFICA EL TIPO DE ATRIBUTO
				int x_attribute_pk=line.indexOf("primaryKey=");//IDENTIFICA LLAVE PRIMARIA
				int x_attribute_formula=line.indexOf("formula=");//IDENTIFICA FORMULA SQL DEL ATRIBUTO DERIVADO PARA CREAR UNA VISTA
				int x_relation=line.indexOf("hasRelationClass name=");//IDENTIFICA UNA RELACION
				int x_relation_name=line.indexOf("name=");//IDENTIFICA EL NOMBRE DE UNA RELACION PARA CREAR LLAVE FORANEA
				int x_relation_destination=line.indexOf("Attribute_Destination");
				
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
			        
			        //ADATA EL DATA TYPE DEL ATRIBUTO DEL MODELO A UN DATA TYPE ACEPTADO POR SQL (DATA TYPE = VARCHAR)
			        if(atributo_type.indexOf("varchar")!= -1){
			        	atributo_type=typeAtributeVarChar(atributo_type);
			        }
			        
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
				if(x_relation != -1){
					//NOMBRE EN LA RELACION
				     String substr_nombre=line.substring(x_relation_name+6, line.length());
				     String substr_claseDestino=line.substring(x_relation_destination+32, line.length());
				     String substr_atributoDestino=line.substring(x_relation_destination+49, line.length());
				     
				     //CRITERIO DE PARADA PARA EXTRACCION DEL DATO
				     int stop_nombre=substr_nombre.indexOf("\"");
				     int stop_classDestino=substr_claseDestino.indexOf("/");
				     int stop_atributoDestino=substr_atributoDestino.indexOf("\"");
				     
				     //EXTRACCION DE DATOS DE LA RELACION
				     String relation_name=substr_nombre.substring(0, stop_nombre);
				     int numClaseDestino= Integer.parseInt(substr_claseDestino.substring(0, stop_classDestino));
				     int numAtributoDestino= Integer.parseInt(substr_atributoDestino.substring(0, stop_atributoDestino));
				     
				     ForeignKey f=new ForeignKey(relation_name, numClaseDestino, numAtributoDestino);
				     t.addForeignKey(f);
				}
				
				
			}
			fr.close();
			sql.addTabla(t);//AGREGO LA ULTIMA TABLA
			WriteSQL.write(sql, path);
			
	}
	
	public static String typeAtributeVarChar(String DataType){
		if(DataType.equals("varchar10")){
			return "varchar(10)";
		}
		if(DataType.equals("varchar30")){
			return "varchar(30)";
		}
		else{
			return "varchar(50)";
		}
	}
	
	public static void main(String[] args) throws IOException {
	    //loadTest();
		//loadXML();
		getCurrentDirectory();
		//test();
	}
}