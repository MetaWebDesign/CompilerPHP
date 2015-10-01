<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Dashboard Permisoscruds';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="dashboard-permisoscrud-index">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Create Dashboard Permisoscrud', ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id_permiso',
            'id_dash',
            'id_rol',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>

</div>
