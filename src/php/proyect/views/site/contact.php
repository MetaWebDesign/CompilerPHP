<?php

/* @var $this yii\web\View */
/* @var $form yii\bootstrap\ActiveForm */
/* @var $model app\models\ContactForm */

use yii\helpers\Html;
use yii\bootstrap\ActiveForm;
use yii\captcha\Captcha;
use app\models\Views;

$this->title = 'Contact';
$this->params['breadcrumbs'][] = $this->title;

$view = Views::find()->where(['title' => 'contact'])->one();
echo $view->content;
?>
