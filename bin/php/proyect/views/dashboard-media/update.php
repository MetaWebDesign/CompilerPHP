<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\DashboardMedia */

$this->title = 'Update Dashboard Media: ' . ' ' . $model->id_media;
$this->params['breadcrumbs'][] = ['label' => 'Dashboard Media', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id_media, 'url' => ['view', 'id' => $model->id_media]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="dashboard-media-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
