'use strict'

angular.module("lvdApp")
    .service("User", function($resource) {
      return {
        Con : $resource('/api/user/con',{} , {'post':  {method:'POST', isArray:false}}),
        GetUser : $resource('/api/user/:id', {id : '@id'}, {'get':  {method:'GET', isArray:false}}),
        Authenticate : $resource('/api/user/authenticate', {}, {'post':  {method:'POST', isArray:false}}),
        Signin : $resource('/api/user/save', {}, {'save':  {method:'POST', isArray:false}}),
        Update : $resource('/api/user/update', {}, {'put':  {method:'PUT', isArray:false}}),
      };
    });
