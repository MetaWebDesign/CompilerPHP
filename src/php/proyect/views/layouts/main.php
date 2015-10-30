<?php
/* @var $this \yii\web\View */
/* @var $content string */
use yii\helpers\Html;
use yii\bootstrap\Nav;
use yii\bootstrap\NavBar;
use yii\widgets\Breadcrumbs;
use app\assets\AppAsset;
use app\models\DashboardConf;
use app\models\Menu;
use app\models\Links;

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
						    NavBar::begin([
						        'brandLabel' => $conf->sitetitle." <small>".$conf->tagline."</small>",
						        'brandUrl' => Yii::$app->homeUrl,
						        'options' => [
						            'class' => 'navbar-inverse navbar-fixed-top',
						        ],
						    ]);
								/*
						    echo Nav::widget([
						        'options' => ['class' => 'navbar-nav navbar-right'],
						        'items' => [
						            ['label' => 'Home', 'url' => ['/site/index']],
						            ['label' => 'About', 'url' => ['/site/about']],
												!Yii::$app->user->isGuest ?
													['label' => 'Dashboard', 'url' => ['/site/dashboard']]:
						            ['label' => 'Contact', 'url' => ['/site/contact']],
						            Yii::$app->user->isGuest ?
						                ['label' => 'Login', 'url' => ['/site/login']] :
						                [
						                    'label' => 'Logout (' . Yii::$app->user->identity->username . ')',
						                    'url' => ['/site/logout'],
						                    'linkOptions' => ['data-method' => 'post']
						                ],
						        ],
						    ]);
								*/

								echo "<ul id=\"w2\" class=\"navbar-nav navbar-right nav\">\n";
								echo "<li class=\"active\"><a href=\"index.php?r=site%2Findex\">Home</a></li>\n";
								echo "<li><a href=\"index.php?r=site/about\">About</a></li>\n";
								echo "<li><a href=\"index.php?r=site/contact\">Contact</a></li>\n";
								if(Yii::$app->user->isGuest){

									echo "<li><a href=\"index.php?r=site/login\">Login</a></li>\n";
								}
								else{
									echo "<li><a href=\"index.php?r=site/dashboard\">Dashboard</a></li>\n";
									$menu_principal=Menu::find()->where(['type'=>'principal'])->one();
									$links=Links::find()->where(['id_menu'=>$menu_principal->id])->all();
									foreach ($links as $link) {
											echo "<li><a href=\"$link->url\">$link->nombre</a></li>\n";
									}
									echo "<li><a data-method=\"post\" href= \"index.php?r=site/logout\" >Logout (". Yii::$app->user->identity->username .")</a></li>\n";
								}
								echo "</ul>\n";

								//echo "<li><a href=\"/public_html/mwd/Elearning/PHP/proyect/web/index.php?r=site%2Flogin\">Loginne</a></li></ul>\n";

						    NavBar::end();
						    ?>
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
