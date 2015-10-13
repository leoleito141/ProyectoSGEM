'use strict';

angular.module('pruebaAngularApp', ['ngRoute','ui.bootstrap','satellizer','LocalStorageModule'])
.run(['dataFactory','$rootScope','$location','$auth',function($dataFactory,$rootScope, $location, $auth){ // esto se ejecuta en tiempo de ejecucion,
  $rootScope.$on('$routeChangeStart', function(event, next, current) {
  
    if(!$auth.isAuthenticated()){

      if (next.templateUrl=='views/main.html'){ // || (next.templateUrl=='views/registro.html'){
        $location.path('/');

      }


    } 
    /* Act on the event */
  });
  
}])
.config(['localStorageServiceProvider', function(localStorageServiceProvider){
	
	/*
	 * While you’re in app.js, also configure localStorageServiceProvider to use "ls" as a localStorage 
	 * name prefix so your app doesn’t accidently read properties from another app using the same variable names
	 */
    localStorageServiceProvider.setPrefix('ls');
    
}])
.config(function ($authProvider,$routeProvider) {
	  
	// Parametros de configuración
    $authProvider.loginUrl = "https://localhost:443/GestionEventoMultideportivo/rest/UsuarioService/login";
    //$authProvider.signupUrl = "http://api.com/auth/signup";
    $authProvider.tokenName = "token";
    $authProvider.tokenPrefix = "myApp";
    
    // Configuración de las rutas/estados
    $routeProvider	    
    .when('/', {
		templateUrl : 'views/login.html',
		controller : 'LoginCtrl'
	}).when('/:tenant/login', {
		templateUrl : 'views/tenant/loginTenant.html',
		controller : 'LoginTenantCtrl',
	    resolve: { 
	    	loadData:['dataFactory','$route', function(dataFactory,$route) {
	    		console.log($route.current.params.tenant);
	    		return dataFactory.getDatosTenant($route.current.params.tenant);   
	    	}]
	    }   
	}).when('/:tenant/registro', {
		templateUrl : 'views/registro.html',
		controller : 'RegistroTenantCtrl',
	    resolve: { 
	    	dataTenant:['dataFactory','$route', function(dataFactory,$route) {
	    		console.log("Registro url : "+$route.current.params.tenant);
	    		return dataFactory.getTenant($route.current.params.tenant);   
	    	}]
	    }   
	}).when('/main', {
		templateUrl : 'views/main.html',
		controller : 'MainCtrl'
	})
//	.when('/:tenant/registro', {
//		templateUrl : 'views/registro.html',
//		controller : 'RegistroCtrl'
//	})
	.when('/:tenant/altaEvento', {
		templateUrl : 'views/altaEvento.html',
		controller : 'MainCtrl'
	})
	.when('/:tenant/altaOrganizador', {
		templateUrl : 'views/altaOrganizador.html',
		controller : 'MainCtrl'
	})
	.when('/:tenant/altaEventoDeportivo', {
		templateUrl : 'views/altaEventDeportivo.html',
		controller : 'EventDeportivoCtrl'
	})
	
	.when('/:tenant/altaComite', {
		templateUrl : 'views/altaComite.html',
		controller : 'RegistroCtrl'
	})
	
	.when('/:tenant/perfilComite', {
		templateUrl : 'views/perfilComite.html',
		controller : 'PerfilCtrl'
	})

	.otherwise({
		redirectTo : '/'
	});
 });

