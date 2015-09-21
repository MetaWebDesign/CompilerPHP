package compilerphp.actions;

import java.util.ArrayList;
import java.util.List;


public class Tabla{
	
	private String nombre;
	private List<Atributo> atributos;
	private List<ForeignKey> llavesForaneas;
	
	public Tabla(){
		this.atributos = new ArrayList<Atributo>();
		this.llavesForaneas = new ArrayList<ForeignKey>();
	}
	
	public void setNombre(String n){
		this.nombre=n;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void addAtributo(Atributo a){
		this.atributos.add(a);
	}
	
	public List<Atributo> getAtributos(){
		return this.atributos;
	}
	
	public void addForeignKey(ForeignKey f){
		this.llavesForaneas.add(f);
	}
	
	public List<ForeignKey> getForeignKeys(){
		return this.llavesForaneas;
	}
	
	public Atributo getPrimaryKey(){
		for (Atributo a: this.atributos){
			if(a.getPrimaryKey()){
				return a;
			}
		}
		return null;
	}
	
	public String findNameAttribute(){
		String name="";
		for (Atributo a: this.atributos){
			if(a.getNombre().indexOf("name") != -1 || a.getNombre().indexOf("nombre") != -1){
				name=a.getNombre();
			}
		}
		return name;
	}
}