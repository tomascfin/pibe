/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function () {
'use strict';
        angular
        .module('app')
        .controller('ControladorUpload', controlador);
        controlador.$inject = ['$scope', '$log', 'ServicioWS', '$http', 'RutHelper', '$q', '$timeout', '$element', 'uibDateParser', 'uiUploader', 'uiGridConstants'];
        function controlador($scope, $log, ServicioWS, $http, RutHelper, $q, $timeout, $element, uibDateParser, uiUploader, uiGridConstants) {

        $scope.btn_remove = function(file) {
        $log.info('deleting=' + file);
                uiUploader.removeFile(file);
        };
                $scope.btn_clean = function() {
                uiUploader.removeAll();
                };
                $scope.btn_upload = function() {
                $log.info('uploading...');
                        uiUploader.startUpload({
                        url: 'http://localhost:8080/pibe/rest/upload/pdf',
                                concurrency: 2,
                                onProgress: function(file) {
                                $log.info(file.name + '=' + file.humanSize);
                                        $scope.$apply();
                                },
                                onCompleted: function(file, response) {
                                $log.info(file + 'response' + response);
                                }
                        });
                };
                $scope.files = [];
                var element = document.getElementById('file1');
                element.addEventListener('change', function(e) {
                var files = e.target.files;
                        uiUploader.addFiles(files);
                        $scope.files = uiUploader.getFiles();
                        $scope.$apply();
                });
        }
})();