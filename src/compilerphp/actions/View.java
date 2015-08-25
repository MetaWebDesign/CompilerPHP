package compilerphp.actions;

public class View{
	String nombre;
	String formula;
	String tabla_name;
	
	public View(String n, String f, String t){
		this.nombre=n;
		this.formula=f;
		this.tabla_name=t;
	}
	
	public View(){
		this.nombre="none";
		this.formula="none";
		this.tabla_name="none";		
	}
	
	public void setNombre(String n){
		this.nombre=n;
	}
	
	public void setFormula(String f){
		this.formula=f;
	}

	public void setTabla(String t){
		this.tabla_name=t;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getFormula(){
		return this.formula;
	}
	
	public String getTabla(){
		return this.tabla_name;
	}
}