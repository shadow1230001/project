'use strict'

var editor = angular.module('editor', ['ngTagsInput', "ng-showdown", 'monospaced.elastic' ] )
.controller('editorController', function( $scope, $http ) {
		
	
		$scope.alert = {}
		
		$scope.closeAlert = function() {
			$scope.alert.show = false;
		}
	
      	$scope.loadTags = function(query) {
      		return $http.post('/tags', query);
      	};
      	
      	$scope.postCreative = function() {
      		//if( !$scope.validate() ) return;
      		
    		var res = $http.post( "/personal/editor/create", angular.toJson( $scope.creative ));
    		
    		res.success(function(data, status, headers, config) {
    			$scope.alert = data;
    			if( angular.equals( $scope.alert.level, "SUCCESS" ) ) {
    				for (var i = 0, len = $scope.creative.chapters.length; i < len; i++) {
    					$scope.creative.chapters[i] = {
    							content: $scope.newChapter.content
    					};
    				}
    				
    				$scope.creative = {};
    			}
    		});
    		res.error(function(data, status, headers, config) {
    			alert( "failure message: " + JSON.stringify({data: data}));
    		});
      	}
      	
		$scope.chapters = [];
		
		$scope.creative = {
				chapters: $scope.chapters
		}
	
	    $scope.addChapter = function() {
			$scope.newChapter = {
				"id": 0,
				"title": "",
				"content": ""
			};
			
			var id = getMaxChapterID();
			$scope.debug = id;
			$scope.newChapter.id = id + 1;
			
			$scope.newChapter.content = 'Enter your [Markdown][1] here.' +
		    '\n' +
		    '\n- *first*' +
		    '\n- **second**' +
		    '\n- third' +
		    '\n' +
		    '\n[1]: http://daringfireball.net/projects/markdown/syntax';
			
			
			$scope.chapters[ id ] = $scope.newChapter;
	    };
		
		function getMaxChapterID() {
			return $scope.chapters.length;
		}
		
		$scope.removeChapter = function( id ) {
			$scope.chapters.splice( id - 1, 1 );
			shiftChapterIndexes();
		}
		
		function shiftChapterIndexes() {
			var index = 1;
			$scope.chapters.forEach( function( chapter ) {
				chapter.id = index;
				index++;
			});
		}	

		$scope.validate = function() {
			if( !angular.isObject($scope.creative) ) {
				return false;
			}
			if( $scope.creative.name ) {
				$scope.validation.name = "Empty creative name";
				return false;
			}else{
				$scope.validation.name = "";
			}
			
			if( $scope.creative.tags.length() > 0 ) {
				$scope.validation.tags = "No creative tags";
				return false;
			}else{
				$scope.validation.tags = "";
			}
			
			if( $scope.creative.description ) {
				$scope.validation.description = "Empty creative description";
				return false;
			}else{
				$scope.validation.description = ""; 
			}
			
			
			return true;
		}
});