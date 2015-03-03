package clases;

import java.util.ArrayList;
import java.util.List;

public class NodoJerarquiaM {
	private String _Id;
	private String idNodoJerarquia;
	public AliasM Alias = new AliasM("","");
	private String Observacions;
	private String MsgAyuda;	
	private String RefPPresentacionM; 	
	public List NodoJerarquia = new ArrayList<NodoJerarquiaM>();
	
	public NodoJerarquiaM(String _Id, String idNodoJerarquia){
		this.setId(_Id);
		this.setIdNodoJerarquia(idNodoJerarquia);
	}
	
	public void addNodoJerarquia(String _Id, String idNodoJerarquia){
		NodoJerarquia.add(new NodoJerarquiaM(_Id, idNodoJerarquia));
	}
	
	public Object getNodoJerarquia(int i){
		return NodoJerarquia.get(i);
	}

	public String getId() {
		return _Id;
	}

	public void setId(String _Id) {
		this._Id = _Id;
	}

	public String getIdNodoJerarquia() {
		return idNodoJerarquia;
	}

	public void setIdNodoJerarquia(String idNodoJerarquia) {
		this.idNodoJerarquia = idNodoJerarquia;
	}

	public String getObservacions() {
		return Observacions;
	}

	public void setObservacions(String observacions) {
		this.Observacions = observacions;
	}

	public String getMsgAyuda() {
		return MsgAyuda;
	}

	public void setMsgAyuda(String msgAyuda) {
		MsgAyuda = msgAyuda;
	}

	public String getRefPPresentacionM() {
		return RefPPresentacionM;
	}

	public void setRefPPresentacionM(String refPPresentacionM) {
		RefPPresentacionM = refPPresentacionM;
	}

}
