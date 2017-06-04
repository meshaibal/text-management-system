(function(){
	var directiveApp = angular.module('angularDirectiveApp', []);
	
	directiveApp.controller('customDirectiveController', ['$scope', 'textService', function($scope, textService){
		$scope.isAll = false;
		$scope.isSingle = false;
		
		$scope.searchAll = function(){
			$scope.isAll = true;
			$scope.isSingle = false;
			textService.getAllText(function(r){
				$scope.texts = r;
			});
		};
		
		$scope.doSearch =  function(){
			$scope.isAll = false;
			$scope.isSingle = true;
			var textId = $scope.textId;
			console.log("textId :"+textId);
			
			textService.getTextById(textId, function(r){
				$scope.text = r;
			});
		}
	}]);
	
	directiveApp.service('textService',['$http', function($http){
		this.getAllText = function(cb){
			$http({
					url :'angular-directive/texts',
					method : 'GET'
				}).then(
				     function success(response){
				    	 console.log("Texts :"+response.data);
				    	 cb(response.data);
				     }
					);
		},
		
		this.getTextById = function(textId, cb){
			$http({
					url :'angular-directive/texts/'+textId,
					method : 'GET'
				}).then(
					function success(response){
						console.log("Text :"+response.data);
						cb(response.data);
					},function error(errorResponse){
						console.log("Error :"+errorResponse);
					}	
				);
		}
	}]);
	
	directiveApp.directive('textDetail', function(){
		return {
			templateUrl : 'js/templates/text-detail.htm'
		}
	});
})();