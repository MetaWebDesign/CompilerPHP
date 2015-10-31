<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\DashboardConf */

$this->title = 'Create Dashboard Conf';
$this->params['breadcrumbs'][] = ['label' => 'Dashboard Confs', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="dashboard-conf-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
