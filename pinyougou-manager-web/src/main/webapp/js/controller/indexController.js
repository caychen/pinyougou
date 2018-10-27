app.controller('indexController', ['$scope', 'loginService', function($scope, loginService){

    //显示当前登录的用户名
    $scope.showLoginName = function () {
        loginService.getLoginName().then(function(response){
            $scope.loginName = response.data.loginName;
        }, function (reason) {
            swal("获取登录者用户名失败!", "", "error");
        })
    }
}]);