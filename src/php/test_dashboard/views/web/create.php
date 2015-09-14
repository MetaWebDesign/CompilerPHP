<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Web */

$this->title = 'Create Web';
$this->params['breadcrumbs'][] = ['label' => 'Webs', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="web-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
