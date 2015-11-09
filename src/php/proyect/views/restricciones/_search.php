<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model app\models\RestriccionesSearch */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="restricciones-search">

    <?php $form = ActiveForm::begin([
        'action' => ['index'],
        'method' => 'get',
    ]); ?>

    <?= $form->field($model, 'id') ?>

    <?= $form->field($model, 'nombre') ?>

    <?= $form->field($model, 'mensaje_error') ?>

    <?= $form->field($model, 'operador') ?>

    <?= $form->field($model, 'service') ?>

    <?php // echo $form->field($model, 'valor') ?>

    <?php // echo $form->field($model, 'clase') ?>

    <?php // echo $form->field($model, 'atributo') ?>

    <div class="form-group">
        <?= Html::submitButton('Search', ['class' => 'btn btn-primary']) ?>
        <?= Html::resetButton('Reset', ['class' => 'btn btn-default']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
