'use strict';

angular.module('pruebaAngularApp')
  .controller('LoginTenantCtrl', ['$scope','$auth','dataFactory','loadData', function ($scope, $auth, dataFactory,loadData) {

   console.log(loadData.login_back_img);
   $scope.customStyle.background= 'url(resources/defecto/img/tenant1/' + loadData.login_back_img +')fixed';
//   $scope.customStyle = {
//	   'background-image': 'url(resources/defecto/img/tenant1/' + loadData.data.image +')'
////	   'background-image': 'url(resources/defecto/img/tenant1/' + loadData.data.image +')fixed',
////	   'background': 'url(resources/defecto/img/tenant1/'+loadData.data.image')'
//	 
//   };
   
   $scope.usuario={};//Informacion del usuario para loguear pasada desde login.html
   
   $scope.loginUser = function () {
       var datos = $scope.usuario;
    
       $auth.login({
           email: datos.email,
           password: btoa(datos.password) // base 64 
       })
       .then(function (data){
          
            $scope.usrLogin.email = $scope.usuario.email;
            $scope.usuario.password="";
            var payLoad = $auth.getPayload();    
            $location.path(payLoad.tenantid + '/altaEvento');
  
        })
        .catch(function(response){
            // Si ha habido errores llegamos a esta parte
        	console.log(response); 
        });
   		};
   		
   	
    

	 


  }]);
