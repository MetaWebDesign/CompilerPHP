package compilerphp.actions;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
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
	private static String name_proyect;
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
		Locate l=new Locate();//OBTITNE LA POSICION DEL PROYECTO EN LOS DIRECTORIOS
		String currentDirectory=l.getPath()+"/runtime-EclipseApplication/";
		ExecuteShellComand obj= new ExecuteShellComand();
		int num_pro=obj.countProyects();
		//Hay solo un proyecto (modelo)
		if(num_pro ==1){
			path=currentDirectory+obj.getProyects()[0];//OBTIENE LA RUTA + NOMBRE DEL PRIMER PROYECTO
			name_proyect=getCurrentFile(path);
			file=name_proyect+".metawebdesign";
			//LECTURA DE XML Y GENERACIÓN DE LA BASE DE DATOS
			try {
				System.out.println(path+"/"+file);
				ReadModel.loadXML(path, file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//hay más de un proyecto (modelo)
		else{
			String proyecto=windowListOptions(obj.getProyects());
			path=currentDirectory+proyecto;
			file=getCurrentFile(path);
			//LECTURA DE XML Y GENERACIÓN DE LA BASE DE DATOS
			try {
				System.out.println(path+"/"+file);
				ReadModel.loadXML(path, file);//LEE EL MODELO Y GENERA LA BASE DE DATOS
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//GENERACION DE CODIGO PHP
		PHP php=new PHP(path+"/PHP/");
		php.start();//IMPORTA EL CODIGO BASE AL PROYECTO PHP
		php.configureBD(name_proyect+".db");//CONFIGURA LA BASE DE DATOS
		php.genModel();//GENERA LOS MODELOS DE LAS TABLAS
		php.genCRUD();//GENERA SERVICIOS Y VISTAS DE LAS TABLAS
		windowMensajeInfo("Compilado con exito!");
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
	
	//BUSCA EL NOMBRE DEL ARCHIVO XML DENTRO DEL DIRECTORIO DEL PROYECTO
	public static String getCurrentFile(String currentDirectory){
		String fileModel = null;
		File folder = new File(currentDirectory);
		File[] listOfFiles = folder.listFiles();
	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	        System.out.println("File " + listOfFiles[i].getName());
	        if(listOfFiles[i].getName().indexOf("metawebdesign")!=-1){
	        	int stop=listOfFiles[i].getName().indexOf(".");
	        	fileModel=listOfFiles[i].getName().substring(0, stop);
	        }
	      } 
	    }
		return fileModel;
	}
	
	//VENTANA CON LAS DIFERENTES PROYECTOS A COMPILAR (EN CASO DE EXISTIR MÁS DE UNO)
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
	
	
	//VENTANA CON INFORMACION
	public static void windowMensajeInfo(String msn){
		MessageDialog.openInformation(
			window.getShell(),
			"CompilerPHP",
			msn);
	}
		
}