/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function () {
    'use strict';
    angular
            .module('app')
            .controller('ControllerDetalleSerie', detalleSerieController);
    detalleSerieController.$inject = ['$scope', '$log', '$stateParams', 'ServicioWS'];
    function detalleSerieController($scope, $log, $stateParams, ServicioWS) {
        var vm = this;
        vm.serie = {};
        vm.idSerie = $stateParams.serieId;
        $log.error($stateParams.serieId);
        

        vm.obtenerDetalleSerie = function () {
            ServicioWS.detalleSerie(vm.idSerie)
                    .then(function (response) {
                        $log.error(response.data);
                        vm.serie = response.data;
                        //response.data.movimientos.$$treeLevel = 1;
                        //$scope.gridOptions.data.push(response.data);
                        
                        //$scope.gridOptions.data.movimientos.$$treeLevel = 1;
                        for (var i = 0; i < response.data.movimientos.length; i++) {
                            $scope.gridOptions.data.push(response.data.movimientos[i]);
                        }
                    }).catch(function (e) {
                $log.error('Error: ', e);
                throw e;
            }).finally(function () {
                $log.error('Finalizado');
            });
        };
        
        
        if (vm.idSerie !== 'undefined' || vm.idSerie !== null || vm.idSerie !== '') {
            vm.obtenerDetalleSerie();
        }
        
        vm.movimientos = [];
        
        $scope.gridOptions = {
            //enableFiltering: true,
            showTreeExpandNoChildren: true,
            columnDefs: [
                {field: 'tipoMovimiento'},
                {field: 'usosMovimiento'},
                {field: 'fechaMovimiento'}
                
            ],
            enableGridMenu: true,
            //enableSelectAll: true,
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
    }
})();