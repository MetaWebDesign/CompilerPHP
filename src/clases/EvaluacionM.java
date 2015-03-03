package clases;

public class EvaluacionM {
	private String _Id;
	private String id_EvaluacionM;
	private String Categoria;
	private String TipoCardinal;
	private String Observacions;
	private String RefEventoM;
	public FormulaM Formula = new FormulaM("","","","","");  
	
	public EvaluacionM(String _Id, String id_EvaluacionM, String Categoria, String TipoCardinal){
		this._Id = _Id;
		this.id_EvaluacionM = id_EvaluacionM;
		this.Categoria = Categoria;
		this.TipoCardinal = TipoCardinal;
	}
	
	public void addFormula(String _Id, String id_Formula, String EsValida, String TipoFormula, String FormulaString){
		Formula.setFormulaM(_Id, id_Formula, EsValida, TipoFormula, FormulaString);
	}
	
	public String getId() {
		return _Id;
	}
	public void setId(String _Id) {
		this._Id = _Id;
	}
	public String getId_EvaluacionM() {
		return id_EvaluacionM;
	}
	public void setId_EvaluacionM(String id_EvaluacionM) {
		this.id_EvaluacionM = id_EvaluacionM;
	}
	public String getCategoria() {
		return Categoria;
	}
	public void setCategoria(String categoria) {
		Categoria = categoria;
	}
	public String getTipoCardinal() {
		return TipoCardinal;
	}
	public void setTipoCardinal(String tipoCardinal) {
		TipoCardinal = tipoCardinal;
	}

	public String getObservacions() {
		return Observacions;
	}

	public void setObservacions(String observacions) {
		Observacions = observacions;
	}
}
