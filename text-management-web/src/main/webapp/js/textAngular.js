
var angularApp = angular.module('textAngularApp',['angularUtils.directives.dirPagination','ngRoute']);

angularApp.service('LoadInitialTextListService',  function($http){
	this.getTextList =  function(cb){
		var textList;
		$http.get('angular/texts')
	     .then(
	       function success(successResponse){
	    	   console.log(successResponse.data);
	    	   cb(successResponse.data);
	       }
	     );
		console.log("textList :"+textList);
	}
});

angularApp.service('AddTextService', function($http){
	this.addText = function(textToAdd, cb){
		$http.post('angular/save-text', textToAdd)
	     .then(
	    	function success(successResponse){
	    		console.log("Inside AddTextService textToAdd: "+textToAdd);
	    		cb(successResponse.data);
	    	}
	     	);
	}
});

angularApp.controller('textAngularListController', ['$scope', '$http', 'LoadInitialTextListService', function($scope, $http, LoadInitialTextListService){

	LoadInitialTextListService.getTextList(function(r){
		$scope.texts = r;
	});
	
	$scope.getData = function(data){
		//alert(JSON.stringify(data));
		$scope.text = data;
	};
	
	$scope.addText = function(){
		//alert($scope.text);
		$http.post('angular/save-text', $scope.text)
	     .then(
	    	function success(successResponse){
	    		//alert(successResponse);
	    		$scope.texts = successResponse.data;
	    	}
	     	);
	
	};
	
	$scope.$on('eventName', function (event, data) {
		$scope.texts = data;
	});
}]);


angularApp.config(['$routeProvider','$locationProvider', function($routeProvider,$locationProvider){
	
	$locationProvider.hashPrefix('');
	$routeProvider.
		when('/addText',{
			templateUrl: 'addText.htm',
			controller:'AddTextController'
		});
		
}]);

angularApp.controller('AddTextController',['$scope','$http','$rootScope','AddTextService', function($scope, $http, $rootScope, AddTextService){
	$rootScope.showAddTextForm = true;
	$scope.addText = function(){
		AddTextService.addText($scope.text, function(r){
			$rootScope.$broadcast('eventName', r);
    		$scope.showAddTextForm = false;
		});
	
	};
}]);