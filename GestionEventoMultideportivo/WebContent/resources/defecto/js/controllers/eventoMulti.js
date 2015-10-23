/*'use strict';

angular.module('pruebaAngularApp')
  .controller('EventoMultiCtrl', ['$scope','dataFactory', function ($scope,dataFactory) {
	
	  $scope.evento={};
//	  $scope.organizador={};
//	  var file = $scope.myFile;
	 
	  
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
	  
	  
	  
	  
  }]);

*/
