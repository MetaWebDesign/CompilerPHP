package clases;

public class ElemAgrupM {
	private String _Id;
	public AliasM Alias = new AliasM("","");
	private String MsgAyuda;
	private String Observacions;
	public RefRolArgumentoM RefRolArgumento = new RefRolArgumentoM("");
	
	ElemAgrupM(String _Id){
		this.setId(_Id);
	}
	
	public void setAlias(String _RefInferencia, String Alias){		
		this.Alias = new AliasM(_RefInferencia, Alias);
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
	
	public Object getRefRolArgumento(){
		return RefRolArgumento;
	}
	
	public AliasM getAlias(){
		return this.Alias;
	}

	public String getId() {
		return _Id;
	}

	public void setId(String _Id) {
		this._Id = _Id;
	} 
}
