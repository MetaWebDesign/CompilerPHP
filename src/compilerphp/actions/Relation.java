package compilerphp.actions;

public class Relation{
	String nombre;
	String table_a;
	String table_b;
	String atributo_a;
	String atributo_b;
	
	public Relation(String n, String t_a, String t_b, String a_a, String a_b){
		this.nombre=n;
		this.table_a=t_a;
		this.table_b=t_b;
		this.atributo_a=a_a;
		this.atributo_b=a_b;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getTableA(){
		return this.table_a;
	}
	
	public String getTableB(){
		return this.table_b;
	}
	
	public String getAtributoA(){
		return this.atributo_a;
	}
	
	public String getAtributoB(){
		return this.atributo_b;
	}
}