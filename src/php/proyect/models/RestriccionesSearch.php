<?php

namespace app\models;

use Yii;
use yii\base\Model;
use yii\data\ActiveDataProvider;
use app\models\Restricciones;

/**
 * RestriccionesSearch represents the model behind the search form about `\app\models\Restricciones`.
 */
class RestriccionesSearch extends Restricciones
{
    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id', 'clase', 'atributo'], 'integer'],
            [['nombre', 'mensaje_error', 'operador', 'service', 'valor'], 'safe'],
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
        $query = Restricciones::find();

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
            'clase' => $this->clase,
            'atributo' => $this->atributo,
        ]);

        $query->andFilterWhere(['like', 'nombre', $this->nombre])
            ->andFilterWhere(['like', 'mensaje_error', $this->mensaje_error])
            ->andFilterWhere(['like', 'operador', $this->operador])
            ->andFilterWhere(['like', 'service', $this->service])
            ->andFilterWhere(['like', 'valor', $this->valor]);

        return $dataProvider;
    }
}
