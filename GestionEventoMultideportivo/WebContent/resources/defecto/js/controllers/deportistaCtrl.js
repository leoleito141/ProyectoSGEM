angular.module('pruebaAngularApp')
  .controller('deportistaCtrl', ['$scope','dataFactory','dataTenant', 
                           function ($scope, dataFactory,dataTenant) {
	
	  $scope.deportista={};
	  console.log($scope.deportista);
	  
	  
	  $scope.openInicio = function($eventInicio) {
		    $scope.statusInicio.opened = true;
		  };
   
	  $scope.statusInicio = {
			    opened: false
			  };
	  
	  $scope.dateOptions = {
			    formatYear: 'yy',
			    startingDay: 1
			  };
	  

	  
	  
	  
	  
  }]);

