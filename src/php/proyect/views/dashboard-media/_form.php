<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;
//use yii\widgets\ActiveForm;

use kartik\file\FileInput;
/* @var $this yii\web\View */
/* @var $model app\models\DashboardMedia */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="dashboard-media-form">

    <?php  /*$form = ActiveForm::begin();*/ ?>


    <?php $form = ActiveForm::begin(['options' => ['enctype' => 'multipart/form-data']]) ?>

    <?=   $form->field($model, 'filename')->fileInput() ?>
    <!--<input type="file" name="filename" id="filename">-->

    <?php  /*$form->field($model, 'filename')->textInput(['maxlength' => true]) */?>
    <?php /*
        // Usage with ActiveForm and model
        echo $form->field($model, 'filename')->widget(FileInput::classname(), [
            'options' => ['accept' => 'upload/*'],
        ]);*/
        ?>

    <?= $form->field($model, 'id_autor')->textInput() ?>

    <?= $form->field($model, 'Fecha')->textInput() ?>

    <?= $form->field($model, 'extencion')->textInput(['maxlength' => true]) ?>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
