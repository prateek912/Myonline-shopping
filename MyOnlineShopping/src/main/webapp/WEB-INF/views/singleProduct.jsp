<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<!-- BreadCrumb -->
		<div class="col-xs-12">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
				<li class="breadcrumb-item"><a
					href="${contextRoot}/show/all/products">Products</a></li>
				<li class="active breadcrumb-item">${product.name}</li>
			</ol>
		</div>

		<!-- Display Product -->
		<div class="row">
			<!-- Product Image -->
			<div class="col-xs-12 col-sm-4">
				<div class="thumbnail">
					<img src="${image}/${product.code}.jpg"
						class="img img-responsive singleProductImg" />
				</div>
			</div>
			<!-- Product Description -->
			<div class="col-xs-12 col-sm-8">
				<h3>${product.name}</h3>
				<hr />
				<p>${product.description}</p>
				<hr />
				<h4>
					Price : <strong>&#8377; ${product.unitprice} /-</strong>
				</h4>
				<hr />
				<security:authorize access="hasAuthority('USER')">
					<c:choose>
						<c:when test="${product.quantity > 0}">
							<h6>Quantity Left : ${product.quantity}</h6>
							<a href="${contextRoot}/cart/add/${product.id}/product"
								class="btn btn-success"><i class="fa fa-shopping-cart"></i></a>
							<a href="${contextRoot}/show/all/products"
								class="btn btn-primary">Back</a>
						</c:when>
						<c:otherwise>
							<h6>
								Quantity Left : <span style="color: red">Out of Stock!!</span>
							</h6>
							<a href="${contextRoot}/cart/add/${product.id}/product"
								class="btn btn-success disabled"><i class="fa fa-shopping-cart"></i></a>
							<a href="${contextRoot}/show/all/products"
								class="btn btn-primary">Back</a>
						</c:otherwise>
					</c:choose>
				</security:authorize>

				<!-- For Admin -->
				<security:authorize access="hasAuthority('ADMIN')">
					<a href="${contextRoot}/manage/${product.id}/product"
								class="btn btn-success warning"><i class="fa fa-pencil"></i></a>
					<a href="${contextRoot}/show/all/products"
								class="btn btn-primary">Back</a>			
				</security:authorize>
			</div>
		</div>
	</div>
</body>
</html>