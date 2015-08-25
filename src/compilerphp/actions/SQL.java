package compilerphp.actions;

import java.util.ArrayList;
import java.util.List;



//STRUCTURA SENTENCIAS SQL
public class SQL{
	//DATOS EXTRAIDOS DEL MODELO
	private List<Tabla> tablas;
	private List<View> views;
	private List<Relation> relations;
	
	//CONSTRUCTOR
	public SQL(){
		tablas = new ArrayList<Tabla>();
		views = new ArrayList<View>();
		relations = new ArrayList<Relation>();
	}
	
	public void addRelation(Relation r){
		relations.add(r);
	}
	
	public List<Relation> getRelations(){
		return this.relations;
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
}