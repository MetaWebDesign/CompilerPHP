package compilerphp.actions;

import java.nio.file.Path;
import java.nio.file.Paths;



public class FileModel{
	
	
	private static String path;
	private static String file;
	
	public FileModel(){
		ExecuteShellComand obj = new ExecuteShellComand();
		String output = obj.executeCommand("locate *.metawebdesign");//UBICA EL ARCHIVO XML
		Path p = Paths.get(output);
		/*
		System.out.format("toString: %s%n", p.toString());
		System.out.format("getFileName: %s%n", p.getFileName());
		System.out.format("getName(0): %s%n", p.getName(0));
		System.out.format("getNameCount: %d%n", p.getNameCount());	
		System.out.format("subpath(0,2): %s%n", p.subpath(0,2));
		System.out.format("getParent: %s%n", p.getParent());
		System.out.format("getRoot: %s%n", p.getRoot());
		*/
		String currentDirectory=p.getParent().toString();//IDENTIFICA LA RUTA DEL XML
		String currentFile=p.getFileName().toString();
		//System.out.println(currentDirectory);
		file=currentFile.substring(0, currentFile.length()-1 );//GUARDO EL NOMBRE DEL ARCHIVO
		path=currentDirectory;//GUARDO LA RUTA AL ARCHIVO
	}

	public static void setFile(String f){
		file=f;
	}
	
	public static void setPath(String p){
		path=p;
	}
	
	public String getFile(){
		return file;
	}
	
	public String getPath(){
		return path;
	}
	
	/*
	public static String getCurrentFile(String currentDirectory){
		String fileModel = null;
		File folder = new File(currentDirectory);
		File[] listOfFiles = folder.listFiles();
	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	        //System.out.println("File " + listOfFiles[i].getName());
	        if(listOfFiles[i].getName().indexOf("metawebdesign")!=-1){
	        	int stop=listOfFiles[i].getName().indexOf(".");
	        	fileModel=listOfFiles[i].getName().substring(0, stop)+"metawebdesign";
	        }
	      } 
	    }
		return fileModel;
	}*/
}