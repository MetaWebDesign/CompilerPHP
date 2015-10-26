<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model app\models\Menu */
/* @var $form yii\widgets\ActiveForm */

//CAPTURA ID UPDATE
$request = \Yii::$app->request;
$id = $request->get('id');
?>

<div class="menu-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'nombre')->textInput(['maxlength' => true]) ?>

    <label class="control-label" for="menu-type">type: </label>
    <select id="id" class="form-control" name="Menu[type]">
      <option value="navegation">navegation</option>
      <option value="principal">principal</option>
    </select>
    <br><br>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
        <?php
        if($id){
          $url="index.php?r=links/create&id=$id";
          echo "<a href=\"$url\" class=\"btn btn-success\" role=\"button\">Add Link </a> ";
          $url2="index.php?r=links/index&id=$id";
          echo " <a href=\"$url2\" class=\"btn btn-warning\" role=\"button\"> View Link </a>";
        }
        ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
