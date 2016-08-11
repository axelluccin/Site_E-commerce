'use strict'

angular.module("lvdApp")
    .controller('CategoryCtrl', function ($scope, Category, Type, $routeParams, $location) {
        $scope.ctgs = Category.query({type : $routeParams.type});
        $scope.type = Type.getOne.get({id : $routeParams.type});
    });
