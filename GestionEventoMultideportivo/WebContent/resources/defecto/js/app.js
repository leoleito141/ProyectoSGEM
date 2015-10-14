'use strict';

angular.module('pruebaAngularApp', ['ui.router','ui.bootstrap','satellizer'])
.run(['dataFactory','$rootScope','$state','$auth',function(dataFactory,$rootScope, $state, $auth){ // esto se ejecuta en tiempo de ejecucion,
  $rootScope.$on('$stateChangeStart', function(event, next, current) {
  console.log($auth.isAuthenticated());
    if(!$auth.isAuthenticated()){
    	console.log(next.templateUrl);
      if (next.templateUrl=='views/main.html' || next.templateUrl=='views/registro.html' ) {
    	 console.log("entre"); 
    	  event.preventDefault();
    	  $state.go('homeLogin');

      }
//   // stop state change
//      event.preventDefault();

    } 
    /* Act on the event */
  });
  
}])
.config(function ($authProvider,$stateProvider, $urlRouterProvider) {
	// Parametros de configuración
    $authProvider.loginUrl = "https://localhost:443/GestionEventoMultideportivo/rest/UsuarioService/login";
    //$authProvider.signupUrl = "http://api.com/auth/signup";
    $authProvider.tokenName = "token";
    $authProvider.tokenPrefix = "myApp";
    
    // Configuración de las rutas/estados
    $urlRouterProvider.otherwise('/');
    $stateProvider	    
    .state('homeLogin', {
    	url:'/',
		templateUrl : 'views/login.html',
		controller : 'LoginCtrl'
	}).state('login', {
		url:'/:tenant/login',
		templateUrl : 'views/tenant/loginTenant.html',
		controller : 'LoginTenantCtrl',
	    resolve: { 
	    	loadData:function(dataFactory,$stateParams) {
	    		return dataFactory.getDatosTenant( $stateParams.tenant);   
        }
	    }   
	}).state('main', {
		url:'/main',
		templateUrl : 'views/main.html',
		controller : 'MainCtrl'
	}).state('registro', {
		url:'/:tenant/registro',
		templateUrl : 'views/registro.html',
		controller : 'RegistroCtrl'
	}).state('altaEvento', {
		url:'/:tenant/altaEvento',
		templateUrl : 'views/altaEvento.html',
		controller : 'MainCtrl'
	}).state('altaEventoDeportivo', {
		url:'/:tenant/altaEventoDeportivo',
		templateUrl : 'views/altaEventDeportivo.html',
		controller : 'EventDeportivoCtrl'
	});
	

 });

