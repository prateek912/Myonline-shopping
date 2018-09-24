<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- Navigation -->
	<div class="container">
		<div class="row">
			<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
				<a class="navbar-brand" href="${contextRoot}/home">Online
					Shopping</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarResponsive" aria-controls="navbarResponsive"
					aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarResponsive">
					<ul class="navbar-nav mr-auto">
						<li id="viewProducts" class="nav-item"><a class="nav-link"
							href="${contextRoot}/show/all/products">View Products</a></li>
						<li id="manageProducts" class="nav-item"><a class="nav-link"
							href="${contextRoot}/manage/products">Manage Products</a></li>
						<li id="about" class="nav-item"><a class="nav-link"
							href="${contextRoot}/about">About</a></li>
						<li id="contact" class="nav-item"><a class="nav-link"
							href="${contextRoot}/contact">Contact</a></li>
					</ul>
					<!-- Sign Up button for new users -->
					<ul class="nav navbar-nav ml-auto">
						<li id="signup" class="nav-item"><a class="nav-link"
							href="${contextRoot}/register">Sign Up</a></li>
						<li id="login" class="nav-item"><a class="nav-link"
							href="${contextRoot}/register">LogIn</a></li>
					</ul>
				</div>

			</nav>
		</div>
	</div>
</body>
</html>