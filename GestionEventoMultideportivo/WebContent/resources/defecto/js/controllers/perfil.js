'use strict';

angular.module('pruebaAngularApp')
  .controller('PerfilCtrl',['$scope','$modal','dataFactory', 
                            function($scope,$modal,dataFactory) {
   
	    

	  $scope.confPerfil={};
	  
	 $("#hue-demo").minicolors({
		    changeDelay: 200,
		    position:'bottom right',
		    
		    change: function(value, opacity) {
		        console.log(value + ' - ' + opacity);
		        $(".PerfilNews").css("background-color", value);
		    }
		});
	 $("#BannerColor").minicolors({
		    changeDelay: 200,
		    position:'bottom right',
		    format: 'rgb',
		    opacity :'true',
		    change: function(value, opacity) {
		        console.log(value + ' - ' + opacity);
		        $(".bannerPerfil").css("background-color", value);
		        $(".bannerPerfil").css("opacity", opacity);
		    }
		});
	 $("#inputLugar").minicolors({
		    changeDelay: 200,
		    position:'bottom right',
		    change: function(value, opacity) {
		        console.log(value + ' - ' + opacity);
		        $(".TituloPerfil").css("background-color", value);
		    }
		});
	 
	 
	 
	 
	 
	 $(".PerfilNews").css("background-color", "#FFFFFF");
	 
	  $scope.perfil={image:'http://www.candombe.com.uy/images/bandera.jpg',titulo:'La concha de tu madre'};
	  	    
	 // console.log($scope.puto);
	  
	/*  $(".bannerPerfil").css("background-color", "#0A2880");
	  $(".bannerPerfil").css("background-color", "red");
	 */
	  
	//  $(".PerfilContent").css("background","url('http://farm3.staticflickr.com/2832/12303719364_c25cecdc28_b.jpg') fixed");
	  
	  $scope.guadrarPerfil = function(){
		  
		  alert(JSON.stringify($scope.confPerfil));
	  };
		  
	  

  }]);
