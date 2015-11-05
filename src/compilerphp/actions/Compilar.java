package compilerphp.actions;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;

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
	protected String path;
	protected String file;
	protected String name_proyect;
	protected SQL sql=new SQL();//MODELO SQL DE LA BASE DE DATOS (ESTRUCTURA DE LOS DATOS)
	static ProgressMonitor monitor;
	static int progress;
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
		ProgressBar bar=new ProgressBar("Prueba de barra");//BARRA DE PROGRESO
		ExecuteShellComand obj= new ExecuteShellComand();
		
		bar.updateProgress(1, "Buscando Proyecto(s)");
		int num_pro=obj.countProyects();
		ReadModel m = new ReadModel();
		
		//Hay solo un proyecto (modelo)
		if(num_pro ==1){
			this.path=currentDirectory+obj.getProyects()[0];//OBTIENE LA RUTA + NOMBRE DEL PRIMER PROYECTO
			this.name_proyect=getCurrentFile(this.path);
			this.file=this.name_proyect+".metawebdesign";
			bar.updateProgress(3, "Leyendo "+this.file);
			//LECTURA DE XML Y GENERACIÓN DE LA BASE DE DATOS
			try {
				//System.out.println(this.path+"/"+this.file);
				//modelo=ReadModel.loadXML(path, file);
				m.loadXML(this.path,this.file);
				if(!m.error_status){
					this.sql = m.getSQL();
				}
				else{
					windowMensajeError(m.getErrorText());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		//hay más de un proyecto (modelo)
		else{
			String proyecto=windowListOptions(obj.getProyects());
			this.path=currentDirectory+proyecto;
			this.name_proyect=getCurrentFile(this.path);
			this.file=getCurrentFile(this.path)+".metawebdesign";
			bar.updateProgress(3, "Leyendo "+this.file);
			//LECTURA DE XML Y GENERACIÓN DE LA BASE DE DATOS
			try {
				//System.out.println(this.path+"/"+this.file);
				//modelo=ReadModel.loadXML(path, file);//LEE EL MODELO Y GENERA LA BASE DE DATOS
				m.loadXML(this.path,this.file);
				this.sql = m.getSQL();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//GENERACION DE LA BDD EN SQLite
		try {
			SQLite.createDB(this.sql, this.path, this.file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!m.error_status){
			//GENERACION DE CODIGO PHP 
			/*
			bar.updateProgress(40, "Creando Proyecto PHP");
			PHP php=new PHP(this.path, this.sql, this.name_proyect);
			bar.updateProgress(45, "Creando Proyecto PHP - Codigo base");
			php.start();//IMPORTA EL CODIGO BASE AL PROYECTO PHP
			php.yiiExec();//EJECUTA BASH PARA LA GENERACION DE MODELO, CONTROLADORES Y PAGINAS DE LOS SEVICIOS USANDO YII
			bar.updateProgress(50, "Creando Proyecto PHP - Configurando Base de Datos");
			php.configureBD_Gii(this.name_proyect+".db");//CONFIGURA LA BASE DE DATOS
			bar.updateProgress(55, "Creando Proyecto PHP - Generado Modelos");
			php.genModel();//GENERA LOS MODELOS DE LAS TABLAS
			bar.updateProgress(57, "Creando Proyecto PHP - Generado Controladores");
			php.genCRUD();//GENERA SERVICIOS Y VISTAS DE LAS TABLAS
			bar.updateProgress(60, "Creando Proyecto PHP - Generado Modelos Vistas");
			php.genModelView();//GENERA LOS MODELOS DE LAS VISTAS
			bar.updateProgress(65, "Creando Proyecto PHP - Generado Modelos Controladores Vistas");
			php.genCRUDView();//GENERA LOS CONTROLADORES DE LAS VISTAS
			php.execPermisos();//PERIMISOS PARA QUE APACHE U OTRO PUEDA EJECUTAR EL SITIO WEB
			bar.updateProgress(70, "Creando Proyecto PHP - Generado Vistas por defecto");
			php.genViewsDefault();//GENERA LAS VISTAS INDEX, ABOUT, CONTACT
			php.configureBD_Apache(this.name_proyect+".db");
			bar.updateProgress(75, "Configurando sitio web");
			php.configureWeb();//CARGA LA CONFIGURACION DEL SITIO WEB
			bar.updateProgress(80, "Configurando acceso a servicios");
			php.permisosCRUD();//CARGA LOS PERMISOS SOBRE LOS SERVICIOS DEL CRUD EN LA BDD
			bar.updateProgress(85, "Cargando atributos clases");
			php.insertClassAtributo();//CARGA LOS ATRIBUTOS DE LAS CLASES PARA EDICIÓN DE VISTAS MODELADAS
			bar.updateProgress(90, "Cargando vistas modeladas");
			php.insertPages(m.getPages());
			bar.updateProgress(95, "Cargando menus");
			php.insertMenus(m.getMenus());
			bar.updateProgress(100, "Proyecto PHP creado!");
			*/
			windowMensajeInfo("Compilado con exito!");
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
	
	//BUSCA EL NOMBRE DEL ARCHIVO XML DENTRO DEL DIRECTORIO DEL PROYECTO
	public static String getCurrentFile(String currentDirectory){
		String fileModel = null;
		File folder = new File(currentDirectory);
		File[] listOfFiles = folder.listFiles();
	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
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
	
	//VENTANA CON INFORMACION
	public static void windowMensajeError(String msn){
		MessageDialog.openError(
			window.getShell(),
			"CompilerPHP",
			msn);
	}
	

		
}