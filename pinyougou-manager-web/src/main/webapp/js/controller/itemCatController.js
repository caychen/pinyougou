//控制层
app.controller('itemCatController', ['$scope', '$controller', 'itemCatService', 'typeTemplateService', function ($scope, $controller, itemCatService, typeTemplateService) {

    $controller('baseController', {
        $scope: $scope
    });//继承

    //当前页的父id
    $scope.currentParentId = 0;

    //读取列表数据绑定到表单中  
    $scope.findAll = function () {
        itemCatService.findAll().then(function (response) {
                $scope.itemCats = response.data;
            }, function (err) {
                swal("网络异常，请稍后重试!", "", "error");
            }
        );
    };

    $scope.itemCat = {};
    //分页
    $scope.search = function (page, size) {
        itemCatService.search($scope.itemCat, page, size).then(function (response) {
                $scope.itemCats = response.data.rows;
                $scope.paginationConf.totalItems = response.data.total;//更新总记录数
            }, function (err) {
                swal("网络异常，请稍后重试!", "", "error");
            }
        );
    };

    //查询实体
    $scope.findOne = function (id) {
        itemCatService.findOne(id).then(function (response) {
            $scope.entity = response.data;
        }, function (err) {
            swal("网络异常，请稍后重试!", "", "error");
        })
    };

    //保存
    $scope.save = function () {
        $scope.entity.parentId = $scope.currentParentId;
        itemCatService.save($scope.entity).then(function (response) {
            if (response.data.success) {
                swal("分类保存成功!", "", "success");
                $scope.findByParentId($scope.currentParentId);
            } else {
                swal("分类保存失败!", "", "error");
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
                itemCatService.del($scope.selectIds).then(function (response) {
                    if (response.data.success) {
                        swal({
                            title: '删除成功!',
                            type: 'success',
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "确认",
                        }, function (isConfirm) {
                            if (isConfirm) {
                                $scope.findByParentId($scope.currentParentId);
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


    //根据上级分类ID查询列表
    $scope.findByParentId = function (parentId) {
        itemCatService.findByParentId(parentId).then(function (response) {
                //保存当前父id
                $scope.currentParentId = parentId;
                $scope.itemCats = response.data;
            }, function (reason) {
                swal("网络异常，请稍后重试!", "", "error");
            }
        );
    };

    $scope.grade = 1;//当前级别
    //设置级别
    $scope.setGrade = function (value) {
        $scope.grade = value;
    };


    $scope.selectList = function (p_entity) {
        //alert($scope.grade);

        if ($scope.grade == 1) {
            $scope.itemCat_1 = null;
            $scope.itemCat_2 = null;
        }
        if ($scope.grade == 2) {
            $scope.itemCat_1 = p_entity;
            $scope.itemCat_2 = null;
        }
        if ($scope.grade == 3) {
            $scope.itemCat_2 = p_entity;
        }

        $scope.findByParentId(p_entity.id);

    };

    $scope.typeTemplateList = {};//类型模版列表
    $scope.findTypeTemplateList = function () {
        typeTemplateService.findTypeTemplateList().then(function (response) {
            $scope.typeTemplateList = response.data;
        }, function (reason) {
            swal("网络异常，请稍后重试!", "", "error");
        });
    };


}]);
