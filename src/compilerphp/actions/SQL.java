package compilerphp.actions;

import java.util.ArrayList;
import java.util.List;



//STRUCTURA SENTENCIAS SQL
public class SQL{
	//DATOS EXTRAIDOS DEL MODELO
	private List<Tabla> tablas;

	//CONSTRUCTOR
	public SQL(){
		tablas = new ArrayList<Tabla>();
	}
	
	public void addTabla(Tabla t){
		tablas.add(t);
	}
	
	public List<Tabla> getTablas(){
		return tablas;
	}
}