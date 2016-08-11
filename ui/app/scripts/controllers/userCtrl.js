'use strict'

angular.module("lvdApp")
    .controller('UserCtrl', function ($scope, User, $location, $route, $window, $routeParams) {
       if($scope.getSession()) {
              $location.path("/category/all/1");
       }

      $scope.message = "";

      $scope.Done = function() {
        var object = User.GetUser.get({id : 0}, function() {
          object.mail = $scope.mailLogin;
          object.password = $scope.password;
          var authenticate = User.Authenticate.post(object)
          .$promise.then(function(data) {
                    $scope.message = "welcome to you";
                    $scope.setSession(data);
                    $window.location.reload();
                    $location.path("/category/all/1");
                  }, function(error) {
                     $scope.message = "Sorry, your email/password is wrong";
                     alert($scope.message);
                  });
        });
      };

      $scope.Signin = function () {
         var object = User.GetUser.get({id : 0}, function() {
                  object.mail = $scope.mailSign;
                  var profil = User.Signin.save(object)
                  .$promise.then(function(data) {
                      $scope.mailSign = ""
                      alert("Welcome in LVD Shop, you can login with email ...");
                  }, function(error) {
                     $scope.message = "An error occured when account creation ";
                     alert($scope.message);
                  });
        });
      };

    });
