﻿window.publication = angular.module('estudante', ['ngRoute', 'LocalStorageModule', 'angular-loading-bar', 'FatecControllers']);

publication.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
    $routeProvider
        
      
        .when('/home', { templateUrl: '/AngularJS/App/Student/Home/StudentHome.view.html', controller: 'StudentHomeController' })

        .when('/question', { templateUrl: '/AngularJS/App/Student/Question/Question.view.html', controller: 'QuestionController' })

        .when('/perfil', { templateUrl: '/AngularJS/App/Student/Profile/Profile.view.html', controller: 'ProfileController' })

        .when('/completed', { templateUrl: '/AngularJS/App/Student/Completed/Completed.view.html', controller: 'CompletedController' })

        .when('/result', { templateUrl: '/AngularJS/App/Student/Result/Result.view.html', controller: 'ResultController' })

        .otherwise({ redirectTo: '/home' });

    $httpProvider.interceptors.push('authorizationInterceptor');
}]);


window.FatecControllers = angular.module('FatecControllers', ['ui.bootstrap', 'ngReallyClickModule', 'ui.select', 'ngSanitize', 'checklist-model']);
