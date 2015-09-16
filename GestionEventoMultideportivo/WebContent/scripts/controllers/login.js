'use strict';

angular.module('pruebaAngularApp')
  .controller('LoginCtrl', ['$scope','$q','$cookieStore','$auth','$location','dataFactory', function ($scope, $q, $cookieStore, $auth, $location, dataFactory) {

   $scope.usuario={};
    	    
   $scope.loginUser = function () {
     
	   console.log($scope.usuario);  
       var datos = $scope.usuario;
       console.log("entre insertar" + datos);

       $auth.login({
           email: datos.email,
           password: datos.password
       })
       .then(function (data){
            console.log(data); 
            console.log(status); 
            console.log($auth.getPayload()); 
            console.log($auth.getToken()); 
            $scope.usrLogin.email = $scope.usuario.email;
            $scope.usuario.password="";
          /*
            $scope.usrLogin.estaConectado = true;
            $cookieStore.put('estaConectado', true);
            $cookieStore.put('usuario', $scope.usuario);
          */
            $location.path('/main');
             

           
        })
        .catch(function(response){
            // Si ha habido errores llegamos a esta parte
        	console.log(response); 
        });
    	};
    

	 


  }]);
