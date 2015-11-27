'use strict';

angular.module('pruebaAngularApp')
  .controller('AppCtrl', ['$scope','$state','$auth', function ($scope,$state,$auth) {
  	$scope.usrLogin={nombre:""};
  	$scope.tenantid={tenant:""};
  	$scope.customStyle={
  			background:"",
  			
  	};
  	$('.carousel').carousel({
	    interval: 5000 //changes the speed
	})

  	$scope.salir = function() {
  		localStorage.removeItem("dataUsuario");
	    $auth.logout(); //Limpia localStorage y pone isAuthenticated en false
	
	    //Redirecciona al login
	    $state.go('main');
    };
    
    $scope.isAuthenticated = function() {
    	 
    	  return $auth.isAuthenticated();
    };
 
	 


  }]);