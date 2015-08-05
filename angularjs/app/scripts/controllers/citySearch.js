(function () {
    'use strict';
    var mod = angular.module('citySearch', []);

    mod.controller('CitySearchCtrl', ['$scope','$http', function ($scope, $http) {
        $scope.mapbox = {
            north : 0,
            south : 0,
            east : 0,
            west : 0
        };

        $scope.searchCitiesInBox = function () {
            var data = $http.get("cityNames.json").success(function (response) { return response.data; });

            console.log("Data ", data);

            return data;
        }        
    }]);
})();

