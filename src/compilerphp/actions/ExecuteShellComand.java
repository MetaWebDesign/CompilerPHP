package compilerphp.actions;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExecuteShellComand {

	public String path_bash="/CompilerPHP/src/bash/";
	
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
		System.out.println("output: "+output);
		return  Integer.parseInt(output);
	}

}
