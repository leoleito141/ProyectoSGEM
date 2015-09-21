 angular.module('pruebaAngularApp')
   .factory('dataFactory', ['$http','$location','$auth', function($http,$location,$auth) {
    var dataFactory = {};

    console.log(dataFactory);
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
        	$auth.setToken("papapapa");
        	
            return $http.get('http://localhost:8080/GestionEventoMultideportivo/rest/UsuarioService/prueba/', {
                headers: { 'Rol' : 'ADMIN'} 
            });
        }; 
        console.log(dataFactory);

        return dataFactory;
}]); 