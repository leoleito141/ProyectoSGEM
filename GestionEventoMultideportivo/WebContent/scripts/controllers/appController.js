'use strict';

angular.module('pruebaAngularApp')
  .controller('AppCtrl', ['$scope','$cookieStore','$location', function ($scope,$cookieStore,$location) {
  	$scope.usrLogin={nombre:"", clave:"", estaConectado:"" };

  	$scope.salir = function() {
      $scope.usrLogin={nombre:"", clave:"", estaConectado:"" };

      $cookieStore.remove('estaConectado');
      $cookieStore.remove('usuario');

      $location.path('/');
    };
 
	 


  }]);