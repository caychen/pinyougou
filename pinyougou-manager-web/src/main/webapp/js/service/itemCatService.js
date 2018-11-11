//服务层
app.service('itemCatService', ['$http', function ($http) {

    this.findAll = function () {
        return $http({
            url: '../itemCat/list',
            method: 'get',
        });
    };

    this.search = function (itemCat, page, size) {
        return $http({
            url: '../itemCat/search?page=' + page + '&size=' + size,
            method: 'post',
            data: itemCat
        });
    };

    this.save = function (entity) {
        return $http({
            url: '../itemCat/' + (entity.id != null ? entity.id : ''),
            method: entity.id != null ? 'put' : 'post',
            data: entity
        });
    };

    this.findOne = function (id) {
        return $http({
            url: '../itemCat/' + id,
            method: 'get'
        });
    };

    this.del = function (selectIds) {
        return $http({
            url: '../itemCat/',
            method: 'delete',
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            },
            data: selectIds
        });
    };


    //根据上级分类查询商品分类列表
    this.findByParentId = function (parentId) {
        return $http({
            url: '../itemCat/sub?pId=' + parentId,
            method: 'get'
        });
    };
}]);
