<?php

class db 
{	
	private static $instance = null; 
	private $connection; 
 
    public static function getInstance(){ 
        if(self::$instance == null){ 
            self::$instance = new self; 
        }    
        return self::$instance;          
    } 
 
    private function __construct(){               
        $connectionInfo = array('Database'=>'YourServerInstance', 
                                'UID'=>'UserName',
                                'PWD'=>'AComplicatedPassword'); 
       
        $this->connection = sqlsrv_connect("DatabaseOfChoice", 
                                           $connectionInfo); 
 
        if ($this->connection === false) { 
            echo '<h2>Unable to connect to database</h2><br/>'; 
            die (print_r(sqlsrv_errors(), true)); 
        };               
    } 
      
    public static function query($query, $params=null) 
    { 
        $db = self::GetInstance(); 
        $result = sqlsrv_query( $db->connection, $query, $params); 
        if (!$result ) 
        {     
             echo 'Error in statement execution.\n'; 
             die( print_r( sqlsrv_errors(), true)); 
        } 
        return $result;          
    } 
} 

?>