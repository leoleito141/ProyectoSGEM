'use strict';

angular.module('pruebaAngularApp')
  .controller('MainCtrl', function ($scope) {
	
	  
	    $scope.myInterval = 2500;
	    $scope.slides = [
	      {
	        image: 'http://www.youwall.com/wallpapers/201207/usain-bolt-wallpaper.jpg'
	      },
	      {
	        image: 'http://www.sportsmafia.info/wp-content/uploads/2012/09/Water-Polo-13.jpg'
	      },
	      {
	        image: 'http://www.sportsmafia.info/wp-content/uploads/2012/09/Wrestling-12.jpg'
	      },
	      
		   {
		    image: 'https://abigailirozuru.files.wordpress.com/2015/06/london-2012-summer-olympic-games-womens-100m-dash.jpg'
		 },
	      {
		    image: 'http://www.designbolts.com/wp-content/uploads/2012/07/Roger-Federer-tennis-HD-Wallpaper1.jpg'
		  },
		   {
		    image: 'http://wiki.fms.k12.nm.us/groups/heightstrack/wiki/27afd/images/cdc6d.jpg'
		 },
	      {
	        image: 'http://www.sportsmafia.info/wp-content/uploads/2012/09/Basketball-04.jpg'
	      }
	    ];
	  
	  
	  
  });

