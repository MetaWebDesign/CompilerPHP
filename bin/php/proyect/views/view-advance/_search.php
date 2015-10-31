<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model app\models\ViewAdvanceSearch */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="view-advance-search">

    <?php $form = ActiveForm::begin([
        'action' => ['index'],
        'method' => 'get',
    ]); ?>

    <?= $form->field($model, 'id') ?>

    <?= $form->field($model, 'id_vista') ?>

    <?= $form->field($model, 'id_clase') ?>

    <?= $form->field($model, 'id_atributo') ?>

    <?= $form->field($model, 'typePresentation') ?>

    <?php // echo $form->field($model, 'x_position') ?>

    <?php // echo $form->field($model, 'y_position') ?>

    <div class="form-group">
        <?= Html::submitButton('Search', ['class' => 'btn btn-primary']) ?>
        <?= Html::resetButton('Reset', ['class' => 'btn btn-default']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
