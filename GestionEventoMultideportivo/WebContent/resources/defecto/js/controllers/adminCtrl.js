'use strict';

angular.module('pruebaAngularApp')
  .controller('AdminCtrl', ['$scope','$auth','$state','dataFactory', function ($scope, $auth, $state, dataFactory) {

   $scope.usuario={};
   
   $('.carousel').carousel({
	    interval: 5000 //changes the speed
	})
	
   $scope.loginAdmin = function () {
	   $scope.cargando = true; 	     
   	   setTimeout( function(){	
     
	       var usuario = $scope.usuario;
	
	       $auth.login({
	           email: usuario.email,
	           password: btoa(usuario.password) // base 64
	       })
	       .then(function (data){ 
	            console.log($auth.getPayload()); 
	            console.log($auth.getToken()); 
	            $scope.usrLogin.email = $scope.usuario.email;
	            $scope.usuario.password="";
	 
	            var payLoad = $auth.getPayload();             
	            var dataUsuario = payLoad.dataUsuario;	
	        
	            localStorage.setItem("dataUsuario", JSON.stringify(dataUsuario));
	             	         
	            $state.go("formAltaEventoMulti.altaEvento");
	           
	        })
	        .catch(function(error){
	       		if(error.status == 404){
	       		  $scope.cargando = false;
	       		  $scope.mensajeValidacion = "Email o contrase\u00F1a incorrecta.";
	       		}else{
	       		  $scope.cargando = false;
	       		  $scope.mensajeValidacion = "Error al autenticar usuario.";
	       		}
	         });
	       
       
       }, 1000); //espera 1 segundo
	};
    

}]);
