package compilerphp.actions;


public class Atributo{
	
	private String nombre;
	private boolean primaryKey=false;
	private boolean derived=false;
	//private String[] type={"numeric", "text", "date time"};
	private String type;
	
	//Construcctor
	public Atributo(String nombrex, boolean pk, boolean der, String typex){
		this.nombre=nombrex;
		this.primaryKey=pk;
		this.derived=der;
		this.type=typex;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public boolean getPrimaryKey(){
		return this.primaryKey;
	}
	
	public boolean getDerived(){
		return this.derived;
	}
	
	public String getType(){
		return this.type;
	}
}