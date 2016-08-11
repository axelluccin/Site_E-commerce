'use strict'

angular.module("lvdApp")
    .controller('TypeCtrl', function ($scope, $rootScope, Type, Cart, User, $location) {
         $scope.con ="none";
         var idUser = 0;

         if($scope.getSession()) {
            var idUser = $scope.getId();
            $scope.idUser = idUser;
         }
         else {
             $location.path("/login");
          }
          $scope.nbArticle = 0;
         var cart = Cart.CartUser.get({user : idUser}, function () {
             if(cart.idArticles) {
              var articles = cart.idArticles.split("/");
              var i;
              var quantity;
              var total = 0;
              for(i = 0; i < articles.length - 1; ++i) {
                quantity = articles[i].split(":");
                total += (parseInt(quantity[0]));
              }
              $scope.nbArticle = total;
             }

         });
         $scope.user = User.GetUser.get({id : idUser});
          $scope.types = Type.getAll.query();
          $scope.selectedIndex = -1;
          $scope.itemClicked = function ($index) {
            $scope.selectedIndex = $index;
          }

          $scope.logout = function () {
          }
    });
