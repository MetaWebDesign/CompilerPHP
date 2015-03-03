package clases;

public class AccionPDependenciaM {
	private String _Id;	
	public FormulaM Formula = new FormulaM("","","","","");
	
	public void addFormula(String _Id, String id_Formula, String EsValida, String TipoFormula, String FormulaString){
		Formula.setFormulaM(_Id, id_Formula, EsValida, TipoFormula, FormulaString);
	}
}
