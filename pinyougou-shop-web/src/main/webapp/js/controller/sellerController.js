//控制层
app.controller('sellerController', ['$scope', '$controller', 'sellerService', function ($scope, $controller, sellerService) {

    $controller('baseController', {
        $scope: $scope
    });//继承

    //读取列表数据绑定到表单中  
    $scope.findAll = function () {
        sellerService.findAll().then(function (response) {
                $scope.sellers = response.data;
            }, function (reason) {
                swal("网络异常，请稍后重试!", "", "error");
            }
        );
    };

    $scope.seller = {};

    //分页
    $scope.findPage = function (page, rows) {
        sellerService.findPage($scope.seller, page, rows).then(function (response) {
                $scope.sellers = response.data.rows;
                $scope.paginationConf.totalItems = response.data.total;//更新总记录数
            }, function (reason) {
                swal("网络异常，请稍后重试!", "", "error");
            }
        );
    };

    //查询实体
    $scope.findOne = function (id) {
        sellerService.findOne(id).then(function (response) {
                $scope.entity = response.data;
            }, function (reason) {
                swal("网络异常，请稍后重试!", "", "error");
            }
        );
    };


    //新增
    $scope.save = function () {
        sellerService.save($scope.entity).then(function (response) {
                if (response.data.success) {
                    swal({
                        title: '商家保存成功!',
                        type: 'success',
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "确认",
                    }, function (isConfirm) {
                        if (isConfirm) {
                            // swal("商家保存成功!", "", "success");
                            //如果注册成功，跳转到登录页
                            if ($scope.entity.id == null) {
                                location.href = "shoplogin.html";
                            } else {
                                //更新
                                $scope.reload();
                            }
                        }
                    });
                } else {
                    swal("商家保存失败!", "", "error");
                }
            }, function (reason) {
                swal("网络异常，请稍后重试!", "", "error");
            }
        );

    };

    //批量删除
    $scope.del = function () {
        //获取选中的复选框
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
                sellerService.del($scope.selectIds).then(function (response) {
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
