<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\DashModel */

$this->title = 'Create Dash Model';
$this->params['breadcrumbs'][] = ['label' => 'Dash Models', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="dash-model-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
