'use strict';

angular.module('pruebaAngularApp', ['ui.router','ui.bootstrap','satellizer'])
.run(['dataFactory','$rootScope','$state','$auth',function(dataFactory,$rootScope, $state, $auth){ // esto se ejecuta en tiempo de ejecucion,
  $rootScope.$on('$stateChangeStart', function(event, next, current) {
  
    if(!$auth.isAuthenticated()){
    	
      if (next.templateUrl=='views/altaEvento.html' ) {
       	  event.preventDefault();
    	  $state.go('adminLogin');

      }


    } 
    /* Act on the event */
  });
  
}])
.config(function ($authProvider,$stateProvider, $urlRouterProvider) {
	// Parametros de configuración
    $authProvider.loginUrl = "https://sgem.com/rest/UsuarioService/login";
    //$authProvider.signupUrl = "http://api.com/auth/signup";
    $authProvider.tokenName = "token";
    $authProvider.tokenPrefix = "myApp";
    
    // Configuración de las rutas/estados
    $urlRouterProvider.otherwise('/');
    $stateProvider	    
    .state('adminLogin', {
    	url:'/',
		templateUrl : 'views/login.html',
		controller : 'LoginCtrl'
	}).state('tenantLogin', {
		url:'/:tenant/login',
		templateUrl : 'views/tenant/loginTenant.html',
		controller : 'LoginTenantCtrl',
	    resolve: { 
	    	loadData:function(dataFactory,$stateParams) {
	    		/***** ESTO ESTARÍA BUENO IMPLEMENTARLO EN UN UTIL O FUNCION ****/
	    		
	    		if(localStorage.getItem("tenantActual") == null || (JSON.parse(localStorage.getItem("tenantActual"))).nombre_url != $stateParams.tenant){

	    			return dataFactory.getDataTenant($stateParams.tenant);
	    			
	    		}else{
	    			return JSON.parse(localStorage.getItem("tenantActual"));
	    		}
	    		/**********************************************************/   
	    	}
	    }   
	}).state('altaEvento', {
		url:'/:tenant/altaEvento',
		templateUrl : 'views/altaEvento.html',
		controller : 'EventoMultiCtrl'
	}).state('altaEventoDeportivo', {
		url:'/:tenant/altaEventoDeportivo',
		templateUrl : 'views/altaEventDeportivo.html',
		controller : 'EventDeportivoCtrl',
		resolve: { 
			dataTenant:function(dataFactory,$stateParams) {
		    	/***** ESTO ESTARÍA BUENO IMPLEMENTARLO EN UN UTIL O FUNCION ****/
		    		
		    	if(localStorage.getItem("tenantActual") == null || (JSON.parse(localStorage.getItem("tenantActual"))).nombre_url != $stateParams.tenant){

		    		return dataFactory.getDataTenant($stateParams.tenant);
		    			
		   		}else{
		   			return JSON.parse(localStorage.getItem("tenantActual"));
		   		}
		   		/**********************************************************/	   
	        }
		}   	
	}).state('altaComite', {
		url:'/:tenant/altaComite',
		templateUrl : 'views/altaComite.html',
		controller : 'RegistroCtrl',
		resolve: { 
		    	dataTenant: function(dataFactory,$stateParams) {
		    		/***** ESTO ESTARÍA BUENO IMPLEMENTARLO EN UN UTIL O FUNCION ****/
		    		
		    		if(localStorage.getItem("tenantActual") == null || (JSON.parse(localStorage.getItem("tenantActual"))).nombre_url != $stateParams.tenant){

		    			return dataFactory.getDataTenant($stateParams.tenant);
		    			
		    		}else{
		    			return JSON.parse(localStorage.getItem("tenantActual"));
		    		}
		    		/**********************************************************/
		    	}
		}
	}).state('perfilComite', {
		url:'/:tenant/perfilComite',
		templateUrl : 'views/perfilComite.html',
		controller : 'PerfilCtrl'
	});
	

 });


