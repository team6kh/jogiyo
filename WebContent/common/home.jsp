<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page isELIgnored="false" %> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="huks">
<link rel="shortcut icon" href="assets/ico/jogiyo.png">

<title>JOGIYO</title>

<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="jogiyo.css" rel="stylesheet">
<link href="common/common-template.css" rel="stylesheet">
<link href="common/home.css" rel="stylesheet">

</head>

<body>

	<%@ include file="/common/header.jsp"%>

	<div class="container">

		<!-- Home Dashboard -->
		<div class="dash-home"><!-- CSS 미지정 -->

			<div class="jumbotron">
				<h1>안녕하세요?</h1>
				<p>조기요에는 ${countRestopt}개의 메뉴와 ${countBuyer+countSeller}명의 회원, ${countPaid}번의 결재, ${countReview+countRecipe+countNotice+countQna}개의 글이 있습니다.</p>
				<p><a class="btn btn-default btn-lg" role="button">지금 가입하세요!</a></p>
			</div>

		</div>
		<!-- /Home Dashboard -->

	</div>
	<!-- /.container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="dist/js/bootstrap.min.js"></script>
</body>
</html>