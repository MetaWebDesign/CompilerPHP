<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "inscritos".
 *
 * @property integer $id
 * @property integer $id_user
 * @property integer $id_class
 */
class Inscritos extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'inscritos';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id_user', 'id_class'], 'integer']
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
            'id_class' => 'Id Class',
        ];
    }
}
