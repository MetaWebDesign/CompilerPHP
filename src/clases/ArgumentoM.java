//<ArgumentoM _Id="Clas_1402428588032496Ser_8Arg_1" IdArgumento="0" Nombre="p_thisadministrador" AdmiteNulos="0" TipoArgumento="O" TipoColeccion="0" EsMultivaluado="0" OrdenFormal="1" OrdenVisual="1" TipoImplicito="P_THIS" EsDeEntrada="1" SelecMultiple="0">

package clases;

import java.util.ArrayList;
import java.util.List;

public class ArgumentoM {
	private String _Id;
	private String Nombre;
	private String AdmiteNulos;
	private String TipoArgumento;
	private String TipoColeccion;
	private String EsMultivaluado;
	private String OrdenFormal;
	private String OrdenVisual;
	private String TipoImplicito;
	private String EsDeEntrada;
	private String SelecMultiple;	
	public AliasM Alias = new AliasM("","");
	private String Observacions;
	private String MsgAyuda;
	
	
	public FormValorDefM FormValorDef = new FormValorDefM(false, false, "", "", false);
	public List PDependencia = new ArrayList<PDependenciaM>();
	public HerArgObjValM HerArgObjValM = new HerArgObjValM("","");
	public HerArgDatoValM HerArgDatoValM = new HerArgDatoValM("","");
	
	ArgumentoM(String _Id, String Nombre, String AdmiteNulos, String TipoArgumento, String TipoColeccion, String EsMultivaluado, String OrdenFormal, String OrdenVisual, String TipoImplicito, String EsDeEntrada, String SelecMultiple){
		this._Id = _Id;
		this.Nombre = Nombre;
		this.AdmiteNulos = AdmiteNulos;
		this.TipoArgumento = TipoArgumento;
		this.TipoColeccion = TipoColeccion;
		this.EsMultivaluado = EsMultivaluado;
		this.OrdenFormal = OrdenFormal;
		this.OrdenVisual = OrdenVisual;
		this.TipoImplicito = TipoImplicito;
		this.EsDeEntrada = EsDeEntrada;
		this.SelecMultiple = SelecMultiple;
	}
	
	public String getId(){
		return this._Id;
	}
	
	public void setId(String _Id){
		this._Id = _Id;
	}
	
	public String getNombre(){
		return this.Nombre;
	}
	
	public void setNombre(String Nombre){
		this.Nombre = Nombre;
	}
	
	public String getTipoImplicito(){
		return this.TipoImplicito;
	}
	
	public void setTipoImplicito(String TipoImplicito){
		this.TipoImplicito = TipoImplicito;
	}
	
	public AliasM getAlias(){
		return this.Alias;
	} 
	
	public void addPDependencia(String _Id, String Agente, String Evento, boolean inferida){
		PDependencia.add(new PDependenciaM(_Id, Agente, Evento, inferida));
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

	public String getAdmiteNulos() {
		return AdmiteNulos;
	}

	public void setAdmiteNulos(String admiteNulos) {
		AdmiteNulos = admiteNulos;
	}

	public String getTipoArgumento() {
		return TipoArgumento;
	}

	public void setTipoArgumento(String tipoArgumento) {
		TipoArgumento = tipoArgumento;
	}

	public String getTipoColeccion() {
		return TipoColeccion;
	}

	public void setTipoColeccion(String tipoColeccion) {
		TipoColeccion = tipoColeccion;
	}

	public String getEsMultivaluado() {
		return EsMultivaluado;
	}

	public void setEsMultivaluado(String esMultivaluado) {
		EsMultivaluado = esMultivaluado;
	}

	public String getOrdenFormal() {
		return OrdenFormal;
	}

	public void setOrdenFormal(String ordenFormal) {
		OrdenFormal = ordenFormal;
	}

	public String getOrdenVisual() {
		return OrdenVisual;
	}

	public void setOrdenVisual(String ordenVisual) {
		OrdenVisual = ordenVisual;
	}
	
	

	
}
