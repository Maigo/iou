'use strict';

/* App Module */

var iouApp = angular.module('iouApp', [
  'ngRoute',
  'iouControllers',
  'iouFilters',
  'iouServices'
]);

iouApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/group', {
        templateUrl: 'partials/group-list.html',
        controller: 'IouGroupListCtrl'
      }).
      when('/group/new', {
        templateUrl: 'partials/group-new.html',
        controller: 'IouGroupNewCtrl'
      }).
      when('/group/:groupId', {
        templateUrl: 'partials/group-detail.html',
        controller: 'IouGroupDetailCtrl'
      }).
      when('/user', {
        templateUrl: 'partials/user-list.html',
        controller: 'IouUserListCtrl',
      }).
      when('/user/new', {
        templateUrl: 'partials/user-new.html',
        controller: 'IouUserNewCtrl'
      }).
      when('/user/:userId', {
        templateUrl: 'partials/user-detail.html',
        controller: 'IouUserDetailCtrl'
      }).
      when('/login', {
        templateUrl: 'partials/login.html',
        controller: 'IouLoginCtrl'
      }).
      otherwise({
        redirectTo: '/group'
      });
  }]);

