angular.module('app').controller('registrationController', function ($scope, $http, $location, $localStorage) {

    const contextPath = 'http://localhost:5555/auth';

    $scope.reg = function () {
        $http.post(contextPath + '/api/v1/reg', $scope.reguser)
            .then(function (response) {
                $location.path("/");
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.marketUser = {username: $scope.reguser.username, token: response.data.token};
                }
            });
    }
});