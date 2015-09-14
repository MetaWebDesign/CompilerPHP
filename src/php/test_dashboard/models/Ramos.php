<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "ramos".
 *
 * @property integer $id
 * @property string $classname
 * @property string $max_student
 * @property string $descrip
 */
class Ramos extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'ramos';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['classname', 'descrip'], 'string'],
            [['max_student'], 'number']
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'classname' => 'Classname',
            'max_student' => 'Max Student',
            'descrip' => 'Descrip',
        ];
    }
}
