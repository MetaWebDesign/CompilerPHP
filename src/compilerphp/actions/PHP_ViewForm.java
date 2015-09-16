package compilerphp.actions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PHP_ViewForm{
	
	static String form;//LINEAS DEL FORM
	static List <String> uses = new ArrayList<String>();//LISTA CON LOS USE DE LOS WIDGETS A UTILIZAR
	static String path_view; //RUTA DE LA VISTA
	static Tabla tabla;
	
	public PHP_ViewForm(String path_proyect, Tabla tabla_){
		path_view=path_proyect+"proyect/...";
		tabla=tabla_;
	}
	
	
	public String  form_DateTime(String name){ //REMPLAZA AL CAMPO DATETIME
		String line="<?php\n";
				line=line+"// Usage with model and Active Form (with no default initial value)\n";
				line=line+"echo $form->field($model, '"+name+"')->widget(DateTimePicker::classname(), [\n";
				line=line+"'	options' => ['placeholder' => 'Enter event time ...'],\n";
				line=line+"'	pluginOptions' => [\n";
				line=line+"'		autoclose' => true\n";
				line=line+"		]\n";
				line=line+"	]);\n";
				line=line+"?>\n";
		return line;
	}
	
	public String form_Text(String name, String dataType){
		int varchar=dataType.indexOf("varchar");//VE SI ES VARCHAR PARA LIMITAR EL CAMPO
		String line="\n";
		if(varchar != -1){
			line=line+"<?= $form->field($model, '"+name+"')->textInput(['maxlength' => true]) ?>";
		}
		else{
			line=line+"<?= $form->field($model, '"+name+"')->textInput() ?>";
		}
		return line;
	}
	
	//GENERA LOS USE, PARA IMPORTAR LOS WIDGETS A UTILIZAR
	public static void genUses(){
		//DEFAULT
		uses.add("use yii\\helpers\\Html;\n");
		uses.add("use yii\\widgets\\ActiveForm;\n");
		
		//WIDGETS PARA ATRIBUTOS
		for(Atributo a : tabla.getAtributos()){
			if(a.getTypeModel().equals("date_time")){ //SI EN EL MODELO HAY UN ATRIBUTO TIPO DATETIME
				uses.add("use kartik\\datetime\\DateTimePicker;");
			}
		}
	}

	//ESCRITURA DE LA VISTA
	public void write() throws IOException{
		genUses();
		form="<?php\n\n";
		for(String use : uses){ //CARGA LOS USES
			form=form+use;
		}
		form=form+"/* @var $this yii\\web\\View */\n";
		form=form+"/* @var $model app\\models\\"+tabla.getNombre()+" */\n";
		form=form+"/* @var $form yii\\widgets\\ActiveForm */\n";
		form=form+"?>\n\n";

		//LECTURA DE ATRIBUTOS
		
		FileWriter fichero = null;
		fichero = new FileWriter(path_view);
		fichero.write(form);
		fichero.close();
	}
	
}