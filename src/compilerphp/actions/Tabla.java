package compilerphp.actions;

import java.util.ArrayList;
import java.util.List;


public class Tabla{
	
	private String nombre;
	private List<Atributo> Atributos;
	
	public Tabla(){
		this.Atributos = new ArrayList<Atributo>();
	}
	
	public void setNombre(String n){
		this.nombre=n;
	}
	
	public void setAtributo(Atributo a){
		this.Atributos.add(a);
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public List<Atributo> getAtributos(){
		return this.Atributos;
	}
}