<?php
session_start();
//Matamos la sesion
session_destroy();
$_SESSION['log']=false;
$_SESSION['rol']="";
$_SESSION['username']="";
?>
<SCRIPT LANGUAGE="javascript">
location.href = "index.php";
</SCRIPT>
