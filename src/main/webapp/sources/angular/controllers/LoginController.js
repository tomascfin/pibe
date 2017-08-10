
(function () {
    'use strict';
    angular
            .module('LoginApp')
            .controller('LoginController', LoginController);
    LoginController.$inject = ['$scope', '$log', '$window', '$http'];
    function LoginController($scope, $log, $window, $http, ) {


        var vm = this;

        vm.activated = true;
        vm.determinateValue = 30;
        vm.test = "test bind";
        vm.usuario = {};
        vm.id = "";
        vm.estadoLogin = "";
        vm.password = "";
        vm.progressCirculo = true;
        vm.pruebame = function () {
            $log.error("pruebame");
        };

        vm.login = function () {
            $log.error("Entro a vm.login");
            vm.usuario.idUsuario = Number(vm.id);
            vm.usuario.password = vm.password;
            $http.post("http://localhost:8080/pibe/ws/pibe/login", vm.usuario).then(function (response) {
                vm.estadoLogin = "Login exitoso";
                $window.location.href = 'http://localhost:8080/pibe/';
            }).catch(function (e) {
                if (e.status === 401) {
                    vm.estadoLogin = "Usuario o clave incorrecta";
                }

            }).finally(function () {
            });
        };
    }
})();