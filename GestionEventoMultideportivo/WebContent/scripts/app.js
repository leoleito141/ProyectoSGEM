'use strict';

angular.module('pruebaAngularApp', ['ngRoute','ui.bootstrap','satellizer'])
.run(function($rootScope, $location, $auth){ // esto se ejecuta en tiempo de ejecucion,
  $rootScope.$on('$routeChangeStart', function(event, next, current) {
	
    if(!$auth.isAuthenticated()){

      if (next.templateUrl=='views/main.html' || next.templateUrl=='views/registro.html' ) {
        $location.path('/');

      }


    } 
    /* Act on the event */
  });
  
})
.config(function ($authProvider,$routeProvider) {
	// Parametros de configuración
    $authProvider.loginUrl = "http://localhost:8080/GestionEventoMultideportivo/rest/UsuarioService/login";
    //$authProvider.signupUrl = "http://api.com/auth/signup";
    $authProvider.tokenName = "token";
    $authProvider.tokenPrefix = "myApp";

    // Configuración de las rutas/estados
    $routeProvider
    
//      .when('/:tenant', {
//          templateUrl: 'views/login.html',
//          controller: 'LoginCtrl'
//        }) 
    
    .when('/', {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl'
      }) 
    
      .when('/main', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/:tenant/registro', {
        templateUrl: 'views/registro.html',
        controller: 'RegistroCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
 });

