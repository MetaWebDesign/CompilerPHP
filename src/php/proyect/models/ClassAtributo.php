<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "ClassAtributo".
 *
 * @property integer $id
 * @property string $nombre
 * @property integer $id_clase
 *
 * @property Dashboard $idClase
 */
class ClassAtributo extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'ClassAtributo';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id_clase'], 'integer'],
            [['nombre'], 'string', 'max' => 50]
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'nombre' => 'Nombre',
            'id_clase' => 'Id Clase',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getIdClase()
    {
        return $this->hasOne(Dashboard::className(), ['id' => 'id_clase']);
    }
}
