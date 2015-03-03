package clases;

import java.util.*;

public class Interfaz {
	private String id_clase;	
	public List id_servicio = new ArrayList<String>();	
	public List atributos = new ArrayList<String>();
	
	public Interfaz(){
		this.id_clase = "";
	};
	
	public void addServicio(String servicio){
		id_servicio.add(servicio);
	}
	
	public Object getServicio(int i){
		return id_servicio.get(i);
	}
	
	
	public String getId_clase() {
		return id_clase;
	}
	public void setId_clase(String id_clase) {
		this.id_clase = id_clase;
	}
	
	public void addatributos(String atributo){
		atributos.add(atributo);
	}
	
	public Object getAtributos(int i){
		return atributos.get(i);
	}
	
}
