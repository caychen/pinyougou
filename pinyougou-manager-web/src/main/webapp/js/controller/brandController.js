app.controller('brandController', ['$scope', '$controller', 'brandService', function ($scope, $controller, brandService) {

    //继承，将当前的$scope与父类的$scope共享
    $controller('baseController', {
        $scope: $scope
    });

    //查询品牌列表
    $scope.findAll = function () {
        brandService.findAll().then(function (response) {
                $scope.brands = response.data;
            }, function (err) {
                swal("网络异常，请稍后重试!", "", "error");
            }
        );
    };


    $scope.brand = {};
    //分页查询
    $scope.search = function (page, size) {
        brandService.search($scope.brand, page, size).then(function (response) {
            $scope.brands = response.data.rows;

            $scope.paginationConf.totalItems = response.data.total;
        }, function (err) {
            swal("网络异常，请稍后重试!", "", "error");
        });
    };

    //添加品牌/修改品牌
    $scope.save = function () {
        //判断品牌名称
        if (typeof($scope.entity.name) === 'undefined' || $scope.entity.name === '') {
            // swal("请输入品牌名称", "", "warning");
            swal({
                title: '请输入品牌名称',
                type: 'warning',
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确认",
            }, function (isConfirm) {
                if (isConfirm) {
                    $("#editModal").modal('show');
                }
            });
            return;
        }

        //判断品牌首字母
        if (typeof($scope.entity.firstChar) === 'undefined' || $scope.entity.firstChar === '') {
            //swal("请输入品牌首字母", "", "warning");
            swal({
                title: '请输入品牌首字母',
                type: 'warning',
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确认",
            }, function (isConfirm) {
                if (isConfirm) {
                    $("#editModal").modal('show');
                }
            });
            return;
        }

        brandService.save($scope.entity).then(function (response) {
            if (response.data.success) {
                swal("品牌保存成功!", "", "success");
                $scope.reload();
            } else {
                swal("品牌添加成功!", "", "error");
            }
        }, function (err) {
            swal("网络异常，请稍后重试!", "", "error");
        });
    };

    $scope.findOne = function (id) {
        brandService.findOne(id).then(function (response) {
            $scope.entity = response.data;
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
                brandService.del($scope.selectIds).then(function (response) {
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
}]);