<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Ramos';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="ramos-index">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
	<?php /*MODIFCIAR LA URL PARA CREAR O UN RAMO O INSCRIVIR A UN PROFESOR */ ?>
        <?= Html::a('Create Ramos', ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id',
            'classname:ntext',
            'max_student',
            'descrip:ntext',
            'profesor:ntext',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>

</div>
