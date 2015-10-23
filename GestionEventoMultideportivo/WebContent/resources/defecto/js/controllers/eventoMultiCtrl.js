'use strict';

angular.module('pruebaAngularApp')
  .controller('EventoMultiCtrl', ['$scope','dataFactory','dataTenant', function ($scope,dataFactory,dataTenant) {
	
	  $scope.nombreTenant = dataTenant.nombre_url;
	  
	  //hacemos de cuenta que somos organizadores
	  
	  
	  $scope.evento={};
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
		  var file = $scope.myFile;

		  dataFactory.altaEvento($scope.evento,file)
	     	.then(function (data, status, headers, config) {
	                 console.log(data);
	                
	                
	            })
	            .catch(function(response){
	                // Si ha habido errores llegamos a esta parte
	            	console.log(response); 
	            });
		  
		  
		  
	  
		  
		  
		  
	  };
	  
	  $scope.soyOrganizador = function() {
	    	 
    	  return false;
    };
	  
	  
  }]);
