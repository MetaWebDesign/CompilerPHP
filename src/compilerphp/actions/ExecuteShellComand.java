package compilerphp.actions;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExecuteShellComand {

	public String path_bash="/CompilerPHP/src/bash/";
	//public int num_proyects=0;
	
	public static void main(String[] args) {

		ExecuteShellComand obj = new ExecuteShellComand();

		String domainName = "google.com";
		
		//in mac oxs
		String command = "ping -c 3 " + domainName;
		
		//in windows
		//String command = "ping -n 3 " + domainName;
		
		String output = obj.executeCommand(command);

		System.out.println(output);

	}

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
	
	public String[] getProyects(){
		System.out.println("getProyects()");
		Locate l=new Locate();
		String path=l.getPath();
		String[] proyectos=new String[2];
		int cont=0;
		//StringBuffer output = new StringBuffer();
		Process p;
		
		String command="ls -l"+path+"/runtime-EclipseApplication/ | awk {'print $9'}";
		try {
			//System.out.println("exec: "+command);
			p = Runtime.getRuntime().exec(command);
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
			e.printStackTrace();
		}
		return proyectos;
	}

}
