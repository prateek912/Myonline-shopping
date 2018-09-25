<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<script type="text/javascript">
	window.userRole = '${userModel.role}'
	console.log("Role of user :"+window.userRole);
</script>
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
						<security:authorize access="hasAuthority('ADMIN')">
							<li id="manageProducts" class="nav-item"><a class="nav-link"
								href="${contextRoot}/manage/products">Manage Products</a></li>
						</security:authorize>
						<li id="about" class="nav-item"><a class="nav-link"
							href="${contextRoot}/about">About</a></li>
						<li id="contact" class="nav-item"><a class="nav-link"
							href="${contextRoot}/contact">Contact</a></li>
					</ul>
					<!-- Sign Up button for new users -->
					<ul class="nav navbar-nav ml-auto">
						<!-- Only for Anonymous user -->
						<security:authorize access="isAnonymous()">
							<li id="signup" class="nav-item"><a class="nav-link"
								href="${contextRoot}/register">Sign Up</a></li>
							<li id="login" class="nav-item"><a class="nav-link"
								href="${contextRoot}/login">LogIn</a></li>
						</security:authorize>
						<security:authorize access="isAuthenticated()">
							<li class="dropdown"><a href="javascript.void(0);"
								class="btn btn-default dropdown-toggle" id="dropdown_menu"
								data-toggle="dropdown"> ${model.fullName} <span
									class="caret"></span>
							</a> <!-- For DropDown menu -->
								<ul class="dropdown-menu">
									<security:authorize access="hasAuthority('USER')">
										<li>
											<!-- Cart of the user --> <a href="${contextRoot}/cart">Cart
												<span class="badge">${model.cart.cartLines}</span>-
												&#8377;${model.cart.grandTotal}
										</a>
										</li>
									</security:authorize>
									<li class="divider" role="seperator"><a
										href="${contextRoot}/perform-Logout">Logout</a></li>
								</ul></li>

						</security:authorize>
					</ul>
				</div>

			</nav>
		</div>
	</div>
</body>
</html>