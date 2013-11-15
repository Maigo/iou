'use strict';

/* Controllers */

var iouControllers = angular.module('iouControllers', []);

iouControllers.controller('IouGroupListCtrl', ['$scope', 'Group',
  function IouGroupListCtrl($scope, Group) {
    $scope.error = null;

    $scope.groups = Group.query({}, function() {}, function(response) {
      $scope.error = {code: response.status};
    });
  }]);

iouControllers.controller('IouGroupNewCtrl', ['$scope', '$location', 'Group',
  function IouGroupNewCtrl($scope, $location, Group) {
    
    $scope.group = {};

    $scope.reset = function() { $scope.group = {}; };
    $scope.save = function() { 
      $scope.group = Group.save($scope.group).$promise.then(
        function(group) { $location.path("/group/" + group.groupId); }
      );
    }
  }]);

iouControllers.controller('IouGroupDetailCtrl', ['$scope', '$routeParams', 'Group', 'User',
  function IouGroupDetailCtrl($scope, $routeParams, Group, User) {
    $scope.error = null;

    $scope.group = Group.get({groupId: $routeParams.groupId},
      function() {},
      function(response) {
        $scope.error = {code: response.status};
        $scope.group = {name: "unknown", description: "unknown"};
      });
    $scope.users = User.query({filter:'group', filterId:$routeParams.groupId},
      function() {},
      function(response) {
        $scope.error = {code: response.status};
        $scope.group = {name: "unknown", description: "unknown"};
      });
  }]);

iouControllers.controller('IouUserListCtrl', ['$scope', 'User',
  function IouUserListCtrl($scope, User) {
    $scope.error = null;

    $scope.users = User.query({}, function() {}, function(response) {
      $scope.error = {code: response.code};
    });
  }]);

iouControllers.controller('IouUserNewCtrl', ['$scope', '$location', 'User',
  function IouUserNewCtrl($scope, $location, User) {

    $scope.user = {};

    $scope.reset = function() { $scope.user = {}; };
    $scope.save = function() {
      $scope.user = User.save($scope.user).$promise.then(
        function(user) { $location.path("/user/" + user.userId); }
      );
    }
  }]);

iouControllers.controller('IouUserDetailCtrl', ['$scope', '$routeParams', 'User', 'Group', 'Event',
  function IouGroupDetailCtrl($scope, $routeParams, User, Group, Event) {
    $scope.error = null;

    $scope.user   = User.get({userId: $routeParams.userId},
      function() {},
      function(response) {
        $scope.error = {code: response.status};
      });
    $scope.groups = Group.query({filter:'user',filterId:$routeParams.userId});
    $scope.events = Event.query({filter:'user',filterId:$routeParams.userId});
  }]);

iouControllers.controller('IouLoginCtrl', ['$scope',
  function IouLoginCtrl($scope) {

  }]);

