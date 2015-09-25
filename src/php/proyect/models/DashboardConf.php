<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "DashboardConf".
 *
 * @property integer $id_web
 * @property string $sitetitle
 * @property string $tagline
 * @property string $admin_mail
 * @property integer $id_index
 *
 * @property Views $idIndex
 */
class DashboardConf extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'DashboardConf';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id_index'], 'integer'],
            [['sitetitle', 'tagline'], 'string', 'max' => 30],
            [['admin_mail'], 'string', 'max' => 50]
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id_web' => 'Id Web',
            'sitetitle' => 'Sitetitle',
            'tagline' => 'Tagline',
            'admin_mail' => 'Admin Mail',
            'id_index' => 'Id Index',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getIdIndex()
    {
        return $this->hasOne(Views::className(), ['id_view' => 'id_index']);
    }
}
