package compilerphp.actions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
/*
 * Generado del Modelo para una Vista SQL para atributos derivados
 */
public class PHP_ModelView{

	static View view;
	static Tabla tabla;
	static String path_proyect;
	
	//GENERA EL MODELO PARA LAS VISTAS EN LA BDD
	//public static void genModelView_(View view, Tabla tabla, String path_proyect) throws IOException{
	public PHP_ModelView(View view_, Tabla tabla_, String path_proyect_){
		view=view_;
		tabla=tabla_;
		path_proyect=path_proyect_;
	} 
			
			
	public void Write()	throws IOException {
		FileWriter php_model_view = null;
		String atributo_model_name;
		String model_view="<?php\n\n";
		List <Atributo> atributos=tabla.getAtributos();
		model_view=model_view+"namespace app\\models;\n\n";
		model_view=model_view+"use Yii;\n";
		model_view=model_view+"/**\n";
		model_view=model_view+" * This is the model class for table \""+view.getNombre()+"\".\n";
		model_view=model_view+" *\n";
		for(Atributo atributo : atributos) {
			model_view=model_view+" * @property "+atributo.getType()+" "+atributo.getNombre()+"\n";
		}
		//model_view=model_view+" * @property "+view.getType()+" "+view.getNombre()+"\n";
		model_view=model_view+" */\n";
		model_view=model_view+"class "+view.getNombre()+" extends \\yii\\db\\ActiveRecord\n";
		model_view=model_view+"{\n";
		model_view=model_view+"public static function tableName()\n";
		model_view=model_view+"{\n";
		model_view=model_view+"    return '"+view.getNombre()+"';\n";
		model_view=model_view+"}\n";
		model_view=model_view+"public function rules()\n";
		model_view=model_view+"{\n";
		model_view=model_view+"    return [\n";
		for(Atributo atributo : atributos) {
			if(!atributo.getType().equals("autoincremental") && !atributo.getDerivedEDO()){
				String typeData=atributo.getType();
				if(typeData.equals("varchar(10)")){
					//typeData="'string', 'max' => 10";
					model_view=model_view+"        [['"+atributo.getNombre()+"'], 'string', 'max' => 10],\n";
				}
				if(typeData.equals("varchar(30)")){
					//typeData="'string', 'max' => 30";
					model_view=model_view+"        [['"+atributo.getNombre()+"'], 'string', 'max' => 30],\n";
				}
				if(typeData.equals("varchar(50)")){
					//typeData="'string', 'max' => 50";
					model_view=model_view+"        [['"+atributo.getNombre()+"'], 'string', 'max' => 50],\n";
				}
				if(typeData.equals("text")){
					//typeData="'string'";
					model_view=model_view+"        [['"+atributo.getNombre()+"'], 'string'],\n";
				}
				else{
					//typeData="'"+typeData+"'";
					model_view=model_view+"        [['"+atributo.getNombre()+"'], '"+typeData+"'],\n";
				}
			}
			
			if (atributo.getDerivedEDO()){
				model_view=model_view+"        [['"+atributo.getNombre()+"'], '"+atributo.getType()+"'],\n";
			}
		}
		
	    model_view=model_view+"    ];\n";
	    model_view=model_view+"}\n";
	    model_view=model_view+"public function attributeLabels()\n";
	    model_view=model_view+"{\n";
	    model_view=model_view+"    return [\n";
		for(Atributo atributo : atributos) {
				atributo_model_name=atributo.getNombre().substring(0, 1).toUpperCase() +atributo.getNombre().substring(1);
				model_view=model_view+"        '"+atributo.getNombre()+"' => '"+atributo_model_name+"',\n";
		}
		
	    model_view=model_view+"    ];\n";
	    model_view=model_view+" }\n";
	    model_view=model_view+"}\n";
	   
	    //ESCRITURA DEL PHP CON EL MODELO DE LA VISTA
	    php_model_view = new FileWriter(path_proyect+view.getNombre()+".php");
	    php_model_view.write(model_view);
	    php_model_view.close();
	}
}
