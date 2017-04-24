
var angularApp = angular.module('textAngularApp',['angularUtils.directives.dirPagination','ngRoute']);

angularApp.controller('textAngularListController', ['$scope', '$http', function($scope, $http){
	$http.get('angular/texts')
	     .then(
	       function success(successResponse){
	    	   $scope.texts = successResponse.data;
	       }
	     );
	
	
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

angularApp.controller('AddTextController',['$scope','$http','$rootScope', function($scope, $http, $rootScope){
	$rootScope.showAddTextForm = true;
	$scope.addText = function(){
		//alert(JSON.stringify($scope.text));
		$scope.showAddTextForm = true;
		$http.post('angular/save-text', $scope.text)
	     .then(
	    	function success(successResponse){
	    		//alert(successResponse);
	    		//$scope.texts = successResponse.data;
	    		$rootScope.$broadcast('eventName', successResponse.data);
	    		$scope.showAddTextForm = false;
	    		//$scope.showAddTextButton = true;
	    	}
	     	);
	
	};
}]);