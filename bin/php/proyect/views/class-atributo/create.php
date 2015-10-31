<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\ClassAtributo */

$this->title = 'Create Class Atributo';
$this->params['breadcrumbs'][] = ['label' => 'Class Atributos', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="class-atributo-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
