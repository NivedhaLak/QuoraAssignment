var app = angular.module('app', ["ngRoute","ngResource"]);
app.controller('controller', [ '$scope','$filter','$http','$rootScope','$routeParams','$location', function($scope, $filter,$http,$rootScope,$routeParams,$location) {
	$rootScope.url="http://localhost:8090";
	$scope.name="in quora home page";
	$scope.search="";
	console.log("$routeParams.param; "+$routeParams.param);
	/*var User = $resource($rootScope.url+'/getAllQuestion');
	User.query(function(user){
		$scope.profiles = user;
		console.log(user);
	});*/
	if($routeParams.method == 'ans' && $routeParams.id!= undefined){
		$http.get($rootScope.url+'/getbyQuestionId/'+$routeParams.id)
		  .then(function(response) {
			  $scope.data = response.data;
			  if(!$scope.data.qust.endsWith('?')){
				  $scope.data.qust=$scope.data.qust+'?'; 
			  }
			  $scope.data.totAns=$scope.data.answer.length;
			  $scope.data.tags = $scope.data.tags.split(",");
			  console.log(response+"---"+'ans');
		  },function(response) {
			  console.log("error while loading allquestion"+response);
		  });
	}else{
		$http.get($rootScope.url+'/getAllQuestion')
		  .then(function(response) {
			  $scope.profiles = response.data;
			  $scope.totQuestion = response.data.length;
			  $scope.isprofile = response.data.length >0 ?true: false;
			  for(data in  $scope.profiles){
				  $scope.profiles[data].tags = $scope.profiles[data].tags.split(",");
				  if($scope.profiles[data].answer)
					  $scope.profiles[data].ansTot=$scope.profiles[data].answer.length;
			  }
				 
			  console.log(response+"---"+$scope.isprofile);
		  },function(response) {
			  console.log("error while loading allquestion"+response);
		  });
	}
	$scope.addQuestion=function(addquestion){
		console.log("in add question");
		var tag=addquestion.tags.$viewValue+'';
		if(tag.endsWith(',') || tag.startsWith(',')){
			addquestion.tags.$setValidity(false);
			return;
		}
		var data={
			"qust":addquestion.qust.$viewValue,
			 "tags":tag,
			 "description":addquestion.description.$viewValue
		};
		
		$http.post($rootScope.url+'/addQuestion',data)
		  .then(function(response) {
			  console.log(response);
			  alert("Successfully updated.");
			  $location.path("/");
		  },function(response) {
			  console.log("error while adding question"+response);
		  });
		
	};
	$scope.searchFilter = function(profile){
		var isMatch = false;
console.log("-----");
	      if ($scope.search && $scope.search.trim().length>0) {
	        var parts = $scope.search.toLowerCase().split(' ');
			parts.forEach(function(part) {
	          if (new RegExp(part).test((profile.tags+'').toLowerCase())) {
	            isMatch = true;
	          }else  if (new RegExp(part).test(profile.description.toLowerCase())) {
		            isMatch = true;
		      }else if (new RegExp(part).test(profile.qust.toLowerCase())) {
		            isMatch = true;
		      }
	          
	        });
	      } else {
	        isMatch = true;
	      }

	      return isMatch;
	};
	$scope.addAnswer = function(addanswer,id){
		var data ={
				'ans':addanswer.ans.$viewValue,
				'quesId':id
		};
		if(addanswer.username.$viewValue!=null && addanswer.username.$viewValue != undefined){
			data.username=addanswer.username.$viewValue;
		}
		$http.post($rootScope.url+'/addAnswer',data)
		  .then(function(response) {
			  alert("Successfully added");
			 // $location.path("#/answer/ans/"+id);
			  console.log(response);
		  },function(response) {
			  console.log("error while loading allquestion"+response);
		  });
	};
	$scope.updateVote = function(ansId,isUp){
		$http.get($rootScope.url+'/updateVote/'+ansId+'/'+isUp)
		  .then(function(response) {
			 // $scope.profiles = response.data;
			 angular.element('#vote'+response.data.id)[0].innerHTML=response.data.vote;
			  console.log(response);
		  },function(response) {
			  console.log("error while vote"+response);
		  });
	};
	
}]);
app.config(function($routeProvider) {
    $routeProvider
    .when("/question", {
    	templateUrl : "question.html",
    	/*controller : "questionController"*/
    }).when("/answer/:method/:id", {
        templateUrl : "answer.html",
    	controller : "controller"
    }).when("/", {
    	templateUrl : "overview.html",
    	/*controller : "overviewController"*/
    });
});

app.directive('onlyLettersInput', onlyLettersInput);

function onlyLettersInput() {
    return {
      require: 'ngModel',
      link: function(scope, element, attr, ngModelCtrl) {
        function fromUser(text) {
          var transformedInput = text.replace(/[^a-zA-Z, ]/g, '');
          //console.log(transformedInput);
          if (transformedInput !== text) {
            ngModelCtrl.$setViewValue(transformedInput);
            ngModelCtrl.$render();
          }
          return transformedInput;
        }
        ngModelCtrl.$parsers.push(fromUser);
      }
    };
  };
/* .when("/", {
templateUrl : "home.html"
})*/