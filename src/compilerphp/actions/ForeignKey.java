package compilerphp.actions;

public class ForeignKey{
	String nombre;
	int destination;
	int atributo_destination;
	
	public ForeignKey(String n, int d, int a_d){
		this.nombre=n;
		this.destination=d;
		this.atributo_destination=a_d;
	}
	
	public ForeignKey(){
		this.nombre="none";
		this.destination=0;
		this.atributo_destination=0;
	}
	
	public String getNombre(){
		return this.nombre;
	}

	public int getDestination(){
		return this.destination;
	}
	
	public int getAtributoDestination(){
		return this.atributo_destination;
	}
}