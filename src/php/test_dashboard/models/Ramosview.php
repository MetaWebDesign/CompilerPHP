<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "ramosview".
 *
 * @property integer $id
 * @property string $classname
 * @property string $max_student
 * @property string $descrip
 * @property string $profesor
 */
class Ramosview extends \yii\db\ActiveRecord
{


    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'ramosview';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id'], 'integer'],
            [['classname', 'descrip'], 'string'],
            [['max_student'], 'number'],
			/*[['profesor'], 'string']*/
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
            /*'profesor' => 'Profesor',*/
        ];
    }
}
