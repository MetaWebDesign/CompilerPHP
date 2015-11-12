<?php
namespace app\models;
use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $searchModel app\models\RestriccionesSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Restricciones';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="restricciones-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <p>
        <?= Html::a('Create Restricciones', ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            //'id',
            'nombre',
            'service',
           //'atributo',
           [                      // the owner name of the model
               'label' => 'atributo',
               'value' => function ($data) {
                   $nombre_pag=ClassAtributo::find()->select('nombre')->where(['id_clase'=>$data->clase, 'id_atributo'=>$data->atributo])->one();
                   return $nombre_pag->nombre;
               },
           ],
            'operador',
            //'mensaje_error:ntext',
             'valor',
            // 'clase',
            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>

</div>
