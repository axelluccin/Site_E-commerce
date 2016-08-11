'use strict'

angular.module("lvdApp")
    .controller('AccountCtrl', function ($scope, User, $routeParams, $window) {

      var userAccount = User.GetUser.get({id : $routeParams.user}, function () {
        $scope.nameAccount = userAccount.name;
        $scope.firstnameAccount = userAccount.firstname;
        $scope.mailAccount = userAccount.mail;
        $scope.pwdAccount = userAccount.password;
      });

      $scope.updateAccount = function () {
       userAccount.id = $routeParams.user;
       userAccount.name = $scope.nameAccount;
       userAccount.firstname = $scope.firstnameAccount;
       userAccount.mail = $scope.mailAccount;
       userAccount.password = $scope.pwdAccount;
       var boolUpdate = User.Update.put(userAccount, function() {
          alert("Account was updated ...");
          $window.location.reload();
       });
      };
    });
