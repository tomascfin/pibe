(function () {
    'use strict';
    angular
            .module('app')
            .controller('reclamosController', Reclamos);

    Reclamos.$inject = ['$scope', '$compile', '$timeout', 'uiCalendarConfig', '$log', 'ServicioWS', 'uiGridConstants', '$stateParams', '$state'];
    function Reclamos($scope, $compile, $timeout, uiCalendarConfig, $log, ServicioWS, uiGridConstants, $stateParams, $state) {
        $log.error("cargo js");
        var vm = this;
        vm.reclamo = {};
        vm.opcionTab = 'todos';
        vm.cargarGrid = true;
        //parametros url test
        vm.foo = $stateParams.serieId;
         $scope.state = $state.current;
    $scope.params = $stateParams;
    $log.error("foo: "+vm.foo);
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

        vm.prueba = function (objeto) {
        };
        $scope.columnasSinBoton = [{field: 'id'},
            {field: 'nombreContacto'},
            {field: 'emailContacto'},
            {field: 'detalleReclamo'},
            {field: 'entidad.nombreEntidad', displayName: 'Nombre entidad'},
            {field: 'fechaReclamo'}];

        $scope.columnasConBoton = [{field: 'id'},
            {field: 'nombreContacto'},
            {field: 'emailContacto'},
            {field: 'detalleReclamo'},
            {field: 'entidad.nombreEntidad', displayName: 'Nombre entidad'},
            {field: 'fechaReclamo'},
            {field: 'Operacion',
                enableFiltering: false,
                cellTemplate: '<button ng-click="grid.appScope.ctlr.cargarRowObject(row.entity)">Click Here</button>'}];

        $scope.gridOptions = {
            paginationPageSize: 9,
            enableFiltering: true,
            columnDefs: $scope.columnasConBoton,
            enableGridMenu: true,
            enableSelectAll: true,
            exporterCsvFilename: 'myFile.csv',
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
        /*Svm.open = function (size, parentSelector) {
         $log.error("objeto open : "+vm.rowObject);
         var parentElem = parentSelector ? 
         angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
         var modalInstance = $uibModal.open({
         ariaLabelledBy: 'modal-title',
         ariaDescribedBy: 'modal-body',
         templateUrl: 'vistas/detalle_reclamo.html',
         controller: 'reclamosController',
         controllerAs: 'ctlr',
         size: size,
         appendTo: parentElem
         });
         
         
         };*/
    }
})();