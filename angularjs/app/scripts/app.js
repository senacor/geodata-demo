'use strict';

/**
 * @ngdoc overview
 * @name angularjsApp
 * @description
 * # angularjsApp
 *
 * Main module of the application.
 */
angular
    .module('angularjsApp', ['ngRoute'])
    .config(function ($routeProvider) {
        $routeProvider.
            when('/view1', {
                templateUrl: 'views/view1.html',
                controller: 'View1Ctrl'
            }).
            when('/view2', {
                templateUrl: 'views/view2.html',
                controller: 'View2Ctrl'
            }).
            when('/view3', {
                templateUrl: 'views/view3.html',
                controller: 'View3Ctrl'
            }).
            when('/view4', {
                templateUrl: 'views/view4.html',
                controller: 'View4Ctrl'
            }).
            otherwise({
                redirectTo: '/view1'
            });
    });
