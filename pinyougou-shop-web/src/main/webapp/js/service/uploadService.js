app.service("uploadService", ['$http', function($http){

    //上传文件
    this.uploadFile = function(){
        var formdata = new FormData();

        //file：文件上传框的name
        formdata.append("file", file.files[0]);

        return $http({
            url: '../upload',
            method: 'post',
            data:formdata,
            headers: {
                'Content-Type':undefined
            },
            transformRequest: angular.identity
        })
    }

}]);