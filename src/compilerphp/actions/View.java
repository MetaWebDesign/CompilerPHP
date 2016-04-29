package compilerphp.actions;

public class View{
	private String sql_view;
	private String tabla;
	private String nombre;
	
	public View(String sql_, String tabla_, String nombre_){
		this.sql_view=sql_;
		this.tabla=tabla_;
		this.nombre=nombre_;
	}
	
	public String getSQL(){
		return this.sql_view;
	}
	
	public void setView(String sql){
		this.sql_view=sql;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getTabla(){
		return this.tabla;
	}
}