'use strict';

/**
 * @ngdoc function
 * @name angularjsApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the angularjsApp
 */
angularjsApp.controller('WelcomeCtrl', function ($scope) {
    });



angularjsApp.controller('SimpleController', function ($scope) {
  $scope.customers = [
    { name: 'Maria', city: 'Bonn'},
    { name: 'Tom', city: 'Beul'} ,
    { name: 'Steffen', city: 'Hamburg'},
    { name: 'Ronja', city: 'Köln'}
  ];
});
