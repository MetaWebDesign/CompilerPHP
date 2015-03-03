package clases;

import java.util.ArrayList;
import java.util.List;

public class VistaM {
	private String _Id;
	private String IdVista;
	private String Nombre;
	public List NodoJerarquia = new ArrayList<NodoJerarquiaM>();
	
	public VistaM(String _Id, String IdVista, String Nombre){
		this._Id = _Id;
		this.IdVista = IdVista;
		this.Nombre = Nombre;
	}
	
	public void addNodoJerarquia(String _Id, String idNodoJerarquia){
		NodoJerarquia.add(new NodoJerarquiaM(_Id, idNodoJerarquia));
	}
	
	public Object getNodoJerarquia(int i){
		return NodoJerarquia.get(i);
	}
	
	public String getId() {
		return _Id;
	}
	public void setId(String _Id) {
		this._Id = _Id;
	}
	public String getIdVista() {
		return IdVista;
	}
	public void setIdVista(String idVista) {
		IdVista = idVista;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	
}
