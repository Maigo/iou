'use strict';

/* Services */

var iouServices = angular.module('iouServices', ['ngResource']);

iouServices.factory('Group', ['$resource',
  function($resource) {
    return $resource('http://localhost/api/rest/:filter/:filterId/group/:groupId', {}, {
      query: {method:'GET', params:{filter:'@filter', filterId:'@filterId', groupId:'@groupId'}, isArray:true}
    });
  }]);

iouServices.factory('User', ['$resource',
  function($resource) {
    return $resource('http://localhost/api/rest/:filter/:filterId/user/:userId', {}, {
      query: {method:'GET', params:{filter:'@filter', filterId:'@filterId', userId:'@userId'}, isArray:true}
    });
  }]);

iouServices.factory('Event', ['$resource',
  function($resource) {
    return $resource('http://localhost/api/rest/:filter/:filterId/event/:eventId', {}, {
      query: {method:'GET', params:{filter:'@filter', filterId:'@filterId', eventId:'@eventId'}, isArray:true}
    });
  }]);

