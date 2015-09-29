<?php

use yii\helpers\Html;
use yii\widgets\DetailView;

/* @var $this yii\web\View */
/* @var $model app\models\DashboardMedia */

$this->title = $model->id_media;
$this->params['breadcrumbs'][] = ['label' => 'Dashboard Media', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="dashboard-media-view">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Update', ['update', 'id' => $model->id_media], ['class' => 'btn btn-primary']) ?>
        <?= Html::a('Delete', ['delete', 'id' => $model->id_media], [
            'class' => 'btn btn-danger',
            'data' => [
                'confirm' => 'Are you sure you want to delete this item?',
                'method' => 'post',
            ],
        ]) ?>
    </p>

    <?= DetailView::widget([
        'model' => $model,
        'attributes' => [
            //'id_media',
            'filename',
            'id_autor',
            'Fecha',
            'extencion',
        ],
    ]) ?>

    <?php
    echo "<center><a href=\"uploads/$model->filename\"><img style=\"width:30%\" src=\"uploads/$model->filename\" class=\"img-responsive\"></a></center>";
     ?>

</div>
