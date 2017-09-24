<%-- 
    Document   : index
    Created on : 08-ago-2017, 20:21:37
    Author     : Tomas
--%>

<%@page import="com.eos.pibe.model.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%

    Usuarios user = (Usuarios) session.getAttribute("user");

    if (user == null) {
        response.sendRedirect("http://localhost:8080/pibe/login.html");
    }

%>
<!DOCTYPE html>
<html data-ng-app="app">
    <head>
        <link rel="stylesheet" type="text/css" href="bower_components/angularjs-toaster/toaster.css">
        <link rel="stylesheet" type="text/css" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="bower_components/angular-ui-bootstrap/dist/ui-bootstrap-csp.css">
        <link rel="stylesheet" type="text/css" href="bower_components/fullcalendar/dist/fullcalendar.min.css">
        <link rel="stylesheet" type="text/css" href="bower_components/angular-ui-grid/ui-grid.min.css">
        <!--<link rel="stylesheet" type="text/css" href="bower_components/angular-material/angular-material.min.css">
        <link rel="stylesheet" type="text/css" href="sources/CSS/registro_entidades.css">-->
        <link rel="stylesheet" type="text/css" href="sources/CSS/select.css">
        <link rel="stylesheet" type="text/css" href="sources/CSS/calendario.css">
        <!--<link rel="stylesheet" type="text/css" href="sources/CSS/header.css">-->
        <link rel="stylesheet" type="text/css" href="sources/CSS/angulr.css">
        <link rel="stylesheet" type="text/css" href="bower_components/select2.css">
        <link rel="stylesheet" type="text/css" href="bower_components/selectize.default.css">

        <style>
            body {
                padding: 15px;
            }

            .select2 > .select2-choice.ui-select-match {
                /* Because of the inclusion of Bootstrap */
                height: 29px;
            }

            .selectize-control > .selectize-dropdown {
                top: 36px;
            }
            /* Some additional styling to demonstrate that append-to-body helps achieve the proper z-index layering. */
            .select-box {
                background: #fff;
                position: relative;
                z-index: 1;
            }
            .alert-info.positioned {
                margin-top: 1em;
                position: relative;
                z-index: 10000; /* The select2 dropdown has a z-index of 9999 */
            }
        </style>
        <title>Pibe</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body ng-controller="ControladorFrutal">

        <div data-ng-include=" 'vistas/blocks/header.html'" ></div>
        <!--<div data-ng-include=" 'vistas/blocks/nav.html'" ></div>-->

        <!-- <div class="app" id="app" ng-class="{'app-header-fixed':app.settings.headerFixed, 'app-aside-fixed':app.settings.asideFixed, 'app-aside-folded':app.settings.asideFolded, 'app-aside-dock':app.settings.asideDock, 'container':app.settings.container}" ui-view></div>-->
        <div ui-view></div>


        <script src="bower_components/jquery/dist/jquery.min.js"></script>

        <script src="bower_components/fusioncharts/fusioncharts.js" type="text/javascript"></script>
        <script type="text/javascript" src="bower_components/moment/min/moment.min.js"></script>
        <script type="text/javascript" src="bower_components/fullcalendar/dist/fullcalendar.min.js"></script>
        <script type="text/javascript" src="bower_components/fullcalendar/dist/gcal.js"></script>
        <script src="bower_components/angular/angular.min.js"></script>
        <script src="bower_components/ag-grid/dist/ag-grid.js" type="text/javascript"></script>
        <script src="bower_components/angularjs-toaster/toaster.js" type="text/javascript"></script>
        <script type="text/javascript" src="bower_components/angular-ui-calendar/src/calendar.js"></script>
        <script type="text/javascript" src="bower_components/angular-ui-uploader/dist/uploader.js"></script>
        <script src="bower_components/angular-ui-bootstrap/dist/ui-bootstrap.js"></script>
        <script src="bower_components/angular-ui-bootstrap/dist/ui-bootstrap-tpls.js"></script>
        <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="bower_components/angular-rut/dist/angular-rut.js" type="text/javascript"></script>
        <script src="bower_components/angular-ui-select/dist/select.min.js" type="text/javascript"></script>
        <script src="bower_components/angular-sanitize/angular-sanitize.min.js" type="text/javascript"></script>
        <script src="bower_components/angular-ui-uploader/dist/uploader.min.js" type="text/javascript"></script>       
        <script src="bower_components/angular-ui-router/release/angular-ui-router.min.js"></script>
        <script src="bower_components/angular-ui-grid/ui-grid.min.js"></script>
        <script src="bower_components/angular-touch/angular-touch.min.js"></script>
        <script src="bower_components/angular-ui-bootstrap/dist/ui-bootstrap-tpls.js"></script>
        <script src="bower_components/angular-animate/angular-animate.min.js" type="text/javascript"></script>
        <script src="bower_components/angular-aria/angular-aria.min.js" type="text/javascript"></script>
        <script src="bower_components/angular-messages/angular-messages.min.js" type="text/javascript"></script>
        <script src="bower_components/angular-material/angular-material.min.js" type="text/javascript"></script>
        <script src="bower_components/pdfmake/build/pdfmake.min.js" type="text/javascript"></script>
        <script src="bower_components/pdfmake/build/vfs_fonts.js" type="text/javascript"></script>
        <script src="bower_components/angular-route/angular-route.min.js" type="text/javascript"></script>
        <script src="bower_components/fusioncharts/themes/fusioncharts.theme.fint.js" type="text/javascript"></script>
        <script src="bower_components/angular-fusioncharts/angular-fusioncharts.min.js" type="text/javascript"></script>
        <script src="bower_components/angular-websocket/dist/angular-websocket.js" type="text/javascript"></script>

        <!--<script src="bower_components/angular-ui-grid/ui-grid.min.js" type="text/javascript"></script>-->
        <!--<script src="bower_components/agGrid-7.1.0/agGrid-Enterprise/ag-grid-enterprise.min.js" type="text/javascript"></script> 
        <script src="bower_components/agGrid-7.1.0/agGrid-free/ag-grid.min.js" type="text/javascript"></script> -->
        <script src="bower_components/moment/moment.js" type="text/javascript"></script>
        <script src="sources/angular/app/app.js"></script>
        <script src="sources/angular/configs/route.js"></script>
        <script src="sources/angular/services/servicios.js"></script>
        <script src="sources/angular/factory/factory.js"></script>
        <script src="sources/grilla.js"></script>
        <script src="sources/graficos.js"></script>
        <script src="sources/angular/controllers/calendario.js"></script>
        <script src="sources/angular/controllers/controlador.js"></script>
        <script src="sources/angular/controllers/upload.js"></script>
        <script src="sources/angular/controllers/detalle_serie.js"></script>
        <script src="sources/angular/controllers/headerController.js"></script>
        <script src="sources/angular/controllers/modalInstance.js"></script>
        <script src="sources/angular/controllers/detalleReclamoController.js"></script>
        <script src="sources/angular/controllers/component.js"></script>
        <script src="sources/reclamos.js"></script>




    </body>



</html>
