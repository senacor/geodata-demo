'use strict';

/**
 * @ngdoc function
 * @name angularjsApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the angularjsApp
 */
angular.module('angularjsApp')
    .controller('PostalcodeLookupCtrl', function ($scope, lookupPostalcodesFactory) {
        // TODO: get countries from service as well
        $scope.countries = [
            {code: 'DE', name: 'Germany'},
            {code: 'CH', name: 'Switzerland'}
        ];
        $scope.search = function () {
            var result = lookupPostalcodesFactory.lookup($scope.postalCode, $scope.country);
            result.success(function (response) {
                if (response.status) {
                    console.error(response.status.message);
                }
                $scope.codes = response.postalCodes;
            });
        };
    }).factory('lookupPostalcodesFactory', function ($http) {
        return {
            lookup: function (postalCode, country) {
                return $http.get('http://api.geonames.org/findNearbyPostalCodesJSON?country=' + country.code + '&radius=10&username=myGeo&postalcode=' + postalCode);
            }
        };
    });