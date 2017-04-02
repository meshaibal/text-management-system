<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
		var dataTable = $('#tableTextList').DataTable({
							"bSort" : false,
							"bFilter": false,
							"columnDefs": [{
											"targets": [ 2 ],
				                			"visible": false
											}
							              ]
						});
		$('#tableTextList tbody').on('click', 'a', function(){
			var rowData = dataTable.row($(this).parents('tr')).data();
			$('#idTextId').val(rowData[0]);
			$('#idTextTitle').val(jQuery(rowData[1]).text());
			$('#idTextContent').val(rowData[2]);
			
			$('#idTextTitle').attr('readonly',true);
			
			$('.header-addtext').text('Edit Text');
			$('.btn-add-edit-text').html('Edit');
			$('.form-add-new-text').attr('action','update-text');
			$('.btn-add-new-text').show();
			$('.form-add-new-text').show(1000);
		});
		
		$('.div-text-added').hide(2000);
		$('.form-add-new-text').hide();
		
		$('.btn-add-new-text').on('click', function(){
			$('.header-addtext').text('Add Text');
			$('.btn-add-edit-text').html('Add');
			
			$('#idTextId').val('');
			$('#idTextTitle').val('');
			$('#idTextContent').val('');
			
			$('#idTextTitle').attr('readonly',false);
			$('.form-add-new-text').attr('action','add-text');
			
			$('.form-add-new-text').show(1000);
			if($('.btn-add-edit-text').html() == 'Add'){
				$(this).hide();
			}
		})
		
		
		
		$('.btn-add-edit-text').on('click', function(){
			$('.form-add-new-text div').each(function(){
				$(this).removeClass('has-error');
			});
			
			if($.trim($('#idTextTitle').val()).length == 0){
				$('#idTextTitle').closest('div').addClass('has-error');
				return false;
			}
			if($.trim($('#idTextContent').val()).length == 0){
				$('#idTextContent').closest('div').addClass('has-error');
				return false;
			}
		});
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