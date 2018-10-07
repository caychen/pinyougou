//服务层
app.service('specificationService', ['$http', function ($http) {

    //读取列表数据绑定到表单中
    this.findAll = function () {
        return $http({
            url: '../specification/list',
            method: 'get',
        });
    };

    //分页
    this.search = function (specification, page, size) {
        return $http({
            url: '../specification/search?page=' + page + '&size=' + size,
            method: 'post',
            data: specification
        });
    };

    //查询实体
    this.findOne = function (id) {
        return $http({
            url: '../specification/' + id,
            method: 'get'
        });
    };

    /*
    * entity = {
    *   specification: {},
    *   specificationOptionList: [
    *       {}
    *   ]
    * }
    *
    */
    this.save = function (entity) {
        return $http({
            url: '../specification/' + (entity.specification.id != null ? entity.specification.id : ''),
            method: entity.specification.id != null ? 'put' : 'post',
            data: entity
        });
    };

    //删除
    this.del = function (ids) {
        return $http({
            url: '../specification/',
            method: 'delete',
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            },
            data: ids
        });
    };

    //下拉列表
    this.selectOptionList = function () {
        return $http.get('../specification/selectOptionList.do');
        return $http({
            url: '../specification/',
            method: 'get',
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            },
            data: ids
        });
    }

}]);
