/**

<AtributoM _Id="Clas_1402428588032496Atr_1" IdAtributo="0" Nombre="id_administrador" Tipo="C" TipoDato="Autonumerico" Tamano="0" EsTemporal="0" AdmiteNulos="0" PedirAlCrear="1" TipoColeccion="" OrdenDefinicion="1">
	<Observacions>administrador's identification function</Observacions>
	<Alias _RefInferencia="Clas_1402428588032496Atr_1" AliasInf="0">id_administrador</Alias>
	<MsgAyuda />
	<ListaDe.Rol.Contadores>
		<ContadorM Valor="0" />
	</ListaDe.Rol.Contadores>
</AtributoM>

 */

package clases;

import java.util.*;

public class AtributoM{
	private String _Id;
	private int IdAtributo;
	private String Nombre;
	private String Tipo;
	private String TipoDato;
	private String Tamano;
	private boolean EsTemporal;
	private boolean AdmiteNulos;
	private boolean PedirAlCrear;
	public AliasM Alias = new AliasM("","");
	public FormValorDefM FormValorDef = new FormValorDefM(false, false, "", "", false);
	public List RefRolEvaluaciones = new ArrayList<RefEvaluacionM>();
	private String Observacions;
	private String MsgAyuda;
	public List RefEvaluacion = new ArrayList<String>();
	
	
	public List Derivaciones = new ArrayList<String>();
	

	public AtributoM(String _Id, int IdAtributo, String Nombre, String Tipo, String TipoDato, String Tamano, boolean EsTemporal, boolean AdmiteNulos, boolean PedirAlCrear){	
		this._Id=_Id;
		this.IdAtributo = IdAtributo;
		this.Nombre = Nombre;
		this.Tipo = Tipo;
		this.TipoDato = TipoDato;
		this.Tamano = Tamano;
		this.EsTemporal = EsTemporal;
		this.AdmiteNulos = AdmiteNulos;
		this.PedirAlCrear = PedirAlCrear;
	}	

	public String getId(){
		return this._Id;
	}
	
	public int getIdAtributo(){
		return this.IdAtributo;
	}
	
	public String getNombre(){
		return this.Nombre;
	}
	
	public String getTipo(){
		return this.Tipo;
	}
	
	public String getTipoDato(){
		return this.TipoDato;
	}
	
	public String getTamano(){
		return this.Tamano;
	}
	
	public boolean getEsTemporal(){
		return this.EsTemporal;
	}
	
	public boolean getAdmiteNulos(){
		return this.AdmiteNulos;
	}
	
	public boolean getPedirAlCrear(){
		return this.PedirAlCrear;
	}
	
	public AliasM getAlias(){
		return this.Alias;
	} 
	
			
	//Faltan objetos
	
	public void setObservacions(String Observacions){
		this.Observacions = Observacions;
	}
	
	public String getObservacions(){
		return this.Observacions;
	}
	
	public void setMsgAyuda(String MsgAyuda){
		this.MsgAyuda = MsgAyuda;
	}
	
	public String getMsgAyuda(){
		return this.MsgAyuda;
	}

	public Object getDerivaciones(int i){
		return Derivaciones.get(i);
	}
	
	public Object getRefRolEvaluaciones(int i){
		return RefRolEvaluaciones.get(i);
	}

	public Object getRefEvaluacion(int i) {
		return RefEvaluacion.get(i);
	}

	public void addRefEvaluacion(String refEvaluacion) {
		RefEvaluacion.add( refEvaluacion );
	}
	
}