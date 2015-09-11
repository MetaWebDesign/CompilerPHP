package compilerphp.actions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PHP{
	
	//String path_php="/CompilerPHP/src/php/";//RUTA DONDE ESTA EL CODIGO PHP BASE DEL SITIO WEB 
	static String path_proyect;//RUTA DONDE ESTA EL CODIGO DEL PROYECTO PHP A GENERAR
	static SQL modelo;
	
	/*
	 * CONSTRUCTOR DE LA CLASE
	 *  path : direccion del proyecto PHP
	 */
	public PHP(String path, SQL model){
		path_proyect=path; // RUTA DEL PROYECTO PHP
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
		ExecuteShellComand obj= new ExecuteShellComand();
		List <View> views=modelo.getViews();
		
		//ESCRITURA DEL PHP CON EL MODELO DE LAS VISTAS
		for(View view : views) {
			try {
				genModelView_(view, modelo.getTabla(view.getTabla()));//GENERO UN MODELO PARA UNA VISTAS DE LA BDD
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		obj.move(path_proyect+"*.php" , path_proyect+"proyect/models/");
	}
	
	//GENERA EL MODELO PARA LAS VISTAS EN LA BDD
	//public static void genModelView_(View view, Tabla tabla, String path_proyect) throws IOException{
	public static void genModelView_(View view, Tabla tabla) throws IOException{
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
	
	public void genCRUDView(){
		ExecuteShellComand obj= new ExecuteShellComand();
		List <View> views=modelo.getViews();
		
		//ESCRITURA DEL PHP CON EL MODELO DE LAS VISTAS
		for(View view : views) {
			genCRUDView_(view);//GENERO UN MODELO PARA UNA VISTAS DE LA BDD
		}
		obj.move(path_proyect+"*.php" , path_proyect+"proyect/models/");
	}
	
	public static void genCRUDView_(View view){
		FileWriter php_crud_view = null;
		String controler="<?php\n";
		controler=controler+"namespace app\\controllers;\n";
		controler=controler+"use Yii;\n";
		controler=controler+"use app\\models\\"+view.getTabla()+view.getNombre()+"view;\n";
		controler=controler+"use yii\\data\\ActiveDataProvider;\n";
		controler=controler+"use yii\\web\\Controller;\n";
		controler=controler+"use yii\\web\\NotFoundHttpException;\n";
		controler=controler+"use yii\\filters\\VerbFilter;\n";
		controler=controler+"/**\n";
		controler=controler+" * "+view.getTabla()+view.getNombre()+"Controller implements the CRUD actions for Cursos model.\n";
		controler=controler+" */\n";
		controler=controler+"class "+view.getTabla()+view.getNombre()+"Controller extends Controller\n";
		controler=controler+"{\n";
		controler=controler+"    public function behaviors()\n";
		controler=controler+"    {\n";
		controler=controler+"        return [\n";
		controler=controler+"            'verbs' => [\n";
		controler=controler+"                'class' => VerbFilter::className(),\n";
		controler=controler+"                'actions' => [\n";
		controler=controler+"                    'delete' => ['post'],\n";
		controler=controler+"                ],\n";
		controler=controler+"            ],\n";
		controler=controler+"        ];\n";
		controler=controler+"    }\n\n";	
		controler=controler+"   /**\n";
		controler=controler+" * Lists all "+view.getTabla()+view.getNombre()+" models.\n";
		controler=controler+" * @return mixed\n";
		controler=controler+" */\n";
		controler=controler+"public function actionIndex()\n";
		controler=controler+"{\n";
		controler=controler+"    $dataProvider = new ActiveDataProvider([\n";
		controler=controler+"        'query' => "+view.getTabla()+view.getNombre()+"view::find(),\n";
		controler=controler+"    ]);\n\n";
		controler=controler+"    return $this->render('index', [\n";
		controler=controler+"        'dataProvider' => $dataProvider,\n";
		controler=controler+"    ]);\n";
		controler=controler+"}\n";
		controler=controler+" /**\n";
		controler=controler+" * Displays a single "+view.getTabla()+view.getNombre()+" model.\n";
		controler=controler+" * @param integer $id\n";
		controler=controler+" * @return mixed\n";
		controler=controler+" */\n";
		controler=controler+"public function actionView($id)\n";
		controler=controler+"{\n";
		controler=controler+"    return $this->render('view', [\n";
		controler=controler+"        'model' => $this->findModel($id),\n";
		controler=controler+"    ]);\n";
		controler=controler+"}\n\n";
		controler=controler+"/**\n";
		controler=controler+" * Creates a new "+view.getTabla()+view.getNombre()+" model.\n";
		controler=controler+" * If creation is successful, the browser will be redirected to the 'view' page.\n";
		controler=controler+" * @return mixed\n";
		controler=controler+" */\n";
		controler=controler+"public function actionCreate()\n";
		controler=controler+"{\n";
		controler=controler+"    $model = new "+view.getTabla()+view.getNombre()+"view();\n";
		controler=controler+"    if ($model->load(Yii::$app->request->post()) && $model->save()) {\n";
		controler=controler+"        return $this->redirect(['view', 'id' => $model->id_curso]);\n";//OJO, LLAVE PRIMARIA!!!
		controler=controler+"    } else {\n";
		controler=controler+"        return $this->render('create', [\n";
		controler=controler+"            'model' => $model,\n";
		controler=controler+"        ]);\n";
		controler=controler+"    }\n";
		controler=controler+"}\n";
		controler=controler+"/**\n";
		controler=controler+" * Updates an existing "+view.getTabla()+view.getNombre()+" model.\n";
		controler=controler+" * If update is successful, the browser will be redirected to the 'view' page.\n";
		controler=controler+" * @param integer $id\n";
		controler=controler+" * @return mixed\n";
		controler=controler+" */\n";
		controler=controler+"public function actionUpdate($id)\n";
		controler=controler+"{\n";
		controler=controler+"    $model = $this->findModel($id);\n";
		controler=controler+"    if ($model->load(Yii::$app->request->post()) && $model->save()) {\n";
		controler=controler+"        return $this->redirect(['view', 'id' => $model->id_curso]);\n";//OJO, LLAVE PRIMARIA!!!
		controler=controler+"    } else {\n";
		controler=controler+"        return $this->render('update', [\n";
		controler=controler+"            'model' => $model,\n";
		controler=controler+"        ]);\n";
		controler=controler+"    }\n";
		controler=controler+"}\n";
		controler=controler+"/**\n";
		controler=controler+" * Deletes an existing "+view.getTabla()+view.getNombre()+" model.\n";
		controler=controler+" * If deletion is successful, the browser will be redirected to the 'index' page.\n";
		controler=controler+" * @param integer $id\n";
		controler=controler+" * @return mixed\n";
		controler=controler+" */\n";
		controler=controler+"public function actionDelete($id)\n";
		controler=controler+"{\n";
		controler=controler+"    $this->findModel($id)->delete();\n";
		controler=controler+"    return $this->redirect(['index']);\n";
		controler=controler+"}\n";
		controler=controler+"/**\n";
		controler=controler+" * Finds the "+view.getTabla()+view.getNombre()+" model based on its primary key value.\n";
		controler=controler+" * If the model is not found, a 404 HTTP exception will be thrown.\n";
		controler=controler+" * @param integer $id\n";
		controler=controler+" * @return "+view.getTabla()+view.getNombre()+" the loaded model\n";
		controler=controler+" * @throws NotFoundHttpException if the model cannot be found\n";
		controler=controler+" */\n";
		controler=controler+"protected function findModel($id)\n";
		controler=controler+"{\n";
		controler=controler+"    if (($model = Cursos::findOne($id)) !== null) {\n";
		controler=controler+"        return $model;\n";
		controler=controler+"    } else {\n";
		controler=controler+"        throw new NotFoundHttpException('The requested page does not exist.');\n";
		controler=controler+"    }\n";
		controler=controler+"}\n";
		
	    //ESCRITURA DEL PHP CON EL MODELO DE LA VISTA
	    php_crud_view = new FileWriter(path_proyect+view.getTabla()+view.getNombre()+"view.php");
	    php_crud_view.write(model_view);
	    php_crud_view.close();
	}
	
	//public void genExtencion()
	
	//public void configure()
	
}