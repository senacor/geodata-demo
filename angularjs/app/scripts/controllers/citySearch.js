(function () {
    'use strict';
    var mod = angular.module('citySearch', []);

    function Mapbox(north, south, east, west) {
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }


    var CitySearchCtrl = function ($scope) {
        $scope.mapbox = new Mapbox(0, 0, 0, 0);

        $scope.searchCitiesInBox = function ($scope) {
            return {};
        }
    }

    mod.controller('CitySearchCtrl', CitySearchCtrl);
})();

