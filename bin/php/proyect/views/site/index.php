<?php

/* @var $this yii\web\View */
use app\models\DashboardConf;
use app\models\Views;

$this->title = 'My Yii Application';
?>
<?php
$conf = DashboardConf::find()->where(['id_web' => 1])->one();
$view = Views::find()->where(['id_view' => $conf->id_index])->one();
echo $view->content;

?>
