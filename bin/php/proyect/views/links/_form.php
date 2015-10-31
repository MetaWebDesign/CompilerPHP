<?php
namespace app\models;
use yii\helpers\Html;
use yii\widgets\ActiveForm;
use yii\helpers\ArrayHelper;

/* @var $this yii\web\View */
/* @var $model app\models\Links */
/* @var $form yii\widgets\ActiveForm */

//CAPTURA ID UPDATE
$request = \Yii::$app->request;
$id = $request->get('id');
?>

<div class="links-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'nombre')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'url')->textInput(['maxlength' => true]) ?>
    <?php
    if($id){
      echo Html::hiddenInput('Links[id_menu]', $id);
    }else{
      echo $form->field($model, 'id_menu')->dropDownList( ArrayHelper::map(Menu::find()->all(), 'id', 'nombre'), ['id'=>'id']);
    }
    ?>
    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
