//控制层
app.controller('goodsController', ['$scope', '$controller', 'goodsService', 'uploadService', 'itemCatService', 'typeTemplateService', function ($scope, $controller, goodsService, uploadService, itemCatService, typeTemplateService) {

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

    $scope.imageEntity = {};
    $scope.uploadFile = function(){
        uploadService.uploadFile().then(function(response){
            if(response.data.success){
                $scope.imageEntity.url = response.data.data;
            }
        }, function (reason) {
            swal("网络异常，请稍后重试!", "", "error");
        })
    };

    $scope.entity = {goods:{category2Id: undefined, category3Id: undefined}, goodsDesc: {itemImages: []}};
    //将当前上传的图片实体存入图片列表
    $scope.add_image_entity = function(){
        if(($scope.imageEntity.color !== '' && typeof $scope.imageEntity.color !== 'undefined') && $scope.imageEntity.url !== ''){
            $scope.entity.goodsDesc.itemImages.push($scope.imageEntity);

            $scope.imageEntity = {};
        }else{
            swal("请补充完整！", "", "warning");
            return ;
        }
    };

    //移除图片
    $scope.remove_image_entity = function(index){
        $scope.entity.goodsDesc.itemImages.splice(index, 1);
    };

    /*
    subIndex：表示分类级别，1为一级分类，以此类推
    parentId：表示父id
     */
    $scope.selectItemCatList = function(subIndex, parentId){
        itemCatService.findByParentId(parentId).then(function(response){
            if(subIndex == 1){
                $scope.itemCatList1 = response.data;
            }else if (subIndex == 2){
                $scope.itemCatList2 = response.data;
            }else if(subIndex == 3){
                $scope.itemCatList3 = response.data;
            }
        }, function (reason) {
            swal("网络异常，请稍后重试!", "", "error");
        })
    };

    //监控
    $scope.$watch('entity.goods.category1Id', function(newValue, oldValue){
        $scope.entity.goods.category2Id = undefined;
        $scope.entity.goods.category3Id = undefined;
        $scope.entity.goods.typeTemplateId = undefined;
        if(typeof newValue !== 'undefined' && newValue != null){
            $scope.selectItemCatList(2, newValue);
        }
    });

    $scope.$watch('entity.goods.category2Id', function(newValue, oldValue){
        if(typeof newValue !== 'undefined' && newValue != null){
            $scope.selectItemCatList(3, newValue);
        }
    });

    $scope.$watch('entity.goods.category3Id', function(newValue, oldValue){
        if(typeof newValue !== 'undefined' && newValue != null) {
            itemCatService.findOne(newValue).then(function (response) {
                $scope.entity.goods.typeTemplateId = response.data.typeId;
            }, function (reason) {

            });
        }
    });

    $scope.$watch('entity.goods.typeTemplateId', function(newValue, oldValue){
        if(typeof newValue !== 'undefined' && newValue != null) {
            typeTemplateService.findOne(newValue).then(function (response) {
                $scope.typeTemplate = response.data;

                $scope.typeTemplate.brandIds = JSON.parse($scope.typeTemplate.brandIds);
            }, function (reason) {

            });
        }
    });

}]);
