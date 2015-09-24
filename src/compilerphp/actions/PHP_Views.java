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
	

}