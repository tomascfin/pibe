(function() {
    'use strict';
        angular
        .module('app')
        .service('ServicioWS', serviceWs);

    serviceWs.$inject = ['$http','$log'];
    function serviceWs($http,$log){
           var vm = this;
        
        
            vm.getProductos = function() {
                $log.error("entro a la funcion del servicio");
            return $http.get("http://localhost:8080/pibe/ws/pibe/listar_comunas");
               };
                vm.getSeriesDisponibles = function() {
                $log.error("entro a la funcion del servicio");
            return $http.get("http://localhost:8080/pibe/ws/pibe/obtener_listado_series");
               };
                vm.getEntidades = function() {
                $log.error("entro a la funcion del servicio");
            return $http.get("http://localhost:8080/pibe/ws/pibe/listar_entidades");
               };
            vm.registrarEntidad = function(json){
                return $http.post("http://localhost:8080/pibe/ws/pibe/registrar_entidad",json);
            };
             vm.verificarIdEntidad = function(id) {
                $log.error("entro a la funcion del servicio");
            return $http.get("http://localhost:8080/pibe/ws/pibe/verficar_id_entidad?id_entidad="+id);
               };
                vm.getEvents = function(id) {
                $log.error("entro a la funcion del servicio");
            return $http.get("http://localhost:8080/pibe/ws/pibe/listar_agendamientos");
               };
               vm.activarPibe = function(json) {
                $log.error("entro a la funcion del servicio");
            return $http.post("http://localhost:8080/pibe/ws/pibe/activacion_pibe", json);
               };
            
     
    
    }
    
    
})();