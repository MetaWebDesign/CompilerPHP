<?php
/* @var $this \yii\web\View */
/* @var $content string */
use yii\helpers\Html;
use yii\bootstrap\Nav;
use yii\bootstrap\NavBar;
use yii\widgets\Breadcrumbs;
use app\assets\AppAsset;
use app\models\Links;
use app\models\Menu;
use app\models\DashboardConf;
//namespace app\models;
$conf = DashboardConf::find()->where(['id_web' => 1])->one();
AppAsset::register($this);
?>
<?php $this->beginPage() ?>
						<!DOCTYPE html>
						<html lang="<?= Yii::$app->language ?>">
						<head>
						    <meta charset="<?= Yii::$app->charset ?>">
						    <meta name="viewport" content="width=device-width, initial-scale=1">
						    <?= Html::csrfMetaTags() ?>
						    <title><?= Html::encode($this->title) ?></title>
						    <?php $this->head() ?>
						</head>
						<body>
						<?php $this->beginBody() ?>
						<div class="wrap">
						    <?php
								?>
								<nav id="w0" class="navbar-inverse navbar-fixed-top navbar" role="navigation"><div class="container">
									<div class="navbar-header">
										<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#w0-collapse">
											<span class="sr-only">Toggle navigation</span>
											<span class="icon-bar"></span>
											<span class="icon-bar"></span>
											<span class="icon-bar"></span>
										</button>
										<a class="navbar-brand" href="/public_html/mwd/Elearning/PHP/proyect/web/index.php">elearning <small> </small></a>
									</div>
								<?php

								echo "<div id=\"w0-collapse\" class=\"collapse navbar-collapse\">\n";
								echo "	<ul id=\"w1\" class=\"navbar-nav navbar-right nav\">\n";
								echo "		<li><a href=\"index.php?r=site/index\">Home</a></li>\n";
								echo "		<li><a href=\"index.php?r=site/about\">About</a></li>\n";

								if (!Yii::$app->user->isGuest){
									echo "  <li><a href=\"index.php?r=site/dashboard\">Dashboard</a></li>\n";
									$menu_principal = Menu::find()->where(['type'=>'principal'])->one();
									if($menu_principal){
										$links = Links::find()->where(['id_menu'=>$menu_principal->id])->all();
										foreach($links AS $link){
											echo "		<li><a href=\"$link->url\">$link->nombre</a></li>\n";
										}
										echo "		<li><a href=\"index.php?r=site/logout\" >Logout (". Yii::$app->user->identity->username . ")</a></li>\n";
									}//if menu principal end
								}
								if(Yii::$app->user->isGuest){
									echo "<li><a href=\"index.php?r=site/login\">Login</a></li>\n";
								}
								echo "</ul>\n";
								echo "</div>\n";
								?>
								</div></nav>
						    <div class="container">
						        <?= Breadcrumbs::widget([
						            'links' => isset($this->params['breadcrumbs']) ? $this->params['breadcrumbs'] : [],
						        ]) ?>
						        <?= $content ?>
						    </div>
						</div>
						<footer class="footer">
						    <div class="container">
						        <p class="pull-left">&copy; My Company <?= date('Y') ?></p>
						        <p class="pull-right"><?= Yii::powered() ?></p>
						    </div>
						</footer>
				<?php $this->endBody() ?>
						</body>
						</html>
						<?php $this->endPage() ?>
