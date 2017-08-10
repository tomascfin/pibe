
(function () {
    'use strict';
    angular
            .module('app')
            .controller('HeaderController', HeaderController);
    HeaderController.$inject = ['$scope', '$log',  '$window', '$http'];
    function HeaderController($scope, $log,  $window, $http) {
        var vm = this;
        vm.test = "test bind";
        vm.usuario = {};
        vm.id = "";
        vm.password = "";
        vm.usuario =  {};
        
        vm.pruebame = function(){
            $log.error("pruebame");
        };

        vm.getLogin = function () {
            $log.error("Entro a vm.login");
            vm.usuario.idUsuario = Number(vm.id);
            vm.usuario.password = String(vm.password);
          $http.get("http://localhost:8080/pibe/ws/pibe/get_login",).then(function (response) {
              $log.error(response.data);
               vm.usuario =  response.data;
            }).catch(function (e) {
                $log.error('Error: ', e);
                throw e;
            }).finally(function () {
            });
        };
    }
})();