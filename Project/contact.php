<!DOCTYPE  html>
<html>
	<head>
		<?php include('lib/head.php'); session_start();?>
	</head>

	<body class="home">

			<!-- HEADER -->
			<?php include('lib/header.php'); ?>
			<!-- ENDS HEADER -->
			<!-- Menu -->
			<?php if(!$_SESSION['log'] ) include('lib/menu_publico.php'); else	include('lib/menu_'.$_SESSION['rol'].'.php');?>
			<!-- ENDS Menu -->

			<!-- MAIN -->
			<div id="main">
				<!-- wrapper-main -->
				<div class="wrapper">
				<center>
					<!-- content -->
					<div id="content">
					</div>
					<!-- ENDS content -->
				</div>
				</div>
				<!-- ENDS wrapper-main -->
			</div>
			<!-- ENDS MAIN -->

	</body>
</html>