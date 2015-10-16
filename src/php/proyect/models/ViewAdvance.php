<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "ViewAdvance".
 *
 * @property integer $id
 * @property integer $id_vista
 * @property integer $id_clase
 * @property string $atributo
 * @property string $typePresentation
 * @property string $x_position
 * @property integer $y_position
 *
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
            [['id_vista', 'id_clase', 'y_position'], 'integer'],
            [['atributo', 'typePresentation'], 'string', 'max' => 50],
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
            'atributo' => 'Atributo',
            'typePresentation' => 'Type Presentation',
            'x_position' => 'X Position',
            'y_position' => 'Y Position',
        ];
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
      $data = ClassAtributo::find()->where(['id_clase'=>$id])->select(['id', 'nombre AS name'])->asArray()->all();
      return $data;
    }
}
