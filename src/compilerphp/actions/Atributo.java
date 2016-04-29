package compilerphp.actions;


public class Atributo{
	
	private String nombre;
	private boolean primaryKey=false;
	private String type;
	private String typeModel;
	private boolean required;
	
	private boolean derived;	
	private String tabla_name;
	private String formula;
	
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
		this.derived=false;
	}
	
	public Atributo(String name, String form, String tabla, String type){
		this.nombre=name;
		this.formula=form;
		this.tabla_name=tabla;
		this.type=type;
		this.derived=true;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getFormula(){
		return this.formula;
	}
	
	public String getTabla(){
		return this.tabla_name;
	}
	
	public boolean getDerivedEDO(){
		return this.derived;
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