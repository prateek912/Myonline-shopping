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
	<!-- BreadCrumb -->
	<div class="col-xs-12">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
			<li class="breadcrumb-item"><a href="${contextRoot}/show/all/products">Products</a></li>
			<li class="active breadcrumb-item">${product.name}</li>
		</ol>
	</div>
	
	<!-- Display Product -->
	<div class="row">
		<!-- Product Image -->
		<div class="col-xs-12 col-sm-4">
			<div class="thumbnail">
				<img src="${image}/${product.code}.jpg" class="img img-responsive singleProductImg"/>
			</div>
		</div>
		<!-- Product Description -->
		<div class="col-xs-12 col-sm-8">
			<h3>${product.name}</h3>
			<hr />
			<p>${product.description}</p>
			<hr />
			<h4>Price : <strong>&#8377; ${product.unitprice} /-</strong></h4>
			<hr />
			<c:choose>
				<c:when test="${product.quantity > 0}">
					<h6>Quantity Left : ${product.quantity}</h6>
					<a href="${contextRoot}/cart/add/${product.id}/product" class="btn btn-success">Add To Cart</a>
					<a href="${contextRoot}/show/all/products" class="btn btn-primary">Back</a> 
				</c:when>
				<c:otherwise>
					<h6>Quantity Left : <span style="color : red ">Out of Stock!!</span></h6>
					<a href="${contextRoot}/cart/add/${product.id}/product" class="btn btn-success disabled">Add To Cart</a>
					<a href="${contextRoot}/show/all/products" class="btn btn-primary">Back</a> 
				</c:otherwise>
			</c:choose>
		
			
		</div>
	</div>
</div>
</body>
</html>