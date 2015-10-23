'use strict';

angular.module('pruebaAngularApp', ['ui.router','ui.bootstrap','satellizer'])
.run(['dataFactory','$rootScope','$state','$auth',function(dataFactory,$rootScope, $state, $auth){ // esto se ejecuta en tiempo de ejecucion,
  $rootScope.$on('$stateChangeStart', function(event, next, current) {
  
    if(!$auth.isAuthenticated()){
    	
      if (next.templateUrl=='views/altaEvento.html' ) {
       	  event.preventDefault();
    	  $state.go('adminLogin');

      }

  //    $scope.rol = 'Organizador';
      
      if (next.templateUrl=='views/tenant/indexOrganizador.html' ) {
       	  if($scope.rol = 'Organizador'){
       		  
       		        	  event.preventDefault();
       		        	  $state.go('indexOrganizador');
       		  
       	  }
    	  


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
	}).state('tenantRegistro', {
		url:'/:tenant/registro',
		templateUrl : 'views/tenant/registroTenant.html',
		controller : 'UsuarioCtrl',
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
	}).state('formAltaEventoMulti', {
		url:'/:tenant/altaEventoMultiDeportivo',
		templateUrl : 'views/formAltaEventoMulti.html',
		controller : 'EventoMultiCtrl'
	})// nested states 
	.state('formAltaEventoMulti.altaEvento', {
		url:'/altaEvento',
		templateUrl : 'views/altaEvento.html'

	})// nested states 
    // each of these sections will have their own view
    // url will be nested (/altaEvento/organizador)
	.state('formAltaEventoMulti.organizador', {
        url: '/organizador',
        templateUrl: 'views/altaOrganizador.html'
        	
    }).state('altaEventoDeportivo', {
		url:'/:tenant/altaEventoDeportivo',
		templateUrl : 'views/tenant/organizador/altaEventDeportivo.html',
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
		templateUrl : 'views/tenant/comite/altaComite.html',
		controller : 'UsuarioCtrl',
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
		templateUrl : 'views/tenant/comite/perfilComite.html',
		controller : 'PerfilCtrl'
			
	}).state('indexTenant', {
		url:'/:tenant/',
		templateUrl : 'views/tenant/index.html',
		controller : 'EventoMultiCtrl',
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
	}).state('altaDeportista', {
		url:'/:tenant/altaDeportista',
		templateUrl : 'views/tenant/comite/altaDeportista.html',
		controller : 'deportistaCtrl',
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
	});
	

 });


