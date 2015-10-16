'use strict';

angular.module('pruebaAngularApp')
  .controller('UsuarioCtrl', ['$scope','$state','dataFactory','dataTenant', 
                                     function ($scope, $state, dataFactory,dataTenant) {

   console.log(dataTenant.tenantId);
   
   $scope.nombreTenant = dataTenant.nombre_url;
   $scope.usuario = {};
   
   $scope.comite={};
   
   $scope.altaUsuarioComun = function () {
    
      var usuarioComun = $scope.usuario;
      usuarioComun.tenantId = dataTenant.tenantId;
      usuarioComun.tipoUsuario = "Comun";
      usuarioComun.password = btoa(usuarioComun.password);
      console.log("entre insertar" + usuarioComun);

      dataFactory.altaUsuarioComun(usuarioComun)
      	.success(function (response, status, headers, config) {
              console.log(response);
              console.log(status);
              console.log(headers);
              console.log(config);
              if(response){
	              event.preventDefault();
	        	  $state.go('tenantLogin', { tenant: $scope.nombreTenant});//$state.go just calls transitionTo with inherit and relative set to true. Almost no difference.
              }else{
            	  alert("Error al dar de alta");
              }
      	}).error(function(error) {
      		console.log(error);
      		alert("Error al dar de alta");
      	});
      
   	};
	  
	 $scope.altaComite = function(){
		  
		  $scope.comite.tenantId = dataTenant.tenantId;
			  
		  dataFactory.altaComite($scope.comite)
	     	.then(function (data, status, headers, config) {
	                $scope.status = data.status;
	                console.log("Entre Alta comite");
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
