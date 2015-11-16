'use strict';

angular.module('pruebaAngularApp')
  .controller('EventoMultiCtrl', ['$scope','dataFactory','$state', function ($scope,dataFactory,$state) {
	
	  
	  //hacemos de cuenta que somos organizadores
	  
	  
	  $scope.evento={};
	  $scope.pais={};
	
	  
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
	  
	 ////////////////////////////////Date timepiquer example/////////// 
	  $scope.$on('$viewContentLoaded' , function(){
          $('#datetimepicker1').datetimepicker({
        	  
        	});
         

	  });
     
	  $scope.prueba={};
	  console.log($scope.prueba);
	  /////////////////////////////////////////////////////////////////
	  $scope.altaEvento = function(){
		  var evento = $scope.evento;
		  evento.dataPais = $scope.pais;
		  console.log(evento);
		  dataFactory.altaEvento(evento)
	     	.then(function (data, status, headers, config) {
	                 console.log(data);
	                 if(data.status == "200"){
		                 
		                 $state.go('main');
	                	 
	                 }
	                
	                
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
