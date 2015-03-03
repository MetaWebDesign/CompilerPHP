package clases;

import java.util.ArrayList;
import java.util.List;

public class ConjVisualM {
	private String _Id;
	private String id_ConjVisualM;
	private String Nombre;
	
	private String observacions;
	
	public AliasM Alias = new AliasM("","");
	public List ElemConjVisual = new ArrayList<ElemConjVisual>();
	
	public ConjVisualM(String _Id, String id_ConjVisualM, String Nombre){
		this._Id = _Id;
		this.id_ConjVisualM = id_ConjVisualM;
		this.Nombre = Nombre;
	}
	
	public void addElemConjVisual(String _Id, String id_Elementos, String Orden){
		ElemConjVisual.add(new ElemConjVisual(_Id, id_Elementos, Orden));
	}
	
	public Object getElemConjVisual(int i){
		return ElemConjVisual.get(i);
	}

	public String getId() {
		return _Id;
	}

	public void setId(String _Id) {
		this._Id = _Id;
	}

	public String getId_ConjVisualM() {
		return id_ConjVisualM;
	}

	public void setId_ConjVisualM(String id_ConjVisualM) {
		this.id_ConjVisualM = id_ConjVisualM;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getObservacions() {
		return observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}
	

}
 