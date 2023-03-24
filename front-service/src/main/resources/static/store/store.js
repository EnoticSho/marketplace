angular.module('app').controller('storeController', function ($scope, $http, $location, $localStorage) {

    const contextPath = 'http://localhost:5555/core';
    const cartContextPath = 'http://localhost:5555/cart';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/api/v1/products').then(function (response) {
            $scope.productsList = response.data;
        });
    }

    $scope.addToCart = function (productId) {
        $http.post(cartContextPath + '/api/v1/cart/' + productId).then(function () {
        });
    }

    $scope.loadProducts();
});