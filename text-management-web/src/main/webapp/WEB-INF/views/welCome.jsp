<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="welcomeMessage" scope="request" type="java.lang.String" />

<html>
<head>
<meta charset="utf-8">


<title>Insert title here</title>
</head>
<body>
<c:out value="${welcomeMessage}"></c:out>
</body>
</html>