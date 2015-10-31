<?php
namespace app\models;
use yii\helpers\Html;
use yii\widgets\ActiveForm;
use yii\helpers\ArrayHelper;
/* @var $this yii\web\View */
/* @var $model app\models\DashboardPermisoscrud */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="dashboard-permisoscrud-form">

    <?php $form = ActiveForm::begin(); ?>

    <?php /* echo $form->field($model, 'id_dash')->textInput()*/ ?>
    <?php /* echo $form->field($model, 'id_rol')->textInput() */ ?>
    <?php /* echo $form->field($model, 'service')->textInput() */ ?>

    <label class="control-label" for="Clases-cod_curso"> Clase: </label>
    <?= Html::activeDropDownList($model, 'id_dash', ArrayHelper::map(Dashboard::find()->all(), 'id', 'nombre')) ?>

    <label class="control-label" for="Clases-cod_curso"> Rol: </label>
    <?= Html::activeDropDownList($model, 'id_rol', ArrayHelper::map(Roles::find()->all(), 'id_rol', 'rolname')) ?>

    <label class="control-label" for="Clases-cod_curso"> Service: </label>
    <?= Html::activeDropDownList($model, 'service', ArrayHelper::map(DashboardPermisoscrud::find()->all(), 'service', 'service')) ?>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
