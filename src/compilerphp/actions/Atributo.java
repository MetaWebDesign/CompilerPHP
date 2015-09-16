package compilerphp.actions;


public class Atributo{
	
	private String nombre;
	private boolean primaryKey=false;
	private String type;
	private String typeModel;
	
	public Atributo(){
		this.nombre="none";
		this.primaryKey=false;
		this.type="text";
		this.typeModel="text";
	}
	
	//Construcctor
	/*
	 * Nombre
	 * Llave primaria?
	 * Atributo derivado?
	 * TypeData:test, varchar, date, time , etc
	 */
	public Atributo(String nombrex, boolean pk, boolean der, String type_, String typeModel_){
		this.nombre=nombrex;
		this.primaryKey=pk;
		this.type=type_;
		this.typeModel=typeModel_;
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
	
}