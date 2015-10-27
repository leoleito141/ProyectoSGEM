'use strict';

angular.module('pruebaAngularApp', ['ui.router','ui.bootstrap','satellizer'])
.run(['dataFactory','$rootScope','$state','$auth',function(dataFactory,$rootScope, $state, $auth){ // esto se ejecuta en tiempo de ejecucion,
  $rootScope.$on('$stateChangeStart', function(event, next, current) {
  
    if(!$auth.isAuthenticated()){
    	
      if (next.templateUrl=='views/altaEvento.html' ) {
       	  event.preventDefault();
    	  $state.go('adminLogin');

      }

//      if (next.templateUrl=='views/tenant/indexOrganizador.html' ) {
//       	  
//
//      }
//      
      
    
    } 
    /* Act on the event */
  });
  
}])
.config(function ($authProvider,$stateProvider, $urlRouterProvider) {
	// Parametros de configuración
    $authProvider.loginUrl = "https://sgem.com/rest/UsuarioService/loginAdmin";
    $authProvider.tokenName = "token";
    $authProvider.tokenPrefix = "sgem.com";
    
    // Configuración de las rutas/estados
    $urlRouterProvider.otherwise('/');
    $stateProvider	    
    .state('adminLogin', {
    	url:'/',
		templateUrl : 'views/login.html',
		controller : 'AdminCtrl'
	})
	.state('main', {
    	url:'/main',
		templateUrl : 'views/main.html',
		controller : 'AdminCtrl'
	})
	.state('formAltaEventoMulti', {
		url:'/altaEventoMultiDeportivo',
		templateUrl : 'views/formAltaEventoMulti.html',
		controller : 'EventoMultiCtrl'
	
	})// nested states 
	.state('formAltaEventoMulti.altaEvento', {
		url:'/altaEvento',
		templateUrl : 'views/altaEvento.html'

	})// nested states each of these sections will have their own view url will be nested (/altaEvento/organizador)
	.state('formAltaEventoMulti.organizador', {
        url: '/organizador',
        templateUrl: 'views/altaOrganizador.html'
        	
    });
	

 });


