<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\Views */

$this->title = 'Update Views: ' . ' ' . $model->title;
$this->params['breadcrumbs'][] = ['label' => 'Views', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->title, 'url' => ['view', 'id' => $model->id_view]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="views-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
