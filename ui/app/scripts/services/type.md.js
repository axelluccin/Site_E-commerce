'use strict'

angular.module("lvdApp")
    .service("Type", function($resource) {
        return {
            getAll : $resource('/api/type/all'),
            getOne : $resource('/api/type/get/:id', {id : '@id'}, {'get':  {method:'GET', isArray:false}})
        };
    });
