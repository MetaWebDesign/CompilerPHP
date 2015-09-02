package compilerphp.actions;

import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * UBICA LA POSICIÓN DE EL PROGRAMA,
 * PARA ENCONTRAR EL PATH CORRECTO A LOS PROYECTOS, 
 * DEPENDENCIAS DEL SISTEMA 
 */

public class Locate{
	String path;
	public Locate(){
		//OBTIENE LA RUATA Y EL NOMBRE DEL ARCHIVO DEL MODELO
		String workingDir = System.getProperty("user.dir");
		Path p = Paths.get(workingDir);
		this.path=p.getParent().toString();
	}
	
	public String getPath(){
		return this.path;
	}
}