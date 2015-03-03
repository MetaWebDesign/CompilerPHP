<!DOCTYPE  html>
<html>
	<head>
		<?php include('lib/head.php'); 
			include('lib/class.php');
			session_start(); 
			if(!$_SESSION['log']){
				$_SESSION['log']=false; 
			}
			else {
				$_SESSION['log']=true; 
			}
		?>
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

					<!-- title -->
					<div id="page-title">
						<span class="title">Home</span>
						<!--<span class="subtitle">Typography styling</span>-->
					</div>
									
					
					<!-- ENDS title -->
						<?php
						if(!$_SESSION['log'] && !isset($_POST['login'])){
						
						echo "	<h1><b>Login to SICOVE</b></h1><br>
								<form method='post' action=''>
								<p>Tipo de usuario<br><select name='rol'>
									<option value='administrador'>Administrador</option>
									<option value='jefe_de_local'>Jefe de local</option>
									<option value='vendedor'>Vendedor</option>
									<option value='cliente'>Cliente</option>
								</select><br><br></p>
								<p>Usuario <input type='text' name='usuario' value='' placeholder='Usuario'><br></p>
								<p>Password <input type='password' name='password' value='' placeholder='Password'><br></p>
								<p><input type='submit' name='login' value='Iniciar Sesion'></p>
							</form>";
						}
						
						else if(isset($_POST['login'])){
							$postgresql = new postgresql;
							$postgresql->connect();
							if($postgresql->login($_POST['usuario'], $_POST['password'], $_POST['rol'])){
								$_SESSION['rol'] = $_POST['rol'];
								$_SESSION['log'] = true;
								$_SESSION['username'] = $_POST['usuario'];
								echo "<SCRIPT LANGUAGE='javascript'>location.href = 'index.php';</SCRIPT>";
							}
							else{
								echo "Datos incorrectos.<br><br><a href='index.php'>Volver</a>";
							}
							$postgresql->close();
						}
						
						else if($_SESSION['log']){
							echo "Bienvenido usuario: ".$_SESSION['username']."";
						}
						?>	
					</div>
					<!-- ENDS content -->
				</div>
				</div>
				<!-- ENDS wrapper-main -->
			</div>
			<!-- ENDS MAIN -->
				
	</body>
</html>