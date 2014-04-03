<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page isELIgnored="false" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="assets/ico/jogiyo.png">

<title>JOGIYO</title>

<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="test/test-template.css" rel="stylesheet">

</head>

<body>

	<!-- header -->
	<%@ include file="/common/header.jsp"%>
	<!-- end of header -->

	<!-- container -->
	<div class="container">
	
		<div class="test-template">
			<div class="row">
		      <div class="col-xs-6 col-md-3">
		        <a href="#" class="thumbnail">
		          <img src="assets/img/rest/western.jpg" alt="Generic placeholder thumbnail">
		        </a>
		      </div>
		      
		</div>	

		<!-- test message -->
		<div class="test-template">
			<h1>test template</h1>
			<p class="lead">당신은 "${sessionType}" 입니다.</p>
			<p class="lead">"${sessionName}" 님 환영합니다.</p>
			<p class="lead">sessionType : ${sessionType}</p>
			<p class="lead">sessionId : ${sessionId}</p>
			<p class="lead">sessionPw : ${sessionPw}</p>
			<p class="lead">sessionName : ${sessionName}</p>
			<p class="lead">actionName : ${actionName}</p>
			<p class="lead">sessionScope.sessionId : ${sessionScope.sessionId}</p>
			<c:set var="sId" value="${sessionScope.sessionId}"></c:set>
			<p class="lead">sId : ${sId}</p>
		</div>
		<!-- end of test message -->

	</div>
	<!-- /.container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="dist/js/bootstrap.min.js"></script>
</body>
</html>