(function () {
    'use strict';
    angular
            .module('app')
            .controller('reclamosController', Reclamos);

    Reclamos.$inject = ['$scope', '$window', '$compile', '$timeout', 'uiCalendarConfig', '$log', 'ServicioWS', 'uiGridConstants', '$stateParams', '$state', '$uibModal'];
    function Reclamos($scope, $window, $compile, $timeout, uiCalendarConfig, $log, ServicioWS, uiGridConstants, $stateParams, $state, $uibModal) {
        $log.error("cargo js");
        var vm = this;
        vm.reclamo = {};
        vm.opcionTab = 'todos';
        vm.cargarGrid = true;
        //parametros url test
        vm.foo = $stateParams.serieId;
        $scope.state = $state.current;
        $scope.params = $stateParams;
        $log.error("foo: " + vm.foo);
        //
        $scope.myData = [];
        $scope.myData2 = [];
        $scope.myData3 = [];
        vm.columnaActivada = true;
        $scope.clickHandler = {
            onClick: function (value) {
                alert('Name: ' + value);
            }
        };
        $scope.modalInstance = {};
        vm.prueba = function (objeto) {
        };
        $scope.columnasSinBoton = [{name: 'P', enableSorting: false, enableFiltering: false, field: 'colorHexidecimalValue', "cellTemplate": "<div class=\"ui-grid-cell-contents ng-scope ng-binding\" ng-style=\"{'background-color':COL_FIELD}\">", width: 30},
            {field: 'id', "cellTemplate": "<div class=\"ui-grid-cell-contents ng-scope ng-binding\" ng-style=\"{'background-color':COL_FIELD}\">", width: 30},
            {field: 'nombreContacto'},
            {field: 'emailContacto'},
            {field: 'detalleReclamo'},
            {field: 'entidad.nombreEntidad', displayName: 'Nombre entidad'},
            {field: 'fechaReclamo'}];

        $scope.columnasConBoton = [
            {field: 'id'},
            {field: 'nombreContacto'},
            {field: 'emailContacto'},
            {field: 'detalleReclamo'},
            {field: 'entidad.nombreEntidad', displayName: 'Nombre entidad'},
            {field: 'fechaReclamo'},
            {field: 'Acciones', enableFiltering: false, width: 285, cellTemplate:
                        '<div class="grid-action-cell">' +
                        '<a   class="btn btn-success" ng-click="grid.appScope.ctlr.open(row.entity.id)">Avanzar</a>' +
                        '<a   href="#" class="btn btn-default">Cerrar</a>' +
                        '<a   href="#" class="btn btn-default">Historial</a>' +
                        '<a   href="#" class="btn btn-danger">Eliminar</a></div>'}];

        $scope.gridOptions = {
            gridMenuShowHideColumns: false,
            paginationPageSize: 9,
            enableFiltering: true,
            columnDefs: $scope.columnasConBoton,
            enableGridMenu: true,
            enableSelectAll: true,
            exporterCsvFilename: 'Reclamos.csv',
            exporterPdfDefaultStyle: {fontSize: 9},
            exporterPdfTableStyle: {margin: [30, 30, 30, 30]},
            exporterPdfTableHeaderStyle: {fontSize: 10, bold: true, italics: true, color: 'red'},
            exporterPdfHeader: {text: "My Header", style: 'headerStyle'},
            exporterPdfFooter: function (currentPage, pageCount) {
                return {text: currentPage.toString() + ' of ' + pageCount.toString(), style: 'footerStyle'};
            },
            exporterPdfCustomFormatter: function (docDefinition) {
                docDefinition.styles.headerStyle = {fontSize: 22, bold: true};
                docDefinition.styles.footerStyle = {fontSize: 10, bold: true};
                return docDefinition;
            },
            exporterPdfOrientation: 'portrait',
            exporterPdfPageSize: 'LETTER',
            exporterPdfMaxGridWidth: 500,
            exporterCsvLinkElement: angular.element(document.querySelectorAll(".custom-csv-link-location")),
            onRegisterApi: function (gridApi) {
                $scope.gridApi = gridApi;
            }
        };
        $scope.splice = function () {
            $scope.columns.splice(1, 0, {field: 'boton',
                enableFiltering: false,
                cellTemplate: '<button ng-click="grid.appScope.ctlr.cargarRowObject(row.entity.detalleReclamo)">Click Here</button>'});


        };
        vm.rowObject = {};
        vm.cargarRowObject = function (objeto) {
            vm.rowObject = JSON.stringify(objeto.detalleReclamo);
            vm.open();
        };
        vm.datos = function (variable) {

            if (variable == 3) {
                $scope.gridOptions.columnDefs = $scope.columnasSinBoton;
            } else {
                $scope.gridOptions.columnDefs = $scope.columnasConBoton;
            }
            $scope.gridOptions.data = [];

            ServicioWS.listarReclamos(variable)
                    .then(function (response) {

                        for (var i = 0; i < response.data.length; i++) {
                            $scope.gridOptions.data.push(response.data[i]);
                            $scope.gridOptions.data[i].fechaReclamo = moment(response.data[i].fechaReclamo).format('YYYY-MM-DD HH:mm:ss');
                            vm.columnaActivada = false;
                        }
                    }).catch(function (e) {
                $log.error('Error: ', e);
                throw e;
            }).finally(function () {
            });
        };

        vm.contadorReclamos = function () {
            ServicioWS.contadorReclamos()
                    .then(function (response) {

                        vm.reclamo = response.data;

                    }).catch(function (e) {
                $log.error('Error: ', e);
                throw e;
            }).finally(function () {
                $log.error('This finally block');
            });
        };
        ////modal



        vm.items = ['item1', 'item2', 'item3'];

        vm.animationsEnabled = true;

        vm.open = function (id) {
            $log.error("entro a open");

            var modalInstance = $uibModal.open({
                animation: vm.animationsEnabled,
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'vistas/myModalContent.html',
                controller: 'ModalInstanceCtrl',
                controllerAs: 'ctlr',
                size: 20,
                resolve: {
                    items: function () {
                        return id;
                    }
                }
            });

            modalInstance.result.then(function (selectedItem) {
                /*for (var i = 0; i < $scope.gridOptions.data.length; ++i) {
                 $scope.gridOptions.data.splice(i--, 1);
                 }
                 vm.datos('1');    */
                location.reload();

                vm.selected = selectedItem;
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };

        vm.openComponentModal = function () {
            var modalInstance = $uibModal.open({
                animation: vm.animationsEnabled,
                component: 'modalComponent',
                resolve: {
                    items: function () {
                        return vm.items;
                    }
                }
            });

            modalInstance.result.then(function (selectedItem) {

                // vm.contadorReclamos();
                vm.selected = selectedItem;
            }, function () {
                $log.info('modal-component dismissed at: ' + new Date());
            });
        };

        vm.openMultipleModals = function () {
            $uibModal.open({
                animation: vm.animationsEnabled,
                ariaLabelledBy: 'modal-title-bottom',
                ariaDescribedBy: 'modal-body-bottom',
                templateUrl: 'stackedModal.html',
                size: 'sm',
                controller: function ($scope) {
                    $scope.name = 'bottom';
                }
            });

            $uibModal.open({
                animation: $ctrl.animationsEnabled,
                ariaLabelledBy: 'modal-title-top',
                ariaDescribedBy: 'modal-body-top',
                templateUrl: 'stackedModal.html',
                size: 'sm',
                controller: function ($scope) {
                    $scope.name = 'top';
                }
            });
        };

        vm.toggleAnimation = function () {
            vm.animationsEnabled = !$ctrl.animationsEnabled;
        };


    }
})();