package compilerphp.actions;

import java.io.FileWriter;
import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import org.sqlite.*;


public class SQLite{
	
	static String name_db; 
	static String path_db;
	
	//ESCRIBRE EN TEXTO PLANO LAS SENTENCIAS SQL
	public static List<String> genSQL(SQL sql, String path){
		List <View> views = sql.getViews();
		List <Tabla> tablas = sql.getTablas();
		List <Atributo> atributos;
		List <ForeignKey> foreignKeys;
		List <String> dataBase = new ArrayList<String>();
		//ESCRITURA DE LA NUEVA SENTENCIA
			
			//ESCRIVO LAS TABLAS
			for(Tabla tabla : tablas) {
				String linea_sql="";
				// ESCRIVO EL NOMBRE DE LA TABLA
			    linea_sql=linea_sql+"CREATE TABLE "+tabla.getNombre()+" (";
			    //CAPTURO LOS ATRIBUTOS DE LA TABLA
			    atributos=tabla.getAtributos();
			    foreignKeys=tabla.getForeignKeys();
			    
			    //IMPRIMO LOS ATRIBUTOS
			    int coma=0;
			    for(Atributo atributo : atributos){
			    	//ESCRIVO LOS ATRIBUTOS
			    	if(coma !=0){
			    		linea_sql=linea_sql+", ";
			    	}
			    	
			    	linea_sql=linea_sql+atributo.getNombre();
			    	
			    	if (atributo.getType().equals("autoincremental")){
			    		linea_sql=linea_sql+" integer primary key not null";
			    	}
			    	
			    	if (!atributo.getType().equals("autoincremental")){
			    		linea_sql=linea_sql+" "+atributo.getType();
			    		/*
			    		if(atributo.getPrimaryKey() == true){
			    			linea_sql=linea_sql+" PRIMARY KEY";
			    		}*/
			    	}
			    	coma++;
			    }
			    
		    	//IMPRIMO LLAVES FORANEAS
			    coma=0;
			    //AGREGO ATRIBUTO DE LAS LLAVES FORANEA
		    	for(ForeignKey fk : foreignKeys){
		    		//String tablaDestino=tablas.get(fk.getDestination()).getNombre();
		    		//String atributoDestino=tablas.get(fk.getDestination()).getAtributos().get(fk.getAtributoDestination()).getNombre();
		    		String typeAtributo=tablas.get(fk.getDestination()).getAtributos().get(fk.getAtributoDestination()).getType();
	    			linea_sql=linea_sql+", "+fk.getNombre()+" ";
		    		if(typeAtributo.equals("autoincremental")){
		    			linea_sql=linea_sql+"integer";
		    		}
		    		else{
		    			linea_sql=linea_sql+typeAtributo;
		    		}
		    		coma++;
		    	}
		    	coma=0;
		    	//AGREGO LAS REFERENCIAS DE LAS LLAVES FORANES
		    	for(ForeignKey fk : foreignKeys){
		    		String tablaDestino=tablas.get(fk.getDestination()).getNombre();
		    		String atributoDestino=tablas.get(fk.getDestination()).getAtributos().get(fk.getAtributoDestination()).getNombre();
		    		//String typeAtributo=tablas.get(fk.getDestination()).getAtributos().get(fk.getAtributoDestination()).getType();
		    		linea_sql=linea_sql+", FOREIGN KEY("+fk.getNombre()+") REFERENCES "+tablaDestino+"("+atributoDestino+")";
		    		coma++;
		    	}
		    	
		    	linea_sql=linea_sql+");";
		    	System.out.println(linea_sql);
		    	dataBase.add(linea_sql);
	        }
			
			//ESCRIVO LAS VISTAS
			for(View view : views){
				String vista="CREATE VIEW "+view.getTabla()+view.getNombre()+"view as ";
				vista=vista+"select * from "+view.getTabla();
				vista=vista+", ("+view.getFormula()+") as derived";
				dataBase.add(vista);
			}
			
			/*
			 * Dashboard
			 * Inserción de datos requeridos para la construcción del Dashboard
			 */
			//DASHBOARD MENU
			dataBase.add("CREATE TABLE Dashboard (id integer primary key not null, nombre varchar(50), vista boolean);");
			
			//DASHBOARD TABLAS
			
			for(Tabla tabla : tablas){
				String dash_table="INSERT INTO Dashboard (nombre, vista) values ('"+tabla.getNombre().toLowerCase()+"', 'false');";
				dataBase.add(dash_table);
			}
			
			//DASHBOARD VISTAS
			
			for(View view : views){
				String dash_table="INSERT INTO Dashboard (nombre, vista) values ('"+view.getTabla()+view.getNombre().toLowerCase()+"view', 'true');";
				dataBase.add(dash_table);
			
			}
			
			//DASHBOARD ROLES POR DEFECTO (ROLES DEL MODELO MWD: ADMIN, AUTHENTICATED, ANONYMUS, ANY)
			dataBase.add("INSERT INTO Roles (rolname) values ('admin');");
			dataBase.add("INSERT INTO Roles (rolname) values ('authenticated');");
			//ANONYMUS: es un usuario no autentificado
			//ANY: es indiferente al rol para acceder 
			
			//DASHBOARD USUARIOS POR DEFECTO
			dataBase.add("INSERT INTO Users (username, passwd, id_rol) values ('mwd', 'mwd123', 1);"); //USUARIO POR DEFECTO DEL SISTEMA
			
			//DASHBOARD VISITAS
			dataBase.add("CREATE TABLE Views(id_view integer primary key not null, title varchar(50), id_rol integer, content text, FOREIGN KEY(id_rol) REFERENCES Roles(id_rol));");
			
			//DASHBOARD CONFIGURACION
			dataBase.add("CREATE TABLE DashboardConf (id_web integer primary key not null, sitetitle varchar(30), tagline varchar(30),admin_mail varchar(50), id_index integer, FOREIGN KEY(id_index) REFERENCES Views(id_view)); );");
			
			
			dataBase.add("CREATE TABLE TypePresentation (id_presentation integer primary key not null, presentationname varchar(50));");
			
			dataBase.add("INSERT INTO TypePresentation (presentationname) values ('string');");
			dataBase.add("INSERT INTO TypePresentation (presentationname) values ('table');");
			dataBase.add("INSERT INTO TypePresentation (presentationname) values ('combobox');");
			dataBase.add("INSERT INTO TypePresentation (presentationname) values ('radiobuttom');");
			dataBase.add("INSERT INTO TypePresentation (presentationname) values ('itemList');");
			dataBase.add("INSERT INTO TypePresentation (presentationname) values ('form_email');");
			dataBase.add("INSERT INTO TypePresentation (presentationname) values ('form_password');");
			dataBase.add("INSERT INTO TypePresentation (presentationname) values ('form_file');");
			dataBase.add("INSERT INTO TypePresentation (presentationname) values ('table_striped');");
			dataBase.add("INSERT INTO TypePresentation (presentationname) values ('table_hover');");
			dataBase.add("INSERT INTO TypePresentation (presentationname) values ('img');");
			
			dataBase.add("CREATE TABLE ViewAttribute(id_view_attribute integer primary key not null, id_view integer, id_tabla integer, id_atributo_tabla integer, pos_x varchar(10), pos_y integer, id_presentation integer, FOREIGN KEY(id_view) REFERENCES Views(id_view), FOREIGN KEY(id_presentation) REFERENCES TypePresentation(id_presentation));");
		
			//DASHBOARD ERROR
			dataBase.add("CREATE TABLE DashboardError (id_error integer primary key not null, error_titulo varchar(50), descrip text);");
			
			//DASHBOARD MENU
			//MENU
		return dataBase;
	}
	
	//GENERO LA BASE DE DATOS Y ALGUNAS ESTRUCTURAS PARA EL SITIO PHP
	/*
	 * Crea base de datos sqlite
	 * Crea script para generar modelos de las tablas
	 * Crea script para generar controladores y vista de los servicios CRUD de c/u de las tablas
	 */
	public static void createDB(SQL model, String path, String file) throws IOException{
		//GENERO EL CODIGO SQL
		List <String> dataBase=SQLite.genSQL(model, path);//CREO UN STRING CON LA SINTAXIS SQL PARA CREAR LAS TABLAS, VISTAS Y LLAVES (PK Y FK)
		List <Tabla> tablas = model.getTablas();//TABLAS DE LA BDD PARA GENERAR MODELO Y CRUD
		//List <View> views = model.getViews(); //VISTAS DE LA BDD PARA GENERAR MODELO Y CRUD
		ExecuteShellComand obj= new ExecuteShellComand();
		
		//ARCHIVO A GENERAR
		FileWriter script_bdd = null;//SCRIPT BASH PARA CREAR LA BDD SQLITE3
		FileWriter script_model = null;//SCRIPT PARA CREAR MODELO USANDO YII MVC
		FileWriter script_crud = null;//SCRIPT PARA CREAR CURD + VISTAS CURD USANDO YII MVC
		
		int stop=file.indexOf(".");
		String nombreScriptBD=file.substring(0, stop);
		name_db=nombreScriptBD; //SETEO EL NOMBRE DE LA BASE DE DATOS
		path_db=path;//SETEO LA RUTA DE DONDE SE ENCUENTRA LA BDD
		//CREO LA CARPETA DEL PROYECTO
		obj.executeCommand("mkdir "+path+"/PHP");
		
		//ESCRITURA DEL SCRIPT BASH PARA LA CREACION DE LA BDD
		script_bdd = new FileWriter(path+"/PHP/"+nombreScriptBD+".sh");
		for(String sql_line : dataBase){
			script_bdd.write("sqlite3 "+path+"/PHP/"+nombreScriptBD+".db \""+sql_line+"\"\n");
			System.out.println("sqlite3-> "+sql_line);
		}
		script_bdd.close();
		
		//ESCRITURA DEL SCRIPT PARA LA CREACION DE LOS MODELOS
		script_model = new FileWriter(path+"/PHP/model.sh");
		script_model.write("cd $1/proyect/\n");
		for(Tabla tabla : tablas) {
			script_model.write("./yii gii/model --tableName="+tabla.getNombre()+" --modelClass="+tabla.getNombre()+" --interactive=0\n");
		}
		
		//ESCRITURA DEL SCRIPT PARA LA CREACION DE LOS MODELOS DASHBOARD
		script_model.write("./yii gii/model --tableName=Dashboard --modelClass=Dashboard --interactive=0\n");
		script_model.write("./yii gii/model --tableName=DashboardConf --modelClass=DashboardConf --interactive=0\n");
		//script_model.write("./yii gii/model --tableName=Views --modelClass=Views --interactive=0\n");
		script_model.write("./yii gii/model --tableName=TypePresentation --modelClass=TypePresentation --interactive=0\n");
		script_model.write("./yii gii/model --tableName=ViewAttribute --modelClass=ViewAttribute --interactive=0\n");
		script_model.write("./yii gii/model --tableName=DashboardError --modelClass=DashboardError --interactive=0\n");
		script_model.close();
		
		//ESCRITURA DEL SCRIPT PARA LA CREACION DEL CRUD
		script_crud = new FileWriter(path+"/PHP/crud.sh");
		script_crud.write("cd $1/proyect/\n");
		for(Tabla tabla : tablas) {
			script_crud.write("./yii gii/crud --interactive=0 --modelClass=\\\\app\\\\models\\\\"+tabla.getNombre()+" --controllerClass=\\\\app\\\\controllers\\\\"+tabla.getNombre()+"Controller\n");
		}
		//ESCRITURA DEL SCRIPT PARA LA CREACION DEL CRUD DASHBOARD
		script_crud.write("./yii gii/crud --interactive=0 --modelClass=\\\\app\\\\models\\\\Dashboard --controllerClass=\\\\app\\\\controllers\\\\DashboardController\n");
		script_crud.write("./yii gii/crud --interactive=0 --modelClass=\\\\app\\\\models\\\\DashboardConf --controllerClass=\\\\app\\\\controllers\\\\DashboardConfController\n");
		//script_crud.write("./yii gii/crud --interactive=0 --modelClass=\\\\app\\\\models\\\\Views --controllerClass=\\\\app\\\\controllers\\\\ViewsController\n");
		script_crud.write("./yii gii/crud --interactive=0 --modelClass=\\\\app\\\\models\\\\TypePresentation  --controllerClass=\\\\app\\\\controllers\\\\TypePresentationController\n");
		script_crud.write("./yii gii/crud --interactive=0 --modelClass=\\\\app\\\\models\\\\ViewAttribute --controllerClass=\\\\app\\\\controllers\\\\ViewAttributeController\n");
		script_crud.write("./yii gii/crud --interactive=0 --modelClass=\\\\app\\\\models\\\\DashboardError --controllerClass=\\\\app\\\\controllers\\\\DashboardErrorController\n");
		script_crud.close();
		
		//DOY PERMISOS AL SCRIPT DE EJECUCIÓN
		obj.executeCommand("chmod +x "+path+"/PHP/*");
		
		//EJECUTO EL SCRIPT PARA CREAR LA BDD
		obj.executeCommand("bash "+path+"/PHP/"+nombreScriptBD+".sh");
	}

	//INSERCIÓN DATOS SQL DE VISTAS POR DEFECTO
	/*
	 * INDEX
	 * ABOUT
	 * CONTACTO
	 */
	public static void insertView(String title, String content) throws IOException{
		ExecuteShellComand obj= new ExecuteShellComand();
		FileWriter script_insertView = null;//SCRIPT BASH PARA CREAR LA BDD SQLITE3
		String name_script=title.replaceAll("\\s+","");
		String sql_line="INSERT INTO Views (title, content) values ('"+title+"', '"+content+"');";
		script_insertView = new FileWriter(path_db+"/PHP/"+name_script+".sh");
		script_insertView.write("sqlite3 "+path_db+"/PHP/proyect/config/"+name_db+".db \""+sql_line+"\"\n");
		script_insertView.close();
		//DOY PERMISOS AL SCRIPT DE EJECUCIÓN
		obj.executeCommand("chmod +x "+path_db+"/PHP/*");
		//EJECUTO EL SCRIPT PARA CREAR LA BDD
		obj.executeCommand("bash "+path_db+"/PHP/"+name_script+".sh");
	}
	
}