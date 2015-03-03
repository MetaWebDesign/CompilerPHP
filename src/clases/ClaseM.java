//<ClaseM _Id="Clas_1402428588032496" IdClase="0" Nombre="administrador" NombreEnBD="administrador" EsTemporal="0" EsLegada="0" DTEPorDefecto="1" EsAgenteAnonimo="0">

package clases;

import java.util.*;

public class ClaseM {
	private String _Id; 
	private String Nombre;
	private String NombreEnBD;	
	private boolean EsTemporal;
	private boolean EsLegada;
	private boolean DTEPorDefecto;
	private boolean EsAgenteAnonimo;	
	//private int aLength; //largo de lista atributos arr.size()
	private String IsAgent = "";
	
	private String Agent = "";
	public List Interfaz = new ArrayList<Interfaz>();
	
	public List ForeignKeys = new ArrayList<String>();
	public List ArrayConstraint = new ArrayList<String>();
	public List Indexes = new ArrayList<String>();
	public List NombresAtributos = new ArrayList<String>();
	
	//List of attributes in the DB
	public List Insert = new ArrayList<String>();
	
	public List Derivaciones = new ArrayList<DerivacionM>();
	
	public List Atributos = new ArrayList<AtributoM>();
	public List Evaluaciones = new ArrayList<EvaluacionM>();
	public List Servicios = new ArrayList<ServicioM>();
	public List ConjVisual = new ArrayList<ConjVisualM>();
	public List PPresentacion = new ArrayList<PPresentacionM>();
	public List ElemAcOfertadas = new ArrayList<String>();
	
	public AliasM Alias = new AliasM("","");
	private String Observacions;
	private String MsgAyuda;
	private String RefAtributoFIM;
	
	public String SQLPOB = "";
	
	public ClaseM(String _Id, String Nombre, String NombreEnBD, boolean EsTemporal, boolean EsLegada, boolean DTEPorDefecto, boolean EsAgenteAnonimo){	
		this._Id=_Id;
		this.Nombre = Nombre;
		this.NombreEnBD = NombreEnBD;
		this.EsTemporal = EsTemporal;
		this.EsLegada = EsLegada;
		this.DTEPorDefecto = DTEPorDefecto;
		this.EsAgenteAnonimo = EsAgenteAnonimo;	
		this.Agent = "0";
	}
	
	public void addAtributo(String _Id, int IdAtributo, String Nombre, String Tipo, String TipoDato, String Tamano, boolean EsTemporal, boolean AdmiteNulos, boolean PedirAlCrear){
		Atributos.add(new AtributoM(_Id, IdAtributo, Nombre, Tipo, TipoDato, Tamano, EsTemporal, AdmiteNulos, PedirAlCrear));		
	}
	
	public void addServicio(String _Id, String Nombre, String Tipo, String DTipoEvento, boolean UsoInterno){
		Servicios.add(new ServicioM(_Id, Nombre, Tipo, DTipoEvento, UsoInterno));
	}
	
	public void addEvaluacion(String _Id, String id_EvaluacionM, String Categoria, String TipoCardinal){
		Evaluaciones.add(new EvaluacionM(_Id, id_EvaluacionM, Categoria, TipoCardinal));
	}
	
	public void addConjVisual(String _Id, String id_ConjVisualM, String Nombre){
		ConjVisual.add(new ConjVisualM(_Id, id_ConjVisualM, Nombre));
	}	
	
	public void addElemAcOfertadas(String ElemAcOfertadas){
		this.ElemAcOfertadas.add(ElemAcOfertadas);
	}
	
	public void addDerivacionM(String _Id, String id_DerivacionM){
		Derivaciones.add(new DerivacionM(_Id, id_DerivacionM));
	}	
	
	public void addPPresentacion(String _Id, String Nombre, String tipoPP){
		PPresentacion.add(new PPresentacionM(_Id, Nombre, tipoPP));
	}
	
	public void addInterfazM(){
		Interfaz.add(new Interfaz());
	}
	
	public String getId(){
		return this._Id;
	}
	
	public String getNombre(){
		return this.Nombre;
	}
	
	public String getNombreEnBD(){
		return this.NombreEnBD;
	}	
	
	public Object getDerivacionM(int i){
		return Derivaciones.get(i);
	}
		
	public Object getAtributoM(int i){
		return Atributos.get(i);
	}
	
	public Object getServicioM(int i){
		return Servicios.get(i);
	}
	
	public Object getInsert(int i){
		return Insert.get(i);
	}
	
	public Object getEvaluacionM(int i){
		return Evaluaciones.get(i);
	}
	
	public Object getConjVisualM(int i){
		return ConjVisual.get(i);
	}
	
	public Object getElemAcOfertadas(int i){
		return ElemAcOfertadas.get(i);
	}
	
	public Object getPPresentacionM(int i){
		return PPresentacion.get(i);
	}
	
	public Object getInterfazM(int i){
		return Interfaz.get(i);
	}
	
	public boolean getEsTemporal(){
		return this.EsTemporal;
	}
	
	public boolean getEsLegada(){
		return this.EsLegada;
	}
	
	public boolean getDTEPorDefecto(){
		return this.DTEPorDefecto;
	}
	
	public boolean getEsAgenteAnonimo(){
		return this.EsAgenteAnonimo;
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

	public String getRefAtributoFIM() {
		return RefAtributoFIM;
	}

	public void setRefAtributoFIM(String refAtributoFIM) {
		RefAtributoFIM = refAtributoFIM;
	}

	public String getIsAgent() {
		return IsAgent;
	}

	public void setIsAgent(String isAgent) {
		IsAgent = isAgent;
	}

	public String getAgent() {
		return Agent;
	}

	public void setAgent(String agent) {
		Agent = agent;
	}	
	
}
