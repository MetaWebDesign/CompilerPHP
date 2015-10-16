<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\ClassAtributo */

$this->title = 'Update Class Atributo: ' . ' ' . $model->id;
$this->params['breadcrumbs'][] = ['label' => 'Class Atributos', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id, 'url' => ['view', 'id' => $model->id]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="class-atributo-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
