<?php
namespace app\models;
use yii\helpers\Html;
use yii\widgets\DetailView;
/* @var $this yii\web\View */
/* @var $model app\models\Views */
use yii\db\Query;
$connection = \Yii::$app->db;

$this->title = $model->title;
$this->params['breadcrumbs'][] = ['label' => 'Views', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="views-view">

    <?php /*<h1><?= Html::encode($this->title) ?></h1> */ ?>

    <p>
        <?= Html::a('Update', ['update', 'id' => $model->id_view], ['class' => 'btn btn-primary']) ?>
        <?= Html::a('Delete', ['delete', 'id' => $model->id_view], [
            'class' => 'btn btn-danger',
            'data' => [
                'confirm' => 'Are you sure you want to delete this item?',
                'method' => 'post',
            ],
        ]) ?>
    </p>

<?php
    echo "<center><h2>$model->title</h2></center>";
    echo $model->content;
    echo "<br><br>\n";

    //----------------------------------------- Soporte ViewAdvance -------------------------------------//

    $string=array();
    $table=array();
    $combobox=array();
    $checkbox=array();
    $itemList=array();
    $form_email=array();
    $form_password=array();
    $form_file=array();
    $form_text=array();
    $form_text=array();
    $table_striped=array();
    $table_border=array();
    $table_hover=array();
    $img=array();
    $clase="ViewAdvance";
    $results=ViewAdvance::find()->where(['id_vista'=>$model->id_view])->all();
    //$results=$clase::find()->where(['id_vista'=>$model->id_view])->all();
    foreach($results AS $view_advance){
        if($view_advance->typePresentation == "string"){
          array_push($string, $view_advance);
        }
        if($view_advance->typePresentation == "table_striped"){
          array_push($table_striped, $view_advance);
        }
    }

//SI ES UNA TABLA STRIPED
    if(count($table_striped) > 0 || count($itemList) > 0){
       //IMPRIMO LOS DATOS
       $objs=array();
       $cont_max=0;//cantidad maxima de filas a imprimir
       $cont_column=0;
       echo "<table class=\"table table-striped\">\n";
       echo"<tr>\n";
       foreach($table_striped AS $dato){
           $clase=Dashboard::find()->where(['id'=>$dato->id_clase])->one();
           $atributo=ClassAtributo::find()->where(['id_atributo'=>$dato->atributo, 'id_clase'=>$dato->id_clase])->one();
           echo "<td>$clase->nombre - $atributo->nombre</td>";
           $sub_objs=array();
           $column=$connection->createCommand("SELECT ".$atributo->nombre." FROM ".$clase->nombre.";'");
	         $results2=$column->queryColumn();
           $new_cont=0;//cantidad de filas encontradas

           foreach ($results2 as $r){
             array_push($sub_objs, $r);
             $new_cont++;
           }
           if($new_cont > $cont_max){
             $cont_max=$new_cont;
           }
           array_push($objs, $sub_objs);
           $cont_column++;
         }
        $i=0;
        while($i<$cont_max){
          echo "<tr>";
          $j=0;
          while($j<$cont_column){
            if(!empty($objs[$j][$i])){
              echo "<td>".$objs[$j][$i]."</td>\n";
            }
            else{
              echo "<td> - </td>\n";
            }
            $j++;
          }
          echo "</tr>";
          $i++;
        }
        echo"</tr>\n";
        echo"</table>\n";
    }

//SI ES UNA TABLA BORDER
    if(count($table_border) > 0 ){
       //IMPRIMO LOS DATOS
       $objs=array();
       $cont_max=0;//cantidad maxima de filas a imprimir
       $cont_column=0;
       echo "<table class=\"table table-bordered\">\n";
       echo"<tr>\n";
       foreach($table_striped AS $dato){
           $clase=Dashboard::find()->where(['id'=>$dato->id_clase])->one();
           $atributo=ClassAtributo::find()->where(['id_atributo'=>$dato->atributo, 'id_clase'=>$dato->id_clase])->one();
           echo "<td>$clase->nombre - $atributo->nombre</td>";
           $sub_objs=array();
           $column=$connection->createCommand("SELECT ".$atributo->nombre." FROM ".$clase->nombre.";'");
	         $results2=$column->queryColumn();
           $new_cont=0;//cantidad de filas encontradas

           foreach ($results2 as $r){
             array_push($sub_objs, $r);
             $new_cont++;
           }
           if($new_cont > $cont_max){
             $cont_max=$new_cont;
           }
           array_push($objs, $sub_objs);
           $cont_column++;
         }
        $i=0;
        while($i<$cont_max){
          echo "<tr>";
          $j=0;
          while($j<$cont_column){
            if(!empty($objs[$j][$i])){
              echo "<td>".$objs[$j][$i]."</td>\n";
            }
            else{
              echo "<td> - </td>\n";
            }
            $j++;
          }
          echo "</tr>";
          $i++;
        }
        echo"</tr>\n";
        echo"</table>\n";
    }

//SI ES UNA TABLA HOVER
    if(count($table_hover) > 0 ){
           //IMPRIMO LOS DATOS
           $objs=array();
           $cont_max=0;//cantidad maxima de filas a imprimir
           $cont_column=0;
           echo "<table class=\"table table-hover\">\n";
           echo"<tr>\n";
           foreach($table_striped AS $dato){
               $clase=Dashboard::find()->where(['id'=>$dato->id_clase])->one();
               $atributo=ClassAtributo::find()->where(['id_atributo'=>$dato->atributo, 'id_clase'=>$dato->id_clase])->one();
               echo "<td>$clase->nombre - $atributo->nombre</td>";
               $sub_objs=array();
               $column=$connection->createCommand("SELECT ".$atributo->nombre." FROM ".$clase->nombre.";'");
    	         $results2=$column->queryColumn();
               $new_cont=0;//cantidad de filas encontradas

               foreach ($results2 as $r){
                 array_push($sub_objs, $r);
                 $new_cont++;
               }
               if($new_cont > $cont_max){
                 $cont_max=$new_cont;
               }
               array_push($objs, $sub_objs);
               $cont_column++;
             }
            $i=0;
            while($i<$cont_max){
              echo "<tr>";
              $j=0;
              while($j<$cont_column){
                if(!empty($objs[$j][$i])){
                  echo "<td>".$objs[$j][$i]."</td>\n";
                }
                else{
                  echo "<td> - </td>\n";
                }
                $j++;
              }
              echo "</tr>";
              $i++;
            }
            echo"</tr>\n";
            echo"</table>\n";
    }


?>
</div>
