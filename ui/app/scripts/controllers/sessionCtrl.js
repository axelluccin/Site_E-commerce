'use strict'

angular.module("lvdApp")
    .controller('SessionCtrl', function ($rootScope, $location, $window) {

      $rootScope.getSession = function() {
        var cookieString = readCookie("RestxSession-com.lvd");
        var cookieJson = JSON.parse(JSON.parse(cookieString));
        if(cookieJson == null) {
          return false;
        }
        else {
          return true;
        }
      };

      $rootScope.setSession = function (authenticate) {
        sessionStorage.setItem("id", authenticate.principal.id);
        sessionStorage.setItem("login", authenticate.principal.login);
        sessionStorage.setItem("firstname", authenticate.principal.firstname);
        sessionStorage.setItem("name", authenticate.principal.name);
        sessionStorage.setItem("email", authenticate.principal.mail);
      };

      $rootScope.getId = function () {
        return sessionStorage.getItem("id");
      };

      $rootScope.deleteSession = function() {
        sessionStorage.clear();
        createCookie("RestxSessionSignature-com.lvd","",-1);
        createCookie("RestxSession-com.lvd","",-1);
        $window.location.reload();
      }


      function readCookie(name) {
      	var nameEQ = name + "=";
      	var ca = document.cookie.split(';');
      	for(var i=0;i < ca.length;i++) {
      		var c = ca[i];
      		while (c.charAt(0)==' ') c = c.substring(1,c.length);
      		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
      	}
      	return null;
      }
      function createCookie(name,value,days) {
      	if (days) {
      		var date = new Date();
      		date.setTime(date.getTime()+(days*24*60*60*1000));
      		var expires = "; expires="+date.toGMTString();
      	}
      	else var expires = "";
      	document.cookie = name+"="+value+expires+"; path=/";
      }
    });
