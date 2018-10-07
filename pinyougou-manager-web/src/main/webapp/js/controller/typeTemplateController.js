//控制层
app.controller('typeTemplateController', function ($scope, $controller, typeTemplateService, brandService, specificationService) {

    $controller('baseController', {
        $scope: $scope
    });//继承

    //读取列表数据绑定到表单中  
    $scope.findAll = function () {
        typeTemplateService.findAll().then(function (response) {
            $scope.typeTemplates = response.data;
        }, function (err) {
            swal("网络异常，请稍后重试!", "", "error");
        });
    };

    $scope.typeTemplate = {};
    //分页
    $scope.search = function (page, rows) {
        typeTemplateService.search($scope.typeTemplate, page, rows).then(function (response) {
            $scope.typeTemplates = response.data.rows;
            $scope.paginationConf.totalItems = response.data.total;//更新总记录数
        }, function (err) {
            swal("网络异常，请稍后重试!", "", "error");
        });
    };

    //查询实体
    $scope.findOne = function (id) {
        typeTemplateService.findOne(id).then(function (response) {
            $scope.entity = response.data;

            //转换字符串为json对象（集合）
            $scope.entity.brandIds = JSON.parse($scope.entity.brandIds);
            $scope.entity.specIds = JSON.parse($scope.entity.specIds);
            $scope.entity.customAttributeItems = JSON.parse($scope.entity.customAttributeItems);

        }, function (err) {
            swal("网络异常，请稍后重试!", "", "error");
        });
    };

    //保存
    $scope.save = function () {
        typeTemplateService.save($scope.entity).then(function (response) {
            if (response.data.success) {
                swal("模板保存成功!", "", "success");
                //重新查询
                $scope.reloadList();//重新加载
            } else {
                swal("模板保存失败!", "", "success");
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
                typeTemplateService.del($scope.selectIds).then(function (response) {
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


    $scope.brandList = {data: [{id: 1, text: '联想'}, {id: 2, text: '华为'}, {id: 3, text: '小米'}]};//品牌列表

    //读取品牌列表
    $scope.findBrandList = function () {
        brandService.selectBrandOptionList().then( function (response) {
            $scope.brandList = {data: response.data};
        }, function(err){
            swal("网络异常，请稍后重试!", "", "error");
        });
    };

    $scope.specList = {data: []};//规格列表

    //读取规格列表
    $scope.findSpecList = function () {
        specificationService.selectOptionList().success(
            function (response) {
                $scope.specList = {data: response};
            }
        );
    };

    //增加扩展属性行
    $scope.addTableRow = function () {
        $scope.entity.customAttributeItems.push({});
    };

    //删除扩展属性行
    $scope.deleTableRow = function (index) {
        $scope.entity.customAttributeItems.splice(index, 1);
    };

});	
