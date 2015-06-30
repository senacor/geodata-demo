'use strict';

/**
 * @ngdoc function
 * @name angularjsApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the angularjsApp
 */
angular.module('angularjsApp')
    .controller('PostalcodeLookupCtrl', function ($scope, $http, lookupPostalcodesFactory) {
        // TODO: get countries from service as well
        $scope.countries = [
            {code: 'DE', name: 'Germany'},
            {code: 'CH', name: 'Switzerland'}
        ];
        $scope.search = function () {
            $scope.codes = lookupPostalcodesFactory.lookup($scope.postalCode, $scope.country);
        };
    }).factory('lookupPostalcodesFactory', function ($http) {
        return {
            lookup: function (postalCode, country) {
                // TODO: use promise, Make username configurable
                $http.get('http://api.geonames.org/findNearbyPostalCodesJSON?country=' + country + '&radius=10&username=demo&postalcode=' + postalCode)
                    .success(function (response) {
                        if (response.status) {
                            console.error(response.status.message);
                        }
                        return response.postalCodes;
                    });
            }
        };
    });