var app = angular.module('app', ["ngRoute"]);
app.controller('questionController', [ '$scope','$filter','$resource','$rootScope', function($scope, $filter,$resource,$rootScope) {
	var User = $resource('http://localhost:8090/getAllQuestion');
	User.query(function(user){
		$scope.profile = user;
		console.log(user);
	})
}]);