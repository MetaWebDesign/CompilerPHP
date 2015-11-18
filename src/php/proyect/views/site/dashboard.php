<?php
namespace app\models;
/* @var $this yii\web\View */

use Yii;
use yii\helpers\Html;
use app\assets\DashboardAsset;

if(!Yii::$app->user->isGuest){
DashboardAsset::register($this);
$this->title = 'Dashboard';
$this->params['breadcrumbs'][] = $this->title;

?>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>MetaWebDesign Admin</title>

</head>


<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">MetaWebDesign Admin</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
              <!-- ERRORES -->
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <?php
                        $id_user = Yii::$app->user->identity->id_user;
                        echo "<li><a href=\"index.php?r=users/view&id=$id_user\" target='_blank'><i class=\"fa fa-user fa-fw\"></i> User Profile</a>\n";
                        echo "</li>\n";
                        echo "<li><a href=\"index.php?r=users/update&id=$id_user\" target='_blank'><i class=\"fa fa-gear fa-fw\"></i> Settings</a>\n";
                        echo "</li>\n";
                         ?>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            <!-- /input-group -->
                        </li>
                        <li>
                            <a href="index.php?r=site/dashboard"><i class="fa fa-dashboard fa-fw"></i>Dashboard</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i>Tablas<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                              <?php
                              							$tablas = Dashboard::find()->indexBy('id')->all();
                              							foreach($tablas AS $tabla){
                              							    echo "                 <li>\n
                              										<a href='index.php?r=$tabla->nombre/index' target='_blank'>$tabla->nombre</a>\n
                               	                </li>\n";
                              							}
                              ?>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="index.php?r=dashboard-media/index"><i class="fa fa-edit fa-fw"></i>Media<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="index.php?r=dashboard-media/create" target='_blank'>Add</a>
                                </li>
                                <li>
                                    <a href="index.php?r=dashboard-media/index" target='_blank'>All</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-wrench fa-fw"></i> UI Elements<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="index.php?r=views/create" target='_blank'>Nueva Página</a>
                                </li>
                                <li>
                                    <a href="index.php?r=views/index" target='_blank'>Paginas</a>
                                </li>
                                <li>
                                    <a href="http://www.yiiframework.com/extension/yii2-widgets/" target='_blank'>Widgets</a>
                                </li>
                              <!--  <li>
                                    <a href="index.php?r=dashboard/index">Menús Dashboard</a>
                                </li>-->
                                <li>
                                    <a href="index.php?r=menu/index" target='_blank'>Menus</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-sitemap fa-fw"></i> Configuración<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="index.php?r=dashboard-conf/index" target='_blank'>General</a>
                                </li>
                                <!--<li>
                                    <a href="index.php?r=dashboard-error/">Reporte de Errores</a>
                                </li>-->
                                <li>
                                    <a href="index.php?r=site/permisos" target='_blank'>Permisos Servicios</a>
                                </li>
                                <li>
                                    <a href="index.php?r=restricciones/index" target='_blank'>Restricciones Servicios</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Dashboard</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <!-- cent -->
            <div class="row">
                <div class="col-lg-3 col-md-6">
                  <!-- -->
                      <div class="thumbnail">
                        <img src="uploads/w_users.png" alt="...">
                        <div class="caption">
                          <h3>Users</h3>
                          <p>Administración de usuarios</p>
                          <p><a href="index.php?r=users/index" class="btn btn-primary" role="button">Ver</a> <a href="index.php?r=users/create" class="btn btn-default" role="button">Crear</a></p>
                        </div>
                  </div>
                  <!-- -->
                </div>
                <div class="col-lg-3 col-md-6">
                  <!-- -->
                      <div class="thumbnail">
                        <img src="uploads/w_permisos.png" alt="...">
                        <div class="caption">
                          <h3>Permisos</h3>
                          <p>Administración de los accesos a los servicios CRUD</p>
                          <p><a href="index.php?r=site/permisos" class="btn btn-primary" role="button">Ver</a> <a href="index.php?r=dashboard-permisoscrud/create" class="btn btn-default" role="button">Crear</a></p>
                        </div>
                  </div>
                  <!-- -->
                </div>
                <div class="col-lg-3 col-md-6">
                  <!-- -->
                      <div class="thumbnail">
                        <img src="uploads/w_media.jpeg" alt="...">
                        <div class="caption">
                          <h3>Media</h3>
                          <p>Administración Archivos, fotos, videos</p>
                          <p><a href="index.php?r=dashboard-media/index" class="btn btn-primary" role="button">Ver</a> <a href="index.php?r=dashboard-media/create" class="btn btn-default" role="button">Crear</a></p>
                        </div>
                  </div>
                  <!-- -->
                </div>
                <div class="col-lg-3 col-md-6">
                  <!-- -->
                      <div class="thumbnail">
                        <img src="uploads/w_page.jpeg" alt="...">
                        <div class="caption">
                          <h3>Paginas</h3>
                          <p>Administración las paginas del sitio web</p>
                          <p><a href="index.php?r=views/index" class="btn btn-primary" role="button">Ver</a> <a href="index.php?r=views/create" class="btn btn-default" role="button">Crear</a></p>
                        </div>
                  </div>
                  <!-- -->

            </div>
            <!-- /.row -->
            <div class="row">

                <!-- /.col-lg-8 -->
                <!-- /.col-lg-4 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

</body>

</html>
<?php
}
else{
?>
<div class="site-error">

    <h1>Forbidden (#403)</h1>

    <div class="alert alert-danger">
        You are not allowed to perform this action.    </div>

    <p>
        The above error occurred while the Web server was processing your request.
    </p>
    <p>
        Please contact us if you think this is a server error. Thank you.
    </p>

</div>
<?php
}
 ?>
