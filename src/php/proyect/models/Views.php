<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "Views".
 *
 * @property integer $id_view
 * @property string $title
 * @property integer $id_rol
 * @property string $content
 *
 * @property DashboardConf[] $dashboardConfs
 * @property ViewAdvance[] $viewAdvances
 * @property Roles $idRol
 */
class Views extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'Views';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id_rol'], 'integer'],
            [['content'], 'string'],
            [['title'], 'string', 'max' => 50]
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id_view' => 'Id View',
            'title' => 'Title',
            'id_rol' => 'Id Rol',
            'content' => 'Content',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getDashboardConfs()
    {
        return $this->hasMany(DashboardConf::className(), ['id_index' => 'id_view']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getViewAdvances()
    {
        return $this->hasMany(ViewAdvance::className(), ['id_vista' => 'id_view']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getIdRol()
    {
        return $this->hasOne(Roles::className(), ['id_rol' => 'id_rol']);
    }
}
