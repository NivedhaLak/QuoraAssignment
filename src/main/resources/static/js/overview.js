var app = angular.module('app');
app.controller('overviewController', [ '$scope','$filter','$resource','$rootScope', function($scope, $filter,$resource,$rootScope) {
	/*$scope.clickfunction = function(){
		var time = $filter('date')(new Date(),'yyyy-MM-dd HH:mm:ss Z');
		$scope.welcome = "Time = " + time;		
	}*/
	$scope.name="in quora home page";
	var User = $resource('http://localhost:8090/getAllQuestion');
	User.query(function(user){
		$scope.profile = user;
		console.log(user);
	});
	
}]);