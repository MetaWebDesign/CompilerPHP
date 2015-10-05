<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "DashboardPermisoscrud".
 *
 * @property integer $id_permiso
 * @property integer $id_dash
 * @property string $service
 * @property integer $id_rol
 *
 * @property Roles $idRol
 * @property Dashboard $idDash
 */
class DashboardPermisoscrud extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'DashboardPermisoscrud';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id_dash', 'id_rol'], 'integer'],
            [['service'], 'string', 'max' => 50]
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id_permiso' => 'Id Permiso',
            'id_dash' => 'Id Dash',
            'service' => 'Service',
            'id_rol' => 'Id Rol',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getIdRol()
    {
        return $this->hasOne(Roles::className(), ['id_rol' => 'id_rol']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getIdDash()
    {
        return $this->hasOne(Dashboard::className(), ['id' => 'id_dash']);
    }

    /**
     * @inheritdoc
     * @return DashboardPermisoscrudQuery the active query used by this AR class.
     */
    public static function find()
    {
        return new DashboardPermisoscrudQuery(get_called_class());
    }
}
