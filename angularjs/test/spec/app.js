'use strict';
describe('Routes test', function () {
    beforeEach(module('angularjsApp'));

    var $location, $route, $rootScope;

    beforeEach(inject(function (_$location_, _$route_, _$rootScope_) {
        $location = _$location_;
        $route = _$route_;
        $rootScope = _$rootScope_;
    }));

    beforeEach(inject(function ($httpBackend) {
        $httpBackend.expectGET('views/welcome.html').respond(200, 'main HTML');
    }));

    it('should route welcome', function () {
        expect($route.routes['/welcome'].controller).toBe('WelcomeCtrl');
        expect($route.routes['/welcome'].templateUrl).
            toEqual('views/welcome.html');
    });

    it('should route playground', function () {
        expect($route.routes['/playground'].controller).toBe('PlaygroundCtrl');
        expect($route.routes['/playground'].templateUrl).
            toEqual('views/playground.html');
    });

    it('should route postalcodeLookup', function () {
        expect($route.routes['/postalcodeLookup'].controller).toBe('PostalcodeLookupCtrl');
        expect($route.routes['/postalcodeLookup'].templateUrl).
            toEqual('views/postalcodeLookup.html');
    });

    it('should route to city Search', function () {
        expect($route.routes['/citySearch'].controller).toBe('CitySearchCtrl');
        expect($route.routes['/citySearch'].templateUrl).
            toEqual('views/citySearch.html');
    });

    it('should route view3', function () {
        expect($route.routes['/view3'].controller).toBe('View3Ctrl');
        expect($route.routes['/view3'].templateUrl).
            toEqual('views/view3.html');
    });

    it('should route view4', function () {
        expect($route.routes['/view4'].controller).toBe('View4Ctrl');
        expect($route.routes['/view4'].templateUrl).
            toEqual('views/view4.html');
    });

    it('should map / to welcome', function () {
        expect($route.current).toBeUndefined();
        $location.path('/');
        $rootScope.$digest();

        expect($location.path()).toBe('/welcome');
    });

});
