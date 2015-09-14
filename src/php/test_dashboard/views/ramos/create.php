<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Ramos */

$this->title = 'Create Ramos';
$this->params['breadcrumbs'][] = ['label' => 'Ramos', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="ramos-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
