'use strict';

angular.module('pruebaAngularApp')
  .controller('LoginCtrl', ['$scope','$q','$cookieStore','$location','dataFactory', function ($scope, $q, $cookieStore, $location, dataFactory) {

   $scope.usuario={};
    	    
   $scope.loginUser = function () {
     
	   console.log($scope.usuario);  
       var datos = $scope.usuario;
       console.log("entre insertar" + datos);

       var usr = dataFactory.login(datos)
       	.then(function (data){
            console.log(data); 
            console.log(status);  
            $scope.usrLogin.nombre = $scope.usuario.nombre;
            $scope.usrLogin.clave  = $scope.usuario.clave;
            $scope.usrLogin.estaConectado = true;

            $cookieStore.put('estaConectado', true);
            $cookieStore.put('usuario', $scope.usuario);

            $location.path('/main');
             

           
        });
    	};
    

	 


  }]);
