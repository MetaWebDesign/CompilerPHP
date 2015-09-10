package compilerphp.actions;

import java.io.FileWriter;
import java.io.IOException;

public class PHP{
	
	//String path_php="/CompilerPHP/src/php/";//RUTA DONDE ESTA EL CODIGO PHP BASE DEL SITIO WEB 
	String path_proyect;//RUTA DONDE ESTA EL CODIGO DEL PROYECTO PHP A GENERAR
	
	/*
	 * CONSTRUCTOR DE LA CLASE
	 *  path : direccion del proyecto PHP
	 */
	public PHP(String path){
		this.path_proyect=path;
	}
	
	//DESPLIEGA EL CODIGO BASE PHP EN LA CARPETA DEL PROYECTO PHP
	public void start(){
		ExecuteShellComand obj= new ExecuteShellComand();
		obj.start_proyect(path_proyect);
	}
	
	/*
	 * CONFIGURA LA BASE DE DATOS
	 * 	configuración para sqlite, si se desea otra BDD, esta desde el
	 *  panel de administración de la plataforma se puede cambiar.
	 */
	public void configureBD(String db_name){
		String config_db=path_proyect+"proyect/config/db.php";
		ExecuteShellComand obj= new ExecuteShellComand();
		obj.backup(config_db);
		FileWriter fichero = null;
		try {
			fichero = new FileWriter(config_db);
			String sqlite_conf="<?php\n"
					+"return [\n"
					+"'class' => 'yii\\db\\Connection', \n"
					+"'dsn' => 'sqlite:config/"+db_name+"',\n"
					+"];";
			fichero.write(sqlite_conf);
			fichero.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Mover la BDD
		obj.executeCommand("mv "+path_proyect+db_name+" "+path_proyect+"proyect/config/");
	}
	
	/*
	 * GENERA EL MODELO DE LAS TABLAS
	 */
	public void genModel(){
		ExecuteShellComand obj= new ExecuteShellComand();
		String comando="bash "+path_proyect+"model.sh "+path_proyect;
		obj.executeCommand(comando);
		obj.executeCommand("mv "+path_proyect+"/*.php "+path_proyect+"/proyect/model/");
	}
	
	/*
	 * GENERA EL CRUD CON LAS VISTAS DE LOS SERVICIOS
	 */
	public void genCRUD(){
		ExecuteShellComand obj= new ExecuteShellComand();
		String comando="bash "+path_proyect+"crud.sh "+path_proyect;
		obj.executeCommand(comando);
		//CRUD VISTA
		/*
		 * Copia Controlador de la tabla que utiliza la vista
		 * Copia las vistas de los servicios del CRUD
		 */
		//obj.executeCommand("cp -r "+path_proyect+)
	}
	
	//public void genExtencion()
	
	//public void configure()
	
}