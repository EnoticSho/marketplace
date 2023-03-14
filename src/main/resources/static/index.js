angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {

    $scope.loadProducts = function () {
        $http.get('http://localhost:8080/api/v1/products').then(function (response) {
            $scope.productsList = response.data;
        });
    }

    $scope.loadCart = function () {
        $http.get('http://localhost:8080/api/v1/cart').then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.addToCart = function (productId) {
        $http.post('http://localhost:8080/api/v1/cart/' + productId).then(function () {
            $scope.loadCart();
        });
    }

    $scope.clearTheCart = function () {
        $http.delete('http://localhost:8080/api/v1/cart').then(function () {
            $scope.loadCart();
        });
    }

    $scope.deleteProductFromCart = function (productId) {
        $http.delete('http://localhost:8080/api/v1/cart/' + productId).then(function () {
            $scope.loadCart();
        });
    }

    $scope.incrementQuantity = function (productId, inc) {
        $http({
            url: 'http://localhost:8080/api/v1/cart/increment',
            method: 'Put',
            params: {
                productId: productId,
                inc: inc
            }
        }).then(function () {
            $scope.loadCart();
        });
    }

    $scope.loadCart();
    $scope.loadProducts();
});