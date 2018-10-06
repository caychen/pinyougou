//控制层
app.controller('specificationController', ['$scope', '$controller', 'specificationService', function ($scope, $controller, specificationService) {

    //继承
    $controller('baseController', {
        $scope: $scope
    });

    //读取列表数据绑定到表单中  
    $scope.findAll = function () {
        specificationService.findAll().then(function (response) {
                $scope.specifications = response.data;
            }, function (err) {
                swal("网络异常，请稍后重试!", "", "error");
            }
        );
    };

    $scope.specification = {};
    //分页
    $scope.search = function (page, rows) {
        specificationService.search($scope.specification, page, rows).then(function (response) {
            $scope.specifications = response.data.rows;
            $scope.paginationConf.totalItems = response.data.total;//更新总记录数
        }, function (err) {
            swal("网络异常，请稍后重试!", "", "error");
        });
    };

    //查询实体
    $scope.findOne = function (id) {
        specificationService.findOne(id).then(function (response) {
            $scope.entity = response.data;
        }, function (err) {
            swal("网络异常，请稍后重试!", "", "error");
        });
    };

    //保存
    $scope.save = function () {
        specificationService.save($scope.entity).then(function (response) {
            if (response.data.success) {
                swal("规格保存成功!", "", "success");
                $scope.reload();
            } else {
                swal("规格添加成功!", "", "error");
            }
        }, function (err) {
            swal("网络异常，请稍后重试!", "", "error");
        })
    };


    //批量删除
    $scope.del = function () {
        if ($scope.selectIds.length > 0) {
            swal({
                title: '确定要删除所选项吗？',
                text: '你将无法恢复它！',
                type: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: '确定删除！',
                cancelButtonText: '取消'
            }, function () {
                specificationService.del($scope.selectIds).then(function (response) {
                    if (response.data.success) {
                        swal({
                            title: '删除成功!',
                            type: 'success',
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "确认",
                        }, function (isConfirm) {
                            if (isConfirm) {
                                $scope.reload();
                                $scope.selectIds = [];
                            }
                        });

                    } else {
                        swal(response.data.message, "", "error");
                    }
                }, function (err) {
                    swal("网络异常，请稍后重试!", "", "error");
                });
            });
        } else {
            swal("请至少选择一项!", "", "warning");
        }
    };

    //增加规格选项行
    $scope.addTableRow = function () {
        $scope.entity.specificationOptionList.push({});
    };


    //删除规格选项行
    $scope.delTableRow = function (index) {
        $scope.entity.specificationOptionList.splice(index, 1);
    };

}]);
