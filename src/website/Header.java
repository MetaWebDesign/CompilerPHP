//Se agrega configuración, head, include por defecto, menu
package website;

import clases.*;

public class Header {

	private String header;
	private String menu;
	private String begin_main;
	private String end_main;
	private String end_html;
	
	
	public Header(){
		this.setHeader("<!DOCTYPE  html>\n<html>\n\t<head>\n"+
						"\t\t<?php include('lib/head.php'); include('check.php'); include('lib/class.php');?>\n\t</head>\n\n" +
						"\t<body class=\"home\">\n\n\t\t\t<!-- HEADER -->\n" + 
						"\t\t\t<?php include('lib/header.php'); ?>\n\t\t\t<!-- ENDS HEADER -->\n");
		
		this.setMenu("\t\t\t<!-- Menu -->\n\t\t\t<?php if(!$_SESSION['log'] ) include('lib/menu_publico.php'); else	include('lib/menu_'.$_SESSION['rol'].'.php');?>\n\t\t\t<!-- ENDS Menu -->\n\n");
		
		this.setBegin_main("\t\t\t<!-- MAIN -->\n\t\t\t<div id=\"main\">\n\t\t\t\t<!-- wrapper-main -->\n"+
					"\t\t\t\t<div class=\"wrapper\">\n\t\t\t\t<center>\n\t\t\t\t\t<!-- content -->\n"+
					"\t\t\t\t\t<div id=\"content\">\n\n");
		
		this.setEnd_main("\t\t\t\t\t</div>\n\t\t\t\t\t<!-- ENDS content -->\n\t\t\t\t</div>\n\t\t\t\t</div>\n"+
						"\t\t\t\t<!-- ENDS wrapper-main -->\n\t\t\t</div>\n\t\t\t<!-- ENDS MAIN -->\n\n");
		
		this.setEnd_html("\t</body>\n</html>");
		
	}


	public String getHeader() {
		return header;
	}


	public void setHeader(String header) {
		this.header = header;
	}


	public String getMenu() {
		return menu;
	}


	public void setMenu(String menu) {
		this.menu = menu;
	}


	public String getBegin_main() {
		return begin_main;
	}


	public void setBegin_main(String begin_main) {
		this.begin_main = begin_main;
	}


	public String getEnd_main() {
		return end_main;
	}


	public void setEnd_main(String end_main) {
		this.end_main = end_main;
	}


	public String getEnd_html() {
		return end_html;
	}


	public void setEnd_html(String end_html) {
		this.end_html = end_html;
	}
	
}
