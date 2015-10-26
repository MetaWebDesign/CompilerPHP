package compilerphp.actions;

public class LinkCRUD{
	protected String name;
	protected String service;
	protected int clase;
	
	public LinkCRUD(String name_, String service_, int clase_){
		this.name=name_;
		this.service=service_;
		this.clase=clase_;
	}
	
	public String  getName(){
		return this.name;
	}
	
	public String getService(){
		return this.service;
	}
	
	public int getClase(){
		return this.clase;
	}
}