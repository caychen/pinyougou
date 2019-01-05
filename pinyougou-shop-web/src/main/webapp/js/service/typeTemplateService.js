//服务层
app.service('typeTemplateService', ['$http', function ($http) {

    //读取列表数据绑定到表单中
    this.findAll = function () {
        return $http({
            url: '../typeTemplate/list',
            method: 'get',
        });
    };

    //分页
    this.search = function (typeTemplate, page, size) {
        return $http({
            url: '../typeTemplate/search?page=' + page + '&size=' + size,
            method: 'post',
            data: typeTemplate
        });
    };

    //查询实体
    this.findOne = function (id) {
        return $http({
            url: '../typeTemplate/' + id,
            method: 'get'
        });
    };

    //保存
    this.save = function (entity) {
        return $http({
            url: '../typeTemplate/' + (entity.id != null ? entity.id : ''),
            method: entity.id != null ? 'put' : 'post',
            data: entity
        });
    };

    //删除
    this.del = function (ids) {
        return $http({
            url: '../typeTemplate/',
            method: 'delete',
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            },
            data: ids
        });
    };

    this.findTypeTemplateList = function () {
        return $http({
            url: '../typeTemplate/options',
            method:'get'
        })
    };
}]);
