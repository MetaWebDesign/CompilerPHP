package compilerphp.actions;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

public class Test2 {
	
	static JFrame f = new JFrame("JProgressBar Sample");
	static Container content = f.getContentPane();
	static JProgressBar progressBar = new JProgressBar();
	
  public static void main(String args[]) {
    
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    progressBar.setValue(30);
    progressBar.setStringPainted(true);
    
    Border border = BorderFactory.createTitledBorder("Creando algo...");
    progressBar.setBorder(border);
    
    
    progressBar.setValue(40);
    progressBar.setStringPainted(true);
    
    border = BorderFactory.createTitledBorder("Creando algo 2...");
    progressBar.setBorder(border);
    
    
    content.add(progressBar, BorderLayout.NORTH);
    
    f.setSize(300, 100);
    f.setVisible(true);
  }
}