'use strict';

angular.module('lvdApp', [
  'ngCookies',
  'ngResource',
  'ngSanitize',
  'ngRoute'
])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/home.html'
      })
      .when('/category/all/:type', {
        templateUrl: 'views/category.html',
        controller: 'CategoryCtrl'
      })
      .when('/article/all/:category', {
        templateUrl: 'views/article.html',
        controller: 'ArticleCtrl'
      })
      .when('/cart/:user', {
        templateUrl: 'views/cart.html',
        controller: 'CartCtrl'
      })
      .when('/account/:user', {
        templateUrl: 'views/account.html',
        controller: 'AccountCtrl'
      })
      .when('/cart/get/archive/:user', {
        templateUrl: 'views/orderArchive.html',
        controller: 'CartCtrl'
      })
      .when('/login', {
        templateUrl: 'views/user.html',
        controller: 'UserCtrl'
      })
      .otherwise({
        redirectTo: '/login'
      });
  });

