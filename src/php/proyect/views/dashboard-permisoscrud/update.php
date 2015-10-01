<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\DashboardPermisoscrud */

$this->title = 'Update Dashboard Permisoscrud: ' . ' ' . $model->id_permiso;
$this->params['breadcrumbs'][] = ['label' => 'Dashboard Permisoscruds', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id_permiso, 'url' => ['view', 'id' => $model->id_permiso]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="dashboard-permisoscrud-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
