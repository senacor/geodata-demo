'use strict';

/**
 * @ngdoc function
 * @name angularjsApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the angularjsApp
 */
angular.module('angularjsApp')
    .controller('PostalcodeCtrl', function ($scope, $http, lookupPostalcodesFactory) {
        $scope.search = function () {
            $scope.codes = lookupPostalcodesFactory.lookup($scope.postalCode);
        };
    }).factory('lookupPostalcodesFactory', function ($http) {
        return {
            lookup: function (postalCode) {
                $http.get('http://api.geonames.org/findNearbyPostalCodesJSON?country=DE&radius=10&username=demo&postalcode=' + postalCode)
                    .success(function (response) {
                        if (response.status) {
                            console.error(response.status.message);
                        }
                        return response.postalCodes;
                    });
            }
        };
    });