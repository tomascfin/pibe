(function () {
    'use strict';
    angular
            .module('app')
            .service('ServicioWS', serviceWs);

    serviceWs.$inject = ['$http', '$log', '$location'];
    function serviceWs($http, $log, $location) {
        var vm = this;


        vm.getProductos = function () {
            $log.error("entro a la funcion del servicio");
            return $http.get("http://localhost:8080/pibe/ws/pibe/listar_comunas");
        };
        vm.getAreas = function () {
            $log.error("entro a la funcion de obtener areas");
            return $http.get("http://localhost:8080/pibe/ws/pibe/areas");
        };
         vm.login = function (json) {
            $log.error("entro a la funcion de obtener areas");
            console.log($location.path("/ws/pibe/login"));
            return $http.post($location.path("/pibe/ws/pibe/login"), json);
        };
        vm.getSeriesDisponibles = function (bool, idEntidad) {
            $log.error("entro a la funcion del servicio");
            return $http.get("http://localhost:8080/pibe/ws/pibe/obtener_listado_series?activado=" + bool + "&valor=" + idEntidad);
        };
        vm.getEntidades = function () {
            $log.error("entro a la funcion del servicio");
            return $http.get("http://localhost:8080/pibe/ws/pibe/listar_entidades");
        };
        vm.contadorReclamos = function () {
            return $http.get("http://localhost:8080/pibe/ws/pibe/contador_reclamos");
        };
        vm.registrarEntidad = function (json) {
            return $http.post("http://localhost:8080/pibe/ws/pibe/registrar_entidad", json);
        };
        vm.ampliarPibe = function (json) {
            return $http.post("http://localhost:8080/pibe/ws/pibe/ampliar_series", json);
        };
        vm.registrarAgendamiento = function (json) {
            $log.error("agendamiento");
            return $http.post("http://localhost:8080/pibe/ws/pibe/registrar_agendamiento", json);
        };
        vm.ingresarReclamo = function (json) {
            $log.error("reclamo", json);
            return $http.post("http://localhost:8080/pibe/ws/pibe/ingresar_reclamo", json);
        };
        vm.modificarReclamo = function (json) {
            $log.error("agendamiento");
            return $http.post("http://localhost:8080/pibe/ws/pibe/modificar_reclamo", json);
        };
        vm.verificarIdEntidad = function (id) {
            $log.error("entro a la funcion del servicio");
            return $http.get("http://localhost:8080/pibe/ws/pibe/verficar_id_entidad?id_entidad=" + id);
        };
        vm.listarReclamos = function (tipo) {
            return $http.get("http://localhost:8080/pibe/ws/pibe/listar_reclamos2?tipo=" + tipo);
        };
        vm.getEvents = function (id) {
            $log.error("entro a la funcion del servicio");
            return $http.get("http://localhost:8080/pibe/ws/agendamiento/listar_agendamientos");
        };
        vm.eliminarAgendamiento = function (id) {
            $log.error("entro a la funcion del servicio");
            return $http.delete("http://localhost:8080/pibe/ws/agendamiento/eliminar_agendamiento?id=" + id);
        };
        vm.listarDetalleSeries = function (id) {
            $log.error("entro a la funcion del servicio");
            return $http.get("http://localhost:8080/pibe/ws/pibe/listar_series_detalle");
        };
        vm.detalleSerie = function (id) {
            $log.error("entro a la funcion del servicio");
            return $http.get("http://localhost:8080/pibe/ws/pibe/detalle_serie/" + id);
        };
        vm.activarPibe = function (json) {
            $log.error("entro a la funcion del servicio");
            return $http.post("http://localhost:8080/pibe/ws/pibe/activacion_pibe", json);
        };



    }


})();