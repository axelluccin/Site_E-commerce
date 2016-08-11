'use strict'

angular.module("lvdApp")
    .service("Article", function($resource) {
        return {
          AllArticles : $resource('/api/article/all/:category', {category : '@category'}),
          OneArticle : $resource('/api/article/get/:id', {}, {'get':  {method:'GET', isArray:false}})
        }
    });
