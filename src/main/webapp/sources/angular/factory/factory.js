(function() {
    'use strict';
        angular
        .module('app')
        .factory('factoryPibe', factoryPibe);

    factoryPibe.$inject = ['$http','$log','$uibModal'];
    function factoryPibe($http,$log, $uibModal){
          var vm = this;
     vm.obtenerUibModal = function(size, template, params){
          return $uibModal.open({
          animation: true,
          templateUrl: template || 'myModalContent.html',
          controller: 'ModalResultInstanceCtrl',
          size: size,
          resolve: {
            params: function() {
              return params;
            }
          }
        });
     };
    
    }
    
    
})();