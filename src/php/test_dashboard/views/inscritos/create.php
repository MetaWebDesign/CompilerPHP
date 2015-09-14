<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Inscritos */

$this->title = 'Create Inscritos';
$this->params['breadcrumbs'][] = ['label' => 'Inscritos', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="inscritos-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
