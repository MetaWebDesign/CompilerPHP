package clases;

import java.util.ArrayList;
import java.util.List;

public class RolPDependenciaM {
public List PDependencia = new ArrayList<PDependenciaM>();
	
	RolPDependenciaM(){}
	
	public void addRolPDependencia(String _Id, String Agente, String Evento, boolean inferida){
		PDependencia.add(new PDependenciaM(_Id, Agente, Evento, inferida));
	}
}
