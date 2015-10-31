<?php

use yii\helpers\Html;
use yii\widgets\DetailView;

/* @var $this yii\web\View */
/* @var $model app\models\DashboardPermisoscrud */
if(!Yii::$app->user->isGuest && Yii::$app->user->identity->id_rol == 1){
  $this->title = $model->id_permiso;
  $this->params['breadcrumbs'][] = ['label' => 'Dashboard Permisoscruds', 'url' => ['index']];
  $this->params['breadcrumbs'][] = $this->title;
?>
<div class="dashboard-permisoscrud-view">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Update', ['update', 'id' => $model->id_permiso], ['class' => 'btn btn-primary']) ?>
        <?= Html::a('Delete', ['delete', 'id' => $model->id_permiso], [
            'class' => 'btn btn-danger',
            'data' => [
                'confirm' => 'Are you sure you want to delete this item?',
                'method' => 'post',
            ],
        ]) ?>
    </p>

    <?= DetailView::widget([
        'model' => $model,
        'attributes' => [
            'id_permiso',
            'id_dash',
            'id_rol',
            'service',
        ],
    ]) ?>

</div>
<?php
}
else{
 ?>
 <div class="site-error">

     <h1>Forbidden (#403)</h1>

     <div class="alert alert-danger">
         You are not Admin to perform this action.    </div>

     <p>
         The above error occurred while the Web server was processing your request.
     </p>
     <p>
         Please contact us if you think this is a server error. Thank you.
     </p>

 </div>

 <?php
}
  ?>
