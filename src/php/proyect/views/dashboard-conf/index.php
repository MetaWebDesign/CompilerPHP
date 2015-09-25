<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Dashboard Confs';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="dashboard-conf-index">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?php /*<?= Html::a('Create Dashboard Conf', ['create'], ['class' => 'btn btn-success']) ?>*/ ?>
    </p>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            /*'id_web',*/
            'sitetitle',
            'tagline',
            'admin_mail',
            'id_index',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>

</div>
