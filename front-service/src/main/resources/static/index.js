angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {

    const contextPath = 'http://localhost:8080/core/api/v1/';

    $scope.tryToAuth = function() {
        $http.post(contextPath + 'auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.marketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(responce) {
            });
    }

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
    };

    $scope.clearUser = function () {
        delete $localStorage.marketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.marketUser) {
            return true;
        } else {
            return false;
        }
    };

    if ($localStorage.marketUser) {
        try {
            let jwt = $localStorage.marketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
                console.log("Token is expired!");
                delete $localStorage.marketUser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e) {
        }
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.marketUser.token;
    }

    $scope.loadProducts = function () {
        $http.get(contextPath + 'products').then(function (response) {
            $scope.productsList = response.data;
        });
    }

    $scope.loadCart = function () {
        $http.get('http://localhost:8090/cart/api/v1/cart').then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.addToCart = function (productId) {
        $http.post('http://localhost:8090/cart/api/v1/cart/' + productId).then(function () {
            $scope.loadCart();
        });
    }

    $scope.clearTheCart = function () {
        $http.delete('http://localhost:8090/cart/api/v1/' + 'cart').then(function () {
            $scope.loadCart();
        });
    }

    $scope.deleteProductFromCart = function (productId) {
        $http.delete('http://localhost:8090/cart/api/v1/' + 'cart/' + productId).then(function () {
            $scope.loadCart();
        });
    }

    $scope.incrementQuantity = function (productId, inc) {
        $http({
            url: 'http://localhost:8090/cart/api/v1/' + 'cart/increment',
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
            url: contextPath + 'order',
            method: 'Post'
        }).then(function () {
            $scope.clearTheCart();
            $scope.loadCart();
        })
    }

    $scope.loadCart();
    $scope.loadProducts();
});