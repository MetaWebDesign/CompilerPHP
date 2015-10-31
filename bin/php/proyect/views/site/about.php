<?php

/* @var $this yii\web\View */

use yii\helpers\Html;
use app\models\Views;

$this->title = 'About';
$this->params['breadcrumbs'][] = $this->title;
$view = Views::find()->where(['title' => 'about'])->one();
echo $view->content;
?>
