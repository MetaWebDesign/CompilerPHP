<?php

namespace app\models;

/**
 * This is the ActiveQuery class for [[DashModel]].
 *
 * @see DashModel
 */
class DashModelQuery extends \yii\db\ActiveQuery
{
    /*public function active()
    {
        $this->andWhere('[[status]]=1');
        return $this;
    }*/

    /**
     * @inheritdoc
     * @return DashModel[]|array
     */
    public function all($db = null)
    {
        return parent::all($db);
    }

    /**
     * @inheritdoc
     * @return DashModel|array|null
     */
    public function one($db = null)
    {
        return parent::one($db);
    }
}