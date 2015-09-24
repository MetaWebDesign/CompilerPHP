package compilerphp.actions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ReadModel{
	
	public static SQL loadXML(String path, String file) throws IOException
	{
			SQL sql= new SQL();
			Tabla t=  new Tabla();
			
			System.out.println("Load XML");
			
			FileReader fr = new FileReader(path+"/"+file);//LECTURA DEL ARCHIVO DEL MODELO
			BufferedReader br = new BufferedReader(fr);
			String line;//LINEA DE LECTURA DEL ARCHIVO
			int cont_tabla=0;//CONTADOR DE TABLAS
			
			String tabla = null;//NOMBRE DE LA TABLA
			//int cont_tablas=0;
		
			//LECTURA
			while((line = br.readLine()) != null) {
				
				//System.out.println(line);
				int x_class=line.indexOf("class name="); //IDENTIFICA UNA CLASE
				int x_attribute=line.indexOf("<hasAttributes");//IDENTIFICA UN ATRIBUTO
				int x_attribute_not_derived=line.indexOf("metawebdesign:NotDerived");//IDENTIFICA UN ATRIBUTO NO DERIVADO
				int x_attribute_derived=line.indexOf("metawebdesign:Derived");//IDENTIFICA UN ATRIBUTO DERIVADO
				int x_attribute_name=line.indexOf("name=");//POSICION DEL NOMBRE DEL ATRIBUTO
				int x_attribute_type=line.indexOf("dataType=");//IDENTIFICA EL TIPO DE ATRIBUTO
				int x_attribute_pk=line.indexOf("PrimaryKey=");//IDENTIFICA LLAVE PRIMARIA
				int x_attribute_formula=line.indexOf("formula=");//IDENTIFICA FORMULA SQL DEL ATRIBUTO DERIVADO PARA CREAR UNA VISTA
				int x_attribute_required=line.indexOf("inTheForm=\"not_required\"");
				int x_relation=line.indexOf("hasRelationClass name=");//IDENTIFICA UNA RELACION
				int x_relation_name=line.indexOf("name=");//IDENTIFICA EL NOMBRE DE UNA RELACION PARA CREAR LLAVE FORANEA
				int x_relation_destination=line.indexOf("Attribute_Destination");
				
				//BUSQUEDA TABLA
				if(x_class != -1){
					//AGREGO LA TABLA IDENTIFICADA ANTERIORMENTE
					if(cont_tabla !=0){
						sql.addTabla(t);//AGREGO LA PRIMERA TABLA INDETIFICADA
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
			        boolean requiered=true;
			        if(x_attribute_pk != -1){
			        	pk=true;
			        }
			        if(x_attribute_required !=-1){
			        	requiered=false;
			        }
			        
			        String atributo_nombre=substr_nombre.substring(0, stop_nombre);
			        String atributo_type_model=substr_type.substring(0, stop_type);
			        
			        //ADATA EL DATA TYPE DEL ATRIBUTO DEL MODELO A UN DATA TYPE ACEPTADO POR SQL (DATA TYPE = VARCHAR)
			        //if(atributo_type.indexOf("varchar")!= -1){
			        	//atributo_type=typeAtributeVarChar(atributo_type);
			        //}
			        
			        String atributo_type=typeAdaptAtribute(atributo_type_model);//Adapta el dataType del modelo a uno aceptado por la BDD;
			        
			        //System.out.println("Atributo nombre: "+atributo_nombre+" tipo: "+atributo_type);
			        Atributo a = new Atributo(atributo_nombre, pk, false, atributo_type, atributo_type_model, requiered);
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
					String substr_type=line.substring(x_attribute_type+10, line.length());
					
					//CRITERIO DE PARADA PARA EXTRACCION DEL DATO
			        int stop_nombre=substr_nombre.indexOf("\"");
			        int stop_formula=substr_formula.indexOf("\"");
			        int stop_type=substr_type.indexOf("\"");
			        
			        String atributo_nombre=substr_nombre.substring(0, stop_nombre);
			        String atributo_type=substr_type.substring(0, stop_type);
			        String atributo_formula=substr_formula.substring(0, stop_formula);
			        View v = new View(atributo_nombre, atributo_formula, tabla, atributo_type);
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
				//cont_tabla++;
			}
			fr.close();
			sql.addTabla(t);//AGREGO LA ULTIMA TABLA DEL MODELO
			
			//AGREGAR TABLAS DASHBOARD
			/*
			Tabla dashboard=new Tabla();
			dashboard.setNombre("Dashboard");
			Atributo id=new Atributo("id", true, false, "autoincremental", "autoincremental", false);
			Atributo nombre=new Atributo("nombre", false, false, "varchar(50)", "varchar50", true);
			Atributo vista=new Atributo("vista", false, false, "boolean", "boolean", true);
			dashboard.addAtributo(id);
			dashboard.addAtributo(nombre);
			dashboard.addAtributo(vista);
			sql.addTabla(dashboard);
			*/
			//cont_tabla++;
			
			//DASHBOARD CONFIG WEB
			/*
			Tabla dashboard_conf=new Tabla();
			dashboard_conf.setNombre("DashboardConf");
			Atributo idconf=new Atributo("id", true, false, "autoincremental", "autoincremental", false);
			Atributo sitetitle=new Atributo("sitetitle", false, false, "varchar(50)", "varchar50", true);
			Atributo tagline=new Atributo("tagline", false, false, "varchar(50)", "varchar50", true);
			Atributo admin_email=new Atributo("admin_email", true, false, "varchar(50)", "varchar50", true);
			dashboard_conf.addAtributo(idconf);
			dashboard_conf.addAtributo(sitetitle);
			dashboard_conf.addAtributo(tagline);
			dashboard_conf.addAtributo(admin_email);
			sql.addTabla(dashboard_conf);
			cont_tabla++;
			*/
			//VIEWS (LISTA DE LAS VIASTAS / PAGUINAS DEL SITIO WEB)
			/*
			Tabla views= new Tabla();
			views.setNombre("Views");
			Atributo idview=new Atributo("idview", true, false, "autoincremental", "autoincremental", false);
			Atributo title=new Atributo("title", false, false, "varchar(50)", "varchar50", true);
			Atributo id_rol=new Atributo("id_rol", false, false, "integer", "integer", true);
			Atributo content=new Atributo("content", false, false, "text", "text", true);
			//ForeignKey fk_id_rol=new ForeignKey("id_rol", sql.getIDTabla("Roles"), 0);
			views.addAtributo(idview);
			views.addAtributo(title);
			views.addAtributo(id_rol);
			views.addAtributo(content);
			//views.addForeignKey(fk_id_rol);
			cont_tabla++;
			*/
			
			SQLite.createDB(sql, path, file);//GENERO LA BASE DE DATOS EN SQLITE
			return sql;
	}
	
	//public static String typeAtributeVarChar(String DataType){
	public static String typeAdaptAtribute(String DataTypeModel){
		if(DataTypeModel.equals("varchar10")){
			return "varchar(10)";
		}
		if(DataTypeModel.equals("varchar30")){
			return "varchar(30)";
		}
		if(DataTypeModel.equals("varchar50")){
			return "varchar(50)";
		}
		if(DataTypeModel.equals("img")){
			return "varchar(100)";
		}
		if(DataTypeModel.equals("date_time")){
			return "datetime";
		}
		else{
			return DataTypeModel;
		}
	}
}