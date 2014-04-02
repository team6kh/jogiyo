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
			<c:if test="${resultClass.test_subject eq null}">
				<form action="insertTest.action" method="post" enctype="multipart/form-data" onsubmit="return validation();">
			</c:if>
			<c:if test="${resultClass.test_subject ne null}">
				<form action="updateTest.action" method="post" enctype="multipart/form-data">
					<input type="hidden" name="test_num" value="%{test_num}" />
					<input type="hidden" name="currentPage" value="%{currentPage}" />
			</c:if>
			<div class="form-group">
				<label for="test_subject">제목</label>
				<input type="text" class="form-control" name="test_subject" placeholder="제목" value="${resultClass.test_subject}">
			</div>
			<div class="form-group">
				<label for="test_writer_name">작성자</label>
				<c:if test="${resultClass.test_writer_name eq null}">
					<input type="text" class="form-control" name="test_writer_name" placeholder="작성자">
				</c:if>
				<c:if test="${resultClass.test_writer_name ne null}">
					<input type="text" class="form-control" value="${resultClass.test_writer_name}" disabled>
					<input type="hidden" name="test_writer_name" value="${resultClass.test_writer_name}">
				</c:if>
			</div>
			<c:if test="${resultClass.test_writer_pw eq null}">
				<div class="form-group">
					<label for="test_writer_pw">비밀번호</label>
					<input type="password" class="form-control" name="test_writer_pw" placeholder="비밀번호" value="${resultClass.test_writer_pw}">
				</div>
			</c:if>
			<c:if test="${resultClass.test_writer_pw ne null}">
				<input type="hidden" name="test_writer_pw" value="${resultClass.test_writer_pw}">
			</c:if>
			<div class="form-group">
				<label for="test_content">내용</label>
				<textarea class="form-control" rows="3" id="test_content" name="test_content">${resultClass.test_content}</textarea>
			</div>

			<div class="pull-right">
				<button type="submit" class="btn btn-primary">글쓰기</button>
				<a href="listTest.action?currentPage=<s:property value="currentPage"/>" class="btn btn-default">목록</a>
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
