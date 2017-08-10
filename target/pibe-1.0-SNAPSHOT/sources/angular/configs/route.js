/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function () {
    'use strict';

    angular
            .module('app')
            .config(config);


    config.$inject = ['$stateProvider', '$urlRouterProvider'];
    function config($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/home');
        var layout = "vistas/app.html";
        $stateProvider
                 .state('home', {
                 url: '/home',
                 templateUrl: 'vistas/home.html'
                 })/*
                 .state('inicio', {
                 url: '/inicio',
                 templateUrl: 'index.html'
                 })*/
                /*.state('home', {
                    url: '/home',
                    templateUrl: 'app.html'
                })*/
                .state('productos', {
                    url: '/productos',
                    templateUrl: 'vistas/productos.html',
                    controller: 'ControladorFrutal'
                })
                .state('header', {
                    url: '/header',
                    templateUrl: 'vistas/blocks/header.html'
                })/*
                .state('login', {
                    url: '/login',
                    templateUrl: 'login.html'
                })*/
                 .state('websocket', {
                    url: '/websocket',
                    templateUrl: 'vistas/websocket2.html'
                })
                .state('registro_entidad', {
                    url: '/registro_entidad',
                    templateUrl: 'vistas/registro_entidad.html'
                })
                .state('registro_series', {
                    url: '/registro_series',
                    templateUrl: 'vistas/registro_series.html'
                })
                .state('activacion_pibe', {
                    url: '/activacion_pibe',
                    templateUrl: 'vistas/activacion_pibe.html'
                })
                .state('registro_reclamo', {
                    url: '/registro_reclamo',
                    templateUrl: 'vistas/registro_reclamo.html'
                })
                .state('ampliacion', {
                    url: '/ampliacion',
                    templateUrl: 'vistas/ampliacion.html'
                })
                .state('calendario', {
                    url: '/calendario',
                    templateUrl: 'vistas/calendario.html',
                    controller: 'calendario'
                })
                .state('tabla_series', {
                    url: '/tabla_series',
                    templateUrl: 'vistas/tabla_series.html'
                })
                .state('agendamiento', {
                    url: '/agendamiento',
                    templateUrl: 'vistas/agendamiento.html',
                    controller: 'calendario'
                })
                .state('reclamos', {
                    reloadOnSearch: false,
                    url: '/reclamos',
                    templateUrl: 'vistas/reclamos.html'
                })
                .state('grafico', {
                    url: '/grafico',
                    templateUrl: 'vistas/grafico.html'
                })
                .state('detalle_serie', {
                    url: '/detalle_serie/:serieId',
                    templateUrl: 'vistas/detalle_serie.html'
                })
                .state('about', {
                });
    }
    ;

})();