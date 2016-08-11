'use strict'

angular.module("lvdApp")
    .controller('ArticleCtrl', function ($scope, Article, Cart, $routeParams, $route, $window, $location) {
        if($scope.getSession()) {
            var idUser = $scope.getId();
         }
         else {
          $location.path("/login");
         }
        $scope.atls = Article.AllArticles.query({category : $routeParams.category});
        $scope.addCart = function (idArticle) {
           var cart = Cart.CartUser.get({user : idUser}, function() {
                if (cart.idUser == 0) {
                    cart.idUser = idUser;
                    cart.idArticles = "1:" + idArticle + "/";
                    Cart.SaveCart.post(cart);
                 }
                else {
                  cart.idArticles = toCompare((cart.idArticles).split("/"), idArticle);
                  Cart.UpdateCart.put(cart);
                }
                alert("Article was added in your baset");
                $window.location.reload();
           });
        };
        function toCompare (articles, idArticle) {

          var i;
          var quantity;
          var result = "";
          var oneArticle;
          var bool = false;

          for(i = 0; i < articles.length - 1; ++i) {
            oneArticle = articles[i].split(":");
            quantity = parseInt(oneArticle[0]);
            if(parseInt(oneArticle[1]) == parseInt(idArticle)) {
              quantity += 1;
              bool = true;
            }
            result += quantity + ":" + oneArticle[1]+ "/";
          }
          if(!bool) {
            result += "1:" + idArticle + "/";
          }

          return result;
        };
    });
