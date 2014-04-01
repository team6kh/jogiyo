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
<link href="board/test/test-template.css" rel="stylesheet">

</head>

<body>

	<!-- header -->
	<%@ include file="/inc/header.jsp"%>
	<!-- end of header -->

	<!-- container -->
	<div class="container">

		<!-- test form pretty -->
		<div class="col-md-12">
			<h3>테스트 게시판 > 글쓰기</h3>
		</div>

		<div class="well col-md-12">
			<s:if test="resultClass == NULL">
				<form action="createTest.action" method="post"
					enctype="multipart/form-data" onsubmit="return validation();">
			</s:if>
			<s:else>
				<form action="updateTest.action" method="post"
					enctype="multipart/form-data">
					<s:hidden name="no" value="%{resultClass.no}" />
					<s:hidden name="currentPage" value="%{currentPage}" />
			</s:else>
			<div class="form-group">
				<label for="subject">제목</label> <input type="text"
					class="form-control" id="subject" name="subject" placeholder="제목">
			</div>
			<div class="form-group">
				<label for="name">작성자</label> <input type="text"
					class="form-control" id="name" name="name" placeholder="작성자">
			</div>
			<div class="form-group">
				<label for="password">비밀번호</label> <input type="text"
					class="form-control" id="password" name="password"
					placeholder="비밀번호">
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea class="form-control" rows="3" id="content" name="content"></textarea>
			</div>

			<div class="pull-right">				
				<button type="submit" class="btn btn-primary">글쓰기</button>
				<a href="listTest.action?currentPage=<s:property value="currentPage"/>"
				 class="btn btn-default">목록</a>
			</div>

		</div>
		<!-- end of test board pretty -->

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
