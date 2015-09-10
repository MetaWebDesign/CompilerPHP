package compilerphp.actions;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * EJECUTAR SENTENCIAS SHELL
 * programa de apoyo para generar software utilizando 
 * el SO, para la generanci{on de codigo automatico
 * configuración del entorno como la BDD
 */
public class ExecuteShellComand {

	public String path_bash="/CompilerPHP/src/bash/";//RUTA DE LOS PROCEDIMIENTOS O RUTINAS DE APOYO A UTILIZAR
	
	//EJECUTAR UN COMANDO BASH DESDE EL SISTEMA
	public String executeCommand(String command) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			System.out.println("exec: "+command);
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";			
			while ((line = reader.readLine())!= null) {
				System.out.println("->"+line);
				output.append(line + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	}
	
	
	//CONTADOR DE PROYECTOS METAWEBDESIGN
	/*
	 * Explora el directorio de los proyectos del editor y los cuenta
	 */
	public int countProyects(){
		Locate l=new Locate();
		String path=l.getPath();
		String comando="bash "+path+path_bash+"contar_dir_proyecto.sh "+path+"/runtime-EclipseApplication/";
		String output=executeCommand(comando);
		
		int count=Integer.parseInt(output.substring(0, output.length()-1));//-1 POR QUE HAY UN SALTO DE LINEA EN EL STRING
		return count;
	}
	
	//OBTIENE TODOS LOS PROYECTOS DE MODELADOS (LISTA DE NOMBRES)
	/*
	 * Explora el directorio de los proyectos del editor grafico e identifica los nombres
	 */
	public String[] getProyects(){
		Locate l=new Locate();
		String path=l.getPath();
		String[] proyectos=new String[countProyects()+1];
		int cont=0;
		Process p;
		
		String comando="bash "+path+path_bash+"get_proyects.sh "+path+"/runtime-EclipseApplication/";
		try {
			p = Runtime.getRuntime().exec(comando);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";			
			while ((line = reader.readLine())!= null) {
				System.out.println("-> "+line);
				proyectos[cont]=line;
				//output.append(line + "\n");
				cont++;
			}
		} catch (Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
		}
		return proyectos;
	}

	/*
	 * INICIAR PROYECTO PHP
	 * copia la base de el sitio web php en la carpeta del proyecto
	 */
	public void start_proyect(String path_proyect){
		Locate l=new Locate();
		String path=l.getPath();
		String path_php="/CompilerPHP/src/php/proyect/";
		String comando="bash "+path+path_bash+"start_php.sh "+path+path_php+" "+path_proyect;
		System.out.println(comando);
		executeCommand(comando);
	}
	
	
	//GENERA EL MODELO DE UNA CLASE
	public void gen_model(String model){
		Locate l=new Locate();
		String path=l.getPath();
		String path_php="/CompilerPHP/src/php/proyect/";
		String comando="bash "+path+path_bash+"gen_model.sh "+path+path_php+"/proyect/ "+model;
		System.out.println(comando);
		executeCommand(comando);
	}
	
	
	//RESPALDAR UN ARCHIVO 
	public void backup(String path_file){
		Locate l=new Locate();
		String path=l.getPath();
		String comando="bash "+path+path_bash+"buckup.sh "+path_file;
		System.out.println(comando);
		executeCommand(comando);
	}

}
