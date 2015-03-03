package clases;

public class AliasM {
	private String _RefInferencia;
	private String Alias;
	
	public AliasM(){};
	
	public AliasM(String _RefInferencia, String Alias){
		this._RefInferencia = _RefInferencia;
		this.Alias = Alias;
	}
	
	public void setRefInferencia(String _RefInferencia){
		this._RefInferencia = _RefInferencia;
	}
	
	public String getRefInferencia(){
		return this._RefInferencia;
	}
	
	public void setAlias(String Alias){
		this.Alias = Alias;
	}
	
	public String getAlias(){
		return this.Alias;
	}

}
