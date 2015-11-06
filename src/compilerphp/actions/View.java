package compilerphp.actions;

public class View{
	private String sql_view;
	
	public View(String sql){
		this.sql_view=sql;
	}
	
	public String getView(){
		return this.sql_view;
	}
	
	public void setView(String sql){
		this.sql_view=sql;
	}
}