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
			    		if(atributo.getPrimaryKey() == true){
			    			linea_sql=linea_sql+" PRIMARY KEY";
			    		}
			    	}
			    	coma++;
			    }
			    
		    	//IMPRIMO LLAVES FORANEAS
			    coma=0;
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
		return dataBase;
	}
	
	//GENERO LA BASE DE DATOS 
	public static void createDB(SQL model, String path, String file) throws IOException{
		System.out.println("CREATEDB");
		//GENERO EL CODIGO SQL
		List <String> dataBase=SQLite.genSQL(model, path);//CREO UN STRING CON LA SINTAXIS SQL PARA CREAR LAS TABLAS, VISTAS Y LLAVES (PK Y FK)
		List <Tabla> tablas = model.getTablas();//TABLAS DE LA BDD PARA GENERAR MODELO Y CRUD
		List <View> views = model.getViews(); //VISTAS DE LA BDD PARA GENERAR MODELO Y CRUD
		ExecuteShellComand obj= new ExecuteShellComand();
		FileWriter script_bdd = null;
		FileWriter script_model = null;
		FileWriter script_crud = null;
		int stop=file.indexOf(".");
		String nombreScriptBD=file.substring(0, stop);
		
		//CREO LA CARPETA DEL PROYECTO
		System.out.println("CREO CARPETA DEL PROYECTO");
		obj.executeCommand("mkdir "+path+"/PHP");
		
		System.out.println("CREO SCRTIP PARA LA BDD");
		//ESCRITURA DEL SCRIPT BASH PARA LA CREACION DE LA BDD
		script_bdd = new FileWriter(path+"/PHP/"+nombreScriptBD+".sh");
		for(String sql_line : dataBase){
			script_bdd.write("sqlite3 "+path+"/PHP/"+nombreScriptBD+".db \""+sql_line+"\"\n");
			System.out.println("sqlite3-> "+sql_line);
		}
		script_bdd.close();
		
		System.out.println("CREO SCRIPT PARA EL MODELO");
		//ESCRITURA DEL SCRIPT PARA LA CREACION DE LOS MODELOS
		script_model = new FileWriter(path+"/PHP/model.sh");
		script_model.write("cd $1/proyect/\n");
		for(Tabla tabla : tablas) {
			script_model.write("./yii gii/model --tableName="+tabla.getNombre()+" --modelClass="+tabla.getNombre()+" --interactive=0\n");
		}
		script_model.close();
		
		System.out.println("CREO SCRIPT PARA EL CRUD");
		//ESCRITURA DEL SCRIPT PARA LA CREACION DEL CRUD
		script_crud = new FileWriter(path+"/PHP/crud.sh");
		script_crud.write("cd $1/proyect/\n");
		for(Tabla tabla : tablas) {
			script_crud.write("./yii gii/crud --interactive=0 --modelClass=\\\\app\\\\models\\\\"+tabla.getNombre()+" --controllerClass=\\\\app\\\\controllers\\\\"+tabla.getNombre()+"Controller\n");
		}
		script_crud.close();
		
		
		System.out.println("CREO PHP CON EL MODELO DE LAS VISTAS");
		//ESCRITURA DEL PHP CON EL MODELO DE LAS VISTAS
		for(View view : views) {
				modelView(view, model.getTabla(view.getTabla()), path+"/PHP/");//GENERO EL MODELO PARA LAS VISTAS DE LA BDD
		}
		
		//CRUD VISTAS
			//COPY CRUD TABLE ORI
		
		//DOY PERMISOS AL SCRIPT DE EJECUCIÓN
		obj.executeCommand("chmod +x "+path+"/PHP/*");
		
		//EJECUTO EL SCRIPT PARA CREAR LA BDD
		obj.executeCommand("bash "+path+"/PHP/"+nombreScriptBD+".sh");
	}

	//GENERA EL MODELO PARA LAS VISTAS EN LA BDD
	public static void modelView(View view, Tabla tabla, String path_proyect) throws IOException{
		FileWriter php_model_view = null;
		String atributo_model_name;
		String model_view="<?php\n";
		List <Atributo> atributos=tabla.getAtributos();
		model_view=model_view+"namespace app\\models;\n";
		model_view=model_view+"use Yii;\n";
		model_view=model_view+"/**\n";
		model_view=model_view+" * This is the model class for table \""+view.getTabla()+view.getNombre()+"view\".\n";
		model_view=model_view+" *\n";
		model_view=model_view+" * @property integer $id\n";
		model_view=model_view+" * @property string $classname\n";
		model_view=model_view+" * @property string $max_student\n";
		model_view=model_view+" * @property string $descrip\n";
		model_view=model_view+" * @property string $profesor\n";
		model_view=model_view+" */\n";
		model_view=model_view+"class Ramosview extends \\yii\\db\\ActiveRecord\n";
		model_view=model_view+"{\n";
		model_view=model_view+"public static function tableName()\n";
		model_view=model_view+"{\n";
		model_view=model_view+"    return '"+view.getTabla()+view.getNombre()+"view';\n";
		model_view=model_view+"}\n";
		model_view=model_view+"public function rules()\n";
		model_view=model_view+"{\n";
		model_view=model_view+"    return [\n";
		for(Atributo atributo : atributos) {
			if(!atributo.getType().equals("autoincremental")){
				String typeData=atributo.getType();
				if(typeData.equals("varchar(10)")){
					typeData="'string', 'max' => 10";
				}
				if(typeData.equals("varchar(30)")){
					typeData="'string', 'max' => 30";
				}
				if(typeData.equals("varchar(50)")){
					typeData="'string', 'max' => 50";
				}
				if(typeData.equals("text")){
					typeData="'string'";
				}
				model_view=model_view+"        [['"+atributo.getNombre()+"'], "+typeData+"],\n";
			}
		}
		model_view=model_view+"        [['"+view.getNombre()+"'], '"+view.getType()+"'],\n";
	    model_view=model_view+"    ];\n";
	    model_view=model_view+"}\n";
	    model_view=model_view+"public function attributeLabels()\n";
	    model_view=model_view+"{\n";
	    model_view=model_view+"    return [\n";
		for(Atributo atributo : atributos) {
				atributo_model_name=atributo.getNombre().substring(0, 1).toUpperCase() +atributo.getNombre().substring(1);
				model_view=model_view+"        '"+atributo.getNombre()+"' => '"+atributo_model_name+"',\n";
		}
		atributo_model_name=view.getNombre().substring(0, 1).toUpperCase() +view.getNombre().substring(1);
		model_view=model_view+"        '"+view.getNombre()+"' => '"+atributo_model_name+"',\n";
	   model_view=model_view+"    ];\n";
	   model_view=model_view+" }\n";
	   model_view=model_view+"}\n";
	   
	   //ESCRITURA DEL PHP CON EL MODELO DE LA VISTA
	   php_model_view = new FileWriter(path_proyect+view.getTabla()+view.getNombre()+"view.php");
	   php_model_view.write(model_view);
	   php_model_view.close();
	}
	
	/*
	public static void main(String[] args) throws IOException {
		sqlite_execute("Create table test2(nombre text);", "/home/leo/runtime-EclipseApplication/Elearning");
	}*/
	
}