<?php

namespace app\models;

class User extends \yii\base\Object implements \yii\web\IdentityInterface
{
    public $id_user;
    public $username;
    public $apellido;
    public $correo;
    public $id_rol;
    public $passwd;
    public $authKey;
    public $accessToken;

    /*
    private static $users = [
        '100' => [
            'id' => '100',
            'username' => 'admin',
            'password' => 'admin',
            'authKey' => 'test100key',
            'accessToken' => '100-token',
        ],
        '101' => [
            'id' => '101',
            'username' => 'demo',
            'password' => 'demo',
            'authKey' => 'test101key',
            'accessToken' => '101-token',
        ],
    ];*/

    /**
     * @inheritdoc
     */
    public static function findIdentity($id)
    {
        //return isset(self::$users[$id]) ? new static(self::$users[$id]) : null;
        $users = Users::find()->where(['id_user'=>$id])->one();
        if(!count($users)){
          return null;
        }
        else{
          //this->password=$users->passwd;
          return new static($users);
        }
    }

    /**
     * @inheritdoc
     */

    public static function findIdentityByAccessToken($token, $type = null)
    {
        foreach (self::$users as $user) {
            if ($user['accessToken'] === $token) {
                return new static($user);
            }
        }

        return null;
    }

    /**
     * Finds user by username
     *
     * @param  string      $username
     * @return static|null
     */
    public static function findByUsername($username)
    {
        $users= Users::find()->where(['username' => $username])->one();
        if(!count($users)){
          return null;
        }
        else{
          return  new static ($users);
        }
    }

    /**
     * @inheritdoc
     */
    public function getId()
    {
        return $this->id_user;
    }

    /**
     * @inheritdoc
     */

    public function getAuthKey()
    {
        return $this->authKey;
    }

    /**
     * @inheritdoc
     */

    public function validateAuthKey($authKey)
    {
        return $this->authKey === $authKey;
    }

    /**
     * Validates password
     *
     * @param  string  $password password to validate
     * @return boolean if password provided is valid for current user
     */
     /*
    public function validatePassword($password, $username)
    {
        //return $this->password === $password;
        $passwd_aut=Users::find()->where(['username'=>$username, 'passwd'=>$password]);
        if(passwd_aut){
          return true;
        }
        else{
          return false;
        }
    }*/
    public function validatePassword($password) {
        return $this->passwd === $password;
    }
}
