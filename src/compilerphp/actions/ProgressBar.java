package compilerphp.actions;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

public class ProgressBar{
	
	static JFrame f ;
	 Border border;
	static Container content;
	static JProgressBar progressBar;
	
	public ProgressBar(String title){
		  
		  f= new JFrame(title);
		  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  content = f.getContentPane();
		  progressBar = new JProgressBar();
		    
		    progressBar.setValue(0);
		    progressBar.setStringPainted(true);
		    border = BorderFactory.createTitledBorder("Iniciando...");
		    progressBar.setBorder(border);
		    content.add(progressBar, BorderLayout.NORTH);
		    f.setSize(500, 100);
		    f.setVisible(true);
	}
	
	public void updateProgress(int porcentage, String msn){
	    progressBar.setValue(porcentage);
	    progressBar.setStringPainted(true);
	    border = BorderFactory.createTitledBorder(msn);
	    progressBar.setBorder(border);
	    content.add(progressBar, BorderLayout.NORTH);
	}
	
}