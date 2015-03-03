package clases;

public class PDependenciaM {
	private String _Id;
	private String Agente;
	private String Evento;
	private boolean inferida;
	
	PDependenciaM(String _Id, String Agente, String Evento, boolean inferida){
		this._Id = _Id;
		this.Agente = Agente;
		this.Evento = Evento;
		this.inferida = inferida;
	}
	
	public String getId(){
		return this._Id;
	}
	
	public String getAgente(){
		return this.Agente;
	}
	
	public String getEvento(){
		return this.Evento;
	}
	
	public boolean getinferida(){
		return this.inferida;
	}
	
}
