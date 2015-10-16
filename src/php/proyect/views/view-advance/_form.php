<?php
namespace app\models;
use yii\helpers\Html;
use yii\widgets\ActiveForm;
use yii\helpers\ArrayHelper;

//use kartik\widgets\DepDrop;
use kartik\depdrop\DepDrop;
use yii\helpers\Url;

//use kartik\widgets\DepDrop;
//use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\ViewAdvance */
/* @var $form yii\widgets\ActiveForm */


?>

<div class="view-advance-form">

<?php

$form = ActiveForm::begin();

// Parent
echo $form->field($model, 'id_vista')->dropDownList( ArrayHelper::map(Views::find()->all(), 'id_view', 'title'), ['id'=>'id_view']);

echo $form->field($model, 'id_clase')->dropDownList(ArrayHelper::map(Dashboard::find()->all(), 'id', 'nombre'), ['id'=>'id']);

// Child # 1

echo $form->field($model, 'atributo')->widget(DepDrop::classname(), [
  'options'=>['id'=>'subcat-atributo'],
  //'options'=>ArrayHelper::map(ClassAtributo::find()->where(['id_clase'=>'1'])->all(), 'id', 'nombre'),
  'pluginOptions'=>[
      'depends'=>['id'],
      'placeholder'=>'Select...',
      'url'=>Url::to(['subcat'])
  ]
]);


?>


    <label class="control-label" for="viewadvance-typePresentation">typePresentation: </label>
    <select id="id" class="form-control" name="ViewAdvance[typePresentation]">
      <option value="string">string</option>
      <option value="table">table</option>
      <option value="ombobox">combobox</option>
      <option value="radiobuttom">radiobuttom</option>
      <option value="itemList">itemList</option>
      <option value="form_email">form_email</option>
      <option value="form_password">form_password</option>
      <option value="form_file">form_file</option>
      <option value="table_striped">table_striped</option>
      <option value="table_hover">table_hover</option>
      <option value="img">img</option>
    </select>
    <p><br>
    <?php /* $form->field($model, 'x_position')->textInput(['maxlength' => true]) */?>
    <label class="control-label" for="viewadvance-x_position">x_position</label>
    <select id="id" class="form-control" name="ViewAdvance[x_position]">
      <option value="left">left</option>
      <option value="center">center</option>
      <option value="right">right</option>
    </select>

    <?php /* $form->field($model, 'y_position')->textInput() */?>
    <label class="control-label" for="viewadvance-y_position">x_position</label>
    <select id="id" class="form-control" name="ViewAdvance[y_position]">
      <option value="1">1</option>
      <option value="2">2</option>
      <option value="3">3</option>
      <option value="4">4</option>
      <option value="5">5</option>
      <option value="6">6</option>
      <option value="7">7</option>
      <option value="8">8</option>
      <option value="9">9</option>
      <option value="10">10</option>
    </select>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
