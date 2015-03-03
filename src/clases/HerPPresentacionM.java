package clases;

import java.util.ArrayList;
import java.util.List;

public class HerPPresentacionM {
	public List ElemAgrup = new ArrayList<ElemAgrupM>();
	
	HerPPresentacionM(){}
	
	public void addElemAgrup(String _Id){
		ElemAgrup.add(new ElemAgrupM(_Id));
	}
	
	public Object getElemAgrup(int i){
		return ElemAgrup.get(i);
	}
}
