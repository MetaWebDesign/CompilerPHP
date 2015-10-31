<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;
use kartik\file\FileInput;
//use yii\helpers\Html;
/* @var $this yii\web\View */
/* @var $model app\models\DashboardMedia */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="dashboard-media-form">

    <?php  /*$form = ActiveForm::begin();*/ ?>


    <?php $form = ActiveForm::begin(['options' => ['enctype' => 'multipart/form-data']]) ?>

    <?php
        // Usage with ActiveForm and model
        echo $form->field($model, 'filename')->widget(FileInput::classname(), [
            'options' => ['accept' => 'upload/*'],
        ]);
        ?>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
