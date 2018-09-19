<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="topDiv">
			<div class="row">
				<!-- First Column -->
				<!-- To display side bar -->
				<div class="col-md-3">
					<%@include file="./shared/side-bar.jsp"%>
				</div>

				<!-- Second Column -->
				<!-- To display Product -->
				<div class="col-md-9">
					<!-- Adding BreadCrumb -->
					<div class="row">
						<div class="col-lg-12">
							<!-- If Clicked on All Product then breadCrumb -->
							<c:if test="${userClickedAllProducts == true}">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
									<li class="breadcrumb-item active">All Products</li>
								</ol>
							</c:if>
							<!-- If Clicked on particular category Product then breadCrumb -->
							<c:if test="${userClickedCatProducts == true}">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
									<li class="breadcrumb-item active">Category</li>
									<li class="breadcrumb-item active">${cat.name}</li>
								</ol>
							</c:if>
							<!-- Adding Product -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>