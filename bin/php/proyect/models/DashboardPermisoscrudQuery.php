<?php

namespace app\models;

/**
 * This is the ActiveQuery class for [[DashboardPermisoscrud]].
 *
 * @see DashboardPermisoscrud
 */
class DashboardPermisoscrudQuery extends \yii\db\ActiveQuery
{
    /*public function active()
    {
        $this->andWhere('[[status]]=1');
        return $this;
    }*/

    /**
     * @inheritdoc
     * @return DashboardPermisoscrud[]|array
     */
    public function all($db = null)
    {
        return parent::all($db);
    }

    /**
     * @inheritdoc
     * @return DashboardPermisoscrud|array|null
     */
    public function one($db = null)
    {
        return parent::one($db);
    }
}