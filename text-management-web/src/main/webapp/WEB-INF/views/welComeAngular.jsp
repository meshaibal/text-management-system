<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css" >
<link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css">  

<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="js/angular.min.js"></script>
<script type="text/javascript" src="js/angular-route.min.js"></script>
<script type="text/javascript" src="js/dirPagination.js"></script>

<link rel="stylesheet" type="text/css" href="css/textAddEdit.css"> 
<script type="text/javascript" src="js/textAngular.js"></script>

<title>Insert title here</title>

</head>
<body ng-app="textAngularApp">
<div class="container main-div">
	<div class="form-div" ng-view>		
			
	</div>
	<div >
		<a class="btn btn-primary btn-add-new-text" ng-href="#addText">Add New Text</a>
	</div>
	<div class="table-div"  ng-controller="textAngularListController" >
			<table id="tableTextList" class="table table-striped table-hover">
				<thead>
					<tr>
						<th>Id</th>
						<th>Title</th>
						<th>Content</th>
						<th>Difficulty Level</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-show="texts.length <= 0"><td colspan="4" style="text-align:center;">Loading new data!!</td></tr>
					<tr dir-paginate="currentText in texts|itemsPerPage:10" ng-click="getData(currentText)">
						<td>{{currentText.textId}}</td>
						<td><a target="#">{{currentText.textTitle}}</a></td>
						<td>{{currentText.textContent}}</td>
						<td>{{currentText.difficultyLevel}}</td>
					</tr>

				</tbody>			
			</table>
			<dir-pagination-controls
		       max-size="5"
		       direction-links="true"
		       boundary-links="true" 
			>
		    </dir-pagination-controls>
	</div>
	
	
</div>

<script type="text/ng-template" id="addText.htm">
			<form name="frmAddText" ng-show="showAddTextForm">
				<input type="hidden" name="textId" id="idTextId" ng-model="text.textId"/>
				<h2 class="header-addtext">Add text</h2>
				<div class="form-group" ng-class="{'has-error' : !frmAddText.textTitle.$valid && !frmAddText.textTitle.$pristine}">
					<label for="idTextTitle">Title</label>
					<input type="text" class="form-control" id="idTextTitle" name="textTitle" placeholder="Title" ng-model="text.textTitle" required />
					<p ng-show="!frmAddText.textTitle.$valid && !frmAddText.textTitle.$pristine" class="help-block with-errors">Title is required!</p>
				</div>
				<div class="form-group" ng-class="{'has-error' : !frmAddText.textContent.$valid && !frmAddText.textContent.$pristine}">
					<label for="idTextContent">Text</label>
					<textarea class="form-control" id="idTextContent" name="textContent" placeholder="Text" ng-model="text.textContent" required></textarea>
					<p ng-show="!frmAddText.textContent.$valid && !frmAddText.textContent.$pristine" class="help-block with-errors" >Text content is required!</p>
				</div>
				<button type="submit" class="btn btn-primary btn-add-edit-text" ng-disabled="!frmAddText.$valid" ng-click="addText()">Add</button>	
			</form>		
	</script>
</body>
</html>