
(function () {
    'use strict';
    angular
            .module('app')
            .controller('ControladorFrutal', controlador);


    controlador.$inject = ['$scope', '$log', 'ServicioWS', '$http', 'RutHelper', '$q', '$timeout', '$element', 'uibDateParser', 'uiUploader', 'uiGridConstants', 'toaster', '$window', 'archivoDirective', 'fileUploadSerive'];
    function controlador($scope, $log, ServicioWS, $http, RutHelper, $q, $timeout, $element, uibDateParser, uiUploader, uiGridConstants, toaster, $window, archivoDirective, fileUploadSerive) {
        ///TEMPLATE
        var isIE = !!navigator.userAgent.match(/MSIE/i);
        if (isIE) {
            angular.element($window.document.body).addClass('ie');
        }
        if (isSmartDevice($window)) {
            angular.element($window.document.body).addClass('smart')
        }
        ;

        // config
        $scope.app = {
            name: 'Angulr',
            version: '2.2.0',
            // for chart colors
            color: {
                primary: '#7266ba',
                info: '#23b7e5',
                success: '#27c24c',
                warning: '#fad733',
                danger: '#f05050',
                light: '#e8eff0',
                dark: '#3a3f51',
                black: '#1c2b36'
            },
            settings: {
                themeID: 1,
                navbarHeaderColor: 'bg-black',
                navbarCollapseColor: 'bg-white-only',
                asideColor: 'bg-black',
                headerFixed: true,
                asideFixed: false,
                asideFolded: false,
                asideDock: false,
                container: false
            }
        };

    

      
        function isSmartDevice($window)
        {
            // Adapted from http://www.detectmobilebrowsers.com
            var ua = $window['navigator']['userAgent'] || $window['navigator']['vendor'] || $window['opera'];
            // Checks for iOs, Android, Blackberry, Opera Mini, and Windows mobile devices
            return (/iPhone|iPod|iPad|Silk|Android|BlackBerry|Opera Mini|IEMobile/).test(ua);
        }
        ///FIN TEMPLATE



        var vm = this;
        $log.error("prueba");
        vm.duracion = 45;
        vm.comunas = [];
        vm.comunasFinales = [];
        vm.isDisabled = false;
        vm.noCache = false;
        vm.ampliacionUsos = 0;
        vm.ordenDeCompra = "";
        vm.user = [];
        vm.reclamos = [];
        vm.comunasLimpio = [];
        vm.comunasFinales = vm.comunas;
        $scope.searchTerm;
        vm.archivos = {};
        vm.activacionUsos = "";
        vm.idExiste = false;
        vm.areas = [];
        vm.areaId = 0;
        vm.tipoActivacion = "Activacion";
        vm.tipoActivaciones = ["Activacion", "Reinstalacion"];
        vm.duraciones = [15, 30, 45, 60];
        vm.tipoAgendamientos = ["Activacion", "Ampliacion", "Reinstalacion", "Reclamo", "Otro"];
        vm.tipoAgendamiento = "";
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
            "ordenDeCompra": null,
            "tipoActivacion": null
        };
        vm.agendamiento = {
            "idEntidad": null,
            "detalleAgendamiento": null,
            "emailAgendamiento": null,
            "nombreContacto": null,
            "telefonoContacto": null,
            "fechaAgendamiento": null,
            "inicioHorario": null,
            "fechaPrueba": null,
            "finHorario": null
        };
        vm.ampliacion = {
            "idEntidad": null,
            "idSerie": null,
            "usos": null,
            "ordenDeCompra": null
        };
        vm.spinnerCargando = false;
        vm.agendar = [];
        vm.ocultarMensaje = true;
        vm.agendar.nombreContacto = "";
        vm.agendar.telefonoContacto = "";
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
        $scope.toaster = {
            type: 'success',
            title: 'Ingresado',
            text: 'Reclamo correctamente ingresado'
        };

        vm.cambiando = function () {
            $log.error("cambio");
        };
        $scope.clearSearchTerm = function () {
            $log.error(vm.opcionSeleccionada);
            $scope.searchTerm = '';
        };
        // The md-select directive eats keydown events for some quick select
        // logic. Since we have a search input here, we don't need that logic.
        $element.find('input').on('keydown', function (ev) {
            ev.stopPropagation();
        });
        vm.clickTab = function (id) {
            $log.error("tab");
            $log.error(id);
        };
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

        }
        ;

        vm.prueba = function () {
            $log.error("prueba");
        };

        vm.subirArchivo = function () {
          var uploadUrl = "";
        };
        function comunaObjeto(value, display, provincia, region) {
            this.value = value;
            this.display = display;
            this.provincia = provincia;
            this.region = region;
        }
        vm.cargarData = function (bool) {
            $log.error(bool);
            vm.obtenerSeriesDisponibles(bool);
            vm.obtenerEntidades();
            vm.obtenerAreas();
        };

        vm.cargarData2 = function (bool) {
            $log.error(bool);
            vm.obtenerEntidades();
            vm.obtenerAreas();
        };
        vm.probarToaster = function () {
            toaster.pop($scope.toaster.type, $scope.toaster.title, $scope.toaster.text);
            $log.error("probando toaster");
            toaster.pop('success', "title", "text");
        };
        vm.ingresarReclamo = function () {
            vm.spinnerCargando = true;
            $log.error(vm.areaId);
            var object = new Reclamo(vm.reclamos.detalleReclamo, vm.reclamos.emailContacto, vm.reclamos.nombreContacto, vm.reclamos.numeroContacto, vm.reclamos.prioridad, vm.reclamos.tipoReclamo, vm.identidadSeleccionada.id, Number(vm.areaId));
            ;
            ServicioWS.ingresarReclamo(object)
                    .then(function (response) {
                        vm.spinnerCargando = false;
                        toaster.pop($scope.toaster.type, $scope.toaster.title, $scope.toaster.text);
                        return response.data;

                    }).catch(function (e) {
                $log.error('Error: ', e);
                throw e;
            }).finally(function () {
                $log.error('This finally block');
            });
        };

        vm.ampliarPibe = function () {
            vm.ampliacion.idEntidad = vm.identidadSeleccionada.id,
                    vm.ampliacion.idSerie = vm.serieSeleccionada.id,
                    vm.ampliacion.usos = vm.ampliacionUsos,
                    vm.ampliacion.ordenDeCompra = vm.ordenDeCompra;
            $log.error(vm.ampliacion);
            ServicioWS.ampliarPibe(vm.ampliacion)
                    .then(function (response) {
                        toaster.pop('success', "Ingreso exitoso", "Se ha ampliado la serie");
                        return response.data;
                    }).catch(function (e) {
                $log.error('Error: ', e);
                throw e;
            }).finally(function () {
                $log.error('This finally block');
            });

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
            vm.activacion.tipoActivacion = vm.tipoActivacion;

            $log.error(vm.activacion);

            ServicioWS.activarPibe(JSON.stringify(vm.activacion))
                    .then(function (response) {
                        toaster.pop('success', "Ingreso exitoso", "Se ha activado la serie");
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

        vm.crearFecha = function () {
            //vm.fecha = new Date();

            $log.error(vm.fecha);
        };

        vm.fecha1 = "";
        vm.agendar = function () {
            $log.error("entra a funcion agendar");

            vm.fecha = $scope.dt;
            vm.spinnerCargando = true;

            vm.fecha.setHours($scope.mytime.getHours());
            vm.fecha.setMinutes($scope.mytime.getMinutes());
            vm.fecha.setSeconds(0);
            vm.fecha.setMilliseconds(0);
            $log.error("duracion: " + vm.duracion);
            $log.error(moment(vm.fecha).format('YYYY-MM-DD hh:mm:ss'));

            vm.agendamiento.detalleAgendamiento = vm.agendar.detalleContacto,
                    vm.agendamiento.idEntidad = vm.identidadSeleccionada.id,
                    vm.agendamiento.emailAgendamiento = vm.agendar.emailContacto,
                    vm.agendamiento.nombreContacto = vm.agendar.nombreContacto,
                    vm.agendamiento.telefonoContacto = vm.agendar.telefonoContacto,
                    //vm.agendamiento.fechaAgendamiento = $scope.mytime;
                    vm.agendamiento.fechaAgendamiento = moment(vm.fecha).format('YYYY-MM-DD'),
                    //vm.agendamiento.inicioHorario = $scope.mytime.toISOString(),
                    vm.agendamiento.inicioHorario = moment(vm.fecha).format('YYYY-MM-DD HH:mm:ss.SSSSSS'),
                    vm.agendamiento.finHorario = moment(vm.fecha).add(vm.duracion, 'minutes').format('YYYY-MM-DD HH:mm:ss.SSSSSS'),
                    vm.agendamiento.fechaPrueba = moment(vm.fecha).format('YYYY-MM-DD HH:mm:ss.SSSSSS');
            $log.error(vm.fecha);

            $log.error(vm.agendamiento);

            ServicioWS.registrarAgendamiento(JSON.stringify(vm.agendamiento))
                    .then(function (response, status) {
                        vm.spinnerCargando = false;
                        //toaster.pop($scope.toaster.type, $scope.toaster.title, $scope.toaster.text);
                        toaster.pop('success', "Ingresado", "Agendamiento ingresado correctamente");
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
         url: 'http://localhost:8080/pibe/rest/upload/pdf',
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
        /* var today = new Date();
         var nextWeek = new Date();
         nextWeek.setDate(nextWeek.getDate() + 7);
         
         $scope.highlightFilteredHeader = function (row, rowRenderIndex, col, colRenderIndex) {
         if (col.filters[0].term) {
         return 'header-filtered';
         } else {
         return '';
         }
         };
         
         $scope.gridOptions = {
         enableFiltering: true,
         onRegisterApi: function (gridApi) {
         $scope.gridApi = gridApi;
         },
         columnDefs: [
         // default
         {field: 'name', headerCellClass: $scope.highlightFilteredHeader},
         // pre-populated search field
         {field: 'gender', filter: {
         term: '1',
         type: uiGridConstants.filter.SELECT,
         selectOptions: [{value: '1', label: 'male'}, {value: '2', label: 'female'}, {value: '3', label: 'unknown'}, {value: '4', label: 'not stated'}, {value: '5', label: 'a really long value that extends things'}]
         },
         cellFilter: 'mapGender', headerCellClass: $scope.highlightFilteredHeader},
         // no filter input
         {field: 'company', enableFiltering: false, filter: {
         noTerm: true,
         condition: function (searchTerm, cellValue) {
         return cellValue.match(/a/);
         }
         }},
         // specifies one of the built-in conditions
         // and a placeholder for the input
         {
         field: 'email',
         filter: {
         condition: uiGridConstants.filter.ENDS_WITH,
         placeholder: 'ends with'
         }, headerCellClass: $scope.highlightFilteredHeader
         },
         // custom condition function
         {
         field: 'phone',
         filter: {
         condition: function (searchTerm, cellValue) {
         var strippedValue = (cellValue + '').replace(/[^\d]/g, '');
         return strippedValue.indexOf(searchTerm) >= 0;
         }
         }, headerCellClass: $scope.highlightFilteredHeader
         },
         // multiple filters
         {field: 'age', filters: [
         {
         condition: uiGridConstants.filter.GREATER_THAN,
         placeholder: 'greater than'
         },
         {
         condition: uiGridConstants.filter.LESS_THAN,
         placeholder: 'less than'
         }
         ], headerCellClass: $scope.highlightFilteredHeader},
         // date filter
         {field: 'mixedDate', cellFilter: 'date', width: '15%', filter: {
         condition: uiGridConstants.filter.LESS_THAN,
         placeholder: 'less than',
         term: nextWeek
         }, headerCellClass: $scope.highlightFilteredHeader
         },
         {field: 'mixedDate', displayName: "Long Date", cellFilter: 'date:"longDate"', filterCellFiltered: true, width: '15%',
         }
         ]
         };
         
         $http.get('https://cdn.rawgit.com/angular-ui/ui-grid.info/gh-pages/data/500_complex.json')
         .then(function (response) {
         $scope.gridOptions.data = response.data;
         $scope.gridOptions.data[0].age = -5;
         
         response.data.forEach(function addDates(row, index) {
         row.mixedDate = new Date();
         row.mixedDate.setDate(today.getDate() + (index % 14));
         row.gender = row.gender === 'male' ? '1' : '2';
         });
         
         $scope.toggleFiltering = function () {
         $scope.gridOptions.enableFiltering = !$scope.gridOptions.enableFiltering;
         $scope.gridApi.core.notifyDataChange(uiGridConstants.dataChange.COLUMN);
         };
         });
         
         
         
         
         
         
         
         $scope.toggleFiltering = function () {
         $scope.gridOptions.enableFiltering = !$scope.gridOptions.enableFiltering;
         $scope.gridApi.core.notifyDataChange(uiGridConstants.dataChange.COLUMN);
         }*/
    }
    ;

})();