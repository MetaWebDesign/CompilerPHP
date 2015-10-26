package compilerphp.actions;

import java.util.ArrayList;
import java.util.List;

public class Menu{
	protected String name;
	protected String typeMenu;
	protected List <LinkCRUD> links_crud;
	
	public Menu(){
		this.links_crud=new ArrayList<LinkCRUD>();
	}
	
	public void setName(String name_){
		this.name=name_;
	}
	
	public void setTypeMenu(String type){
		this.typeMenu=type;
	}
	
	public void addLinkCRUD(LinkCRUD link){
		this.links_crud.add(link);
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getTypeMenu(){
		return this.typeMenu;
	}
	
	public List<LinkCRUD> getLinksCRUD(){
		return this.links_crud;
	}
}