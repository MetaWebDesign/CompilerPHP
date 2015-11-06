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
		//sql.genViews();
		System.out.println("Paso");
		//List <View> views = sql.getViews();
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
			    	if(!atributo.getDerivedEDO()){
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
			    		}
			    		coma++;
			    	}
			    }
			    
			    //IMPRIMO LLAVES FORANEAS
			    coma=0;
			    //AGREGO ATRIBUTO DE LAS LLAVES FORANEA
			    for(ForeignKey fk : foreignKeys){
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
			    dataBase.add(linea_sql);
			}
			
			//ESCRIVO LAS VISTAS
			System.out.println("Escrivo las Vistas");
			for(View view : sql.getViews()){
				System.out.println("Escriviendo: "+view.getNombre());
				dataBase.add(view.getSQL());
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
			
			for(View view : sql.getViews()){
				String dash_table="INSERT INTO Dashboard (nombre, vista) values ('"+view.getTabla().toLowerCase()+view.getNombre().toLowerCase()+"', 'true');";
				dataBase.add(dash_table);
			
			}
			
			//DASHBOARD ROLES POR DEFECTO (ROLES DEL MODELO MWD: ADMIN, AUTHENTICATED, ANONYMUS, ANY)
			dataBase.add("INSERT INTO Roles (rolname) values ('admin');");
			dataBase.add("INSERT INTO Roles (rolname) values ('authenticated');");
			dataBase.add("INSERT INTO Roles (rolname) values ('any');");
			dataBase.add("INSERT INTO Roles (rolname) values ('anonymous');");
			
			//DASHBOARD USUARIOS POR DEFECTO
			dataBase.add("INSERT INTO Users (username, passwd, id_rol) values ('mwd', 'mwd123', 1);"); //USUARIO POR DEFECTO DEL SISTEMA
			
			//DASHBOARD PAGINAS
			dataBase.add("CREATE TABLE Views(id_view integer primary key not null, title varchar(50), id_rol integer, content text, FOREIGN KEY(id_rol) REFERENCES Roles(id_rol));");
			
			//DASHBOARD PAGINAS (ADVANCE) VISTAS MODELADAS
			dataBase.add("CREATE TABLE ViewAdvance (id integer primary key not null, id_vista integer, id_clase integer, id_atributo integer, typePresentation varchar(50), x_position varchar(20), y_position integer, FOREIGN KEY(id_vista) REFERENCES Views(id_view), FOREIGN KEY(id_clase) REFERENCES Dashboard(id), FOREIGN KEY(id_atributo) REFERENCES ClassAtributo(id) );");
			
			//DASHBOARD ATRIBUTOS CLASES
			dataBase.add("CREATE TABLE ClassAtributo (id integer primary key not null, nombre varchar(50), id_clase integer, id_atributo integer, FOREIGN KEY(id_clase) REFERENCES Dashboard(id));");
			
			//DASHBOARD CONFIGURACION
			dataBase.add("CREATE TABLE DashboardConf (id_web integer primary key not null, sitetitle varchar(30), tagline varchar(30),admin_mail varchar(50), id_index integer, FOREIGN KEY(id_index) REFERENCES Views(id_view)); );");
			
			//DASHBOARD MEDIA
			/*
			 * Para gestor para subir archivo o fotos al sitio web
			 */
			dataBase.add("CREATE TABLE DashboardMedia (id_media integer primary key not null, filename varchar(100), id_autor integer, Fecha datetime, extencion varchar(10), FOREIGN KEY(id_autor) REFERENCES Users (id_user));");
			
			// DASHBIARD PERMISOS PARA ACCEDER A LOS SERVICIOS Y SUS PAGINAS
			dataBase.add("CREATE TABLE DashboardPermisoscrud (id_permiso integer primary key not null, id_dash integer, service varchar(50) ,id_rol integer, FOREIGN KEY(id_dash) REFERENCES Dashboard(id), FOREIGN KEY(id_rol) REFERENCES Roles(id_rol));");
			
			//MENU
			dataBase.add("CREATE TABLE Menu (id integer primary key not null, nombre varchar(50), type varchar(50), id_view integer, FOREIGN KEY (id_view) REFERENCES Views(id_view) );");
			dataBase.add("CREATE TABLE Links (id integer primary key not null, nombre varchar(50), url varchar(70), id_menu integer, FOREIGN KEY (id_menu) REFERENCES Menu(id));");
			
		return dataBase;
	}
	
	//GENERO LA BASE DE DATOS Y ALGUNAS ESTRUCTURAS PARA EL SITIO PHP
	/*
	 * Crea base de datos sqlite
	 */
	public static void createDB(SQL model, String path, String file) throws IOException{
		//GENERO EL CODIGO SQL
		List <String> dataBase=SQLite.genSQL(model, path);//CREO UN STRING CON LA SINTAXIS SQL PARA CREAR LAS TABLAS, VISTAS Y LLAVES (PK Y FK)
		ExecuteShellComand obj= new ExecuteShellComand();
		//DOY PERMISOS AL SCRIPT DE EJECUCIÓN
		obj.clean_proyect(path+"/PHP/");//en caso de existir un proyecto anterior lo respalda
		
		//ARCHIVO A GENERAR
		FileWriter script_bdd = null;//SCRIPT BASH PARA CREAR LA BDD SQLITE3
		
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
			//System.out.println("sqlite3-> "+sql_line);
		}
		script_bdd.close();	
		obj.executeCommand("chmod +x "+path+"/PHP/*");
		
		//EJECUTO EL SCRIPT PARA CREAR LA BDD
		obj.executeCommand("bash "+path+"/PHP/"+nombreScriptBD+".sh");
	}
	

	public static void insertConfWeb(String site_title, String tagline, String admin_mail, int id_index) throws IOException{
		ExecuteShellComand obj= new ExecuteShellComand();
		FileWriter script_conf_web = null;//SCRIPT BASH PARA CREAR LA BDD SQLITE3
		//String name_script=site_title.replaceAll("\\s+","");
		String name_script="conf_web";
		String sql_line="INSERT INTO DashboardConf (sitetitle,  tagline, admin_mail, id_index ) values ('"+site_title+"', '"+tagline+"', '"+admin_mail+"', '"+id_index+"');";
		script_conf_web = new FileWriter(path_db+"/PHP/"+name_script+".sh");
		script_conf_web.write("sqlite3 "+path_db+"/PHP/proyect/config/"+name_db+".db \""+sql_line+"\"\n");
		script_conf_web.close();
		//DOY PERMISOS AL SCRIPT DE EJECUCIÓN
		obj.executeCommand("chmod +x "+path_db+"/PHP/*");
		//EJECUTO EL SCRIPT PARA CREAR LA BDD
		obj.executeCommand("bash "+path_db+"/PHP/"+name_script+".sh");
	} 
	
	//INSERCIÓN DATOS SQL DE VISTAS POR DEFECTO
	/* Usado para generar:
	 * - INDEX
	 * - ABOUT
	 * - CONTACTO
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
	
	//INSERTA LOS PERMISOS PARA ACCEDER A LOS SERVICIOS DEL CRUD Y PAGINAS
	public void insertPermisos(SQL sql) throws IOException{
		FileWriter script_permisos = null;//SCRIPT BASH PARA INSERTAR LOS PERMISOS DE ACCESO A LOS SERVICIOS DEL CRUD
		ExecuteShellComand obj= new ExecuteShellComand();
		//List <String> dataBase = new ArrayList<String>();
		String insert_permisos="";
		List <Tabla> tablas = sql.getTablas();
		for(Tabla tabla : tablas){
			Roles r=tabla.getRoles();
			insert_permisos=insert_permisos+"sqlite3 "+path_db+"/PHP/proyect/config/"+name_db+".db \"INSERT INTO DashboardPermisoscrud (id_dash, service, id_rol) VALUES ((select id from Dashboard where nombre='"+tabla.getNombre().toLowerCase()+"'), 'create', (select id_rol from Roles where rolname='"+r.getFCreate()+"'));\"\n";
			insert_permisos=insert_permisos+"sqlite3 "+path_db+"/PHP/proyect/config/"+name_db+".db \"INSERT INTO DashboardPermisoscrud (id_dash, service, id_rol) VALUES ((select id from Dashboard where nombre='"+tabla.getNombre().toLowerCase()+"'), 'update', (select id_rol from Roles where rolname='"+r.getFUpdate()+"'));\"\n";
			insert_permisos=insert_permisos+"sqlite3 "+path_db+"/PHP/proyect/config/"+name_db+".db \"INSERT INTO DashboardPermisoscrud (id_dash, service, id_rol) VALUES ((select id from Dashboard where nombre='"+tabla.getNombre().toLowerCase()+"'), 'delete', (select id_rol from Roles where rolname='"+r.getFDelete()+"'));\"\n";
			insert_permisos=insert_permisos+"sqlite3 "+path_db+"/PHP/proyect/config/"+name_db+".db \"INSERT INTO DashboardPermisoscrud (id_dash, service, id_rol) VALUES ((select id from Dashboard where nombre='"+tabla.getNombre().toLowerCase()+"'), 'index', (select id_rol from Roles where rolname='"+r.getFIndex()+"'));\"\n";
			insert_permisos=insert_permisos+"sqlite3 "+path_db+"/PHP/proyect/config/"+name_db+".db \"INSERT INTO DashboardPermisoscrud (id_dash, service, id_rol) VALUES ((select id from Dashboard where nombre='"+tabla.getNombre().toLowerCase()+"'), 'view', (select id_rol from Roles where rolname='"+r.getFView()+"'));\"\n";
		}
		script_permisos = new FileWriter(path_db+"/PHP/permisos.sh");
		script_permisos.write(insert_permisos);
		script_permisos.close();
		
		//DOY PERMISOS AL SCRIPT DE EJECUCIÓN
		obj.executeCommand("chmod +x "+path_db+"/PHP/*");
		//EJECUTO EL SCRIPT PARA CREAR LA BDD
		obj.executeCommand("bash "+path_db+"/PHP/permisos.sh");
	}
	
	/*
	 * INSERTAR ATRIBUTOS DE LAS CLASES
	 * - para editar las vistas modeladas desde la plataforma web
	 * - insertar datos en ClassAtributo
	 */
	public void insertAtributosClases(SQL sql) throws IOException{
		ExecuteShellComand obj= new ExecuteShellComand();
		FileWriter script_atributo = null;//SCRIPT BASH PARA CREAR LA BDD SQLITE3
		int id_tabla=1;
		String insert_atributo="";
		for(Tabla tabla: sql.getTablas()){
			int id_atributo=1;
			for (Atributo atributo : tabla.getAtributos()){
				insert_atributo=insert_atributo+"sqlite3 "+path_db+"/PHP/proyect/config/"+name_db+".db \"insert into ClassAtributo (nombre, id_clase, id_atributo) values ('"+atributo.getNombre()+"', "+id_tabla+", "+id_atributo+");\"\n";
				id_atributo++;
			}
			id_tabla++;
		}
		script_atributo = new FileWriter(path_db+"/PHP/classAtributo.sh");
		script_atributo.write(insert_atributo);
		script_atributo.close();
		
		//DOY PERMISOS AL SCRIPT DE EJECUCIÓN
		obj.executeCommand("chmod +x "+path_db+"/PHP/*");
		//EJECUTO EL SCRIPT PARA CREAR LA BDD
		obj.executeCommand("bash "+path_db+"/PHP/classAtributo.sh");
	}

	public void insertPages(List <Page> pages) throws IOException{
		ExecuteShellComand obj= new ExecuteShellComand();
		FileWriter script_pages = null;//SCRIPT BASH PARA CREAR LA BDD SQLITE3
		String insert_page="";
		int cont_page=5;
		for(Page page : pages){
			//INSERT PAGE
			//ROL
			String insert_view_page="INSERT INTO Views (id_view, title, content, id_rol) values ("+cont_page+",'"+page.getTitle()+"', '"+page.getContentHTML()+"', (SELECT id_rol FROM Roles where rolname='"+page.getRol().toLowerCase()+"' ));";
			insert_page=insert_page+"sqlite3 "+path_db+"/PHP/proyect/config/"+name_db+".db \""+insert_view_page+"\"\n";
			for(ViewAttribute atribute : page.getAtributos()){
				String insert_view_attribute="INSERT INTO ViewAdvance (id_vista, id_clase, id_atributo, typePresentation, x_position, y_position) VALUES ("+cont_page+", "+atribute.getClase()+", "+atribute.getAtributo()+",'"+atribute.getTypePresentation()+"', '"+atribute.getX_Pos()+"', "+atribute.getY_Pos()+");";
				insert_page=insert_page+"sqlite3 "+path_db+"/PHP/proyect/config/"+name_db+".db \""+insert_view_attribute+"\"\n";
			}
			cont_page++;
		}
		script_pages = new FileWriter(path_db+"/PHP/pages.sh");
		script_pages.write(insert_page);
		script_pages.close();
		
		//DOY PERMISOS AL SCRIPT DE EJECUCIÓN
		obj.executeCommand("chmod +x "+path_db+"/PHP/*");
		//EJECUTO EL SCRIPT PARA CREAR LA BDD
		obj.executeCommand("bash "+path_db+"/PHP/pages.sh");
	}
	
	public void insertMenus(List <Menu> menus, SQL sql) throws IOException{
		ExecuteShellComand obj= new ExecuteShellComand();
		FileWriter script_menu = null;//SCRIPT BASH PARA CREAR LA BDD SQLITE3
		String insert_menu="";
		int cont_menu=1;
		for(Menu menu : menus){
			insert_menu=insert_menu+"sqlite3 "+path_db+"/PHP/proyect/config/"+name_db+".db \""+"INSERT INTO Menu(nombre , type, id_view) VALUES ('"+menu.getName()+"', '"+menu.getTypeMenu()+"', "+menu.getIdView()+");\"\n";
			for(LinkCRUD link_crud : menu.getLinksCRUD()){
				Tabla tabla=sql.getTablaByInt(link_crud.getClase());
				insert_menu=insert_menu+"sqlite3 "+path_db+"/PHP/proyect/config/"+name_db+".db \"INSERT INTO Links (nombre, url, id_menu) VALUES ('"+link_crud.getName()+"', 'index.php?r="+tabla.getNombre().toLowerCase()+"/"+link_crud.getService()+"', "+cont_menu+");\"\n";
			}
			
			for(LinkView link_view : menu.getLinksView()){
				insert_menu=insert_menu+"sqlite3 "+path_db+"/PHP/proyect/config/"+name_db+".db \"INSERT INTO Links (nombre, url, id_menu) VALUES ('"+link_view.getName()+"', 'index.php?r=views/view&id="+link_view.getIdView()+"', "+cont_menu+");\"\n";
			}
			cont_menu++;
		}
		script_menu = new FileWriter(path_db+"/PHP/menus.sh");
		script_menu.write(insert_menu);
		script_menu.close();
		
		//DOY PERMISOS AL SCRIPT DE EJECUCIÓN
		obj.executeCommand("chmod +x "+path_db+"/PHP/*");
		//EJECUTO EL SCRIPT PARA CREAR LA BDD
		obj.executeCommand("bash "+path_db+"/PHP/menus.sh");
	}
}