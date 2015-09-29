'use strict';

angular.module('pruebaAngularApp')
  .controller('AppCtrl', ['$scope','$location','$auth', function ($scope,$location,$auth) {
  	$scope.usrLogin={nombre:""};
  	$scope.tenantid={tenant:""};

  	$scope.salir = function() {
  		
	    $scope.usrLogin={nombre:""};//Deja usrLogin vacio
	    $auth.logout(); //Limpia localStorage y pone isAuthenticated en false
	
	    //Redirecciona al login
	    $location.path('/');
    };
    
    $scope.isAuthenticated = function() {
    	 
    	  return $auth.isAuthenticated();
    };
 
	 


  }]);