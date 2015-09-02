package compilerphp.actions;

//import java.io.IOException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
//import org.eclipse.swt.graphics.Path;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.jface.dialogs.MessageDialog;



/**
 * Our sample action implements workbench action delegate.
 * The action proxy will be created by the workbench and
 * shown in the UI. When the user tries to use the action,
 * this delegate will be created and execution will be 
 * delegated to it.
 * @see IWorkbenchWindowActionDelegate
 */
public class Compilar implements IWorkbenchWindowActionDelegate {
	private static IWorkbenchWindow window;
	private static String path;
	private static String file;
	//public ReadModel RM;
	/**
	 * The constructor.
	 */
	public Compilar() {
	}

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		System.out.println("Compilando..");
		//OBTIENE LA RUATA Y EL NOMBRE DEL ARCHIVO DEL MODELO
		String workingDir = System.getProperty("user.dir");
		Path p = Paths.get(workingDir);
		String currentDirectory=p.getParent().toString()+"/runtime-EclipseApplication/";//IDENTIFICA LA RUTA DEL XML
		ExecuteShellComand obj= new ExecuteShellComand();
		int num_pro=obj.countProyects();
		System.out.println("num_pro: "+num_pro);
		
		if(num_pro ==1){//Hay solo un proyecto (modelo)

			
			//Acción aca
			/*
			try {
				System.out.println("Ejecutando el resto");
				ReadModel.loadXML(" ", " ");
				//ReadModel.loadXML("/home/leo/runtime-EclipseApplication/Elearning", "elearning.metawebdesign");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			windowMensajeInfo("Probando compilador");

		}
		else{//hay más de un proyecto (modelo)
			System.out.println("Else");
			String proyecto=windowListOptions(obj.getProyects());
			path=currentDirectory+proyecto;
			file=getCurrentFile(path);
			try {
				System.out.println(path+"/"+file);
				ReadModel.loadXML(path, file);
				//ReadModel.loadXML("/home/leo/runtime-EclipseApplication/Elearning", "elearning.metawebdesign");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		

	}

	/**
	 * Selection in the workbench has been changed. We 
	 * can change the state of the 'real' action here
	 * if we want, but this can only happen after 
	 * the delegate has been created.
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system
	 * resources we previously allocated.
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/**
	 * We will cache window object in order to
	 * be able to provide parent shell for the message dialog.
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
	
	public static String getCurrentFile(String currentDirectory){
		System.out.println("getCurrentFile :"+currentDirectory);
		String fileModel = null;
		File folder = new File(currentDirectory);
		File[] listOfFiles = folder.listFiles();
	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	        System.out.println("File " + listOfFiles[i].getName());
	        if(listOfFiles[i].getName().indexOf("metawebdesign")!=-1){
	        	int stop=listOfFiles[i].getName().indexOf(".");
	        	fileModel=listOfFiles[i].getName().substring(0, stop)+".metawebdesign";
	        }
	      } 
	    }
		return fileModel;
	}
	
	
	public static String windowListOptions(String[] choices){
		//String[] choices = { "A", "B", "C", "D", "E", "F" };
		String input = (String) JOptionPane.showInputDialog(null, "Choose now...",
				"The Choice of a Lifetime", JOptionPane.QUESTION_MESSAGE, null, // Use
                                                                        // default
                                                                        // icon
        choices, // Array of choices
        choices[1]); // Initial choice
		System.out.println(input);
		return input;
	}
	
	public static void windowMensajeInfo(String msn){
		//IF NO HAY ERRORES
		MessageDialog.openInformation(
			window.getShell(),
			"CompilerPHP",
			msn);
	}
	
	public static void test(){
		ExecuteShellComand obj = new ExecuteShellComand();
		String comando="ls -1 /home/leo/Documentos/github/runtime-EclipseApplication | wc | awk {'print $1'}";
		String num_dir=  obj.executeCommand(comando);//UBICA EL ARCHIVO XML
		System.out.println("comando");
		System.out.println("Number proyect: "+num_dir);
	}
	
	
	public static void main(String[] args) throws IOException {
		//getCurrentDirectory();
		test();
	}
}