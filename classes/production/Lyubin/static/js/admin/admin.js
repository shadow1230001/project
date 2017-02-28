'use strict'

var editor = angular.module('admin', [] )
.controller('adminController', function( $scope, $http ) {
	
	$scope.isProfilesLoaded = false;
	
	$scope.showUsers = true;
	$scope.showCreatives = false;
	$scope.showHack = false;
	
	$scope.currentProfilesPage = 0;
	$scope.profiles = {}
		
	$scope.getNextProfilePage = function() {
		if( $scope.profiles.page.hasNext ) {
			$scope.currentProfilesPage++;
			$scope.loadProfiles();
		};
	}
	
	$scope.getPreviousProfilePage= function() {
		if( $scope.profiles.page.hasPrevious) {
			$scope.currentProfilesPage--;
			$scope.loadProfiles();
		};
	}
	
	$scope.loadProfiles = function() {
		$http.get('/admin/profiles?page='+ $scope.currentProfilesPage ).then(function successCallback(response) {
			$scope.profiles = {};
			$scope.profiles = response.data;
			$scope.isProfileLoaded = true;
		  }, function errorCallback(response) {
			  alert("Didn't get profiles. Something went wrong");
		  });
	}
	
	$scope.openUsers = function() {
		$scope.showCreatives = false;
		$scope.showHack = false;
		$scope.showUsers = true;
	}
	
	$scope.openCreatives = function() {
		$scope.showHack = false;
		$scope.showUsers = false;
		$scope.showCreatives = true;
	}
	
	$scope.openHack = function() {
		$scope.showCreatives = false;
		$scope.showUsers = false;
		$scope.showHack = true;
	}
	
});