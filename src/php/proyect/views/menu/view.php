<?php
namespace app\models;
use yii\helpers\Html;
use yii\widgets\DetailView;

/* @var $this yii\web\View */
/* @var $model app\models\Menu */

$this->title = $model->id;
$this->params['breadcrumbs'][] = ['label' => 'Menus', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="menu-view">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Update', ['update', 'id' => $model->id], ['class' => 'btn btn-primary']) ?>
        <?= Html::a('Delete', ['delete', 'id' => $model->id], [
            'class' => 'btn btn-danger',
            'data' => [
                'confirm' => 'Are you sure you want to delete this item?',
                'method' => 'post',
            ],
        ]) ?>
        <?php
        $url="index.php?r=links/create&id=$model->id";
        echo "<a href=\"$url\" class=\"btn btn-success\" role=\"button\">Add Link </a> ";
        $url2="index.php?r=links/index&id=$model->id";
        echo " <a href=\"$url2\" class=\"btn btn-warning\" role=\"button\"> View Link </a>";
        ?>
    </p>

    <?= DetailView::widget([
        'model' => $model,
        'attributes' => [
            'id',
            'nombre',
            'type',
        ],
    ]) ?>

    <?php
    echo "<center><h3>Links:</h3></center>\n";
    $results= Links::find()->where(['id_menu'=>$model->id])->all();
    echo " <table class=\"table table-striped table-bordered detail-view\">\n";
    $cont_links=1;
    foreach ($results AS $link) {
      echo "<tr>\n";
      echo "<td>$cont_links</td>\n";
      echo "<td>$link->nombre</td>";
      echo "<td><a href=\"index.php?r=links/update&id=$link->id\">$link->url</a></td>";
      echo "</tr>\n";
      $cont_links++;
    }
    echo "</table>\n";
    ?>

</div>
