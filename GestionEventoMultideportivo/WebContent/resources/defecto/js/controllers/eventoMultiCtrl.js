'use strict';

angular.module('pruebaAngularApp')
  .controller('EventoMultiCtrl', ['$scope','dataFactory','$state', function ($scope,dataFactory,$state) {
	  
	  $scope.evento={};
	  $scope.pais={};
	
	  
	 
	  
	 ////////////////////////////////Date timepiquer example/////////// 
	  $scope.$on('$viewContentLoaded' , function(){
          $('#datetimepicker2').datetimepicker({
        	  locale: 'es'	 
        	  
        	});
          $("#datetimepicker2").on("dp.change", function (e) {
        	  $scope.evento.fechaFin = e.date;
              
          });
          $('#datetimepicker1').datetimepicker({
        	  locale: 'es'
        	  
        	});
          $("#datetimepicker1").on("dp.change", function (e) {
        	  $scope.evento.fechaInicio = e.date;
              
          });
          
          

	  });
     
	 
	
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
