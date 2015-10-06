<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "Dashboard".
 *
 * @property integer $id
 * @property string $nombre
 * @property boolean $vista
 *
 * @property DashboardPermisoscrud[] $dashboardPermisoscruds
 */
class Dashboard extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'Dashboard';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['vista'], 'boolean'],
            [['nombre'], 'string', 'max' => 50]
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
            'vista' => 'Vista',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getDashboardPermisoscruds()
    {
        return $this->hasMany(DashboardPermisoscrud::className(), ['id_dash' => 'id']);
    }

    /**
     * @inheritdoc
     * @return DashboardQuery the active query used by this AR class.
     */
    public static function find()
    {
        return new DashboardQuery(get_called_class());
    }
}
