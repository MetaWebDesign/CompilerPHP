package compilerphp.actions;

import javax.swing.JPanel;

public class Test extends JPanel {

  public static void main(String args[]) {
	  ProgressBar bar=new ProgressBar("Prueba de barra"); 
	  bar.updateProgress(10, "10% de wea");
	  
  }
}