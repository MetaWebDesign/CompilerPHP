package clases;

public class HerArgObjValM {
	private String EsTHIS;
	private String Inicializa;
	private String RefClaseM;
	private String RefPPPobClaseM;
	
	HerArgObjValM(String EsTHIS, String Inicializa){
		this.setEsTHIS(EsTHIS);
		this.setInicializa(Inicializa);
	}

	public String getEsTHIS() {
		return EsTHIS;
	}

	public void setEsTHIS(String esTHIS) {
		EsTHIS = esTHIS;
	}

	public String getInicializa() {
		return Inicializa;
	}

	public void setInicializa(String inicializa) {
		Inicializa = inicializa;
	}

	public String getRefClaseM() {
		return RefClaseM;
	}

	public void setRefClaseM(String refClaseM) {
		RefClaseM = refClaseM;
	}

	public String getRefPPPobClaseM() {
		return RefPPPobClaseM;
	}

	public void setRefPPPobClaseM(String refPPPobClaseM) {
		RefPPPobClaseM = refPPPobClaseM;
	}
}
