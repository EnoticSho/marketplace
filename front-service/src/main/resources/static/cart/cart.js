angular.module('app').controller('cartController', function ($scope, $http, $localStorage, $location) {

    const contextPath = 'http://localhost:5555/cart';

    $scope.loadCart = function () {
        $http.get(contextPath + '/api/v1/cart').then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.clearTheCart = function () {
        $http.delete(contextPath + '/api/v1/' + 'cart').then(function () {
            $scope.loadCart();
        });
    }

    $scope.deleteProductFromCart = function (productId) {
        $http.delete(contextPath + '/api/v1/' + 'cart/' + productId).then(function () {
            $scope.loadCart();
        });
    }

    $scope.incrementQuantity = function (productId, inc) {
        $http({
            url: contextPath + '/api/v1/cart/increment',
            method: 'Put',
            params: {
                productId: productId,
                inc: inc
            }
        }).then(function () {
            $scope.loadCart();
        });
    }

    $scope.createOrder = function () {
        $http({
            url: 'http://localhost:5555/core/api/v1/order',
            method: 'Post'
        }).then(function () {
            $scope.clearTheCart();
            $scope.loadCart();
        })
    }

    $scope.loadCart();
});