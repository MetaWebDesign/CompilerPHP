package clases;

public class HerArgDatoValM {
	private String TipoDato;
	private String Tamanyo;
	private String RefAtributoM;
	
	HerArgDatoValM(String TipoDato, String Tamanyo){
		this.setTipoDato(TipoDato);
		this.setTamanyo(Tamanyo);
	}

	public String getTipoDato() {
		return TipoDato;
	}

	public void setTipoDato(String tipoDato) {
		TipoDato = tipoDato;
	}

	public String getTamanyo() {
		return Tamanyo;
	}

	public void setTamanyo(String tamanyo) {
		Tamanyo = tamanyo;
	}

	public String getRefAtributoM() {
		return RefAtributoM;
	}

	public void setRefAtributoM(String refAtributoM) {
		RefAtributoM = refAtributoM;
	}


}
