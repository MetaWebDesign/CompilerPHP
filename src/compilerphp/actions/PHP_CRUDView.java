package compilerphp.actions;

import java.io.FileWriter;
import java.io.IOException;

public class PHP_CRUDView{
	
	static View view;
	static SQL modelo;
	static String path_proyect;
	
	public PHP_CRUDView(View view_, SQL modelo_, String path_proyect_){
		view=view_;
		modelo=modelo_;
	}
	
	public void write() throws IOException{ {
		
			//DEBE USAR LA TABLA NO LA VISTA!, PARA LOS SERVIVIOS DEL CRUD
			Tabla tabla=modelo.getTabla(view.getTabla());
			Atributo pk=tabla.getPrimaryKey();
			FileWriter php_crud_view = null;
			String controler="<?php\n\n";
			controler=controler+"namespace app\\controllers;\n\n";
			controler=controler+"use Yii;\n";
			//controler=controler+"use app\\models\\"+view.getTabla()+view.getNombre()+"view;\n";
			controler=controler+"use app\\models\\"+view.getTabla()+";\n";
			controler=controler+"use yii\\data\\ActiveDataProvider;\n";
			controler=controler+"use yii\\web\\Controller;\n";
			controler=controler+"use yii\\web\\NotFoundHttpException;\n";
			controler=controler+"use yii\\filters\\VerbFilter;\n\n";
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
			//VISTAS
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
			//VIEW
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
			//CREATE
			controler=controler+"public function actionCreate()\n";
			controler=controler+"{\n";
			controler=controler+"    $model = new "+view.getTabla()+"();\n";
			controler=controler+"    if ($model->load(Yii::$app->request->post()) && $model->save()) {\n";
			controler=controler+"        return $this->redirect(['view', 'id' => $model->"+pk+"]);\n";//OJO, LLAVE PRIMARIA!!!
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
			//UPDATE
			controler=controler+"public function actionUpdate($id)\n";
			controler=controler+"{\n";
			controler=controler+"    $model = $this->findModel($id);\n";
			controler=controler+"    if ($model->load(Yii::$app->request->post()) && $model->save()) {\n";
			controler=controler+"        return $this->redirect(['view', 'id' => $model->"+pk.getNombre()+"]);\n";//OJO, LLAVE PRIMARIA!!!
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
			//DELETE
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
		    php_crud_view = new FileWriter(path_proyect+view.getTabla()+view.getNombre()+"Controller.php");
		    php_crud_view.write(controler);
		    php_crud_view.close();
		}
	}
}