<?php

namespace app\controllers;

use Yii;
use app\models\DashboardMedia;
use yii\data\ActiveDataProvider;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;

use yii\filters\AccessControl;

use yii\web\UploadedFile; //new
use app\models\UploadForm;//new
/**
 * DashboardMediaController implements the CRUD actions for DashboardMedia model.
 */
class DashboardMediaController extends Controller
{
  public function behaviors()
  {
      return [
          'access'=>[
              'class'=>AccessControl::className(),
              'rules'=>[
                  [
                      'actions'=>[
                          'index',
                          'create',
                          'update',
                          'delete',
                          'view'
                      ],
                      'allow'=>true,
                      'roles' => ['@'],
                  ],
              ],
          ],
          'verbs' => [
              'class' => VerbFilter::className(),
              'actions' => [
                  'delete' => ['post'],
              ],
          ],
      ];
  }

    /**
     * Lists all DashboardMedia models.
     * @return mixed
     */
    public function actionIndex()
    {
        $dataProvider = new ActiveDataProvider([
            'query' => DashboardMedia::find(),
        ]);

        return $this->render('index', [
            'dataProvider' => $dataProvider,
        ]);
    }

    /**
     * Displays a single DashboardMedia model.
     * @param integer $id
     * @return mixed
     */
    public function actionView($id)
    {
        return $this->render('view', [
            'model' => $this->findModel($id),
        ]);
    }

    /**
     * Creates a new DashboardMedia model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return mixed
     */
    public function actionCreate()
    {
        $model = new DashboardMedia();

        if ($model->load(Yii::$app->request->post())){

            $model->filename=UploadedFile::getInstance($model, 'filename');
            $model->extencion=$model->filename->extension;
            $model->id_autor=Yii::$app->user->identity->id_user;
            $model->Fecha=date('Y-m-d H:i:s');
            if( $model->save()) {

              $model->filename = UploadedFile::getInstance($model, 'filename');
              if($model->upload()){
                return $this->redirect(['view', 'id' => $model->id_media]);
              }
            }
            else {
              # code...
              return $this->render('create', [
                  'model' => $model,
              ]);
            }
        } else {
            return $this->render('create', [
                'model' => $model,
            ]);
        }
    }


    /**
     * Updates an existing DashboardMedia model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param integer $id
     * @return mixed
     */
    public function actionUpdate($id)
    {
        $model = $this->findModel($id);

        if ($model->load(Yii::$app->request->post())){

            $model->filename=UploadedFile::getInstance($model, 'filename');
            $model->extencion=$model->filename->extension;
            $model->id_autor=Yii::$app->user->identity->id_user;
            $model->Fecha=date('Y-m-d H:i:s');
            if( $model->save()) {

              $model->filename = UploadedFile::getInstance($model, 'filename');
              if($model->upload()){
                return $this->redirect(['view', 'id' => $model->id_media]);
              }
            }
            else {
              # code...
              return $this->render('update', [
                  'model' => $model,
              ]);
            }
        } else {
            return $this->render('update', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Deletes an existing DashboardMedia model.
     * If deletion is successful, the browser will be redirected to the 'index' page.
     * @param integer $id
     * @return mixed
     */
    public function actionDelete($id)
    {
        $this->findModel($id)->delete();

        return $this->redirect(['index']);
    }

    /**
     * Finds the DashboardMedia model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param integer $id
     * @return DashboardMedia the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id)
    {
        if (($model = DashboardMedia::findOne($id)) !== null) {
            return $model;
        } else {
            throw new NotFoundHttpException('The requested page does not exist.');
        }
    }


}
