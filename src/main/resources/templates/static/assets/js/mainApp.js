var app = angular.module('myApp', []);

function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}

app.controller('sendMessageController', function($scope,$http) {
    
   $("#sendMessageButton").prop('disabled', true);
    
    
    $scope.sendMessageData=function(){
    
    var sendMessage={
    name:$scope.name,
    email:$scope.email,
    subject:$scope.subject,
    message:$scope.message
    };

$http.post('/save', JSON.stringify(sendMessage)).then(function (response) {
location.reload();
});
    
    }
    
  

    
    
});