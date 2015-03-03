package clases;

public class DerivacionM {

	private String _Id;
	private String id_DerivacionM;
	
	public FormulaM Formula = new FormulaM("","","","","");
	
	DerivacionM(String _Id, String id_DerivacionM){
		this.setId(_Id);
		this.setId_DerivacionM(id_DerivacionM);
	}

	public String getId() {
		return _Id;
	}

	public void setId(String _Id) {
		this._Id = _Id;
	}
	
	public void addFormula(String _Id, String id_Formula, String EsValida, String TipoFormula, String FormulaString){
		Formula.setFormulaM(_Id, id_Formula, EsValida, TipoFormula, FormulaString);
	}

	public String getId_DerivacionM() {
		return id_DerivacionM;
	}

	public void setId_DerivacionM(String id_DerivacionM) {
		this.id_DerivacionM = id_DerivacionM;
	}
	
	
}
