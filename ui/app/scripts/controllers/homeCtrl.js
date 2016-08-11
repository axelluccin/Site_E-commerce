'use strict'

angular.module("lvdApp")
    .controller('HomeCtrl', function ($scope, $location) {
        //$scope.setSession(0, true);
                if(!$scope.getSession()) {
                  $location.path("/login");
                 }
    });
