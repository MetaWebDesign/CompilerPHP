package compilerphp.actions;


public class ViewAttribute{
	
	protected int clase;
	protected int atributo;
	protected String typePresentation;
	
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
	
	public String getTypePresentation(){
		return this.typePresentation;
	}
	
	
	public void setTypePresentation(String typePresentation_){
		this.typePresentation=typePresentation_;
	}
}