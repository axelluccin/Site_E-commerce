'use strict'

angular.module("lvdApp")
    .controller('CartCtrl', function ($scope, Cart, Article, $routeParams, $location, $window) {
        $scope.atls = [];
        $scope.mult = [];
         if($scope.getSession()) {
            var idUser = $scope.getId();
         }
         else {
          $location.path("/login");
         }
        var cart = Cart.CartUser.get({user : idUser}, function() {
          if(cart.idArticles) {
            var articles = (cart.idArticles).split("/");
            var i;
            var article;


            for(i = 0; i < articles.length - 1 ; ++i) {
                article = articles[i].split(":");
                $scope.mult[i] = parseInt(article[0]);
                $scope.atls[i] = Article.OneArticle.get({id : article[1]});
            }
          }
        });
        $scope.getTotal = function () {
          var total = 0;
          var i;
          for(i = 0; i < $scope.atls.length; i++){
              var atl = $scope.atls[i];
              total += atl.price * $scope.mult[i];
          }
          return (Math.round((total * 100)) / 100);
        }

        $scope.acceptCart = function () {
          var cart = Cart.CartUser.get({user : idUser}, function() {
                   if(cart.idArticles) {
                     var accept = confirm("Your basket will be confirm, Are you sure ?");
                     if(accept == true) {
                       Cart.ValidateCart.save({idUser : idUser});
                       alert("Order is done, check your email ...");
                       $window.location.reload();
                      }
                   }
                   else {
                    alert("Your basket is empty....");
                   }
          });
        }

        var archives = Cart.CartArchive.query({user : idUser}, function() {
        /*  var k;
          var articlesArchive;
          var j;
          var articleArchive;
          for(k = 0; k < archives.length; ++k) {
            articlesArchive = (archives[k].idArticles).split("/");

            for(j = 0; j < articlesArchive.length - 1 ; ++j) {
              articleArchive = articlesArchive[j].split(":");
              $scope.multArchive[j] = parseInt(articleArchive[0]);
              $scope.atlsArchive[j] = Article.OneArticle.get({id : articleArchive[1]});
            }
          }*/

          $scope.nbArchive = archives.length;
        });
    });
