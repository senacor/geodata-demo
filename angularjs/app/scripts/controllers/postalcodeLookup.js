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
        $http.get('http://api.geonames.org/findNearbyPostalCodesJSON?postalcode=8775&country=CH&radius=10&username=demo')
            .success(function (response) {
                $scope.codes = response.postalCodes;
            });
    });