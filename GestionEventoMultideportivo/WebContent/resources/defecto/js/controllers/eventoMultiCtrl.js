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
     	
	  $scope.habilitarGuardar = function() {		  
		  if($scope.evento == undefined || $scope.pais == null){
			  return false;
		  }
		  return  ($scope.evento.nombre == null || $scope.pais.pais == null 
			    || $scope.pais.ciudad == null || $scope.evento.anio == null 
			    || $scope.evento.twitter == null || $scope.evento.facebook == null || $scope.evento.instagram == null
			    || $scope.evento.canalYoutube == null || $scope.evento.fechaInicio == null
			    || $scope.evento.fechaFin == null || $scope.evento.emailOrganizador == null || $scope.evento.passwordOrganizador == null
			    
			    || $scope.evento.nombre == "" || $scope.pais.pais == ""
			    || $scope.pais.ciudad == "" || $scope.evento.anio == 0
			    || $scope.evento.twitter == "" || $scope.evento.facebook == "" || $scope.evento.instagram == ""
			    || $scope.evento.canalYoutube == "" 
			    || $scope.evento.emailOrganizador == "" || $scope.evento.passwordOrganizador == "");	

		  };
	  
	  ////////////////////////////Alta EventoMultideportivo /////////////////////////////////////
	  $scope.altaEvento = function(){
		  $scope.cargando = true;	
		  var evento = $scope.evento;
		  evento.pais = $scope.pais;
		  console.log(evento);
		  dataFactory.altaEvento(evento)
	     	.then(function (data, status, headers, config) {
	                
	                 if(data.status == "200"){
	                	 $scope.cargando = false;	
		                 $state.go('main');
	                	 
	                 }
	                
	                
	            })
	            .catch(function(response){
	            	$scope.cargando = false;	
	                // Si ha habido errores llegamos a esta parte
	            	console.log(response); 
	            });
		  
	  };
	 
	 
	  
	  
	  
  }]);
