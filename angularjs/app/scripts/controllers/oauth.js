'use strict';

var angularjsApp = angular.module('angularjsApp');

angularjsApp.factory('userInfoFactory', ['$http', function ($http) {
  var factory = {};
  factory.getUserInfo = function (accessToken) {
    return $http.get('https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=' + accessToken);
  };
  factory.getTokenInfo = function (accessToken) {
    return $http.get('https://www.googleapis.com/oauth2/v1/tokeninfo?access_token=' + accessToken);
  };
  return factory;
}]);


/**
 * @ngdoc function
 * @name angularjsApp.controller:OAuthCtrl
 * @description
 * # OAuthCtrl
 * Controller of the angularjsApp
 */
angularjsApp.controller('OAuthCtrl', function ($scope, $timeout, AccessToken, userInfoFactory) {
  function init() {
    $scope.accessToken = null;
    $scope.loggedIn = false;
    $scope.userinfo = emptyUserInfo();
    $scope.tokenInfo = {
      'expiresAt': null,
      'expiresIn': null
    };
  }
  init();

  function emptyUserInfo() {
    return {
      'id': null,
      'email': '',
      'verified_email': false,
      'name': '',
      'given_name': '',
      'family_name': '',
      'picture': '',
      'locale': 'de'
    };
  }

  $scope.$on('oauth:login', function (event, token) {
    var accessToken = token.access_token;
    setLoginData(accessToken);
    loadUserInfo(accessToken);
    loadTokenInfo(accessToken);
  });

  $scope.$on('oauth:authorized', function (event, token) {
    var accessToken = token.access_token;
    setLoginData(accessToken);
    if ($scope.userinfo === null || $scope.userinfo.id === null) {
      loadUserInfo(accessToken);
    }
    if ($scope.tokenInfo === null || $scope.tokenInfo.expiresIn === null) {
      loadTokenInfo(accessToken);
    }
  });

  $scope.$on('oauth:logout', function (event) {
    clearLoginData();
  });

  function clearLoginData() {
    $scope.accessToken = null;
    $scope.loggedIn = false;
    $scope.tokenInfo =  {
      'expiresAt': null,
      'expiresIn': null
    };
    $scope.userinfo = emptyUserInfo();
  }

  function setLoginData(token) {
    $scope.accessToken = token;
    $scope.loggedIn = true;
  }

  function loadUserInfo(accessToken) {
    userInfoFactory.getUserInfo(accessToken)
      .success(function (data) {
        $scope.userinfo = data;
      })
      .error(function (error) {
        $scope.userinfo = emptyUserInfo();
        // TODO report error
        $scope.userinfo.name = 'Load failed';
      });
  }

  function loadTokenInfo(accessToken) {
    userInfoFactory.getTokenInfo(accessToken)
      .success(function (data) {
        $scope.tokenInfo.expiresIn = data.expires_in;
        $scope.tokenInfo.expiresAt = Date.now() + (data.expires_in * 1000);
      })
      .error(function (error) {
        $scope.tokenInfo.expiresAt = null;
        $scope.tokenInfo.expiresIn = null;
      });
  }
});
