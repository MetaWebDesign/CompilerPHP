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
		String uses="namespace app\\models;\n\n";
		uses=uses+"use yii\\helpers\\Html;\n";
		uses=uses+"use yii\\widgets\\ActiveForm;\n";
		uses=uses+"use yii\\helpers\\ArrayHelper;\n";
		
		//WIDGETS PARA ATRIBUTOS
		for(Atributo a : tabla.getAtributos()){
			if(!a.getDerivedEDO()){
				if(a.getTypeModel().equals("date_time")){ //SI EN EL MODELO HAY UN ATRIBUTO TIPO DATETIME
					uses=uses+"use kartik\\datetime\\DateTimePicker;\n";
				}
				if(a.getTypeModel().equals("time")){
					uses=uses+"use kartik\\time\\TimePicker;\n";
				}
				if(a.getTypeModel().equals("date")){
					uses=uses+"use kartik\\date\\DatePicker;\n";
				}
				if(a.getTypeModel().equals("file") || a.getTypeModel().equals("img")){
					uses=uses+"use kartik\\file\\FileInput;\n";
				}
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
			 boolean auto=true; //EVITAR QUE SE CUMPLAN DOS CASOS A LA VEZ, SE QUEDA CON EL PRIMERO
			if(atributo.getRequired() && !atributo.getDerivedEDO()){//SI EL ATRIBUTO ES REQUERIDO EN EL FORMULARIO
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
					form=form+"						]);?>\n\n";
					auto=false;
				}
				//si el atributo es de tipo time
				if(atributo.getTypeModel().equals("time")){
					form=form+"				<?php\n";
					form=form+"				$form = ActiveForm::begin();\n";
					form=form+"				echo $form->field($model, '"+atributo.getNombre()+"')->widget(TimePicker::classname(), []);\n";
					form=form+"				?>\n\n";
					auto=false;
				}
				//si el atributo es de tipo date
				if(atributo.getTypeModel().equals("date")){
					form=form+"									<?php\n";
					form=form+"											// Usage with model and Active Form (with no default initial value)\n";
					form=form+"										echo $form->field($model, '"+atributo.getNombre()+"')->widget(DatePicker::classname(), [\n";
					form=form+"											    'options' => ['placeholder' => 'Enter birth date ...'],\n";
					form=form+"											    'pluginOptions' => [\n";
					form=form+"											        'autoclose'=>true\n";
					form=form+"											    ]\n";
					form=form+"											]);\n";
					form=form+"											?>\n";
				   auto=false;
				}
				//si el atributo es de tipo passwd
				if(atributo.getTypeModel().equals("passwd")){
					form=form+"					<?= $form->field($model, '"+atributo.getNombre()+"')->passwordInput() ?>\n";
					auto=false;
				}
				//si el atributo es de tipo file
				if(atributo.getTypeModel().equals("file") || atributo.getTypeModel().equals("img")){
					form=form+"					<?php\n";
					form=form+"							// Usage with ActiveForm and model\n";
					form=form+"							echo $form->field($model, '"+atributo.getNombre()+"')->widget(FileInput::classname(), [\n";
					form=form+"							    'options' => ['accept' => 'upload/*'],\n";
					form=form+"							]);\n";
					form=form+"							?>\n";
					auto=false;
				}
				else{
					if(auto){
						form=form+"				 <?= $form->field($model, '"+atributo.getNombre()+"')->textInput() ?>\n\n";
					}
				}
			}
			//LLAVES FORANEAS (igual que atributos, por lo general son comobox a otra tabla)
		}	
	    for(ForeignKey fk : foreignKeys){
	    	Tabla destino=model.getTablaByInt(fk.getDestination());
	    	Atributo destino_pk=destino.getPrimaryKey();
	    	form=form+"<?php echo $form->field($model, '"+fk.getNombre()+"')->dropDownList(ArrayHelper::map("+destino.getNombre()+"::find()->all(), '"+destino_pk.getNombre()+"', '"+destino.findNameAttribute()+"'), ['id'=>'"+destino_pk.getNombre()+"']); ?>\n";
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