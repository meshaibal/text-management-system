<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:if test="${textAdded}">
	<jsp:useBean id="textAdded" scope="request" type="java.lang.Boolean" />
</c:if>
<c:if test="${not empty welcomeMessage}">
	<jsp:useBean id="welcomeMessage" scope="request" type="java.lang.String" />
</c:if>
<c:if test="${not empty availableTextList}">
	<jsp:useBean id="availableTextList" scope="request" type="java.util.List<com.edia.text.management.persistence.model.Text>" />
</c:if>

<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css">  

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
<title>Insert title here</title>

<script type="text/javascript">
	$(document).ready(function(){
		$('#tableTextList').DataTable({
			"bSort" : false,
			"bFilter": false
		});
		
		$('.div-text-added').hide(2000);
		$('.form-add-new-text').hide();
		
		$('.btn-add-new-text').on('click', function(){
			$('.form-add-new-text').show(1000);
			$(this).hide();
		})
	});
</script>

<style type="text/css">

.main-div{
	width: 400px;
	float: none;
    margin: 0 auto; 
    background-color: #D8D7CF;
    margin-top: 20px;
}

.form-div{
	margin-bottom: 50px;
}

.header-mytext, .header-addtext{
	background-color: #ABCDEC;
	padding: 5px 5px 5px 5px;
}

.btn-add-new-text{
	margin-top: 5px;
}
</style>
</head>
<body>
<div class="container main-div">
	<div class="form-div">		
		<form:form commandName="text" method="POST" action="save-text" class="form-add-new-text">
			<h2 class="header-addtext">Add text</h2>
			<div class="form-group">
				<label for="idTextTitle">Title</label>
				<input type="text" class="form-control" id="idTextTitle" name="textTitle" placeholder="Title" />
			</div>
			<div class="form-group">
				<label for="idTextContent">Text</label>
				<textarea class="form-control" id="idTextContent" name="textContent" placeholder="Text" ></textarea>
			</div>
			<button type="submit" class="btn btn-primary">Add</button>
		</form:form>
		<button type="button" class="btn btn-primary btn-add-new-text">Add New Text</button>
	</div>
	<c:if test="${textAdded}">
		<div class="alert alert-success div-text-added">
			<strong>Success!</strong> Text is added !
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
						<th>Created Date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${availableTextList}" var="currentText">
						<tr>
							<td>${currentText.textId}</td>
							<td>${currentText.textTitle}</td>
							<td>${currentText.createdDate}</td>
						</tr>
					</c:forEach>
				</tbody>
			
			</table>
		</c:if>
	</div>
</div>
</body>
</html>