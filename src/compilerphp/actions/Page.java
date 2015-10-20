package compilerphp.actions;

import java.util.ArrayList;
import java.util.List;


public class Page{
	protected String title;
	protected List<ViewAttribute> atributos = new ArrayList<ViewAttribute>();

	
	public void setTitle(String title_){
		this.title=title_;
	}
	
	public void setAtributo(ViewAttribute atributo_){
		this.atributos.add(atributo_);
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public List <ViewAttribute> getAtributos(){
		return this.atributos;
	}
}