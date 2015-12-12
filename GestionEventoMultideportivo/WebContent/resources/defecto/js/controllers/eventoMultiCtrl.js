'use strict';

angular.module('pruebaAngularApp')
  .controller('EventoMultiCtrl', ['$scope','dataFactory','$state', function ($scope,dataFactory,$state) {
	  
	  $scope.evento={};
	  $scope.pais={};
	
	  $('.carousel').carousel({
		    interval: 5000 //changes the speed
		})
	 
	  
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
     
	 
	
	  ////////////////////////////Alta EventoMultideportivo /////////////////////////////////////
	  $scope.altaEvento = function(){
		  var evento = $scope.evento;
		  evento.pais = $scope.pais;
		  console.log(evento);
		  dataFactory.altaEvento(evento)
	     	.then(function (data, status, headers, config) {
	                
	                 if(data.status == "200"){
		                 
		                 $state.go('main');
	                	 
	                 }
	                
	                
	            })
	            .catch(function(response){
	                // Si ha habido errores llegamos a esta parte
	            	console.log(response); 
	            });
		  
	  };
	 
	 
	  
	  
	  
  }]);
