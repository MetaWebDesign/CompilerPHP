<?php

session_start();

if(!$_SESSION['log'])
	echo "<SCRIPT LANGUAGE='javascript'>location.href = 'index.php';</SCRIPT>";

?>