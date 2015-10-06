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
    $dash_crud = DashboardPermisoscrud::find()->where(['id_dash'=>$vista->id, 'service'=>'view'])->one();
    if($id_user_rol == $dash_crud->id_rol){
      return true;
    }
    else{
      return false;
    }
  }

}

?>
