package compilerphp.actions;

import java.io.FileWriter;
import java.io.IOException;

public class PHP_Layouts{
	static String web_name;
	static String path_layouts;
	
	public PHP_Layouts(String web_name_, String path_layouts_){
		web_name=web_name_;
		path_layouts=path_layouts_;
	}
	
	public void write() throws IOException{
		String layouts="<?php\n";
		layouts=layouts+"/* @var $this \\yii\\web\\View */\n";
		layouts=layouts+"/* @var $content string */\n";
		layouts=layouts+"use yii\\helpers\\Html;\n";
		layouts=layouts+"use yii\\bootstrap\\Nav;\n";
		layouts=layouts+"use yii\\bootstrap\\NavBar;\n";
		layouts=layouts+"use yii\\widgets\\Breadcrumbs;\n";
		layouts=layouts+"use app\\assets\\AppAsset;\n";
		layouts=layouts+"use app\\models\\DashboardConf;\n";
		layouts=layouts+"$conf = DashboardConf::find()->where(['id_web' => 1])->one();\n";
		layouts=layouts+"AppAsset::register($this);\n";
		layouts=layouts+"?>\n";
		layouts=layouts+"<?php $this->beginPage() ?>\n";
		layouts=layouts+"						<!DOCTYPE html>\n";
		layouts=layouts+"						<html lang=\"<?= Yii::$app->language ?>\">\n";
		layouts=layouts+"						<head>\n";
		layouts=layouts+"						    <meta charset=\"<?= Yii::$app->charset ?>\">\n";
		layouts=layouts+"						    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n";
		layouts=layouts+"						    <?= Html::csrfMetaTags() ?>\n";
		layouts=layouts+"						    <title><?= Html::encode($this->title) ?></title>\n";
		layouts=layouts+"						    <?php $this->head() ?>\n";
		layouts=layouts+"						</head>\n";
		layouts=layouts+"						<body>\n";
		layouts=layouts+"						<?php $this->beginBody() ?>\n";
		layouts=layouts+"						<div class=\"wrap\">\n";
		layouts=layouts+"						    <?php\n";
		layouts=layouts+"						    NavBar::begin([\n";
		layouts=layouts+"						        ''brandLabel' => $conf->sitetitle,\n";
		layouts=layouts+"						        'brandUrl' => Yii::$app->homeUrl,\n";
		layouts=layouts+"						        'options' => [\n";
		layouts=layouts+"						            'class' => 'navbar-inverse navbar-fixed-top',\n";
		layouts=layouts+"						        ],\n";
		layouts=layouts+"						    ]);\n";
		layouts=layouts+"						    echo Nav::widget([\n";
		layouts=layouts+"						        'options' => ['class' => 'navbar-nav navbar-right'],\n";
		layouts=layouts+"						        'items' => [\n";
		layouts=layouts+"						            ['label' => 'Home', 'url' => ['/site/index']],\n";
		layouts=layouts+"						            ['label' => 'About', 'url' => ['/site/about']],\n";
		layouts=layouts+"									['label' => 'Dashboard', 'url' => ['/site/dashboard']],\n";
		layouts=layouts+"						            ['label' => 'Contact', 'url' => ['/site/contact']],\n";
		layouts=layouts+"						            Yii::$app->user->isGuest ?\n";
		layouts=layouts+"						                ['label' => 'Login', 'url' => ['/site/login']] :\n";
		layouts=layouts+"						                [\n";
		layouts=layouts+"						                    'label' => 'Logout (' . Yii::$app->user->identity->username . ')',\n";
		layouts=layouts+"						                    'url' => ['/site/logout'],\n";
		layouts=layouts+"						                    'linkOptions' => ['data-method' => 'post']\n";
		layouts=layouts+"						                ],\n";
		layouts=layouts+"						        ],\n";
		layouts=layouts+"						    ]);\n";
		layouts=layouts+"						    NavBar::end();\n";
		layouts=layouts+"						    ?>\n";
		layouts=layouts+"						    <div class=\"container\">\n";
		layouts=layouts+"						        <?= Breadcrumbs::widget([\n";
		layouts=layouts+"						            'links' => isset($this->params['breadcrumbs']) ? $this->params['breadcrumbs'] : [],\n";
		layouts=layouts+"						        ]) ?>\n";
		layouts=layouts+"						        <?= $content ?>\n";
		layouts=layouts+"						    </div>\n";
		layouts=layouts+"						</div>\n";
		layouts=layouts+"						<footer class=\"footer\">\n";
		layouts=layouts+"						    <div class=\"container\">\n";
		layouts=layouts+"						        <p class=\"pull-left\">&copy; My Company <?= date('Y') ?></p>\n";
		layouts=layouts+"						        <p class=\"pull-right\"><?= Yii::powered() ?></p>\n";
		layouts=layouts+"						    </div>\n";
		layouts=layouts+"						</footer>\n";
		layouts=layouts+"				<?php $this->endBody() ?>\n";
		layouts=layouts+"						</body>\n";
		layouts=layouts+"						</html>\n";
		layouts=layouts+"						<?php $this->endPage() ?>\n";
		
		//ESCRITURA DE LA VISTA CON LAS MODIFICACIONES
		FileWriter fichero = null;
		fichero = new FileWriter(path_layouts);
		fichero.write(layouts);
		fichero.close();
	}
}