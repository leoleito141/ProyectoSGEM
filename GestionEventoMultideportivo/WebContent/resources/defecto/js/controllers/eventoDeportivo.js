angular.module('pruebaAngularApp')
  .controller('EventDeportivoCtrl', ['$scope','$location','dataFactory','$routeParams', 
                           function ($scope, $location, dataFactory, $routeParams) {
	
	  $scope.eventoDeportivo={};
	  console.log($scope.eventoDeportivo);
	  
	  
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
	  
	  
	  $scope.altaEventoDeportivo = function(){
		  dataFactory.altaEventoDeportivo($scope.eventoDeportivo)
	     	.then(function (data, status, headers, config) {
	                $scope.status = data.status;
	                console.log("Entre Alta Evento Deportivo");
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

