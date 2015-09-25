<?php
namespace app\models;

use yii\helpers\Html;
use yii\widgets\ActiveForm;
use yii\helpers\ArrayHelper;
/* @var $this yii\web\View */
/* @var $model app\models\DashboardConf */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="dashboard-conf-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'sitetitle')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'tagline')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'admin_mail')->textInput(['maxlength' => true]) ?>

    <label class="control-label" for="Archivos-id_clase">Index / Home Web: </label>
    <?= Html::activeDropDownList($model, 'id_index', ArrayHelper::map(Views::find()->all(), 'id_view', 'title')) ?>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
