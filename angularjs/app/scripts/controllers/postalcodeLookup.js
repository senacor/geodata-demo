'use strict';

/**
 * @ngdoc function
 * @name angularjsApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the angularjsApp
 */
angular.module('angularjsApp')
    .controller('PostalcodeCtrl', function ($scope, $http) {
        $scope.search = function () {
            $http.get('http://api.geonames.org/findNearbyPostalCodesJSON?country=CH&radius=10&username=demo&postalcode=' + $scope.postalCode)
                .success(function (response) {
                    $scope.codes = response.postalCodes;
                });
        }
    });