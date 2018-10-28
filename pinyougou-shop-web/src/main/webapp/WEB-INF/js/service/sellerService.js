//服务层
app.service('sellerService', ['$http', function ($http) {

    //读取列表数据绑定到表单中
    this.findAll = function () {
        return $http({
            url: '../seller/list',
            method: 'get'
        });
    };

    //分页
    this.search = function (seller, page, rows) {
        return $http({
            url: '../seller/search?page=' + page + '&rows=' + rows,
            method: 'post',
            data: seller
        });
    };

    //查询实体
    this.findOne = function (id) {
        return $http({
            url: '../seller/' + id,
            method: 'get'
        });
    };

    //增加/更新
    this.save = function (entity) {
        return $http({
            url: '../seller/' + (entity.id != null ? entity.id : ''),
            method: entity.id != null ? 'put' : 'post',
            data: entity
        });
    };

    //删除
    this.del = function (selectIds) {
        return $http({
            url: '../seller/',
            method: 'delete',
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            },
            data: selectIds
        });
    };

}]);
