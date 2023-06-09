(function () {
    angular
        .module('app', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/cart', {
                templateUrl: 'cart/carts.html',
                controller: 'cartController'
            })
            .when('/order', {
                templateUrl: 'order/order.html',
                controller: 'orderController'
            })
            .when('/register', {
                templateUrl: 'registration/registration.html',
                controller: 'registrationController'
            })
            .otherwise({
                redirectTo: '/store'
            });
    }

    function run($rootScope, $http, $localStorage) {
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

        if (!$localStorage.marketGuestCardId) {
            $http.get('http://localhost:5555/cart/api/v1/cart/generate_uuid')
                .then(function successCallback(response) {
                    $localStorage.marketGuestCardId = response.data.value;
                    console.log(response.data.value)
                });
        }
    }
})();

angular.module('app').controller('indexController', function ($scope, $http, $localStorage) {

    $scope.tryToAuth = function() {
        $http.post('http://localhost:5555/auth/api/v1/auth', $scope.user)
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
});