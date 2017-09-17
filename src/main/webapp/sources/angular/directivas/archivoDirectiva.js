(function () {
    'use strict';
    angular
        .module('app')
        .directive('archivoDirective', archivoDirective);

    archivoDirective.$inject = ['$http', '$log', '$location', '$parse'];
    function archivoDirective($http, $log, $location, $parse) {
        var vm = this;

        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;

                element.bind('change', function () {
                    scpè.$apply(function () {
                        modelSetter(scope, element[0].files[0]);
                    })
                })
            }
        }





    }


})();