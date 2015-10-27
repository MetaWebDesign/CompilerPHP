package compilerphp.actions;

public class LinkView{
	protected String name;
	protected int id_view;
	
	public LinkView(String name_, int id_view_){
		this.name=name_;
		this.id_view=id_view_;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getIdView(){
		return this.id_view;
	}
}