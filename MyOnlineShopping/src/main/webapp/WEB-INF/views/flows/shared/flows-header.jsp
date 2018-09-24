<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Setting Context Root variable to use -->
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!-- Creating variable of resource path to use according to our file system -->
<!-- This /resources/* value is coming from spring.xml file -->
<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="image" value="/resources/images" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Online Shopping - ${title}</title>

<!-- For Showing Active Menu on Page -->
<script type="text/javascript">
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}'
</script>

<!-- Bootstrap core CSS -->
<!-- Variable CSS that is created at the TOP -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">
<!-- For adding Custom theme to Project -->
<link href="${css}/bootstrap-sketchy.css" rel="stylesheet">
<!-- For adding CSS JQuery Data Table -->
<link href="${css}/dataTables.bootstrap4.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>
<body>
	<div class="wrapper">
	<%@include file="flows-navbar.jsp" %>
	<!-- Page Content -->
	<div class="content">