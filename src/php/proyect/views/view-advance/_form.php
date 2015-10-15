<?php
namespace app\models;
use yii\helpers\Html;
use yii\widgets\ActiveForm;
use yii\helpers\ArrayHelper;

/* @var $this yii\web\View */
/* @var $model app\models\ViewAdvance */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="view-advance-form">

    <?php $form = ActiveForm::begin(); ?>

    <?php /* $form->field($model, 'id_vista')->textInput() */?>
    <label class="control-label" for="Clases-cod_curso">Vista</label>
    <?= Html::activeDropDownList($model, 'id_vista', ArrayHelper::map(Views::find()->all(), 'id_view', 'title')) ?>

    <?php /* $form->field($model, 'id_clase')->textInput() */?>
    <label class="control-label" for="Clases-cod_curso">Clase</label>
    <?= Html::activeDropDownList($model, 'id_clase', ArrayHelper::map(Dashboard::find()->all(), 'id', 'nombre')) ?>

    <?= $form->field($model, 'atributo')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'typePresentation')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'x_position')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'y_position')->textInput() ?>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
