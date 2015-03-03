package clases;

import java.util.ArrayList;
import java.util.List;

public class ProyectoM {

	private String _Id;
	private String Nombre;
	private String descripcion;
	private String directorio;
	public List Clase = new ArrayList<ClaseM>();
	public List ClaseActora = new ArrayList<String>();
	//public List Agregacion = new ArrayList<AgregacionM>();
	//public List Herencia = new ArrayList<HerenciaM>();
	//public List EvCompartido = new ArrayList<EvCompartidoM>();
	public List Agregacion = new ArrayList<AgregacionM>();
	public List Vista = new ArrayList<VistaM>();
	//public List Cluster = new ArrayList<ClusterM>();
	
	public List RelationDB = new ArrayList<RelationDB>();
	
	public ProyectoM(){
		
	}
	
	public void set(String _Id, String Nombre){
		this._Id = _Id;
		this.Nombre = Nombre;
	}
	
	public void addClase(String _Id, String Nombre, String NombreEnBD, boolean EsTemporal, boolean EsLegada, boolean DTEPorDefecto, boolean EsAgenteAnonimo){
		Clase.add(new ClaseM(_Id, Nombre, NombreEnBD, EsTemporal, EsLegada, DTEPorDefecto, EsAgenteAnonimo));
		
	}
	
	public void addAgregacion(String RCompuesta, String RComponente, String minCompuesta, String MaxCompuesta, String minComponente, String MaxComponente, String EsDinamica){
		Agregacion.add(new AgregacionM(RCompuesta, RComponente, minCompuesta, MaxCompuesta, minComponente, MaxComponente, EsDinamica));
	}
	
	public void addVista(String _Id, String IdVista, String Nombre){
		Vista.add(new VistaM(_Id, IdVista, Nombre));
	}
	
	public void addRelation(String tables, String relation, String tablafk){
		RelationDB.add(new RelationDB(tables, relation, tablafk));
	}
	
	public String getNombre(){
		return this.Nombre;
	}
	
	public String getDirectorio(){
		return this.directorio; 
	}
	
	public void setDirectorio(String directorio){
		this.directorio = directorio;
	}
	
	public Object getClaseM(int i){
		return Clase.get(i);
	}
	
	public Object getAgregacionM(int i){
		return Agregacion.get(i);
	}
	
	public Object getVistaM(int i){
		return Vista.get(i);
	}
	
	public Object getRelation(int i){
		return RelationDB.get(i);
	}
	
}
