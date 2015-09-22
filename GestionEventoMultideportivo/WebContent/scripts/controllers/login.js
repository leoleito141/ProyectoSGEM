'use strict';

angular.module('pruebaAngularApp')
  .controller('LoginCtrl', ['$scope','$auth','$location','dataFactory','$routeParams', function ($scope, $auth, $location, dataFactory,$routeParams) {

	 
   $scope.usuario={};//Informacion del usuario para loguear pasada desde login.html
   //$scope.tenantid.tenant= $routeParams.tenant;
   		
   $scope.loginUser = function () {
     
	   console.log($scope.usuario);  
       var datos = $scope.usuario;
       console.log("entre insertar" + datos);

       $auth.login({
           email: datos.email,
           password: btoa(datos.password) // base 64
       })
       .then(function (data){
            console.log(data); 
            console.log(status); 
            console.log($auth.getPayload()); 
            console.log($auth.getToken()); 
            $scope.usrLogin.email = $scope.usuario.email;
            $scope.usuario.password="";
 
            var payLoad = $auth.getPayload();
            console.log( payLoad.tenantid);
            $location.path(payLoad.tenantid + '/altaEvento');
             

           
        })
        .catch(function(response){
            // Si ha habido errores llegamos a esta parte
        	console.log(response); 
        });
   		};
    

	 


  }]);
