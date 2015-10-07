<?php
use Yii;
use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\DashboardPermisoscrud */
if(!Yii::$app->user->isGuest && Yii::$app->user->identity->id_rol == 1){
  $this->title = 'Update Dashboard Permisoscrud: ' . ' ' . $model->id_permiso;
  $this->params['breadcrumbs'][] = ['label' => 'Dashboard Permisoscruds', 'url' => ['index']];
  $this->params['breadcrumbs'][] = ['label' => $model->id_permiso, 'url' => ['view', 'id' => $model->id_permiso]];
  $this->params['breadcrumbs'][] = 'Update';
?>
<div class="dashboard-permisoscrud-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
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
