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
	static SQL model;
	
	public PHP_ViewForm(String path_proyect, Tabla tabla_, SQL model_){
		path_view=path_proyect+"_form.php";
		tabla=tabla_;
		model=model_;
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
	public static String genUses(){
		//DEFAULT
		String uses="namespace app\\models;";
		uses=uses+"use yii\\helpers\\Html;\n";
		uses=uses+"use yii\\widgets\\ActiveForm;\n";
		uses=uses+"use yii\\helpers\\ArrayHelper;\n";
		
		//WIDGETS PARA ATRIBUTOS
		for(Atributo a : tabla.getAtributos()){
			if(a.getTypeModel().equals("date_time")){ //SI EN EL MODELO HAY UN ATRIBUTO TIPO DATETIME
				uses=uses+"use kartik\\datetime\\DateTimePicker;\n";
			}
			if(a.getTypeModel().equals("time")){
				uses=uses+"use kartik\\time\\TimePicker;\n";
			}
		}
		
		return uses;
	}

	//ESCRITURA DE LA VISTA
	public void write() throws IOException{
		genUses();
		List <Atributo> atributos=tabla.getAtributos();
		List <ForeignKey> foreignKeys=tabla.getForeignKeys();
		form="<?php\n\n";
		form=form+genUses();
		form=form+"/* @var $this yii\\web\\View */\n";
		form=form+"/* @var $model app\\models\\"+tabla.getNombre()+" */\n";
		form=form+"/* @var $form yii\\widgets\\ActiveForm */\n";
		form=form+"?>\n\n";

		form=form+"<div class=\"archivos-form\">\n";
		form=form+"    <?php $form = ActiveForm::begin(); ?>\n";

			
		//LECTURA DE ATRIBUTOS
		 for(Atributo atributo : atributos){
			form=form+"\n";
			//si el atributo es de tipo datetime
			if(atributo.getTypeModel().equals("date_time")){//campo para fecha y hora
				form=form+"					<?php\n";   
				form=form+"						// Usage with model and Active Form (with no default initial value)\n";
				form=form+"						echo $form->field($model, '"+atributo.getNombre()+"')->widget(DateTimePicker::classname(), [\n";
				form=form+"							'options' => ['placeholder' => 'Enter event time ...'],\n";
				form=form+"							'pluginOptions' => [\n";
				form=form+"								'autoclose' => true\n";
				form=form+"							]\n";
				form=form+"						]);?>\n";
			}
			//si el atributo es de tipo time
			if(atributo.getTypeModel().equals("time")){
				
			}
			//si el atributo es de tipo date
			//si el atributo es de tipo passwd
			//si el atributo es de tipo file
			//si el atributo es de tipo img
			else{
				form=form+"				 <?= $form->field($model, '"+atributo.getNombre()+"')->textInput() ?>\n";
			}
		}
		 
		//LLAVES FORANEAS (igual que atributos, por lo general son comobox a otra tabla)
		 
	    for(ForeignKey fk : foreignKeys){
	    	Tabla destino=model.getTablaByInt(fk.getDestination());
	    	Atributo destino_pk=destino.getPrimaryKey();
			form=form+"<label class=\"control-label\" for=\""+tabla.getNombre()+"-"+fk.getNombre()+"\">"+destino.getNombre()+": </label>\n";
			form=form+"<?= Html::activeDropDownList($model, '"+fk.getNombre()+"', ArrayHelper::map("+destino.getNombre()+"::find()->all(), '"+destino_pk.getNombre()+"', '"+destino.findNameAttribute()+"')) ?>\n";
	    }
		
		form=form+"\n\n		 <div class=\"form-group\">\n";
		form=form+"	        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>\n";
		form=form+"	    </div>\n";
		form=form+"	    <?php ActiveForm::end(); ?>\n";
		form=form+"	</div>\n";

		//ESCRITURA DE LA VISTA CON LAS MODIFICACIONES
		FileWriter fichero = null;
		fichero = new FileWriter(path_view);
		fichero.write(form);
		fichero.close();
	}
	
}