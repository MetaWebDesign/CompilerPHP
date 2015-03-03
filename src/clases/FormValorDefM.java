package clases;

public class FormValorDefM {
	private boolean EsString;
	private boolean EsInt;
	private String m_nombre;
	private String m_valor;
	private boolean m_estado;
	
	public FormulaM Formula = new FormulaM("","","","","");  
	
	FormValorDefM(boolean EsString, boolean EsInt, String m_nombre, String m_valor, boolean m_estado){
		this.EsString = EsString;
		this.EsInt = EsInt;
		this.m_nombre = m_nombre;
		this.m_valor = m_valor;
		this.m_estado = m_estado;
	}
	
	public void addFormula(String _Id, String id_Formula, String EsValida, String TipoFormula, String FormulaString){
		Formula.setFormulaM(_Id, id_Formula, EsValida, TipoFormula, FormulaString);
	}
	
	public boolean getEsString(){
		return this.EsString;
	}
	
	public void setEsString(boolean EsString){
		this.EsString = EsString;
	}
	
	public boolean getEsInt(){
		return this.EsInt;
	}
	
	public void setEsInt(boolean EsInt){
		this.EsInt = EsInt;
	}
	
	public String getm_nombre(){
		return this.m_nombre;
	}
	
	public void setm_nombre(String m_nombre){
		this.m_nombre = m_nombre;
	}
	
	public String getm_valor(){
		return this.m_valor;
	}
	
	public void setm_valor(String m_valor){
		this.m_valor = m_valor;
	}
	
	public void setm_estado(boolean m_estado){
		this.m_estado = m_estado;
	}
	
	public boolean getm_estado(){
		return this.m_estado;
	}
	
}
