 angular.module('pruebaAngularApp')
   .factory('dataFactory', ['$http', function($http) {
    var dataFactory = {};

    dataFactory.insertUser = function (datos) {

            console.log(datos);
            return $http.post('https://localhost:443/GestionEventoMultideportivo/rest/UsuarioService/usuarios', datos);

        };

    dataFactory.getStatus = function () {

        console.log($location.absUrl());
        return $http.get('https://localhost:443/GestionEventoMultideportivo/rest/UsuarioService/status/', {
            headers: { 'Rol' : 'ADMIN'} 
        });
    };
        
     dataFactory.getUsuario = function (nombre) {

    	    console.log(nombre);
            return $http.get('https://localhost:443/GestionEventoMultideportivo/rest/UsuarioService/usuarioPrueba/'+nombre );

        };
    /*
     * Esta funcion queda comentada porque el login ahora se realiza mediante 
     * satellizer
     *     
     dataFactory.login = function (datos) {

            console.log(datos);
            return $http.post('http://localhost:8080/GestionEventoMultideportivo/rest/UsuarioService/login', datos);

        };   
    */
        dataFactory.getPrueba = function () {
            return $http.get('https://localhost:443/GestionEventoMultideportivo/rest/UsuarioService/prueba/', {
                headers: { 'Rol' : 'ADMIN'} 
            });
        }; 
        
        dataFactory.altaEvento = function(datos){
        	console.log(datos);
            return $http.post('https://localhost:443/GestionEventoMultideportivo/rest/EventoMultiService/eventos', datos,
            		{headers: { 'Rol' : 'ADMIN'}});       	
        }; 
        
        dataFactory.guardarOrganizador = function(datos){
        	console.log(datos);
            return $http.post('https://localhost:443/GestionEventoMultideportivo/rest/UsuarioService/organizador', datos,
            		{headers: { 'Rol' : 'ADMIN'}});	      	
        };
        
        dataFactory.altaEventoDeportivo = function(datos){
        	console.log(datos);
            return $http.post('https://localhost:443/GestionEventoMultideportivo/rest/EventoDeportivoService/altaEventoDeportivo', datos,
            		{headers: { 'Rol' : 'ADMIN'}});       	
        }; 
        
        
        dataFactory.getDatosTenant = function(tenant){
        	
        	 return $http.get('https://localhost:443/GestionEventoMultideportivo/rest/EventoMultiService/obtenerDatosTenant/'+tenant)
        	  .then(function (data, status, headers, config) {
                  
                  console.log("Entre get datos tenant");
                  console.log(data);
                  console.log(status);
                  console.log(headers);
                  console.log(config);
                  return data; 
                  
              })
              .catch(function(response){
                  // Si ha habido errores llegamos a esta parte
              	console.log(response); 
              });
              		 
        };
        
        dataFactory.getTenant = function(tenant){
        	
	   	return $http.get('https://localhost:443/GestionEventoMultideportivo/rest/EventoMultiService/obetenerTenant/'+tenant)
		       	.then(function (data, status, headers, config) {
		                 
		                 console.log("Entre get tenant");
		                 console.log(data);
		                 console.log(status);
		                 console.log(headers);
		                 console.log(config);
		                 return data; 
		                 
	             }).catch(function(response){
		             	console.log(response); 
		        });
	         		 
	    };
     
        return dataFactory;
}]); 