package compilerphp.actions;

public class Roles{
	protected String functionCreate;
	protected String functionUpdate;
	protected String functionDelete;
	protected String functionIndex;
	protected String functionView;
	protected String functionAdmin; 
	
	public Roles(){
		this.functionCreate="any";
		this.functionUpdate="any";
		this.functionDelete="any";
		this.functionIndex="any";
		this.functionView="any";
		this.functionAdmin="any";
	}
	
	public void setFCreate(String rol){
		this.functionCreate=rol;
	}
	
	public void setFUpdate(String rol){
		this.functionUpdate=rol;
	}
	
	public void setFDelete(String rol){
		this.functionDelete=rol;
	}
	public void setFIndex(String rol){
		this.functionIndex=rol;
	}
	
	public void setFView(String rol){
		this.functionView=rol;
	}
	public void setFAdmin(String rol){
		this.functionAdmin=rol;
	}
	
	public String getFCreate(){
		return this.functionCreate;
	}
	
	public String getFUpdate(){
		return this.functionUpdate;
	}
	
	public String getFDelete(){
		return this.functionDelete;
	}
	
	public String getFIndex(){
		return this.functionIndex;
	}

	public String getFView(){
		return this.functionView;
	}
	
	public String getFAdmin(){
		return this.functionAdmin;
	}	
}