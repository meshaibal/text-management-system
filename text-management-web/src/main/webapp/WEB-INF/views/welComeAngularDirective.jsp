<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="UTF-8">

<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" >

<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="js/angular.min.js"></script>

<script type="text/javascript" src="js/textAngularDirective.js"></script>

</head>
<body ng-app="angularDirectiveApp">

<div ng-controller="customDirectiveController">

	<div>
		<form class="form-inline">
			<div class="form-group">
				<label>Text Id:</label>
				<input type="text" class="form-control" ng-model="textId"/>
			</div>
			<button class="btn btn-primary" ng-click="doSearch()">Search</button>
			<button class="btn btn-primary" ng-click="searchAll()">Show All</button>
		</form>
	</div>
	<div class="col-md-4" ng-show="!isAll && isSingle" text-detail></div>
	<div ng-repeat="text in texts" class="col-md-4" ng-show="isAll">
		<text-detail></text-detail>
	</div>
</div>
</body>
</html>