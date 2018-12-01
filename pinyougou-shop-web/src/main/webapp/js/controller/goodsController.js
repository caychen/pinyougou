//控制层
app.controller('goodsController', ['$scope', '$controller', 'goodsService', function ($scope, $controller, goodsService) {

    $controller('baseController', {
        $scope: $scope
    });//继承

    //读取列表数据绑定到表单中  
    $scope.findAll = function () {
        goodsService.findAll().then(function (response) {
            $scope.goods = response.data;
        }, function (reason) {
            swal("网络异常，请稍后重试!", "", "error");
        });
    };

    $scope.goods = {};
    //分页
    $scope.findPage = function (page, rows) {
        goodsService.search($scope.goods, page, rows).then(function (response) {
            $scope.goods = response.data.rows;
            $scope.paginationConf.totalItems = response.data.total;//更新总记录数
        }, function (reason) {
            swal("网络异常，请稍后重试!", "", "error");
        });
    };

    //查询实体
    $scope.findOne = function (id) {
        goodsService.findOne(id).then(function (response) {
            $scope.entity = response.data;
        }, function (reason) {
            swal("网络异常，请稍后重试!", "", "error");
        });
    };

    //保存
    $scope.save = function () {
        //从富文本编辑器中获取html内容
        $scope.entity.goodsDesc.introduction = editor.html();

        goodsService.save($scope.entity).then(function (response) {
            if (response.data.success) {
                swal("商品保存成功!", "", "success");
                $scope.entity = {};
                editor.html('');//清空富文本编辑器内容
            } else {
                swal("商品保存失败!", "", "error");
            }
        }, function (err) {
            swal("网络异常，请稍后重试!", "", "error");
        });
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
                goodsService.del($scope.selectIds).then(function (response) {
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
