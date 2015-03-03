package clases;

public class FormulaM {
	private String _Id;
	private String id_Formula;
	private String EsValida;
	private String TipoFormula;
	private String FormulaString;
	private String m_Clase;
	private String m_ClaseFormula;
	
	public FormulaM(String _Id, String id_Formula, String EsValida, String TipoFormula, String FormulaString){
		this._Id = _Id;
		this.id_Formula = id_Formula;
		this.EsValida = EsValida;
		this.TipoFormula = TipoFormula;
		this.FormulaString = FormulaString;
	}
	
	public void setFormula(String _Id, String id_Formula, String EsValida, String TipoFormula){
		this._Id = _Id;
		this.id_Formula = id_Formula;
		this.EsValida = EsValida;
		this.TipoFormula = TipoFormula;	
	}
	
	public void setFormulaM(String _Id, String id_Formula, String EsValida, String TipoFormula, String FormulaString){
		this._Id = _Id;
		this.id_Formula = id_Formula;
		this.EsValida = EsValida;
		this.TipoFormula = TipoFormula;
		this.FormulaString = FormulaString;
	}
	
	public String getId() {
		return _Id;
	}
	public void setId(String _Id) {
		this._Id = _Id;
	}
	public String getId_Formula() {
		return id_Formula;
	}
	public void setId_Formula(String id_Formula) {
		this.id_Formula = id_Formula;
	}
	public String getEsValida() {
		return EsValida;
	}
	public void setEsValida(String esValida) {
		EsValida = esValida;
	}
	public String getTipoFormula() {
		return TipoFormula;
	}
	public void setTipoFormula(String tipoFormula) {
		TipoFormula = tipoFormula;
	}
	public String getFormulaString() {
		return FormulaString;
	}
	public void setFormulaString(String formulaString) {
		FormulaString = formulaString;
	}

	public String getM_Clase() {
		return m_Clase;
	}

	public void setM_Clase(String m_Clase) {
		this.m_Clase = m_Clase;
	}

	public String getM_ClaseFormula() {
		return m_ClaseFormula;
	}

	public void setM_ClaseFormula(String m_ClaseFormula) {
		this.m_ClaseFormula = m_ClaseFormula;
	}
}
