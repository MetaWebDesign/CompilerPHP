<?php

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

            'id',
            'nombre',
            'mensaje_error:ntext',
            'operador',
            'service',
            // 'valor',
            // 'clase',
            // 'atributo',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>

</div>
