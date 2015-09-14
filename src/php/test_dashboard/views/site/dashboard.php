<?php
namespace app\models;
/* @var $this yii\web\View */

use yii\helpers\Html;

$this->title = 'Dashboard';
$this->params['breadcrumbs'][] = $this->title;


/*
$criteria = new CDbCriteria;
$criteria->condition = '';
$results = DashModel::model()->findAll($criteria);
foreach($results AS $model){
echo $model->name;
//echo $model->_cosa;
}
*/

$tablas = DashModel::find()
    ->indexBy('id')
    ->all();

foreach($tablas AS $tabla){
echo $tabla->name;
//echo $model->otra_cosa;
}

?>
