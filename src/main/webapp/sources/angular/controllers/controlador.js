/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function () {
    'use strict';
    angular
            .module('app')
            .controller('ControladorFrutal', controlador);


    controlador.$inject = ['$scope', '$log', 'ServicioWS', '$http', 'RutHelper', '$q', '$timeout', '$element', 'uiUploader', 'uibDateParser'];
    function controlador($scope, $log, ServicioWS, $http, RutHelper, $q, $timeout, $element, uiUploader, uibDateParser) {
        var vm = this;
        $log.error("prueba");
        vm.comunas = [];
        vm.comunasFinales = [];
        vm.isDisabled = false;
        vm.noCache = false;
        vm.user = [];
        vm.comunasLimpio = [];
        vm.comunasFinales = vm.comunas;
        $scope.searchTerm;
        vm.activacionUsos = "";
        vm.idExiste = false;
        vm.entidad = {
            "idEntidad": null,
            "direccion": null,
            "email": null,
            "nombreContacto": null,
            "nombreEntidad": null,
            "telefonoContacto": null,
            "comunaId": null
        };
        vm.activacion = {
            "idSerie": null,
            "idEntidad": null,
            "usos": null,
            "idEjecutivo": null,
            "fechaActivacion": null,
            "observacion": null,
            "ordenDeCompra": null
        };
        vm.agendamiento = {
            "idEntidad": null,
            "detalleAgendamiento" : null,
            "emailAgendamiento": null,
            "nombreContacto": null,
            "telefonoContacto": null,
            "fechaAgendamiento" : null,
            "inicioHorario" : null,
            "fechaPrueba": null,
            "finHorario" : null
        };
        vm.agendar =[];
        vm.ocultarMensaje = true;
        vm.agendar.nombreContacto = "";
        vm.agendar.telefonoContacto= "";
        vm.agendar.emailContacto = "";
        vm.agendar.detalleContacto = "";
        vm.seriesDisponibles = [];
        vm.format = 'yyyy/MM/dd';
        vm.date = new Date();
        vm.opcionSeleccionada = $scope.searchTerm;
        vm.serieSeleccionada = $scope.search;
        //vm.querySearch = querySearch;
        //vm.selectedItemChange = selectedItemChange;
        //vm.searchTextChange = searchTextChange;
        $scope.direccionEntidad = "";
        vm.entidades = [];
        vm.identidadSeleccionada = $scope.searchEntidades;

        $scope.clearSearchTerm = function () {
            $log.error(vm.opcionSeleccionada);
            $scope.searchTerm = '';
        };
        // The md-select directive eats keydown events for some quick select
        // logic. Since we have a search input here, we don't need that logic.
        $element.find('input').on('keydown', function (ev) {
            ev.stopPropagation();
        });

        vm.prueba = function () {
            $log.error("prueba");
        };
        function comunaObjeto(value, display, provincia, region) {
            this.value = value;
            this.display = display;
            this.provincia = provincia;
            this.region = region;
        }
        vm.cargarData = function () {
            vm.obtenerSeriesDisponibles();
            vm.obtenerEntidades();
        };
        vm.obtenerSeriesDisponibles = function () {
            $log.error("entro a series disponibles controler");
            ServicioWS.getSeriesDisponibles()
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

        vm.obtenerComunas = function () {

            ServicioWS.getProductos()
                    .then(function (response) {
                        vm.comunas = response.data;
                        //$log.error(vm.comunas.Comunas);
                        for (var i = 0; i < vm.comunas.Comunas.length; i++) {
                            //  $log.error(vm.comunas.Comunas[i].display);
                            var objeto = new comunaObjeto(vm.comunas.Comunas[i].value, vm.comunas.Comunas[i].display, vm.comunas.Comunas[i].provincia, vm.comunas.Comunas[i].region);
                            vm.comunasFinales.push(objeto);
                        }

                    });
        };


        vm.registrarEntidad = function () {
            vm.entidad.idEntidad = vm.user.rut;
            vm.entidad.direccion = vm.user.direccionEntidad;
            vm.entidad.email = vm.user.emailContacto;
            vm.entidad.nombreContacto = vm.user.nombreContacto;
            vm.entidad.nombreEntidad = vm.user.nombreEntidad;
            vm.entidad.telefonoContacto = vm.user.telefonoContacto;
            vm.entidad.comunaId = vm.opcionSeleccionada.value;





            $log.error(vm.entidad);
            ServicioWS.registrarEntidad(vm.entidad)
                    .then(function (response) {
                        return response.data;
                    }).catch(function (e) {
                vm.idExiste = true;
                $log.error('Error: ', e);
                throw e;
            }).finally(function () {
                $log.error('This finally block');
            });
        };
        vm.searchTextChange = function (text) {
            $log.info('Text changed to ' + text);
        }

        vm.selectedItemChange = function (item) {
            $log.info('Item changed to ' + JSON.stringify(item));
        }

        vm.verificarId = function () {
            $log.error("entro a verificar id");
            ServicioWS.verificarIdEntidad(vm.user.rut)
                    .then(function (response) {
                        vm.idExiste = false;
                        return response.data;
                    }).catch(function (e) {
                vm.idExiste = true;
                $log.error('Error: ', e);
                throw e;
            }).finally(function () {
                $log.error('This finally block');
            });
        };


        vm.activarPibe = function () {
            vm.fecha = new Date(2017, 5, 1);
            $log.error("entro a activar pibe")
            vm.activacion.idSerie = vm.serieSeleccionada.id,
                    vm.activacion.idEntidad = vm.identidadSeleccionada.id,
                    vm.activacion.usos = vm.activacionUsos,
                    vm.activacion.idEjecutivo = "prueba",
                    vm.activacion.fechaActivacion = vm.fecha,
                    vm.activacion.observacion = "observacion de prueba",
                    vm.activacion.ordenDeCompra = "orden de prueba";

            $log.error(vm.activacion);

            ServicioWS.activarPibe(JSON.stringify(vm.activacion))
                    .then(function (response) {
                        return response.data;
                    }).catch(function (e) {
                vm.idExiste = true;
                $log.error('Error: ', e);
                throw e;
            }).finally(function () {
                $log.error('This finally block');
            });


        };


        vm.loadAll = function () {
            var allStates = " ";
            $log.error("entro a load all");
            for (var i = 0; i < vm.comunasFinales.length; i++) {
                allStates += vm.comunasFinales[i].nombre;

            }


            return allStates.split(/, +/g).map(function (state) {
                return {
                    value: state.toLowerCase(),
                    display: state
                };
            });
        };


        vm.querySearch = function (query) {
            $log.error("entro a query search" + query);
            var results = query ? vm.comunasFinales.filter(createFilterFor(query)) : vm.comunasFinales,
                    deferred;
            if (vm.simulateQuery) {
                deferred = $q.defer();
                $timeout(function () {
                    deferred.resolve(results);
                }, Math.random() * 1000, false);
                return deferred.promise;
            } else {
                $log.error(results);
                return results;
            }
        };

        function createFilterFor(query) {
            var lowercaseQuery = angular.lowercase(query);

            return function filterFn(state) {
                return (state.value.indexOf(lowercaseQuery) === 0);
            };

        }

        $scope.mytime = new Date();

        $scope.hstep = 1;
        $scope.mstep = 15;

        $scope.options = {
            hstep: [1, 2, 3],
            mstep: [1, 5, 10, 15, 25, 30]
        };

        $scope.ismeridian = true;
        $scope.toggleMode = function () {
            $scope.ismeridian = !$scope.ismeridian;
        };

        $scope.update = function () {
            var d = new Date();
            d.setHours(14);
            d.setMinutes(0);
            $scope.mytime = d;
        };

        $scope.changed = function () {
            $log.log('Time changed to: ' + $scope.mytime);
        };

        $scope.clear = function () {
            $scope.mytime = null;
        };
        
        vm.crearFecha = function(){
            //vm.fecha = new Date();
           
            $log.error(vm.fecha);
        };
        
        vm.fecha1 = "";
         vm.agendar = function () {
            $log.error("entra a funcion agendar");
           
            vm.fecha = $scope.dt;
            
          
            vm.fecha.setHours($scope.mytime.getHours());
            vm.fecha.setMinutes($scope.mytime.getMinutes());
            vm.fecha.setSeconds(0);
            vm.fecha.setMilliseconds(0);
          
            $log.error(moment(vm.fecha).format('YYYY-MM-DD hh:mm:ss'));
            
                    vm.agendamiento.detalleAgendamiento =  vm.agendar.detalleContacto,
                    vm.agendamiento.idEntidad = vm.identidadSeleccionada.id,
                    vm.agendamiento.emailAgendamiento = vm.agendar.emailContacto,
                    vm.agendamiento.nombreContacto = vm.agendar.nombreContacto,
                    vm.agendamiento.telefonoContacto = vm.agendar.telefonoContacto,
                    //vm.agendamiento.fechaAgendamiento = $scope.mytime;
                    vm.agendamiento.fechaAgendamiento = moment(vm.fecha).format('YYYY-MM-DD'),
                    //vm.agendamiento.inicioHorario = $scope.mytime.toISOString(),
            vm.agendamiento.inicioHorario = moment(vm.fecha).format('YYYY-MM-DD HH:mm:ss.SSSSSS'),
                    vm.agendamiento.finHorario = moment(vm.fecha).minutes(45).format('YYYY-MM-DD HH:mm:ss.SSSSSS'),
                    vm.agendamiento.fechaPrueba = moment(vm.fecha).format('YYYY-MM-DD HH:mm:ss.SSSSSS');
                     $log.error(vm.fecha);
            
            $log.error(vm.agendamiento);

            ServicioWS.registrarAgendamiento(JSON.stringify(vm.agendamiento))
                    .then(function (response, status) {
                        vm.ocultarMensaje = false;
                        return response.data;
                    }).catch(function (e) {
                //vm.idExiste = true;
                $log.error('Error: ', e);
                throw e;
            }).finally(function () {
                $log.error('This finally block');
            });


        };
        /* $scope.btn_remove = function(file) {
         $log.info('deleting=' + file);
         uiUploader.removeFile(file);
         };
         $scope.btn_clean = function() {
         uiUploader.removeAll();
         };
         $scope.btn_upload = function() {
         $log.info('uploading...');
         uiUploader.startUpload({
         url: 'http://localhost:8080/pibe/ws/pibe/subir_archivo',
         concurrency: 2,
         onProgress: function(file) {
         $log.info(file.name + '=' + file.humanSize);
         $scope.$apply();
         },
         onCompleted: function(file, response) {
         $log.info(file + 'response' + response);
         }
         });
         };
         $scope.files = [];
         var element = document.getElementById('file1');
         element.addEventListener('change', function(e) {
         var files = e.target.files;
         uiUploader.addFiles(files);
         $scope.files = uiUploader.getFiles();
         $scope.$apply();
         });*/

    }
    ;
})();