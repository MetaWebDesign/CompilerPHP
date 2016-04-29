<?php
namespace app\models;
use yii\helpers\Html;
use yii\grid\GridView;
use yii\data\ActiveDataProvider;

/* @var $this yii\web\View */
/* @var $searchModel app\models\LinksSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Links';
$this->params['breadcrumbs'][] = $this->title;

//CAPTURA ID UPDATE
$request = \Yii::$app->request;
$id = $request->get('id');
?>
<div class="links-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <p>
        <?= Html::a('Create Links', ['create'], ['class' => 'btn btn-success']) ?>
    </p>


    <?php
    if($id){
      $results= Links::find()->where(['id_menu'=>$id]);
      $dataProvider = new ActiveDataProvider([
          'query' => $results,
      ]);
    }
    ?>


    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id',
            'nombre',
            'url:url',
            'id_menu',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>

</div>
