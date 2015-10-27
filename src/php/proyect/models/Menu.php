<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "Menu".
 *
 * @property integer $id
 * @property string $nombre
 * @property string $type
 * @property integer $id_view
 *
 * @property Links[] $links
 * @property Views $idView
 */
class Menu extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'Menu';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id_view'], 'integer'],
            [['nombre', 'type'], 'string', 'max' => 50]
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
            'type' => 'Type',
            'id_view' => 'Id View',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getLinks()
    {
        return $this->hasMany(Links::className(), ['id_menu' => 'id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getIdView()
    {
        return $this->hasOne(Views::className(), ['id_view' => 'id_view']);
    }
}
