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

    <p>
        <?= Html::a('Create View Advance', ['create'], ['class' => 'btn btn-success']) ?>
    </p>

<?php
if($id_view){
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

            'id',
            'id_vista',
            'id_clase',
            'atributo',
            'typePresentation',
            // 'x_position',
            // 'y_position',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>
<?php
}else{
 ?>
 <?= GridView::widget([
     'dataProvider' => $dataProvider,
     'filterModel' => $searchModel,
     'columns' => [
         ['class' => 'yii\grid\SerialColumn'],

         'id',
         'id_vista',
         'id_clase',
         'atributo',
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
