'use strict'

angular.module("lvdApp")
    .service("Cart", function($resource) {
      return  {
        CartUser : $resource('/api/cart/get/:user', {user : '@user'}, {'get':  {method:'GET', isArray:false}}),
        CartArchive : $resource('/api/cart/get/archive/:user', {user : '@user'}),
        SaveCart : $resource('/api/cart/save', {}, {'post':  {method:'POST', isArray:false}}),
        UpdateCart : $resource('/api/cart/update', {}, {'put':  {method:'PUT', isArray:false}}),
        ValidateCart : $resource('/api/cart/lvd/validate', {}, {'save':  {method:'POST', isArray:false}})
      };
    });
