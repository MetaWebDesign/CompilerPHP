package compilerphp.actions;

public class View{
	private String sql_view;
	private String tabla;
	private String nombre;
	
	public View(String sql, String tabla, String nombre){
		this.sql_view=sql;
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