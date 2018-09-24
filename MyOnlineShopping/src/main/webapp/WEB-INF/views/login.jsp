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
<!-- For Font in bootstrap -->
<link
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
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
		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<a href="${contextRoot}/home">Online Shopping</a>
				</div>
			</div>
		</nav>

		<!-- Login Form -->
		<div class="container">
			<!-- To display Invalid UserName and Password Message -->
			<c:if test="${not empty message}">
				<div class="row">
					<div class="col-md-offset-3 col-md-6">
						<div class="alert alert-danger">${message}</div>
					</div>
				</div>
			</c:if>
			<div class="row">
				<div class="col-md-offset-3 col-md-6">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h4>Login</h4>
						</div>
						<div class="panel-body">
							<form action="${contextRoot}/login" method="post"
								class="form-horizontal" id="loginForm">
								<div class="form-group">
									<label for="username" class="col-md-4 control-label">Email
										:</label>
									<div class="col-md-8">
										<input type="text" name="email" id="email"
											class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label for="password" class="col-md-4 control-label">Password
										:</label>
									<div class="col-md-8">
										<input type="password" name="password" id="password"
											class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-8 col-md-offset-4">
										<input type="submit" value="Login" class="btn btn-primary" />
									</div>
								</div>
								<!-- For CSRF protection -->
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Footer  -->
		<%@include file="./shared/footer.jsp"%>

		<!-- Bootstrap core JavaScript -->
		<script src="${js}/jquery.js"></script>
		<script src="${js}/bootstrap.bundle.min.js"></script>
		<!-- Jquery Validation JavaScript -->
		<script src="${js}/jquery.validate.js"></script>
		<!-- Linking my own JS file -->
		<script src="${js}/myapp.js"></script>
	</div>
</body>

</html>