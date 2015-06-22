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
        $httpBackend.expectGET('views/view1.html').respond(200, 'main HTML');
    }));

    it('should route view1', function () {
        expect($route.routes['/view1'].controller).toBe('View1Ctrl');
        expect($route.routes['/view1'].templateUrl).
            toEqual('views/view1.html');
    });

    it('should route view2', function () {
        expect($route.routes['/view2'].controller).toBe('View2Ctrl');
        expect($route.routes['/view2'].templateUrl).
            toEqual('views/view2.html');
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

    it('should map / to view1', function () {
        expect($route.current).toBeUndefined();
        $location.path('/');
        $rootScope.$digest();

        expect($location.path()).toBe('/view1');
    });

});
