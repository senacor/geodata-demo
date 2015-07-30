'use strict';

describe('ChartsCtrl', function () {

        beforeEach(module('angularjsApp'));

        var $controller, $scope;

        beforeEach(inject(function (_$controller_, $rootScope) {
            // The injector unwraps the underscores (_) from around the parameter names when matching
            $controller = _$controller_;
            $scope = $rootScope.$new();
        }));


        describe('Controller and functions', function () {
            var controller;

            beforeEach(function () {
                controller = $controller('ChartsCtrl', {$scope: $scope});
            });

            it('is available', function () {
                expect(controller).toBeDefined();
            });

            it('defines the onDragComplete method', function () {
                expect($scope.onDragComplete).toBeDefined();
            });

            it('defines the onDropComplete method', function () {
                expect($scope.onDragComplete).toBeDefined();
            });

        });

        describe('Initial state', function () {
            var controller;

            beforeEach(function () {
                controller = $controller('ChartsCtrl', {$scope: $scope});
            });

            it('has Germany as initial country', function () {
                expect($scope.country.name).toBe('Germany');
            });

            it('has 2921044 as initial geonameId', function () {
                expect($scope.country.geonameId).toBe('2921044');
            });

            it('has the correct options', function () {
                expect($scope.options.responsive).toBe(true);
                expect($scope.options.segmentShowStroke).toBe(true);
                expect($scope.options.segmentStrokeColor).toBe('#fff');
                expect($scope.options.segmentStrokeWidth).toBe(2);
                expect($scope.options.percentageInnerCutout).toBe(0);
                expect($scope.options.animationSteps).toBe(50);
                expect($scope.options.animationEasing).toBe('easeOut');
                expect($scope.options.animateRotate).toBe(false);
                expect($scope.options.animateScale).toBe(false);
            });

        });

        function createGetNeighboursResult(countries) {
            return {
                'geonames': countries
            };
        }

        function dragCountry($httpBackend, country) {
            // Have to set this manually in test. I assume the chart framework takes care of this normally?
            $scope.chart = true;
            // initial call to get neighbours from germany
            $httpBackend.flush();
            $scope.onDragComplete({}, {'event': {'country': country}});
            // call to get neighbours
            $httpBackend.flush();
        }

        describe('Choose Denmark as new country', function () {
            var controller, $httpBackend;
            var germany = {'name': 'Germany', 'geonameId': '2921044'};
            var denmark = {'name': 'Denmark', 'geonameId': '2623032'};

            beforeEach(inject(function (_$httpBackend_) {
                $httpBackend = _$httpBackend_;
                $httpBackend.when('GET', 'http://api.geonames.org/neighboursJSON?formatted=true&geonameId=' + germany.geonameId + '&username=mygeo')
                    .respond(createGetNeighboursResult([germany, denmark]));
                $httpBackend.when('GET', 'http://api.geonames.org/neighboursJSON?formatted=true&geonameId=' + denmark.geonameId + '&username=mygeo')
                    .respond(createGetNeighboursResult([germany, denmark]));
                controller = $controller('ChartsCtrl', {$scope: $scope});
            }));

            it('should set country to Denmark', function () {
                expect($scope.country).toEqual(germany);

                dragCountry($httpBackend, denmark);

                expect($scope.country).toEqual(denmark);
            });

            it('should set chart data to Denmark and Germany', function () {
                dragCountry($httpBackend, denmark);

                expect($scope.data.length).toBe(2);
                expect($scope.data[0].country).toEqual(germany);
                expect($scope.data[1].country).toEqual(denmark);
            });
        });

    }
);