<?php
namespace app\models;
use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Configuración sitio web';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="dashboard-conf-index">

    <h1><?= Html::encode($this->title) ?></h1>


    <?php
      $web = DashboardConf::find()->where(['id_web'=>'1'])->one();
      echo "<center>\n";
      echo "<div class=\"jumbotron\">\n";
      echo "  <h1>$web->sitetitle</h1>\n";
      echo "  <p>$web->tagline</p>\n";
      echo "  <p><b>Mail Administrador: </b>$web->admin_mail</p>\n";
      echo "  <p><a class=\"btn btn-primary btn-lg\" href=\"index.php?r=dashboard-conf/update&id=1\" role=\"button\">Update</a></p>\n";
      echo "</div>\n";
      echo "</center>\n";
     ?>






</div>
