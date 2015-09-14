<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "roluser".
 *
 * @property integer $id
 * @property integer $id_user
 * @property integer $id_rol
 */
class RolUser extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'roluser';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id_user', 'id_rol'], 'integer']
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'id_user' => 'Id User',
            'id_rol' => 'Id Rol',
        ];
    }
}
