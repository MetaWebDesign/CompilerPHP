<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "{{%dash_model}}".
 *
 * @property integer $id
 * @property string $name
 * @property boolean $vista
 */
class DashModel extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return '{{%dash_model}}';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['vista'], 'boolean'],
            [['name'], 'string', 'max' => 50]
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'name' => 'Name',
            'vista' => 'Vista',
        ];
    }

    /**
     * @inheritdoc
     * @return DashModelQuery the active query used by this AR class.
     */
    public static function find()
    {
        return new DashModelQuery(get_called_class());
    }
}
