'use strict';

var angularjsApp = angular.module('angularjsApp');

angularjsApp.factory('simpleFactory', function () {
  var customers = [
    {name: 'Maria', city: 'Bonn'},
    {name: 'Tom', city: 'Beul'},
    {name: 'Steffen', city: 'Hamburg'},
    {name: 'Ronja', city: 'Köln'}
  ];

  var factory = {};
  factory.getCustomers = function () {
    return customers;
  };

  return factory;
});

/**
 * @ngdoc function
 * @name angularjsApp.controller:PlaygroundCtrl
 * @description
 * # PlaygroundCtrl
 * Controller of the angularjsApp
 */
angularjsApp.controller('PlaygroundCtrl', function ($scope, simpleFactory) {

  $scope.customers = [];

  function init() {
    $scope.customers = simpleFactory.getCustomers();
  }
  init();

  $scope.addCustomer = function () {
    $scope.customers.push({name: $scope.inputData.name, city: $scope.inputData.city});
  };

});
