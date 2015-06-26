'use strict';

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

  factory.postCustomer = function(customer) {

  };
  return factory;
});

/**
 * @ngdoc function
 * @name angularjsApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the angularjsApp
 */
angularjsApp.controller('PlaygroundController', function ($scope, simpleFactory) {

  $scope.customers = [];

  init();

  function init() {
    $scope.customers = simpleFactory.getCustomers();
  }

  $scope.addCustomer = function () {
    $scope.customers.push({name: $scope.inputData.name, city: $scope.inputData.city});
  };

});
