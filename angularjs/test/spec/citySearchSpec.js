//(function() {
//
//    'use strict';
//    beforeEach(module('citySearch'));
//
//    describe('CitySearchCtrl', function() {
//        // var $controller;
//        var $scope;
//
//        beforeEach(inject(function($rootScope, $controller) {
//            $scope = $rootScope.$new();
//
//            console.log($controller);
//
//            $controller('CitySearchCtrl', {$scope: $scope});
//        }));
//
//        it('should create an initial empty map box model', function() {
//            expect($scope.mapbox).toBe(new Mapbox(0, 0, 0, 0));
//        });
//
//
//        it('should search for cities within the map box', function() {
//            // todo how would we mock this?
//
//            $scope.searchCitiesInBox();
//
//            expect($scope.cities.length).toBe({ foo : 'bar' });
//        });
//    });
//
//})();
