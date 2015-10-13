'use strict';

angular.module('pruebaAngularApp')
  .controller('RegistroTenantCtrl', ['$scope','$modal','$auth','dataFactory','dataTenant', 
                                     function ($scope,$modal, $auth, dataFactory,dataTenant) {

   console.log(dataTenant.data.tenant.tenantId);
   console.log(dataTenant.data.tenant.login_back_img);
   console.log(dataTenant.data.tenant.registro_back_img);
  
   var tenant = dataTenant.data.tenant;   
   $scope.tenantId = tenant.tenantId;
     
   $scope.status;
   $scope.users;
   $scope.formInfo={};
   $scope.usurio;
   $scope.nombre;
   	    

  $scope.insertUser = function () {
    
	   console.log($scope.formInfo);  
      var datos = $scope.formInfo;
      console.log("entre insertar" + datos);

      dataFactory.insertUser(datos)
      	.success(function (data, status, headers, config) {
              console.log(data);
              $scope.status = data.status;
              console.log(status);
           }).
           error(function(error) {
               $scope.status = 'Unable to insert user: ' + error.message;
           });
   	};
   

 $scope.getStatus = function(){

    dataFactory.getStatus()
     .success(function (data, status, headers, config) {
               $scope.status = status;
               console.log("Entre get stat");
               console.log(data.status);
               console.log(status);
               console.log(headers);
               console.log(config);
               
           }).
           error(function(error) {
               $scope.status = 'Unable to insert user: ' + error.message;
           });
   
 };
 
 $scope.getPrueba = function(){

	     dataFactory.getPrueba()
	     	.success(function (data, status, headers, config) {
	                $scope.status = data.status;
	                console.log("Entre get stat");
	                console.log(data.status);
	                console.log(status);
	                console.log(headers);
	                console.log(config);
	                
	            }).
	            error(function(error) {
	                $scope.status = error.message;
	            });
	    
	  };
 
 $scope.getUsuario = function(){
	     var nombre = $scope.nombre;
	     dataFactory.getUsuario(nombre)
	      .success(function (data, status, headers, config) {
	                $scope.usurio = data;
	                $scope.status = status;
	                console.log("entre usu");
	                console.log(data);
	               
	                
	                
	            }).
	            error(function(error) {
	                $scope.status = 'Unable to insert user: ' + error.message;
	            });
	    
	  };
	  $scope.showModal = function(){
		  var modalInstance = $modal.open({
         	templateUrl:'views/modal.html',
         	controller: 'RegistroCtrl',
         	size:'sm',
         	scope: $scope
         });
		  
	  };


  }]);
