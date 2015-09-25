package compilerphp.actions;

import java.io.IOException;

public class PHP_Views{
	static String web_name;
	
	public PHP_Views(String web_name_){
		web_name=web_name_;
	}
	
	public void index() throws IOException{
		String title="index";
		String content="<div class=\\\"site-index\\\">\n";
		content=content+"	    <div class=\\\"jumbotron\\\">\n";
		content=content+"	        <h1>Congratulations!</h1>\n";
		content=content+"	        <p class=\\\"lead\\\">You have successfully created your Yii-powered application.</p>\n";
		content=content+"	        <p><a class=\\\"btn btn-lg btn-success\\\" href=\\\"http://www.yiiframework.com\\\">Get started with Yii</a></p>\n";
		content=content+"	    </div>\n";
		content=content+"	    <div class=\\\"body-content\\\">\n";
		content=content+"	        <div class=\\\"row\\\">\n";
		content=content+"	            <div class=\\\"col-lg-4\\\">\n";
		content=content+"	                <h2>Heading</h2>\n";
		content=content+"	                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et\n";
		content=content+"	                    dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip\n";
		content=content+"	                    ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu\n";
		content=content+"	                    fugiat nulla pariatur.</p>\n";
		content=content+"	                <p><a class=\\\"btn btn-default\\\" href=\\\"http://www.yiiframework.com/doc/\\\">Yii Documentation &raquo;</a></p>\n";
		content=content+"	            </div>\n";
		content=content+"	            <div class=\\\"col-lg-4\\\">\n";
		content=content+"	                <h2>Heading</h2>\n";
		content=content+"	                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et\n";
		content=content+"	                    dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip\n";
		content=content+"	                    ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu\n";
		content=content+"	                    fugiat nulla pariatur.</p>\n";
		content=content+"	                <p><a class=\\\"btn btn-default\\\" href=\\\"http://www.yiiframework.com/forum/\\\">Yii Forum</a></p>\n";
		content=content+"	            </div>\n";
		content=content+"	            <div class=\\\"col-lg-4\\\">\n";
		content=content+"	                <h2>Heading</h2>\n";
		content=content+"	                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et\n";
		content=content+"	                    dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip\n";
		content=content+"	                    ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu\n";
		content=content+"	                    fugiat nulla pariatur.</p>\n";
		content=content+"	                <p><a class=\\\"btn btn-default\\\" href=\\\"http://www.yiiframework.com/extensions/\\\">Yii Extensions &raquo;</a></p>\n";
		content=content+"	            </div>\n";
		content=content+"	        </div>\n";
		content=content+"	    </div>\n";
		SQLite.insertView(title, content);
	}
	
	public void about() throws IOException{
		String title="about";
		String content="<?php\n";
		content=content+"/* @var $this yii\\web\\View */\n";
		content=content+"use yii\\helpers\\Html;\n";
		content=content+"$this->title = 'About';\n";
		content=content+"$this->params['breadcrumbs'][] = $this->title;\n";
		content=content+"?>\n";
		content=content+"				<div class=\"site-about\">\n";
		content=content+"				    <h1><?= Html::encode($this->title) ?></h1>\n";
		content=content+"				    <p>\n";
		content=content+"				        This is the About page. You may modify the following file to customize its content:\n";
		content=content+"				    </p>\n";
		content=content+"				    <code><?= __FILE__ ?></code>\n";
		content=content+"				</div>\n";

		SQLite.insertView(title, content);
	}
	
	public void contact() throws IOException{
		String title="contact";
		String content="<?php\n";
		content=content+"/* @var $this yii\\web\\View */\n";
		content=content+"/* @var $form yii\\bootstrap\\ActiveForm */\n";
		content=content+"/* @var $model app\\models\\ContactForm */\n";
		content=content+"use yii\\helpers\\Html;\n";
		content=content+"use yii\\bootstrap\\ActiveForm;\n";
		content=content+"use yii\\captcha\\Captcha;\n";
		content=content+"$this->title = 'Contact';\n";
		content=content+"$this->params['breadcrumbs'][] = $this->title;\n";
		content=content+"?>\n";
		content=content+"		<div class=\"site-contact\">\n";
		content=content+"		    <h1><?= Html::encode($this->title) ?></h1>\n";
		content=content+"		    <?php if (Yii::$app->session->hasFlash('contactFormSubmitted')): ?>\n";
		content=content+"		        <div class=\"alert alert-success\">\n";
		content=content+"		            Thank you for contacting us. We will respond to you as soon as possible.\n";
		content=content+"		        </div>\n";
		content=content+"		        <p>\n";
		content=content+"		            Note that if you turn on the Yii debugger, you should be able\n";
		content=content+"		            to view the mail message on the mail panel of the debugger.\n";
		content=content+"		            <?php if (Yii::$app->mailer->useFileTransport): ?>\n";
		content=content+"		                Because the application is in development mode, the email is not sent but saved as\n";
		content=content+"		                a file under <code><?= Yii::getAlias(Yii::$app->mailer->fileTransportPath) ?></code>.\n";
		content=content+"		                Please configure the <code>useFileTransport</code> property of the <code>mail</code>\n";
		content=content+"		                application component to be false to enable email sending.\n";
		content=content+"		            <?php endif; ?>\n";
		content=content+"		        </p>\n";
		content=content+"		    <?php else: ?>\n";
		content=content+"		        <p>\n";
		content=content+"		            If you have business inquiries or other questions, please fill out the following form to contact us.\n";
		content=content+"		            Thank you.\n";
		content=content+"		        </p>\n";
		content=content+"		        <div class=\"row\">\n";
		content=content+"		            <div class=\"col-lg-5\">\n";
		content=content+"		                <?php $form = ActiveForm::begin(['id' => 'contact-form']); ?>\n";
		content=content+"		                    <?= $form->field($model, 'name') ?>\n";
		content=content+"		                    <?= $form->field($model, 'email') ?>\n";
		content=content+"		                    <?= $form->field($model, 'subject') ?>\n";
		content=content+"		                    <?= $form->field($model, 'body')->textArea(['rows' => 6]) ?>\n";
		content=content+"		                    <?= $form->field($model, 'verifyCode')->widget(Captcha::className(), [\n";
		content=content+"		                        'template' => '<div class=\"row\"><div class=\"col-lg-3\">{image}</div><div class=\"col-lg-6\">{input}</div></div>',\n";
		content=content+"		                    ]) ?>\n";
		content=content+"		                    <div class=\"form-group\">\n";
		content=content+"		                        <?= Html::submitButton('Submit', ['class' => 'btn btn-primary', 'name' => 'contact-button']) ?>\n";
		content=content+"		                    </div>\n";
		content=content+"		                <?php ActiveForm::end(); ?>\n";
		content=content+"				</div>\n";
		content=content+"			</div>\n";
		content=content+"			    <?php endif; ?>\n";
		content=content+"		</div>\n";
		SQLite.insertView(title, content);

	}

}