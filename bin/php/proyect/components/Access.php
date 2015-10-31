<?php
namespace app\components;
//namespace app\models;
use Yii;
use yii\base\Component;
use app\models\Dashboard;
use app\models\DashboardPermisoscrud;
use yii\base\InvalidConfigException;

//class Access extends Component {
class Access{

  public function validate($id_user_rol, $clase_name, $service){
    $vista = Dashboard::find()->where(['nombre'=>$clase_name])->one();
    $dash_crud = DashboardPermisoscrud::find()->where(['id_dash'=>$vista->id, 'service'=>$service])->all();
    if($id_user_rol == 1){
    return true;
    }
    else{
      foreach($dash_crud AS $permiso){
        if($id_user_rol == $permiso->id_rol){
          return true;
        }
      }
    }
      return false;
  }

}

?>
