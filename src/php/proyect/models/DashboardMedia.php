<?php

namespace app\models;

use yii\base\Model;//new
use yii\web\UploadedFile; //NEW

use Yii;


/**
 * This is the model class for table "DashboardMedia".
 *
 * @property integer $id_media
 * @property string $filename
 * @property integer $id_autor
 * @property string $Fecha
 * @property string $extencion
 *
 * @property Users $idAutor
 */
class DashboardMedia extends \yii\db\ActiveRecord
{
  //public $filename_file;
  //public $filename;

    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'DashboardMedia';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id_autor'], 'integer'],
            [['Fecha'], 'safe'],
          //  [['filename'], 'string', 'max' => 100],

            //[['filename'], 'file', 'skipOnEmpty' => false, 'extensions' => 'png, jpg'],
            [['filename'], 'file'],
            [['extencion'], 'string', 'max' => 10]
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id_media' => 'Id Media',
            'filename' => 'Filename',
            'id_autor' => 'Id Autor',
            'Fecha' => 'Fecha',
            'extencion' => 'Extencion',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getIdAutor()
    {
        return $this->hasOne(Users::className(), ['id_user' => 'id_autor']);
    }

    public function upload(){
            if ($this->validate()) {
                //$this->file->saveAs('uploads/' . $this->filename->baseName . '.' . $this->filename->extension);
                //$this->filename=$this->filename->baseName;
                $this->filename->saveAs('uploads/' . $this->filename->baseName . '.' . $this->filename->extension);
                //$this->filename=$this->filename->baseName;
                return true;
            } else {
                return false;
            }
    }
}
