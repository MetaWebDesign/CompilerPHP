package compilerphp.actions;

import java.io.FileWriter;
import java.io.IOException;

public class PHP_CRUDView{
	
	private View view;
	private SQL modelo;
	private String path_proyect_controller;
	private String path_proyect_view;
	private Tabla tabla;
	private String ruta;
	//static String controler;
	//static String index;
	
	public PHP_CRUDView(View view_, SQL modelo_, String path_proyect_){
		ExecuteShellComand obj= new ExecuteShellComand();
		this.view=view_;
		this.modelo=modelo_;
		this.path_proyect_controller=path_proyect_+"proyect/controllers/";
		this.path_proyect_view=path_proyect_+"proyect/views/";
		this.tabla=this.modelo.getTabla(view.getTabla());
		this.ruta=this.path_proyect_view+this.tabla.getNombre().toLowerCase()+this.view.getNombre().toLowerCase();
		obj.executeCommand("mkdir "+ruta);
		
	}


	public void writeControlador() throws IOException{
		//ExecuteShellComand obj= new ExecuteShellComand();
		FileWriter php_crud = null;
		//DEBE USAR LA TABLA NO LA VISTA!, PARA LOS SERVIVIOS DEL CRUD
		Atributo pk=this.tabla.getPrimaryKey();
		String controler="<?php\n\n";
		controler=controler+"\n";
		controler=controler+"namespace app\\controllers;\n";
		controler=controler+"\n";
		controler=controler+"use Yii;\n";
		controler=controler+"use app\\models\\"+this.tabla.getNombre()+this.view.getNombre()+";\n";
		controler=controler+"use app\\models\\"+this.tabla.getNombre()+"Search;\n";
		controler=controler+"use yii\\web\\Controller;\n";
		controler=controler+"use yii\\web\\NotFoundHttpException;\n";
		controler=controler+"use yii\\filters\\VerbFilter;\n";
		controler=controler+"\n";
		controler=controler+"use yii\\filters\\AccessControl;\n";
		controler=controler+"\n";
		controler=controler+"class "+this.tabla.getNombre()+this.view.getNombre()+"Controller extends Controller\n";
		controler=controler+"{\n";
		controler=controler+"		    protected $roles_= array();\n";
		controler=controler+"\n";
		controler=controler+"		    public function behaviors()\n";
		controler=controler+"		    {\n";
		controler=controler+"	        return [\n";
		controler=controler+"          'access'=>[\n";
		controler=controler+"              'class'=>AccessControl::className(),\n";
		controler=controler+"\n";
		controler=controler+"                  'rules'=>[\n";
		controler=controler+"                            [\n";
		controler=controler+"                                 'actions'=>[\n";
		controler=controler+"                                    'index'\n";
		controler=controler+"                                ],\n";
		controler=controler+"                                'allow'=>true,\n";
		controler=controler+"                                'matchCallback'=>function(){\n";
		controler=controler+"                                    if(!Yii::$app->user->isGuest){\n";
		controler=controler+"                                      return (Yii::$app->access->validate(Yii::$app->user->identity->id_rol, '"+this.tabla.getNombre().toLowerCase()+"', 'index'));\n";
		controler=controler+"                                    }\n";
		controler=controler+"                                    else{\n";
		controler=controler+"                                      false;\n";
		controler=controler+"                                    }\n";
		controler=controler+"                                }\n";
		controler=controler+"                            ],\n";
		controler=controler+"                            [\n";
		controler=controler+"                                'actions'=>[\n";
		controler=controler+"                                    'create'\n";
		controler=controler+"                                ],\n";
		controler=controler+"                                'allow'=>true,\n";
		controler=controler+"                                'matchCallback'=>function(){\n";
		controler=controler+"                                  if(!Yii::$app->user->isGuest){\n";
		controler=controler+"                                    return (Yii::$app->access->validate(Yii::$app->user->identity->id_rol, '"+this.tabla.getNombre().toLowerCase()+"', 'create'));\n";
		controler=controler+"                                  }\n";
		controler=controler+"                                  else{\n";
		controler=controler+"                                    false;\n";
		controler=controler+"                                  }\n";
		controler=controler+"                                }\n";
		controler=controler+"                            ],\n";
		controler=controler+"                            [\n";
		controler=controler+"                                'actions'=>[\n";
		controler=controler+"                                    'update',\n";
		controler=controler+"                                ],\n";
		controler=controler+"                                'allow'=>true,\n";
		controler=controler+"                                'matchCallback'=>function(){\n";
		controler=controler+"                                  if(!Yii::$app->user->isGuest){\n";
		controler=controler+"                                    return (Yii::$app->access->validate(Yii::$app->user->identity->id_rol, '"+this.tabla.getNombre().toLowerCase()+"', 'update'));\n";
		controler=controler+"                                  }\n";
		controler=controler+"                                  else{\n";
		controler=controler+"                                    false;\n";
		controler=controler+"                                  }\n";
		controler=controler+"                                }\n";
		controler=controler+"                            ],\n";
		controler=controler+"                            [\n";
		controler=controler+"                                'actions'=>[\n";
		controler=controler+"                                   'delete',\n";
		controler=controler+"                                ],\n";
		controler=controler+"                                'allow'=>true,\n";
		controler=controler+"                                'matchCallback'=>function(){\n";
		controler=controler+"                                  if(!Yii::$app->user->isGuest){\n";
		controler=controler+"                                    return (Yii::$app->access->validate(Yii::$app->user->identity->id_rol, '"+this.tabla.getNombre().toLowerCase()+"', 'delete'));\n";
		controler=controler+"                                  }\n";
		controler=controler+"                                  else{\n";
		controler=controler+"                                    false;\n";
		controler=controler+"                                  }\n";
		controler=controler+"                                }\n";
		controler=controler+"                            ],\n";
		controler=controler+"                            [\n";
		controler=controler+"                                'actions'=>[\n";
		controler=controler+"                                    'view'\n";
		controler=controler+"                                ],\n";
		controler=controler+"                                'allow'=>true,\n";
		controler=controler+"                                'matchCallback'=>function(){\n";
		controler=controler+"                                  if(!Yii::$app->user->isGuest){\n";
		controler=controler+"                                    return (Yii::$app->access->validate(Yii::$app->user->identity->id_rol, '"+this.tabla.getNombre().toLowerCase()+"', 'view'));\n";
		controler=controler+"                                  }\n";
		controler=controler+"                                  else{\n";
		controler=controler+"                                    false;\n";
		controler=controler+"                                  }\n";
		controler=controler+"                                }\n";
		controler=controler+"                            ],\n";
		controler=controler+"                        ],\n";
		controler=controler+"                    ],\n";
		controler=controler+"		                  'verbs' => [\n";
		controler=controler+"		                      'class' => VerbFilter::className(),\n";
		controler=controler+"		                      'actions' => [\n";
		controler=controler+"		                          'delete' => ['post'],\n";
		controler=controler+"		                      ],\n";
		controler=controler+"		                  ],\n";
		controler=controler+"		              ];\n";
		controler=controler+"\n";
		controler=controler+"		    }\n";
		controler=controler+"\n";
		controler=controler+"		    public function actionIndex()\n";
		controler=controler+"		    {\n";
		controler=controler+"		        $searchModel = new "+this.tabla.getNombre()+"Search();\n";
		controler=controler+"		        $dataProvider = $searchModel->search(Yii::$app->request->queryParams);\n";
		controler=controler+"\n";
		controler=controler+"		        return $this->render('index', [\n";
		controler=controler+"		            'searchModel' => $searchModel, \n";
		controler=controler+"		            'dataProvider' => $dataProvider,\n";
		controler=controler+"		        ]);\n";
		controler=controler+"		    }\n";
		controler=controler+"\n";
		controler=controler+"		    public function actionView($id)\n";
		controler=controler+"		    {\n";
		controler=controler+"		        return $this->render('view', [\n";
		controler=controler+"		            'model' => $this->findModel($id),\n";
		controler=controler+"		        ]);\n";
		controler=controler+"		    }\n";
		controler=controler+"\n";
		controler=controler+"		    public function actionCreate()\n";
		controler=controler+"		    {\n";
		controler=controler+"		        $model = new "+this.tabla.getNombre()+this.view.getNombre()+"();\n";
		controler=controler+"\n";
		controler=controler+"		        if ($model->load(Yii::$app->request->post()) && $model->save()) {\n";
		controler=controler+"		            return $this->redirect(['view', 'id' => $model->"+pk.getNombre()+"]);\n";
		controler=controler+"		        } else {\n";
		controler=controler+"		            return $this->render('create', [\n";
		controler=controler+"		                'model' => $model,\n";
		controler=controler+"		            ]);\n";
		controler=controler+"		        }\n";
		controler=controler+"		    }\n";
		controler=controler+"\n";
		controler=controler+"		    public function actionUpdate($id)\n";
		controler=controler+"		    {\n";
		controler=controler+"		        $model = $this->findModel($id);\n";
		controler=controler+"\n";
		controler=controler+"		        if ($model->load(Yii::$app->request->post()) && $model->save()) {\n";
		controler=controler+"		            return $this->redirect(['view', 'id' => $model->"+pk.getNombre()+"]);\n";
		controler=controler+"		        } else {\n";
		controler=controler+"		            return $this->render('update', [\n";
		controler=controler+"		                'model' => $model,\n";
		controler=controler+"		            ]);\n";
		controler=controler+"		        }\n";
		controler=controler+"		    }\n";
		controler=controler+"\n";
		controler=controler+"		    public function actionDelete($id)\n";
		controler=controler+"		    {\n";
		controler=controler+"		        $this->findModel($id)->delete();\n";
		controler=controler+"\n";
		controler=controler+"		        return $this->redirect(['index']);\n";
		controler=controler+"		    }\n";
		controler=controler+"\n";
		controler=controler+"		    protected function findModel($id)\n";
		controler=controler+"		    {\n";
		controler=controler+"			        if (($model = "+this.tabla.getNombre()+"::findOne($id)) !== null) {\n";
		controler=controler+"			            return $model;\n";
		controler=controler+"			        } else {\n";
		controler=controler+"			            throw new NotFoundHttpException('The requested page does not exist.');\n";
		controler=controler+"			        }\n";
		controler=controler+"		    }\n";
		controler=controler+"}\n";

		//ESCRITURA DEL CONTROLADOR DE LA VISTA
		php_crud = new FileWriter(path_proyect_controller+this.view.getTabla()+this.view.getNombre()+"Controller.php");
		php_crud.write(controler);
		php_crud.close();
	}
	
	public void writeIndex() throws IOException{
		FileWriter php_crud_view = null;
		String index="<?php\n";
		index=index+"namespace app\\models;\n";
		index=index+"\n";
		index=index+"use Yii;\n";
		index=index+"use yii\\helpers\\Html;\n";
		index=index+"use yii\\grid\\GridView;\n";
		index=index+"use yii\\data\\ActiveDataProvider;\n";
		index=index+"\n";
		index=index+"if(!Yii::$app->user->isGuest && Yii::$app->user->identity->id_rol == 1){\n";
		index=index+"						  $this->title = '"+this.tabla.getNombre()+this.view.getNombre()+"';\n";
		index=index+"						  $this->params['breadcrumbs'][] = $this->title;\n";
		index=index+"				?>\n";
		index=index+"						  <div class=\""+this.tabla.getNombre()+this.view.getNombre()+"-index\">\n";
		index=index+"\n";
		index=index+"						    <h1><?= Html::encode($this->title) ?></h1>\n";
		index=index+"\n";
		index=index+"						    <p>\n";
		index=index+"						        <?= Html::a('Create "+this.tabla.getNombre()+" "+this.view.getNombre()+"', ['create'], ['class' => 'btn btn-success']) ?>\n";
		index=index+"						    </p>\n";
		index=index+"						    <?php\n";
		index=index+"						    $results= "+this.tabla.getNombre()+this.view.getNombre()+"::find();\n";
		index=index+"				    $resultsProvider = new ActiveDataProvider([\n";
		index=index+"						        'query' => $results,\n";
		index=index+"						    ]);\n";
		index=index+"						    echo GridView::widget([\n";
		index=index+"						    'dataProvider' => $resultsProvider,\n";
		index=index+"						    'filterModel' => $searchModel,\n";
		index=index+"						    'columns' => [\n";
												for(Atributo a : this.tabla.getAtributos()){
		index=index+"						        '"+a.getNombre()+"',\n";
												}
												for(ForeignKey fk : this.tabla.getForeignKeys()){
		index=index+"						        '"+fk.getNombre()+"',\n";
												}
		index=index+"						        '"+this.view.getNombre()+"',\n";
		//index=index+"				        ['class' => 'yii\\grid\\ActionColumn'],\n";
		index=index+"						    ]\n";
		index=index+"						]);\n";
		index=index+"						    ?>\n";
		index=index+"						<?php\n";
		index=index+"						}else{\n";
		index=index+"						 ?>\n";
		index=index+"						 <div class=\"site-error\">\n";
		index=index+"\n";
		index=index+"						     <h1>Forbidden (#403)</h1>\n";
		index=index+"\n";
		index=index+"						     <div class=\"alert alert-danger\">\n";
		index=index+"						         You are not Admin to perform this action.    </div>\n";
		index=index+"\n";
		index=index+"						     <p>\n";
		index=index+"						         The above error occurred while the Web server was processing your request.\n";
		index=index+"						     </p>\n";
		index=index+"						     <p>\n";
		index=index+"						         Please contact us if you think this is a server error. Thank you.\n";
		index=index+"						     </p>\n";
		index=index+"\n";
		index=index+"						 </div>\n";
		index=index+"						<?php\n";
		index=index+"						}\n";
		index=index+"						 ?>\n";

		//ESCRITURA DE LA VISTA INDEX
		php_crud_view = new FileWriter(ruta+"/index.php");
		php_crud_view.write(index);
		php_crud_view.close();
		
	}
	
	
	public void create() throws IOException{
		FileWriter php_crud_create = null;
		String create="<?php\n";
		create=create+"Yii::$app->response->redirect(array('"+this.tabla.getNombre().toLowerCase()+"/create'));\n";
		create=create+"?>\n";
		//ESCRITURA DE LA VISTA INDEX
		String ruta=this.path_proyect_view+this.tabla.getNombre().toLowerCase()+this.view.getNombre().toLowerCase();
		php_crud_create = new FileWriter(ruta+"/create.php");
		php_crud_create.write(create);
		php_crud_create.close();
	}

}