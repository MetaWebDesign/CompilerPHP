package compilerphp.actions;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExecuteShellComand {

	public String path_bash="/CompilerPHP/src/bash/";
	
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
				System.out.println("-> "+line);
				output.append(line + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	}
	
	public int countProyects(){
		Locate l=new Locate();
		String path=l.getPath();
		String comando="bash "+path+path_bash+"contar_dir_proyecto.sh "+path+"/runtime-EclipseApplication/";
		String output=executeCommand(comando);
		
		int count=Integer.parseInt(output.substring(0, output.length()-1));//-1 POR QUE HAY UN SALTO DE LINEA EN EL STRING
		System.out.println("output: |"+count+"|");
		return count;
	}
	
	//OBTIENE TODOS LOS PROYECTOS DE MODELADOS (LISTA DE NOMBRES)
	public String[] getProyects(){
		System.out.println("getProyects()");
		Locate l=new Locate();
		String path=l.getPath();
		String[] proyectos=new String[countProyects()+1];
		int cont=0;
		Process p;
		
		String comando="bash "+path+path_bash+"get_proyects.sh "+path+"/runtime-EclipseApplication/";
		try {
			System.out.println("try :"+comando);
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
	
	public void start_proyect(String path_proyect){
		Locate l=new Locate();
		String path=l.getPath();
		String path_php="/CompilerPHP/src/php/proyect/";
		String comando="bash "+path+path_bash+"start_php.sh "+path+path_php+" "+path_proyect;
		System.out.println(comando);
		executeCommand(comando);
	}

}
