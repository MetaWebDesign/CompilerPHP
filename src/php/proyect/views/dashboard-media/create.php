<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\DashboardMedia */

$this->title = 'Create Dashboard Media';
$this->params['breadcrumbs'][] = ['label' => 'Dashboard Media', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="dashboard-media-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
