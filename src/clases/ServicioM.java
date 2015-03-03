package clases;

import java.util.ArrayList;
import java.util.List;

public class ServicioM {
	private String _Id;
	private String Nombre;
	private String Tipo;
	private String DTipoEvento;
	private boolean UsoInterno;
	public AliasM Alias = new AliasM("","");
	private String Observacions;
	private String MsgAyuda;
	
	private String dont="";
	//public List RolArgsEntrada = new ArrayList<RolArgsEntradaM>();
	public List Argumento = new ArrayList<ArgumentoM>();
	
	public List RefEvaluacion = new ArrayList<String>();
	
	public FormulaM Formula = new FormulaM("","","","","");
	
	//public List HerTransaccion = new ArrayList<HerTransaccion>();
	public List RolPDependencia = new ArrayList<RolPDependenciaM>();
	public List PPresentacion = new ArrayList<PPresentacionM>();
	
	ServicioM(String _Id, String Nombre, String Tipo, String DTipoEvento, boolean UsoInterno){
		this._Id = _Id;
		this.Nombre = Nombre;
		this.Tipo = Tipo;
		this.DTipoEvento = DTipoEvento;
		this.UsoInterno = UsoInterno;
	}
	
	public void addFormula(String _Id, String id_Formula, String EsValida, String TipoFormula, String FormulaString){
		Formula.setFormulaM(_Id, id_Formula, EsValida, TipoFormula, FormulaString);
	}
	
	public void addArgumento(String _Id, String Nombre, String AdmiteNulos, String TipoArgumento, String TipoColeccion, String EsMultivaluado, String OrdenFormal, String OrdenVisual, String TipoImplicito, String EsDeEntrada, String SelecMultiple){
		Argumento.add(new ArgumentoM(_Id, Nombre, AdmiteNulos, TipoArgumento, TipoColeccion, EsMultivaluado, OrdenFormal, OrdenVisual, TipoImplicito, EsDeEntrada, SelecMultiple));
	}
	
	public String getId(){
		return this._Id;
	}
	
	public String getNombre(){
		return this.Nombre;
	}
	
	public String getTipo(){
		return this.Tipo;
	}
	
	public String getDTipoEvento(){
		return this.DTipoEvento;
	}
	
	public boolean getUsoInterno(){
		return this.UsoInterno;
	}
	
	public AliasM getAlias(){
		return this.Alias;
	} 
	
	public void setObservacions(String Observacions){
		this.Observacions = Observacions;
	}
	
	public String getObservacions(){
		return this.Observacions;
	}
	
	public void setMsgAyuda(String MsgAyuda){
		this.MsgAyuda = MsgAyuda;
	}
	
	public String getMsgAyuda(){
		return this.MsgAyuda;
	}
		
	public void addRolPDependencia(String _Id, String Agente, String Evento, boolean inferida){
		((RolPDependenciaM) RolPDependencia).addRolPDependencia(_Id, Agente, Evento, inferida);
	}
	
	public void addPPresentacion(String _Id, String Nombre, String tipoPP){
		PPresentacion.add(new PPresentacionM(_Id, Nombre, tipoPP));
	}
	
	public Object getArgumentoM(int i){
		return Argumento.get(i);
	}
	
	public Object getPPresentacionM(int i){
		return PPresentacion.get(i);
	}
	
	public Object getRefEvaluacion(int i) {
		return RefEvaluacion.get(i);
	}

	public void addRefEvaluacion(String refEvaluacion) {
		RefEvaluacion.add( refEvaluacion );
	}

	public String getDont() {
		return dont;
	}

	public void setDont(String dont) {
		this.dont = dont;
	}
	
}
