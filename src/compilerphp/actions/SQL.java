package compilerphp.actions;

import java.util.ArrayList;
import java.util.List;



//STRUCTURA SENTENCIAS SQL
public class SQL{
	//DATOS EXTRAIDOS DEL MODELO
	private List<Tabla> tablas;

	//CONSTRUCTOR
	public SQL(){
		this.tablas = new ArrayList<Tabla>();
	}
	
	public void addTabla(Tabla t){
		this.tablas.add(t);
	}
	
	public List<Tabla> getTablas(){
		return this.tablas;
	}
}