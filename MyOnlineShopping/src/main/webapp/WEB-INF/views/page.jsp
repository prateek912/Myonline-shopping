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
		<!-- Navigation -->
		<%@include file="./shared/nav-bar.jsp"%>
		
		<!-- Page Content -->
		<div class="content">
			<!-- Load Page content only when user clicked -->
			<c:if test="${userClickedHome == true}">
				<!-- Page Content -->
				<%@include file="home.jsp"%>
			</c:if>

			<!-- Load About page only if User click -->
			<c:if test="${userClickedAbout == true}">
				<!-- Page Content -->
				<%@include file="about.jsp"%>
			</c:if>

			<!-- Load Contact page only if User click -->
			<c:if test="${userClickedContact == true}">
				<!-- Page Content -->
				<%@include file="contact.jsp"%>
			</c:if>
			
			<!-- Load when user clicked Products -->
			<c:if test="${userClickedAllProducts == true or userClickedCatProducts == true}">
				<%@include file="listProducts.jsp"%>
			</c:if>
			
			<!-- For loading single product page -->
			<c:if test="${userClickedShowProduct == true}">
				<%@include file="singleProduct.jsp" %>
			</c:if>
			
			<!-- For loading Manage Product page -->
			<c:if test="${userClickedManage == true}">
				<%@include file="productManage.jsp" %>
			</c:if>
			
		</div>

		<!-- Footer  -->
		<%@include file="./shared/footer.jsp"%>

		<!-- Bootstrap core JavaScript -->
		<script src="${js}/jquery.js"></script>
		<script src="${js}/bootstrap.bundle.min.js"></script>
		<!-- JQuery DataTable Plugin -->
		<script src="${js}/jquery.dataTables.js"></script>
		<!-- JQuery DataTable Design -->
		<script src="${js}/dataTables.bootstrap4.js"></script>
		<!-- Bootbox for alert and dialog box -->
		<script src="${js}/bootbox.min.js"></script>
		<!-- Jquery Validation JavaScript -->
		<script src="${js}/jquery.validate.js"></script>
		<!-- Linking my own JS file -->
		<script src="${js}/myapp.js"></script>
	</div>
</body>

</html>