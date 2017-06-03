<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:if test="${isExists}">
	<jsp:useBean id="isExists" scope="request" type="java.lang.Boolean" />
</c:if>
<c:if test="${textAdded}">
	<jsp:useBean id="textAdded" scope="request" type="java.lang.Boolean" />
</c:if>
<c:if test="${textUpdated}">
	<jsp:useBean id="textUpdated" scope="request" type="java.lang.Boolean" />
</c:if>
<c:if test="${not empty welcomeMessage}">
	<jsp:useBean id="welcomeMessage" scope="request" type="java.lang.String" />
</c:if>
<c:if test="${not empty availableTextList}">
	<jsp:useBean id="availableTextList" scope="request" type="java.util.List<com.pelican.text.management.persistence.model.Text>" />
</c:if>

<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" >
<link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css">  

<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" charset="utf8" src="js/jquery.dataTables.min.js"></script>

<link rel="stylesheet" type="text/css" href="css/textAddEdit.css"> 
<script type="text/javascript" src="js/textAddEdit.js"></script>

<title>Insert title here</title>

</head>
<body>
<div class="container main-div">
	<div class="form-div">		
		<form:form commandName="text" method="POST" action="add-text" class="form-add-new-text">
			<input type="hidden" name="textId" id="idTextId" />
			<h2 class="header-addtext">Add text</h2>
			<div class="form-group">
				<label for="idTextTitle">Title</label>
				<input type="text" class="form-control" id="idTextTitle" name="textTitle" placeholder="Title" />
			</div>
			<div class="form-group">
				<label for="idTextContent">Text</label>
				<textarea class="form-control" id="idTextContent" name="textContent" placeholder="Text" ></textarea>
			</div>
			<button type="submit" class="btn btn-primary btn-add-edit-text">Add</button>
		</form:form>
			<button type="button" class="btn btn-primary btn-add-new-text">Add New Text</button>
	</div>
	<c:if test="${textAdded}">
		<div class="alert alert-success div-text-added">
			<strong>Success!</strong> Text is added !
		</div>
	</c:if>
	<c:if test="${textUpdated}">
		<div class="alert alert-success div-text-added">
			<strong>Success!</strong> Text is Updated !
		</div>
	</c:if>
	<c:if test="${isExists}">
		<div class="alert alert-danger div-title-already-exists">
			<strong>Error!</strong> Title already exists !
		</div>
	</c:if>
	<div class="table-div">
		<c:if test="${not empty availableTextList}">
			<h2 class="header-mytext">My texts</h2>
			<table id="tableTextList" >
				<thead>
					<tr>
						<th>Id</th>
						<th>Title</th>
						<th>Content</th>
						<th>Created Date</th>
						<th>Difficulty Level</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${availableTextList}" var="currentText">
						<tr>
							<td>${currentText.textId}</td>
							<td><a target="#">${currentText.textTitle}</a></td>
							<td>${currentText.textContent}</td>
							<td>${currentText.createdDate}</td>
							<td>${currentText.difficultyLevel}</td>
						</tr>
					</c:forEach>
				</tbody>
			
			</table>
		</c:if>
	</div>
</div>
</body>
</html>