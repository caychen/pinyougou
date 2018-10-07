app.service('brandService', ['$http', function ($http) {
    this.findAll = function () {
        return $http({
            url: '../brand/list',
            method: 'get',
        });
    };

    this.search = function (brand, page, size) {
        return $http({
            url: '../brand/search?page=' + page + '&size=' + size,
            method: 'post',
            data: brand
        });
    };

    this.save = function (entity) {
        return $http({
            url: '../brand/' + (entity.id != null ? entity.id : ''),
            method: entity.id != null ? 'put' : 'post',
            data: entity
        });
    };

    this.findOne = function (id) {
        return $http({
            url: '../brand/' + id,
            method: 'get'
        });
    };

    this.del = function (selectIds) {
        return $http({
            url: '../brand/',
            method: 'delete',
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            },
            data: selectIds
        });
    };

    this.selectBrandOptionList = function(){
        return $http({
            url: '../brand/options',
            method: 'get'
        });
    };
}])