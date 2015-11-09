package compilerphp.actions;

public class Restriccion{

	protected String msj_error;
	protected String nombre;
	protected String operator;
	protected String service;
	protected int clase;
	protected int atributo;
	protected String valor;
	
	
	public Restriccion(String msj, String nombre_, String operator_, String service_, String valor_, int clase_, int atributo_){
		this.msj_error=msj;
		this.nombre=nombre_;
		this.operator=operator_;
		this.service=service_;
		this.valor=valor_;
		this.clase=clase_;
		this.atributo=atributo_;
	}
	
	public String getMsjError(){
		return this.msj_error;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getOperator(){
		return this.operator;
	}
	
	public String getService(){
		return this.service;
	}
	
	public String getValor(){
		return this.valor;
	}
	
	public int getClase(){
		return this.clase;
	}
	
	public int getAtributo(){
		return this.atributo;
	}
	
}