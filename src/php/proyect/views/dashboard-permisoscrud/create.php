<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\DashboardPermisoscrud */

$this->title = 'Create Dashboard Permisoscrud';
$this->params['breadcrumbs'][] = ['label' => 'Dashboard Permisoscruds', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="dashboard-permisoscrud-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
