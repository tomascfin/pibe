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

        $stateProvider
                .state('home', {
                    url: '/home',
                    templateUrl: 'vistas/index.html'
                })
                .state('productos', {
                    url: '/productos',
                    templateUrl: 'vistas/productos.html',
                    controller: 'ControladorFrutal'
                })
                .state('header', {
                    url: '/header',
                    templateUrl: 'vistas/blocks/header.html'
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
                .state('about', {

                });
    }
    ;

})();