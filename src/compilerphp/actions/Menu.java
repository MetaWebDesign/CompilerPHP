package compilerphp.actions;

import java.util.ArrayList;
import java.util.List;

public class Menu{
	protected String name;
	protected String typeMenu;
	protected int id_view;
	protected List <LinkCRUD> links_crud;
	protected List <LinkView> links_view;
	
	public Menu(){
		this.links_crud=new ArrayList<LinkCRUD>();
		this.links_view=new ArrayList<LinkView>();
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
	
	public void addLinkView(LinkView link){
		this.links_view.add(link);
	}
	
	public void setIdView(int id){
		this.id_view=id;
	}
	
	public int getIdView(){
		return this.id_view;
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
	
	public List<LinkView> getLinksView(){
		return this.links_view;
	}
}