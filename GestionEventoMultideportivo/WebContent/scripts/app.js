'use strict';

angular.module('pruebaAngularApp', ['ngRoute','ui.bootstrap','ngCookies'])
.run(function($rootScope, $location, $cookieStore){ // esto se ejecuta en tiempo de ejecucion,
  $rootScope.$on('$routeChangeStart', function(event, next, current) {
    if($cookieStore.get('estaConectado') == false || $cookieStore.get('estaConectado') == null ){

      if (next.templateUrl=='views/main.html' || next.templateUrl=='views/registro.html' ) {
        $location.path('/');

      }


    }else{
      var usuario = $cookieStore.get('usuario');
      if (usuario.nombre == 'maxi') {
        $location.path('/registro');

      }

    }

    
    /* Act on the event */
  });
  
})
.config(function ($routeProvider) {
    $routeProvider
      .when('/', {
          templateUrl: 'views/login.html',
          controller: 'LoginCtrl'
        })
      .when('/main', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/registro', {
        templateUrl: 'views/registro.html',
        controller: 'RegistroCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
 });

