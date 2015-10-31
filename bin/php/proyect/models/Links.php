<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "Links".
 *
 * @property integer $id
 * @property string $nombre
 * @property string $url
 * @property integer $id_menu
 *
 * @property Menu $idMenu
 */
class Links extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'Links';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id_menu'], 'integer'],
            [['nombre'], 'string', 'max' => 50],
            [['url'], 'string', 'max' => 70]
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
            'url' => 'Url',
            'id_menu' => 'Id Menu',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getIdMenu()
    {
        return $this->hasOne(Menu::className(), ['id' => 'id_menu']);
    }
}
