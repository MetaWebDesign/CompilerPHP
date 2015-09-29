<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Dashboard Media';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="dashboard-media-index">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Create Dashboard Media', ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id_media',
            'filename',
            'id_autor',
            'Fecha',
            'extencion',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>

</div>
