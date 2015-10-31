<?php

namespace app\models;

use Yii;
use yii\base\Model;
use yii\data\ActiveDataProvider;
use app\models\ViewAdvance;

/**
 * ViewAdvanceSearch represents the model behind the search form about `\app\models\ViewAdvance`.
 */
class ViewAdvanceSearch extends ViewAdvance
{
    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id', 'id_vista', 'id_clase', 'id_atributo', 'y_position'], 'integer'],
            [['typePresentation', 'x_position'], 'safe'],
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
        $query = ViewAdvance::find();

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
            'id' => $this->id,
            'id_vista' => $this->id_vista,
            'id_clase' => $this->id_clase,
            'id_atributo' => $this->id_atributo,
            'y_position' => $this->y_position,
        ]);

        $query->andFilterWhere(['like', 'typePresentation', $this->typePresentation])
            ->andFilterWhere(['like', 'x_position', $this->x_position]);

        return $dataProvider;
    }
}
