package compilerphp.actions;

public class PHP{
	
	//String path_php="/CompilerPHP/src/php/";//RUTA DONDE ESTA EL CODIGO PHP BASE DEL SITIO WEB 
	String path_proyect;//RUTA DONDE ESTA EL CODIGO DEL PROYECTO PHP A GENERAR
	
	/*
	 * CONSTRUCTOR DE LA CLASE
	 *  path : direccion del proyecto PHP
	 */
	public PHP(String path){
		this.path_proyect=path;
	}
	
	//DESPLIEGA EL CODIGO BASE PHP EN LA CARPETA DEL PROYECTO PHP
	public void start(){
		ExecuteShellComand obj= new ExecuteShellComand();
		obj.start_proyect(path_proyect);
	}
	
	/*
	public void genCRUD(){
		
	}*/
	
	/*
	public void genModel(){
		
	}*/
	
	
	//public void genExtencion()

	
	//public void configure()
	
	
}