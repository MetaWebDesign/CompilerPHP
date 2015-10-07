<?php
namespace app\models;

//use kartik\grid\GridView; //new
use Yii;
use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $dataProvider yii\data\ActiveDataProvider */
if(!Yii::$app->user->isGuest && Yii::$app->user->identity->id_rol == 1){
  $this->title = 'Dashboard Permisoscruds';
  $this->params['breadcrumbs'][] = $this->title;
?>
  <div class="dashboard-permisoscrud-index">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Create Dashboard Permisoscrud', ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?php
    echo GridView::widget([
    'dataProvider' => $dataProvider,
    'filterModel' => $searchModel,
    'columns' => [
        ['class' => 'yii\grid\SerialColumn'],
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
                $nombre_rol=Roles::find()->select('rolname')->where(['id_rol'=>$data->id_rol])->one();
                return $nombre_rol->rolname;
            },
        ],
        'service',
        //'orderAmount',
        ['class' => 'yii\grid\ActionColumn'],
    ]
]);
    ?>

    <?php /*
      GridView::widget([
        'dataProvider' => $dataProvider,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            //'id_permiso',
            //'id_dash',
            [                      // the owner name of the model
                'label' => 'Clase',
                'value' => function ($data) {
                    $nombre_pag=Dashboard::find()->select('nombre')->where(['id'=>$data->id_dash])->one();
                    return $nombre_pag->nombre;
                },
            ],
            //'id_rol',
            [                      // the owner name of the model
                'label' => 'Clase',
                'value' => function ($data) {
                    $nombre_rol=Roles::find()->select('rolname')->where(['id_rol'=>$data->id_rol])->one();
                    return $nombre_rol->rolname;
                },
            ],

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]);
    */
    ?>


<?php
  $nombre_pag=Dashboard::find()->select('nombre')->where(['id'=>1])->one();
  echo "<h2>$nombre_pag->nombre</h2>";
?>

</div>
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
