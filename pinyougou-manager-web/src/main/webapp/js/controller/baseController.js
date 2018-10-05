app.controller('baseController', ['$scope', function ($scope) {
    //分页控件配置：
    //  currentPage： 当前页
    //  totalItems： 总记录数
    //  itemsPerPage： 每页记录数
    //  perPageOptions： 分页选项
    //  onChange: 当页码变更后自动触发的方法
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 10,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function () {
            $scope.reload();
        }
    };

    //重新加载
    $scope.reload = function () {
        $scope.search($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);//重新加载
    };

    //用户勾选的品牌id集合
    $scope.selectIds = [];

    $scope.updateSelection = function ($event, id) {
        if ($event.target.checked) {
            $scope.selectIds.push(id);
        } else {
            $scope.selectIds.splice($scope.selectIds.indexOf(id), 1);
        }
    };

}]);