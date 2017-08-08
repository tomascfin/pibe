(function () {
    'use strict';
    angular
            .module('app')
            .controller('GrillaController', Grilla);

    Grilla.$inject = ['$scope', '$compile', '$timeout', 'uiCalendarConfig', '$log', 'ServicioWS', 'uiGridConstants'];
    function Grilla($scope, $compile, $timeout, uiCalendarConfig, $log, ServicioWS, uiGridConstants) {

        var vm = this;
        $scope.clickHandler = {
            onClick: function (value) {
                $log.error("clickHandler");
                alert('Name: ' + value);
            }
        };
        
        vm.prueba = function(objeto){
            $log.error(objeto);
        };
        
        $scope.gridOptions = {
            enableFiltering: true,
            columnDefs: [
                {field: 'numeroDeSerie'},
                {field: 'usos'},
                {field: 'establecimiento'},
                {field: 'boton',
                    enableFiltering: false,
                    cellTemplate:'<button><a ui-sref="detalle_serie({serieId: row.entity.numeroDeSerie})">Ver detalles</a></button>'}
                
            ],
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

        $scope.myData = [];
        vm.datos = function () {
            ServicioWS.listarDetalleSeries()
                    .then(function (response) {
                        $log.error(response.data.datos.length);
                        for (var i = 0; i < response.data.datos.length; i++) {
                            $scope.gridOptions.data.push(response.data.datos[i]);
                        }

                    }).catch(function (e) {
                $log.error('Error: ', e);
                throw e;
            }).finally(function () {
                $log.error('This finally block');
            });
        };



    }
})();