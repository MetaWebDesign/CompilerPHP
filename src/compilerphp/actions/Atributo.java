package compilerphp.actions;


public class Atributo{
	
	private String nombre;
	private boolean primaryKey=false;
	private String type;
	private String typeModel;
	private boolean required;
	
	public Atributo(){
		this.nombre="none";
		this.primaryKey=false;
		this.type="text";
		this.typeModel="text";
		this.required=true;
	}
	
	//Construcctor
	/*
	 * Nombre
	 * Llave primaria?
	 * Atributo derivado?
	 * TypeData:test, varchar, date, time , etc
	 */
	public Atributo(String nombrex, boolean pk, boolean der, String type_, String typeModel_, boolean required_){
		
		this.nombre=nombrex;
		this.primaryKey=pk;
		this.type=type_;
		this.typeModel=typeModel_;
		this.required=required_;
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
	
	public String getTypeModel(){
		return this.typeModel;
	}
	
	public boolean getRequired(){
		return this.required;
	}
	
}