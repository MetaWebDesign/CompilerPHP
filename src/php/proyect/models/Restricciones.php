<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "Restricciones".
 *
 * @property integer $id
 * @property string $nombre
 * @property string $mensaje_error
 * @property string $operador
 * @property string $service
 * @property string $valor
 * @property integer $clase
 * @property integer $atributo
 *
 * @property ClassAtributo $atributo0
 * @property Dashboard $clase0
 */
class Restricciones extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'Restricciones';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['mensaje_error'], 'string'],
            [['clase', 'atributo'], 'integer'],
            [['nombre', 'valor'], 'string', 'max' => 50],
            [['operador'], 'string', 'max' => 4],
            [['service'], 'string', 'max' => 30]
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
            'mensaje_error' => 'Mensaje Error',
            'operador' => 'Operador',
            'service' => 'Service',
            'valor' => 'Valor',
            'clase' => 'Clase',
            'atributo' => 'Atributo',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getAtributo0()
    {
        return $this->hasOne(ClassAtributo::className(), ['id' => 'atributo']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getClase0()
    {
        return $this->hasOne(Dashboard::className(), ['id' => 'clase']);
    }
}
