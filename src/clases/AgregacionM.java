package clases;

public class AgregacionM {
	private String _Id;
	private String AliasRCompuesta;
	private String RCompuesta;
	private String AliasRComponente;
	private String RComponente;
	private String minCompuesta;
	private String MaxCompuesta;
	private String minComponente;
	private String MaxComponente;
	private String EsDinamica;
	
	public AgregacionM(String RCompuesta, String RComponente, String minCompuesta, String MaxCompuesta,
			String minComponente, String MaxComponente, String EsDinamica){
		this.RCompuesta = RCompuesta;
		this.RComponente = RComponente;
		this.minCompuesta = minCompuesta;
		this.MaxCompuesta = MaxCompuesta;
		this.minComponente = minComponente;
		this.MaxComponente = MaxComponente;
		this.EsDinamica = EsDinamica; 
	}
	
	public String getId() {
		return _Id;
	}
	
	public void setId(String _Id) {
		this._Id = _Id;
	}
	
	public String getAliasRCompuesta() {
		return AliasRCompuesta;
	}
	
	public void setAliasRCompuesta(String aliasRCompuesta) {
		AliasRCompuesta = aliasRCompuesta;
	}
	
	public String getRCompuesta() {
		return RCompuesta;
	}
	
	public void setRCompuesta(String rCompuesta) {
		RCompuesta = rCompuesta;
	}
	
	public String getAliasRComponente() {
		return AliasRComponente;
	}
	
	public void setAliasRComponente(String aliasRComponente) {
		AliasRComponente = aliasRComponente;
	}
	
	public String getRComponente() {
		return RComponente;
	}
	
	public void setRComponente(String RComponente) {
		RComponente = RComponente;
	}

	public String getMinCompuesta() {
		return minCompuesta;
	}

	public void setMinCompuesta(String minCompuesta) {
		this.minCompuesta = minCompuesta;
	}

	public String getMaxCompuesta() {
		return MaxCompuesta;
	}

	public void setMaxCompuesta(String maxCompuesta) {
		MaxCompuesta = maxCompuesta;
	}

	public String getMinComponente() {
		return minComponente;
	}

	public void setMinComponente(String minComponente) {
		this.minComponente = minComponente;
	}

	public String getMaxComponente() {
		return MaxComponente;
	}

	public void setMaxComponente(String maxComponente) {
		MaxComponente = maxComponente;
	}

	public String getEsDinamica() {
		return EsDinamica;
	}

	public void setEsDinamica(String esDinamica) {
		EsDinamica = esDinamica;
	}
	
}
