package clases;

public class PPresentacionM {
	private String _Id;
	private String Nombre;
	private String tipoPP;
	public AliasM Alias = new AliasM("","");
	private String MsgAyuda;
	private String Observacions;
	private String RefNavegaOfertadaM;
	private String RefAccionesOfertaM;
	private String RefConjVisualM;
	
	public HerPPresentacionM HerPPresentacion = new HerPPresentacionM();
	
	PPresentacionM(String _Id, String Nombre, String tipoPP){
		this._Id = _Id;
		this.Nombre = Nombre;
		this.tipoPP = tipoPP;
	}
	
	public String getId(){
		return this._Id;
	}
	
	public String getNombre(){
		return this.Nombre;
	}
	
	public String gettipoPP(){
		return this.tipoPP;
	}
	
	public AliasM getAlias(){
		return this.Alias;
	} 
	//Faltan objetos
	
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

	public String getRefNavegaOfertadaM() {
		return RefNavegaOfertadaM;
	}

	public void setRefNavegaOfertadaM(String refNavegaOfertadaM) {
		RefNavegaOfertadaM = refNavegaOfertadaM;
	}

	public String getRefAccionesOfertaM() {
		return RefAccionesOfertaM;
	}

	public void setRefAccionesOfertaM(String refAccionesOfertaM) {
		RefAccionesOfertaM = refAccionesOfertaM;
	}

	public String getRefConjVisualM() {
		return RefConjVisualM;
	}

	public void setRefConjVisualM(String refConjVisualM) {
		RefConjVisualM = refConjVisualM;
	}
}
