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
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<div class="row">
				<a class="navbar-brand" href="${contextRoot}/home">Online
					Shopping</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarResponsive" aria-controls="navbarResponsive"
					aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarResponsive">
					<ul class="navbar-nav ml-auto">
						<li id="home" class="nav-item active"><a class="nav-link"
							href="${contextRoot}/home">Home <span class="sr-only">(current)</span></a>
						</li>
						<li id="viewProducts" class="nav-item"><a class="nav-link"
							href="${contextRoot}/show/all/products">View Products</a></li>
							<li id="manageProducts" class="nav-item"><a class="nav-link"
							href="${contextRoot}/manage/products">Manage Products</a></li>
						<li id="about" class="nav-item"><a class="nav-link"
							href="${contextRoot}/about">About</a></li>
						<li id="contact" class="nav-item"><a class="nav-link"
							href="${contextRoot}/contact">Contact</a></li>
					</ul>
				</div>
			</div>
		</div>
	</nav>

</body>
</html>