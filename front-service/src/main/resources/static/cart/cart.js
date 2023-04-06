angular.module('app').controller('cartController', function ($scope, $http, $localStorage, $location) {

    const contextPath = 'http://localhost:5555/cart';

    $scope.loadCart = function () {
        $http.get(contextPath + '/api/v1/cart/' + $localStorage.marketGuestCardId)
            .then(function (response) {
                console.log("21")
                console.log($localStorage.marketGuestCardId)
                $scope.cart = response.data;
            });
    }

    $scope.clearTheCart = function () {
        $http.delete(contextPath + '/api/v1/cart/' + $localStorage.marketGuestCardId)
            .then(function () {
                $scope.loadCart();
            });
    }

    $scope.deleteProductFromCart = function (productId) {
        $http.delete(contextPath + '/api/v1/cart/' + $localStorage.marketGuestCardId + productId)
            .then(function () {
                $scope.loadCart();
            });
    }

    $scope.incrementQuantity = function (productId, inc) {
        $http({
            url: contextPath + '/api/v1/cart/' + $localStorage.marketGuestCardId + '/increment',
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