package compilerphp.actions;


public class ViewAttribute{
	
	protected int clase;
	protected int atributo;
	protected String typePresentation;
	protected String x_pos;
	protected int y_pos;
	
	public ViewAttribute(int clase_,  int atributo_, String typePresentation_, String x_pos_, int y_pos_){
		this.clase=clase_;
		this.atributo=atributo_;
		this.typePresentation=typePresentation_;
		this.x_pos=x_pos_;
		this.y_pos=y_pos_;
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
	
	public String getX_Pos(){
		return this.x_pos;
	}
	
	public int getY_Pos(){
		return this.y_pos;
	}
	
	public void setTypePresentation(String typePresentation_){
		this.typePresentation=typePresentation_;
	}
	
	
	public void setX_Pos(String x){
		this.x_pos=x;
	}
	
	public void setY_Pos(int y){
		this.y_pos=y;
	}
}