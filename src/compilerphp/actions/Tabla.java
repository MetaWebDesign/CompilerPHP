package compilerphp.actions;

import java.util.ArrayList;
import java.util.List;


public class Tabla{
	
	private String nombre;
	private List<Atributo> atributos;
	
	public Tabla(){
		this.atributos = new ArrayList<Atributo>();
	}
	
	public void setNombre(String n){
		this.nombre=n;
	}
	
	public void addAtributo(Atributo a){
		this.atributos.add(a);
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public List<Atributo> getAtributos(){
		return this.atributos;
	}
}