package clases;

public class ElemConjVisual {
	private String _Id;
	private String id_Elementos;
	private String Orden;
	private String observacions;
	
	public FormulaM Formula = new FormulaM("","","","","");	
	public AliasM Alias = new AliasM("","");
	
	public ElemConjVisual(String _Id, String id_Elementos, String Orden){
		this.setId(_Id);
		this.setId_Elementos(id_Elementos);
		this.setOrden(Orden);
	}

	public String getId() {
		return _Id;
	}

	public void setId(String _Id) {
		this._Id = _Id;
	}

	public String getId_Elementos() {
		return id_Elementos;
	}

	public void setId_Elementos(String id_Elementos) {
		this.id_Elementos = id_Elementos;
	}

	public String getOrden() {
		return Orden;
	}

	public void setOrden(String orden) {
		Orden = orden;
	}

	public String getObservacions() {
		return observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}
	
	
}
