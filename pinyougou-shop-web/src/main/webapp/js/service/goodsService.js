//服务层
app.service('goodsService', ['$http', function ($http) {

    //读取列表数据绑定到表单中
    this.findAll = function () {
        return $http({
            url: '../goods/list',
            method: 'get'
        });
    };

    //分页
    this.search = function (goods, page, rows) {
        return $http({
            url: '../goods/search?page=' + page + '&rows=' + rows,
            method: 'post',
            data: goods
        });
    };

    //查询实体
    this.findOne = function (id) {
        return $http({
            url: '../goods/' + id,
            method: 'get'
        });
    };

    //增加
    this.save = function (entity) {
        return $http({
            url: '../goods/' + (entity.id != null ? entity.id : ''),
            method: entity.id != null ? 'put' : 'post',
            data: entity
        });
    };


    //删除
    this.del = function (selectIds) {
        return $http({
            url: '../goods/',
            method: 'delete',
            data: selectIds,
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            },
        });
    };
}]);
