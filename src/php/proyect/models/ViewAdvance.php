<?php

namespace app\models;

use Yii;


/**
 * This is the model class for table "ViewAdvance".
 *
 * @property integer $id
 * @property integer $id_vista
 * @property integer $id_clase
 * @property integer $id_atributo
 * @property string $typePresentation
 * @property string $x_position
 * @property integer $y_position
 *
 * @property ClassAtributo $idAtributo
 * @property Dashboard $idClase
 * @property Views $idVista
 */
class ViewAdvance extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'ViewAdvance';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id_vista', 'id_clase', 'id_atributo', 'y_position'], 'integer'],
            [['typePresentation'], 'string', 'max' => 50],
            [['x_position'], 'string', 'max' => 20]
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'id_vista' => 'Id Vista',
            'id_clase' => 'Id Clase',
            'id_atributo' => 'Id Atributo',
            'typePresentation' => 'Type Presentation',
            'x_position' => 'X Position',
            'y_position' => 'Y Position',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getIdAtributo()
    {
        return $this->hasOne(ClassAtributo::className(), ['id' => 'id_atributo']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getIdClase()
    {
        return $this->hasOne(Dashboard::className(), ['id' => 'id_clase']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getIdVista()
    {
        return $this->hasOne(Views::className(), ['id_view' => 'id_vista']);
    }

    public function getAtributo($id){
      $data = ClassAtributo::find()->where(['id_clase'=>$id])->select(['id_atributo AS id', 'nombre AS name'])->asArray()->all();
    return $data;
  }
}
