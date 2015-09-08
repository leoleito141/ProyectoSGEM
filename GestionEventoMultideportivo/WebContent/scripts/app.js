'use strict';

angular.module('pruebaAngularApp', [
  'ngRoute',
  'ui.bootstrap'
  ])
.config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/registro', {
        templateUrl: 'views/registro.html',
        controller: 'RegistroCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
