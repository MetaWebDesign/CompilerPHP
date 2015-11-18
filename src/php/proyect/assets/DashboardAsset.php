<?php


namespace app\assets;

use yii\web\AssetBundle;

/**
 * @author Leonardo Bravo
 * @since 1.0
 */
class DashboardAsset extends AssetBundle
{
    public $basePath = '@webroot';
    public $baseUrl = '@web';
    public $css = [
		//'css/bootstrap.min.css',
		'dashboard/bower_components/bootstrap/dist/css/bootstrap.min.css',
		'dashboard/bower_components/metisMenu/dist/metisMenu.min.css',
		'dashboard/dist/css/timeline.css',
		'dashboard/dist/css/sb-admin-2.css',
		//'dashboard/bower_components/morrisjs/morris.css',
		'dashboard/bower_components/font-awesome/css/font-awesome.min.css',
    ];
    public $js = [

		'dashboard/bower_components/jquery/dist/jquery.min.js',
		//'dashboard/bower_components/bootstrap/dist/js/bootstrap.min.js/jquery.min.js',
    'dashboard/bower_components/bootstrap/dist/js/bootstrap.min.js',
		'dashboard/bower_components/metisMenu/dist/metisMenu.min.js',
		'dashboard/bower_components/raphael/raphael-min.js',
		//'dashboard/bower_components/morrisjs/morris.min.js',
		//'dashboard/js/morris-data.js',
		'dashboard/dist/js/sb-admin-2.js',
    ];
    public $depends = [
        'yii\web\YiiAsset',
        'yii\bootstrap\BootstrapAsset',
    ];
}
