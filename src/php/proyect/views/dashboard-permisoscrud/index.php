<?php
namespace app\models;

//use kartik\grid\GridView; //new
use Yii;
use yii\helpers\Html;
use yii\grid\GridView;
use yii\data\ActiveDataProvider;


/* @var $this yii\web\View */
/* @var $dataProvider yii\data\ActiveDataProvider */
if(!Yii::$app->user->isGuest && Yii::$app->user->identity->id_rol == 1){
  $this->title = 'Dashboard Permisoscruds';
  $this->params['breadcrumbs'][] = $this->title;


  $request = Yii::$app->request;
  $id_dash = $request->get('id');

  if($id_dash== null){
    $id_dash=0;
  }

?>
  <div class="dashboard-permisoscrud-index">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Create Dashboard Permisoscrud', ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?php
    if($id_dash !=0){
      $results= DashboardPermisoscrud::find()->where(['id_dash'=>$id_dash]);
      $dataProvider = new ActiveDataProvider([
          'query' => $results,
      ]);
    }

    echo GridView::widget([
    'dataProvider' => $dataProvider,
    //'dataProvider' => $resultsProvider,
    'filterModel' => $searchModel,
    'columns' => [
        'id_dash',
        [                      // the owner name of the model
            'label' => 'clase',
            'value' => function ($data) {
                $nombre_pag=Dashboard::find()->select('nombre')->where(['id'=>$data->id_dash])->one();
                return $nombre_pag->nombre;
            },
        ],
        'id_rol',
        [                      // the owner name of the model
            'label' => 'Rol',
            'value' => function ($data) {
              if($data->id_rol){
                $nombre_rol=Roles::find()->select('rolname')->where(['id_rol'=>$data->id_rol])->one();
                return $nombre_rol->rolname;
              }else {
                return "none";
              }
            },
        ],
        'service',
        //'orderAmount',
        ['class' => 'yii\grid\ActionColumn'],
    ]
]);
    ?>




<?php
}else{
 ?>
 <div class="site-error">

     <h1>Forbidden (#403)</h1>

     <div class="alert alert-danger">
         You are not Admin to perform this action.    </div>

     <p>
         The above error occurred while the Web server was processing your request.
     </p>
     <p>
         Please contact us if you think this is a server error. Thank you.
     </p>

 </div>
<?php
}
 ?>
