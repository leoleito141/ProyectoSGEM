'use strict';

angular.module('pruebaAngularApp')
  .controller('MainCtrl', function ($scope) {
	
	  
	    $scope.myInterval = 2500;
	    $scope.slides = [
	      {image:'images/primera.jpg', description: 'Buenisima'},
	      {image:'images/segunda.jpg', description: 'Mas Buena'},
	      {image:'images/tercera.jpg', description: 'Tremenda'},
	      {image:'images/cuarta.jpg', description: 'Para Bruno'}
	    ];
	  
	  
	  
  });

