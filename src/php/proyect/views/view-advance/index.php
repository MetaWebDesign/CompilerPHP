<?php
namespace app\models;
use Yii;
use yii\helpers\Html;
use yii\grid\GridView;
use yii\data\ActiveDataProvider;

/* @var $this yii\web\View */
/* @var $searchModel app\models\ViewAdvanceSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'View Advances';
$this->params['breadcrumbs'][] = $this->title;

//CAPTURA ID DE LA VISTA
$request = Yii::$app->request;
$id_view = $request->get('id');

?>
<div class="view-advance-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

        <?= Html::a('Create View Advance', ['create'], ['class' => 'btn btn-success']) ?>


<?php
if($id_view){
    $url="index.php?r=views/update&id=$id_view";
    echo "<a href=\"$url\" class=\"btn btn-info\" role=\"button\">Back </a>";

    $url2="index.php?r=views%2Fview&id=$id_view";
    echo "<a href=\"$url2\" class=\"btn btn-warning\" role=\"button\"> Ver</a>";
?>
    <?php
    $results= ViewAdvance::find()->where(['id_vista'=>$id_view]);
    $resultsProvider = new ActiveDataProvider([
        'query' => $results,
    ]);
    ?>

    <?= GridView::widget([
        'dataProvider' => $resultsProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            //'id',
            //'id_vista',
            [                      // the owner name of the model
                'label' => 'Vista',
                'value' => function ($data) {
                    $vista=Views::find()->where(['id_view'=>$data->id_vista])->one();
                    return $vista->title;
                },
            ],
            //'id_clase',
            [                      // the owner name of the model
                'label' => 'Clase',
                'value' => function ($data) {
                    $clase=Dashboard::find()->where(['id'=>$data->id_clase])->one();
                    return $clase->nombre;
                },
            ],
            //'id_atributo',
            [                      // the owner name of the model
                'label' => 'Atributo',
                'value' => function ($data) {
                    $atributo=ClassAtributo::find()->where(['id_atributo'=>$data->id_atributo, 'id_clase'=>$data->id_clase])->one();
                    return $atributo->nombre;
                },
            ],
            'typePresentation',
            // 'x_position',
            // 'y_position',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>
<?php
}else{
  $url="index.php?r=views/index";
  echo "<a href=\"$url\" class=\"btn btn-info\" role=\"button\">Back </a>";
 ?>
 <?= GridView::widget([
     'dataProvider' => $dataProvider,
     'filterModel' => $searchModel,
     'columns' => [
         ['class' => 'yii\grid\SerialColumn'],

         //'id',
         //'id_vista',
         [                      // the owner name of the model
             'label' => 'Vista',
             'value' => function ($data) {
                 $vista=Views::find()->where(['id_view'=>$data->id_vista])->one();
                 return $vista->title;
             },
         ],
         //'id_clase',
         [                      // the owner name of the model
             'label' => 'Clase',
             'value' => function ($data) {
                 $clase=Dashboard::find()->where(['id'=>$data->id_clase])->one();
                 return $clase->nombre;
             },
         ],
         'id_atributo',

         [                      // the owner name of the model
             'label' => 'Atributo',
             'value' => function ($data) {
                 $atributo=ClassAtributo::find()->where(['id_atributo'=>$data->id_atributo, 'id_clase'=>$data->id_clase])->one();
                 if($atributo){
                   return $atributo->nombre;
                }
                else{
                    return "None";
                }
             },
         ],
         'typePresentation',
         // 'x_position',
         // 'y_position',
         ['class' => 'yii\grid\ActionColumn'],
     ],
 ]); ?>
 <?php
}
  ?>
</div>
