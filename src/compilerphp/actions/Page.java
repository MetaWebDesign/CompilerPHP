package compilerphp.actions;

import java.util.List;


public class Page{
	protected String title;
	protected List<ViewAttribute> atributos;
	protected String typePresentation;
	
	public void setTitle(String title_){
		this.title=title_;
	}
	
	public void setAtributo(ViewAttribute atributo_){
		this.atributos.add(atributo_);
	}
	
	public void setTypePresentation(String typePresentation_){
		this.typePresentation=typePresentation_;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public List <ViewAttribute> getAtributos(){
		return this.atributos;
	}
	
	public String getTypePresentation(){
		return this.typePresentation;
	}
}