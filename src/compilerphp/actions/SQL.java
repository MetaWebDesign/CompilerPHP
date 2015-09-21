package compilerphp.actions;

import java.util.ArrayList;
import java.util.List;



//STRUCTURA SENTENCIAS SQL
public class SQL{
	//DATOS EXTRAIDOS DEL MODELO
	private List<Tabla> tablas;
	private List<View> views;
	
	//CONSTRUCTOR
	public SQL(){
		tablas = new ArrayList<Tabla>(); //CONTIENE A LA(S) LLAVE(S) FORANEAS
		views = new ArrayList<View>();
	}

	public void addTabla(Tabla t){
		tablas.add(t);
	}
	
	public List<Tabla> getTablas(){
		return tablas;
	}
	
	public void addView(View v){
		views.add(v);
	}
	
	public List<View> getViews(){
		return this.views;
	}
	
	public Tabla getTabla(String tabla_name){
		for(Tabla t : this.tablas) {
			if(t.getNombre().equals(tabla_name)){
				return t;
			}
		}
		return null;
	}
	
	public Tabla getTablaByInt(int table_position){
		return this.tablas.get(table_position);
	}
	
}