angular.module('app').controller('orderController', function ($scope, $http) {

    const contextPath = 'http://localhost:5555/core';

    $scope.loadOrder = function () {
        $http.get(contextPath + '/api/v1/order')
            .then(function (response) {
                $scope.order = response.data;
            });
    }

    $scope.loadOrder();
});