
var angularApp = angular.module('textAngularApp',['angularUtils.directives.dirPagination']);

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
}]);