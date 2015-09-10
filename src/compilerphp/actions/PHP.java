package compilerphp.actions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PHP{
	
	//String path_php="/CompilerPHP/src/php/";//RUTA DONDE ESTA EL CODIGO PHP BASE DEL SITIO WEB 
	String path_proyect;//RUTA DONDE ESTA EL CODIGO DEL PROYECTO PHP A GENERAR
	static SQL modelo;
	
	/*
	 * CONSTRUCTOR DE LA CLASE
	 *  path : direccion del proyecto PHP
	 */
	public PHP(String path, SQL model){
		this.path_proyect=path; // RUTA DEL PROYECTO PHP
		modelo=model;//MODELO DE LA BASE DE DATOS
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
		obj.move(path_proyect+"*.php" , path_proyect+"proyect/models/");
		//BORRAR BASH
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
		//BORRAR BASH
	}
	
	//public static void genModelView(SQL model){
	public void genModelView(){
		
		List <View> views=modelo.getViews();
		
		//ESCRITURA DEL PHP CON EL MODELO DE LAS VISTAS
		for(View view : views) {
			try {
				genModelView_(view, modelo.getTabla(view.getTabla()), path_proyect);//GENERO EL MODELO PARA LAS VISTAS DE LA BDD
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//GENERA EL MODELO PARA LAS VISTAS EN LA BDD
	//public static void genModelView_(View view, Tabla tabla, String path_proyect) throws IOException{
	public static void genModelView_(View view, Tabla tabla, String path_proyect) throws IOException{
		FileWriter php_model_view = null;
		String atributo_model_name;
		String model_view="<?php\n";
		List <Atributo> atributos=tabla.getAtributos();
		model_view=model_view+"namespace app\\models;\n";
		model_view=model_view+"use Yii;\n";
		model_view=model_view+"/**\n";
		model_view=model_view+" * This is the model class for table \""+view.getTabla()+view.getNombre()+"view\".\n";
		model_view=model_view+" *\n";
		for(Atributo atributo : atributos) {
			model_view=model_view+" * @property "+atributo.getType()+" "+atributo.getNombre()+"\n";
		}
		model_view=model_view+" * @property "+view.getType()+" "+view.getNombre()+"\n";
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
	
	//public void genExtencion()
	
	//public void configure()
	
}