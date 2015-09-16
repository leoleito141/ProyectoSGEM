'use strict';

angular.module('pruebaAngularApp')
  .controller('AppCtrl', ['$scope','$cookieStore','$location','$auth', function ($scope,$cookieStore,$location,$auth) {
  	$scope.usrLogin={nombre:""};

  	$scope.salir = function() {
    $scope.usrLogin={nombre:""};
    $auth.logout();
      
/*
      $cookieStore.remove('estaConectado');
      $cookieStore.remove('usuario');
*/
      $location.path('/');
    };
    
    $scope.isAuthenticated = function() {
    	 
    	  return $auth.isAuthenticated();
    };
 
	 


  }]);