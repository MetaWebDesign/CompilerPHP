package compilerphp.actions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Yii{
	
	static SQL model;
	static String path;
	
	public Yii(SQL model_, String path_){
		model=model_;
		path=path_;
	}
	
	public void model() throws IOException{
		//GENERO EL CODIGO SQL
		List <Tabla> tablas = model.getTablas();//TABLAS DE LA BDD PARA GENERAR MODELO Y CRUD
		ExecuteShellComand obj= new ExecuteShellComand();
		
		//ARCHIVO A GENERAR
		FileWriter script_model = null;//SCRIPT PARA CREAR MODELO USANDO YII MVC
		
		//ESCRITURA DEL SCRIPT PARA LA CREACION DE LOS MODELOS
		script_model = new FileWriter(path+"model.sh");
		script_model.write("cd $1/proyect/\n");
		for(Tabla tabla : tablas) {
			script_model.write("./yii gii/model --tableName="+tabla.getNombre()+" --modelClass="+tabla.getNombre()+" --interactive=0\n");
		}
		
		//ESCRITURA DEL SCRIPT PARA LA CREACION DE LOS MODELOS DASHBOARD
		script_model.write("./yii gii/model --tableName=Dashboard --modelClass=Dashboard --interactive=0\n");
		//script_model.write("./yii gii/model --tableName=DashboardConf --modelClass=DashboardConf --interactive=0\n"); //LO CARGA POR DEFECTO
		//script_model.write("./yii gii/model --tableName=Views --modelClass=Views --interactive=0\n");//LO CARGA POR DEFECTO
		script_model.write("./yii gii/model --tableName=TypePresentation --modelClass=TypePresentation --interactive=0\n");
		script_model.write("./yii gii/model --tableName=ViewAttribute --modelClass=ViewAttribute --interactive=0\n");
		script_model.write("./yii gii/model --tableName=DashboardError --modelClass=DashboardError --interactive=0\n");
		//script_model.write("./yii gii/model --tableName=DashboardMedia --modelClass=DashboardMedia --interactive=0\n");//LO CARGA POR DEFECTO
		script_model.write("./yii gii/model --tableName=DashboardPermisoscrud --modelClass=DashboardPermisoscrud --interactive=0\n");//LO CARGA POR DEFECTO
		script_model.close();
		
		//DOY PERMISOS AL SCRIPT DE EJECUCIÓN
		obj.executeCommand("chmod +x "+path+"*");
		
	}	
	
	public void crud() throws IOException{
		//GENERO EL CODIGO SQL
		List <Tabla> tablas = model.getTablas();//TABLAS DE LA BDD PARA GENERAR MODELO Y CRUD
		ExecuteShellComand obj= new ExecuteShellComand();
		
		//ARCHIVO A GENERAR
		FileWriter script_crud = null;//SCRIPT PARA CREAR CURD + VISTAS CURD USANDO YII MVC
		
		//ESCRITURA DEL SCRIPT PARA LA CREACION DEL CRUD
		script_crud = new FileWriter(path+"crud.sh");
		script_crud.write("cd $1/proyect/\n");
		for(Tabla tabla : tablas) {
			script_crud.write("./yii gii/crud --interactive=0 --modelClass=\\\\app\\\\models\\\\"+tabla.getNombre()+" --controllerClass=\\\\app\\\\controllers\\\\"+tabla.getNombre()+"Controller\n");
		}
		//ESCRITURA DEL SCRIPT PARA LA CREACION DEL CRUD DASHBOARD
		script_crud.write("./yii gii/crud --interactive=0 --modelClass=\\\\app\\\\models\\\\Dashboard --controllerClass=\\\\app\\\\controllers\\\\DashboardController\n");
		//script_crud.write("./yii gii/crud --interactive=0 --modelClass=\\\\app\\\\models\\\\DashboardConf --controllerClass=\\\\app\\\\controllers\\\\DashboardConfController\n");//LO CARGA POR DEFECTO
		//script_crud.write("./yii gii/crud --interactive=0 --modelClass=\\\\app\\\\models\\\\Views --controllerClass=\\\\app\\\\controllers\\\\ViewsController\n");//LO CARGA POR DEFECTO
		script_crud.write("./yii gii/crud --interactive=0 --modelClass=\\\\app\\\\models\\\\TypePresentation  --controllerClass=\\\\app\\\\controllers\\\\TypePresentationController\n");
		script_crud.write("./yii gii/crud --interactive=0 --modelClass=\\\\app\\\\models\\\\ViewAttribute --controllerClass=\\\\app\\\\controllers\\\\ViewAttributeController\n");
		script_crud.write("./yii gii/crud --interactive=0 --modelClass=\\\\app\\\\models\\\\DashboardError --controllerClass=\\\\app\\\\controllers\\\\DashboardErrorController\n");
		//script_crud.write("./yii gii/crud --interactive=0 --modelClass=\\\\app\\\\models\\\\DashboardMedia --controllerClass=\\\\app\\\\controllers\\\\DashboardMediaController\n");//LO CARGA POR DEFECTO
		script_crud.write("./yii gii/crud --interactive=0 --modelClass=\\\\app\\\\models\\\\DashboardPermisoscrud --controllerClass=\\\\app\\\\controllers\\\\DashboardPermisoscrudController\n");//LO CARGA POR DEFECTO
		script_crud.close();
		
		//DOY PERMISOS AL SCRIPT DE EJECUCIÓN
		obj.executeCommand("chmod +x "+path+"*");		
	}
	

}