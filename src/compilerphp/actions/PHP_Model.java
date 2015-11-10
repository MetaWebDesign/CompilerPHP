package compilerphp.actions;

import java.io.FileWriter;
import java.io.IOException;

/*
 * Generador de Modelos Yii
 * - carga las restricciones modeladas
 */
public class PHP_Model{
	
	protected Tabla tabla;
	protected String path_proyect;
	
	public PHP_Model(Tabla tabla_, String path) {
		this.tabla=tabla_;
		this.path_proyect=path;
	}
	
	public void write() throws IOException{
		FileWriter php_model = null;
		String model="<?php\n";
		model=model+"namespace app\\models;\n";
		model=model+"use Yii;\n";
		model=model+"/**\n";
		model=model+" * This is the model class for table \""+this.tabla.getNombre()+"\".\n";
		model=model+" *\n";
		for(Atributo atributo : this.tabla.getAtributos()){
			model=model+" * @property "+atributo.getType()+" "+atributo.getNombre()+"\n";
		}
		model=model+" *\n";
		for(ForeignKey fk : tabla.getForeignKeys()){
			model=model+" * @property "+fk.getDestination()+" "+fk.getNombre()+"\n";
		}
		
		model=model+" */\n";
		model=model+"class "+this.tabla.getNombre()+" extends \\yii\\db\\ActiveRecord\n";
		model=model+"{\n";
		model=model+"    /**\n";
		model=model+"     * @inheritdoc\n";
		model=model+"     */\n";
		model=model+"    public static function tableName()\n";
		model=model+"    {\n";
		model=model+"        return '"+this.tabla.getNombre()+"';\n";
		model=model+"    }\n";

		model=model+"    /**\n";
		model=model+"     * @inheritdoc\n";
		model=model+"     */\n";
		model=model+"    public function rules()\n";
		model=model+"    {\n";
		model=model+"        return [\n;";
		int id_atributo=0;
		for(Atributo atributo : this.tabla.getAtributos()){
			if(this.tabla.checkRestriccion(id_atributo)){ 
				Restriccion restriccion = this.tabla.getRestriccion(id_atributo);
				
				if(restriccion.getOperator().equals("menor") || restriccion.getOperator().equals("menor_igual")){
					model=model+"            [['"+atributo.getNombre()+"'], '"+atributo.getType()+"', 'min'=>"+restriccion.getValor()+"],\n;";
				}
				
				if(restriccion.getOperator().equals("mayor") || restriccion.getOperator().equals("mayor_igual")){
					model=model+"            [['"+atributo.getNombre()+"'], '"+atributo.getType()+"', 'max'=>"+restriccion.getValor()+"],\n;";
				}
			}
			else{
				model=model+"            [['"+atributo.getNombre()+"'], '"+atributo.getType()+"']\n";
			}
			id_atributo++;
		}
		for(ForeignKey fk : tabla.getForeignKeys()){
			model=model+"            [['"+fk.getNombre()+"'], 'integer']\n";
		}
		model=model+"        ];\n";
		model=model+"    }\n";

		model=model+"    /**\n";
		model=model+"     * @inheritdoc\n";
		model=model+"     */\n;";
		model=model+"    public function attributeLabels()\n";
		model=model+"    {\n";
		model=model+"        return [\n";
		for(Atributo atributo : this.tabla.getAtributos()){
			model=model+"            '"+atributo.getNombre()+"' => '"+atributo.getNombre()+",\n";
		}
		for(ForeignKey fk : tabla.getForeignKeys()){
			model=model+"            '"+fk.getNombre()+"' => '"+fk.getNombre()+",\n";
		}
		model=model+"        ];\n";
		model=model+"    }\n";
		model=model+"}\n";
		
	    //ESCRITURA DEL PHP CON EL MODELO DE LA VISTA
	    php_model = new FileWriter(path_proyect+this.tabla.getNombre()+".php");
	    php_model.write(model);
	    php_model.close();

	};
};
		
		
		
		