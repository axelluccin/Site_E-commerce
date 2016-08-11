'use strict'

angular.module("lvdApp")
    .service("Category", function($resource) {
        return $resource('/api/category/all/:type', {type : '@type'});
    });
