'use strict';

/**
 * @ngdoc overview
 * @name angularjsApp
 * @description
 * # angularjsApp
 *
 * Main module of the application.
 */
var angularjsApp = angular.module('angularjsApp', ['oauth', 'ngRoute', 'tc.chartjs', 'citySearch'])
    .config(function ($routeProvider) {
        $routeProvider.
            when('/welcome', {
                templateUrl: 'views/welcome.html',
                controller: 'WelcomeCtrl'
            }).
            when('/charts', {
                templateUrl: 'views/charts.html',
                controller: 'ChartsCtrl'
            }).
            when('/playground', {
                templateUrl: 'views/playground.html',
                controller: 'PlaygroundCtrl'
            }).
            when('/postalcodeLookup', {
                templateUrl: 'views/postalcodeLookup.html',
                controller: 'PostalcodeLookupCtrl'
            }).
            when('/citySearch', {
                templateUrl: 'views/citySearch.html',
                controller: 'CitySearchCtrl'
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
                redirectTo: '/welcome'
            });
    });


angularjsApp.config(function ($routeProvider) {
    $routeProvider
        .when('/access_token=:accessToken', {
            template: '',
            controller: function ($location, AccessToken) {
                var hash = $location.path().substr(1);
                AccessToken.setTokenFromString(hash);
                $location.path('/');
                $location.replace();
            }
        });
});
// can be replaced by oauth html5 mode
//angularjsApp.config(function($locationProvider) {
//  $locationProvider.html5Mode(true).hashPrefix('!');
//});

