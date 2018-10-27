app.service("loginService", ['$http', function($http){

    this.getLoginName = function(){
       return $http({
           url:'../login/name',
           method:'get'
       });
   }
}]);