<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\DashboardConf */

$this->title = 'Update Dashboard Conf: ' . ' ' . $model->id_web;
$this->params['breadcrumbs'][] = ['label' => 'Dashboard Confs', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id_web, 'url' => ['view', 'id' => $model->id_web]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="dashboard-conf-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
