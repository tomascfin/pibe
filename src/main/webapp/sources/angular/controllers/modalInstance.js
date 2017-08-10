/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function () {
    'use strict';
    angular
            .module('app')
            .controller('ModalInstanceCtrl', ModalInstanceCtrl);
    ModalInstanceCtrl.$inject = ['$scope', '$log', '$uibModalInstance', 'ServicioWS','items', '$window'];
    function ModalInstanceCtrl($scope, $log, $uibModalInstance, ServicioWS, items, $window) {
        var vm = this;
        vm.observacion = "";
        vm.reclamo = {id: items, observacion: "", idArea: 0};
        vm.reclamoBoolean = true;
        vm.areas = [];
        vm.areaId = 0;
        vm.siDerivar = function (bool){
          if(bool === true){
              vm.reclamoBoolean = false;
          }else{
              vm.reclamoBoolean = true;
          }  
        };
        vm.editarReclamo = function (){
            $log.error(vm.observacion);
            vm.modificar();
        };
        
        vm.obtenerAreas = function(){
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
         vm.modificar = function (){
             vm.reclamo.observacion = vm.observacion;
             vm.reclamo.idArea = Number(vm.areaId);
              ServicioWS.modificarReclamo(vm.reclamo)
                    .then(function (response) {
                        $uibModalInstance.close();
                        $window.location.reload();
                    }).catch(function (e) {
                $log.error('Error: ', e);
                throw e;
            }).finally(function () {
            });
         };
        

    }
})();