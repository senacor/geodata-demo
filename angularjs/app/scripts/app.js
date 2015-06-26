'use strict';

/**
 * @ngdoc overview
 * @name angularjsApp
 * @description
 * # angularjsApp
 *
 * Main module of the application.
 */
var angularjsApp = angular.module('angularjsApp', ['oauth', 'ngRoute'])
    .config(function ($routeProvider) {
        $routeProvider.
            when('/', {
              templateUrl: 'views/welcome.html',
              controller: 'WelcomeCtrl'
            }).
            when('/playground', {
              templateUrl: 'views/playground.html',
              controller: 'PlaygroundController'
            }).
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
                redirectTo: '/'
            });
    });

// extra route for oauth
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
    })
});
// can be replaced by oauth html5 mode
//angularjsApp.config(function($locationProvider) {
//  $locationProvider.html5Mode(true).hashPrefix('!');
//});


angularjsApp.controller('OAuthCtrl', function ($scope, $timeout, AccessToken) {
  init();
  function init() {
    $scope.accessToken = null;
    $scope.loggedIn = false;
  }
  $scope.$on('oauth:login', function(event, token) {
    $scope.accessToken = token.access_token;
    $scope.loggedIn = true;
  });

  $scope.$on('oauth:logout', function(event) {
    $scope.accessToken = null;
    $scope.loggedIn = false;
  });
});
