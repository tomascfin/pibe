/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function () {
    'use strict';
    angular
        .module('app')
        .controller('DetalleReclamoCtrl', DetalleReclamoCtrl);
    DetalleReclamoCtrl.$inject = ['$scope', '$log', '$uibModalInstance', 'ServicioWS', 'items', '$window', '$http'];

    function DetalleReclamoCtrl($scope, $log, $uibModalInstance, ServicioWS, items, $window, $http) {
        var vm = this;
        vm.reclamo = {};
        vm.urlPath = "C:/fotos_pibe";
        vm.urlPath2 = "C:\\fotos_pibe";

        vm.obtenerReclamo = function (id) {
            $log.error("parametro id: " + vm.reclamo);
            $http.get('http://localhost:8080/pibe/ws/reclamo/' + Number(items))
                .then(function (response) {
                    vm.reclamo = response.data;
                    vm.urlPath = vm.urlPath + vm.reclamo.rutaArchivo1;
                    vm.urlPath2 = vm.urlPath2 + vm.reclamo.rutaArchivo1;
                    $log.error(vm.reclamo);
                }).catch(function (e) {
                $log.error('Error: ', e);
                throw e;
            }).finally(function () {

            });
        };

        vm.descargarImagen = function () {
            $log.error(" rutaArchivo: " + vm.reclamo.rutaArchivo1);
            $http.get('http://localhost:8080/pibe/ws/archivos/fotos?activado=' +vm.reclamo.rutaArchivo1 )
                .then(function (response) {
                    $log.error(response);
                }).catch(function (e) {
                $log.error('Error: ', e);
                throw e;
            }).finally(function () {

            });
        }

    }
})();