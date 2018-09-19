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
	<h1 class="my-4">Categories</h1>
	<div class="list-group">
		<!-- Iterating into Categories list -->
		<c:forEach items="${catlist}" var="list">
			<a id="a_${list.name}" href="${contextRoot}/show/category/${list.id}/products" 
				class="list-group-item">${list.name}</a>
		</c:forEach>
	</div>
</body>
</html>