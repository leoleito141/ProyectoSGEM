'use strict';

angular.module('pruebaAngularApp')
  .controller('MainCtrl', ['$scope','$location','dataFactory','$routeParams', 
                           function ($scope, $location, dataFactory, $routeParams) {
	
	  $scope.evento={};
	  console.log($scope.evento);
	  
	  $scope.altaEvento = function(){
		  dataFactory.altaEvento($scope.evento)
	     	.then(function (data, status, headers, config) {
	                $scope.status = data.status;
	                console.log("Entre Alta Evento");
	                console.log(data.status);
	                console.log(status);
	                console.log(headers);
	                console.log(config);
	                
	            })
	            .catch(function(response){
	                // Si ha habido errores llegamos a esta parte
	            	console.log(response); 
	            });
		  
		  
		  
	  };
	  
	  
	  
	  
  }]);

