 angular.module('pruebaAngularApp')
   .factory('dataFactory', ['$http', function($http) {
    var dataFactory = {};

    dataFactory.insertUser = function (datos) {

            console.log(datos);
            return $http.post('http://localhost:8080/GestionEventoMultideportivo/rest/UsuarioService/usuarios', datos);

        };

    dataFactory.getStatus = function () {

        console.log($location.absUrl());
        return $http.get('http://localhost:8080/GestionEventoMultideportivo/rest/UsuarioService/status/', {
            headers: { 'Rol' : 'ADMIN'} 
        });
    };
        
     dataFactory.getUsuario = function (nombre) {

    	    console.log(nombre);
            return $http.get('http://localhost:8080/GestionEventoMultideportivo/rest/UsuarioService/usuarioPrueba/'+nombre );

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
            return $http.get('http://localhost:8080/GestionEventoMultideportivo/rest/UsuarioService/prueba/', {
                headers: { 'Rol' : 'ADMIN'} 
            });
        }; 
        
        dataFactory.altaEvento = function(datos){
        	console.log(datos);
            return $http.post('http://localhost:8080/GestionEventoMultideportivo/rest/UsuarioService/eventos', datos,
            		{headers: { 'Rol' : 'ADMIN'}});       	
        }; 
        
        
        dataFactory.altaEventoDeportivo = function(datos){
        	console.log(datos);
            return $http.post('http://localhost:8080/GestionEventoMultideportivo/rest/EventoDeportivoService/altaEventoDeportivo', datos,
            		{headers: { 'Rol' : 'ADMIN'}});       	
        }; 

        return dataFactory;
}]); 