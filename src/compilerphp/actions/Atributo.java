package compilerphp.actions;


public class Atributo{
	
	private String nombre;
	private boolean primaryKey=false;
	private String type;
	
	public Atributo(){
		this.nombre="none";
		this.primaryKey=false;
		this.type="text";
	}
	
	//Construcctor
	public Atributo(String nombrex, boolean pk, boolean der, String typex){
		this.nombre=nombrex;
		this.primaryKey=pk;
		this.type=typex;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public boolean getPrimaryKey(){
		return this.primaryKey;
	}
	
	
	public String getType(){
		return this.type;
	}
	
}