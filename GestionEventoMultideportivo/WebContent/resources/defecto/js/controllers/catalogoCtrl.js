'use strict';

angular.module('pruebaAngularApp')
  .controller('CatalogoCtrl', ['$scope','$auth','$state','dataFactory', function ($scope, $auth, $state, dataFactory) {
	
	  
	 //////////////////////Listar EventosMultideportivos////////////////////////	  
	dataFactory.listarEventosMulti()
   		.then(function (response, status, headers, config) {
          
            for(var i=0; i < response.data.length ; i++){
            	if(response.data[i].pagina != null){
    	   			response.data[i].pagina.ruta = "https://sgem-eventos.com:8443/"+ response.data[i].pagina.ruta.substr(response.data[i].pagina.ruta.indexOf("resources"));
    	   			response.data[i].pagina.ruta = response.data[i].pagina.ruta.replace(/\\/g,"/");
            	}
	   				
            }
   			
   			var arreglo = [];

   			var i,j,temparray,chunk = 4;
   			for (i=0,j=response.data.length; i<j; i+=chunk) {
   				arreglo.push(response.data.slice(i,i+chunk));
   			}	
   			$scope.catalogo = arreglo;
   			console.log($scope.catalogo);
            
          })
          .catch(function(response){
              // Si ha habido errores llegamos a esta parte
          	console.log(response); 
          });
	
	$scope.hoverIn = function(){
	    this.hoverInfo = true;
	};

	$scope.hoverOut = function(){
	    this.hoverInfo = false;
	};
	////////////////Fin listado de eventos///////////////////  
    

}]);
