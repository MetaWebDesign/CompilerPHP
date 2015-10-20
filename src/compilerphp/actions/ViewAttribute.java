package compilerphp.actions;


public class ViewAttribute{
	
	protected int clase;
	protected int atributo;
	
	public ViewAttribute(int clase_,  int atributo_){
		this.clase=clase_;
		this.atributo=atributo_;
	}
	
	public int getClase(){
		return this.clase;
	}
	
	public int getAtributo(){
		return this.atributo;
	}
}