'use strict';

angular.module('pruebaAngularApp', ['ngRoute','ui.bootstrap','satellizer'])
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
.config(function ($authProvider,$routeProvider) {
	  
	// Parametros de configuración
    $authProvider.loginUrl = "https://sgem.com/rest/UsuarioService/login";
    //$authProvider.signupUrl = "http://api.com/auth/signup";
    $authProvider.tokenName = "token";
    $authProvider.tokenPrefix = "myApp";
    
//    if(localStorage.getItem("tenantActual") != null){
//    	localStorage.setItem("tenantActual", "");
//    }
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
	    		
	    		/***** ESTO ESTARÍA BUENO IMPLEMENTARLO EN UN UTIL O FUNCION ****/
	    		
	    		if(localStorage.getItem("tenantActual") == null || (JSON.parse(localStorage.getItem("tenantActual"))).nombre_url != $route.current.params.tenant){

	    			return dataFactory.getDataTenant($route.current.params.tenant);
	    			
	    		}else{
	    			return JSON.parse(localStorage.getItem("tenantActual"));
	    		}
	    		/**********************************************************/
	    	}]
	    }   
	}).when('/:tenant/registro', {
		templateUrl : 'views/registro.html',
		controller : 'RegistroTenantCtrl',
	    resolve: { 
	    	dataTenant:['dataFactory','$route', function(dataFactory,$route) {
	    		/***** ESTO ESTARÍA BUENO IMPLEMENTARLO EN UN UTIL O FUNCION ****/
	    		
	    		if(localStorage.getItem("tenantActual") == null || (JSON.parse(localStorage.getItem("tenantActual"))).nombre_url != $route.current.params.tenant){

	    			return dataFactory.getDataTenant($route.current.params.tenant);
	    			
	    		}else{
	    			return JSON.parse(localStorage.getItem("tenantActual"));
	    		}
	    		/**********************************************************/
	    	}]
	    }   
	}).when('/main', {
		templateUrl : 'views/main.html',
		controller : 'MainCtrl'
	})
//	.when('/:tenant/registro', {
//		templateUrl : 'views/registro.html',
//		controller : 'RegistroCtrl',
//		
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
		controller : 'RegistroCtrl',
		resolve: { 
		    	dataTenant:['dataFactory','$route', function(dataFactory,$route) {
		    		/***** ESTO ESTARÍA BUENO IMPLEMENTARLO EN UN UTIL O FUNCION ****/
		    		
		    		if(localStorage.getItem("tenantActual") == null || (JSON.parse(localStorage.getItem("tenantActual"))).nombre_url != $route.current.params.tenant){

		    			return dataFactory.getDataTenant($route.current.params.tenant);
		    			
		    		}else{
		    			return JSON.parse(localStorage.getItem("tenantActual"));
		    		}
		    		/**********************************************************/
		    	}]
		}
	})
	
	.when('/:tenant/perfilComite', {
		templateUrl : 'views/perfilComite.html',
		controller : 'PerfilCtrl'
	})

	.otherwise({
		redirectTo : '/'
	});
 });

