angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {

    const contextPath = 'http://localhost:8080/api/v1/';

    $scope.loadProducts = function () {
        $http.get(contextPath + 'products').then(function (response) {
            $scope.productsList = response.data;
        });
    }

    $scope.loadCart = function () {
        $http.get(contextPath + 'cart').then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.addToCart = function (productId) {
        $http.post(contextPath + 'cart/' + productId).then(function () {
            $scope.loadCart();
        });
    }

    $scope.clearTheCart = function () {
        $http.delete(contextPath + 'cart').then(function () {
            $scope.loadCart();
        });
    }

    $scope.deleteProductFromCart = function (productId) {
        $http.delete(contextPath + 'cart/' + productId).then(function () {
            $scope.loadCart();
        });
    }

    $scope.incrementQuantity = function (productId, inc) {
        $http({
            url: contextPath + 'cart/increment',
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