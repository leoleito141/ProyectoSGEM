'use strict';

angular.module('pruebaAngularApp')
  .controller('MainCtrl', ['$scope','$location','$auth','dataFactory','$routeParams', 
                           function ($scope, $location, $auth, dataFactory, $routeParams) {
	
	  $scope.evento={};
	  $scope.organizador={};
	  console.log($scope.evento);
	  
	  $scope.openInicio = function($eventInicio) {
		    $scope.statusInicio.opened = true;
		  };

	  $scope.openFin = function($eventFin) {
		    $scope.statusFin.opened = true;
		  };
	   
	  $scope.statusInicio = {
			    opened: false
			  };
	  
	  $scope.statusFin = {
			    opened: false
			  };
	  
	  $scope.dateOptions = {
			    formatYear: 'yy',
			    startingDay: 1
			  };
	  
	  
	  $scope.altaEvento = function(){
		  dataFactory.altaEvento($scope.evento)
	     	.then(function (data, status, headers, config) {
	                $scope.status = data.status;
	                console.log("Entre Alta Evento");
	                console.log(data.status);
	                console.log(status);
	                console.log(headers);
	                console.log(config);
	                
	                var payLoad = $auth.getPayload();
	                console.log( payLoad.tenantid);
	                $location.path(payLoad.tenantid + '/altaOrganizador');
	                
	            })
	            .catch(function(response){
	                // Si ha habido errores llegamos a esta parte
	            	console.log(response); 
	            });
	
	  };
	  
	  $scope.guardarOrganizador = function(){
		  dataFactory.guardarOrganizador($scope.evento,$scope.organizador)
	     	.then(function (data, status, headers, config) {
	                $scope.status = data.status;
	                console.log("Entre Alta Organizador");
	                console.log(data.status);
	                console.log(status);
	                console.log(headers);
	                console.log(config);
	                
	                var payLoad = $auth.getPayload();
	                console.log( payLoad.tenantid);
	                $location.path(payLoad.tenantid + '/');
	                
	            })
	            .catch(function(response){
	                // Si ha habido errores llegamos a esta parte
	            	console.log(response); 
	            });
	
	  };
	  
	  
	  
	  
  }]);

