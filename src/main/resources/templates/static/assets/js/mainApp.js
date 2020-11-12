var app = angular.module('myApp', []);

function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}

app.controller('sendMessageController', function($scope,$http) {
    
   
    
 /* functionstart*/
    $scope.sendMessageData=function(){


        var flag = true;

        if($scope.name == null || $scope.name == "" ){
            $scope.error="Name Can't be empty!";
            flag=false;
        }else if($scope.email == null || $scope.email == ""){
            $scope.error="Please Enter Valid email.";
            flag=false;
        }else if($scope.subject == null || $scope.subject == ""){
            $scope.error="Subject Can't be empty!";
            flag=false;
        }else if($scope.message == null || $scope.message == ""){
            $scope.error="Message Can't be empty!";
            flag=false;
        }
        $("#loaderGif").removeAttr("style");
        $("#sendMessageButton").prop('disabled', true);
        $('#myModal').modal('toggle');
        if(!flag){
            $scope.error="";
            $("#loaderGif").removeAttr("style");
            $("#sendMessageButton").prop('disabled', true);
            $('#myModal').modal('toggle');
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


        
    
    }
    /*functionend*/ 
  

    
    
});