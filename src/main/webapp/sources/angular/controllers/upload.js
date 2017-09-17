/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function () {
    'use strict';
    angular
        .module('app')
        .controller('ControladorUpload', controlador);
    controlador.$inject = ['$scope', '$log', 'ServicioWS', '$http', 'RutHelper', '$q', '$timeout', '$element', 'uibDateParser', 'uiUploader', 'uiGridConstants'];

    function controlador($scope, $log, ServicioWS, $http, RutHelper, $q, $timeout, $element, uibDateParser, uiUploader, uiGridConstants) {
        var vm = this;
        vm.spinnerCargando = false;
        vm.reclamos = [];
        vm.seriesDisponibles = [];
        vm.areas = [];
        vm.entidades = [];
        vm.id = "";

        function Reclamo(detalleReclamo, emailContacto, nombreContacto, numeroContacto, prioridad, tipoReclamo, idEntidad, idArea) {
            this.id = null,
                this.detalleReclamo = detalleReclamo,
                this.emailContacto = emailContacto,
                this.nombreContacto = nombreContacto,
                this.numeroContacto = numeroContacto,
                this.prioridad = prioridad,
                this.rutaArchivo = null,
                this.tipoReclamo = tipoReclamo,
                this.idEntidad = idEntidad,
                this.idArea = idArea;

        };

        vm.cargarData = function (bool) {
            $log.error(bool);
            vm.obtenerSeriesDisponibles(bool);
            vm.obtenerEntidades();
            vm.obtenerAreas();
        };

        vm.obtenerSeriesDisponibles = function (bool, id) {
            $log.error("entro a series disponibles controler");
            $log.error(id);
            $log.error(bool);
            ServicioWS.getSeriesDisponibles(bool, id)
                .then(function (response) {
                    vm.seriesDisponibles = response.data;
                    $log.error(vm.seriesDisponibles);
                });
        };

        vm.obtenerEntidades = function () {

            ServicioWS.getEntidades()
                .then(function (response) {
                    vm.entidades = response.data;
                });

        };

        vm.obtenerAreas = function(){
            $log.error("areas");
            ServicioWS.getAreas()
                .then(function(response){
                    for (var i = 0; i < response.data.areas.length; i++) {
                        vm.areas.push(response.data.areas[i]);
                    }
                    $log.error(vm.areas);
                }).catch(function (e) {
                $log.error('Error: ', e);
                throw e;
            }).finally(function () {

            });
        };


        vm.ingresarReclamo = function () {
            vm.spinnerCargando = true;
            $log.error(vm.areaId);
            var object = new Reclamo(vm.reclamos.detalleReclamo, vm.reclamos.emailContacto, vm.reclamos.nombreContacto, vm.reclamos.numeroContacto, vm.reclamos.prioridad, vm.reclamos.tipoReclamo, vm.identidadSeleccionada.id, Number(vm.areaId));
            ;
            ServicioWS.ingresarReclamo(object)
                .then(function (response) {
                    vm.spinnerCargando = false;
                    vm.id = response.data.id;
                    $log.error("id: "+ response.data.id);
                    //toaster.pop($scope.toaster.type, $scope.toaster.title, $scope.toaster.text);
                    return response.data;

                }).catch(function (e) {
                $log.error('Error: ', e);
                throw e;
            }).finally(function () {
                $log.error('This finally block');
                $scope.btn_upload(vm.id);
            });

        };


        $scope.btn_remove = function (file) {
            $log.info('deleting=' + file);
            uiUploader.removeFile(file);
        };
        $scope.btn_clean = function () {
            uiUploader.removeAll();
        };
        $scope.btn_upload = function (id) {
            $log.info('uploading...');
            uiUploader.startUpload({
                url: 'http://localhost:8080/pibe/rest/upload/pdf?id='+id,
                concurrency: 2,
                onProgress: function (file) {
                    $log.info(file.name + '=' + file.humanSize);
                    $scope.$apply();
                },
                onCompleted: function (file, response) {
                    $log.info(file + 'response' + response);
                }
            });
        };
        $scope.files = [];
        var element = document.getElementById('file1');
        element.addEventListener('change', function (e) {
            var files = e.target.files;
            uiUploader.addFiles(files);
            $scope.files = uiUploader.getFiles();
            $scope.$apply();
        });
    }
})();