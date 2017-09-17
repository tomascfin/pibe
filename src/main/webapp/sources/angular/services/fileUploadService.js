(function () {
    'use strict';
    angular
        .module('app')
        .service('fileUploadSerive', fileUploadSerive);

    fileUploadSerive.$inject = ['$http', '$log', '$location', '$parse'];

    function fileUploadSerive($http, $log, $location, $parse) {
        this.post = function (uploadUrl, data) {
            var fd = new FormData();
            for (var key in data) {
                fd.append(key, data[key]);
            }
            $http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            })
        }
    }
})();