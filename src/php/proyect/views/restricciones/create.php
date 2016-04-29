<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Restricciones */

$this->title = 'Create Restricciones';
$this->params['breadcrumbs'][] = ['label' => 'Restricciones', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="restricciones-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
