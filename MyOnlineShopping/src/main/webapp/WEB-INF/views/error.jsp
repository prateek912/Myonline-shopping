<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Setting Context Root variable to use -->
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!-- Creating variable of resource path to use according to our file system -->
<!-- This /resources/* value is coming from spring.xml file -->
<spring:url var="css" value="/resources/css" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Online Shopping - ${title}</title>

<!-- Bootstrap core CSS -->
<!-- Variable CSS that is created at the TOP -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">
<!-- For adding Custom theme to Project -->
<link href="${css}/bootstrap-sketchy.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>

	<div class="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
			<div class="container">
				<div class="row">
					<a class="navbar-brand" href="${contextRoot}/home">Home</a>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarResponsive" aria-controls="navbarResponsive"
						aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
				</div>
			</div>
		</nav>

		<div class="container">
			<div class="row">
				<div class="col-xs-12">
					<div class="jumbotron">
						<h1>${errorTitle}</h1>
						<hr />
						<blockquote style="word-wrap:break-word">${errorDescription}</blockquote>
					</div>
				</div>
			</div>
		</div>

		<!-- Bootstrap core JavaScript -->
		<script src="${js}/jquery.js"></script>
		<script src="${js}/bootstrap.bundle.min.js"></script>
		<!-- Linking my own JS file -->
		<script src="${js}/myapp.js"></script>
	</div>
</body>

</html>