<?php

namespace app\models;

use Yii;
use yii\base\Model;
use yii\data\ActiveDataProvider;
use app\models\DashboardPermisoscrud;

/**
 * DashboardPermisoscrudSearch represents the model behind the search form about `\app\models\DashboardPermisoscrud`.
 */
class DashboardPermisoscrudSearch extends DashboardPermisoscrud
{
    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id_permiso', 'id_dash', 'id_rol'], 'integer'],
            [['service'], 'safe'],
        ];
    }

    /**
     * @inheritdoc
     */
    public function scenarios()
    {
        // bypass scenarios() implementation in the parent class
        return Model::scenarios();
    }

    /**
     * Creates data provider instance with search query applied
     *
     * @param array $params
     *
     * @return ActiveDataProvider
     */
    public function search($params)
    {
        $query = DashboardPermisoscrud::find();

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
        ]);

        $this->load($params);

        if (!$this->validate()) {
            // uncomment the following line if you do not want to return any records when validation fails
            // $query->where('0=1');
            return $dataProvider;
        }

        $query->andFilterWhere([
            'id_permiso' => $this->id_permiso,
            'id_dash' => $this->id_dash,
            'id_rol' => $this->id_rol,
        ]);

        $query->andFilterWhere(['like', 'service', $this->service]);

        return $dataProvider;
    }
}
