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
<link href="common/common-template.css" rel="stylesheet">

</head>

<body>

	<!-- header -->
	<%@ include file="/common/header.jsp"%>
	<!-- end of header -->

	<!-- container -->
	<div class="container">

		<!-- test form pretty -->
		<div class="col-md-12">
			<h3>글쓰기</h3>
		</div>
		<c:set var="actionName"/>
		<c:set var="textName"/>
		<c:if test="${resultClass.qna_num ne 0}">
			<c:set var="actionName" value="updateQna.action"/>
			<c:set var="textName" value="수정"/>
		</c:if>
		<c:if test="${resultClass.qna_num eq 0}">
			<c:set var="actionName" value="insertQna.action"/>
			<c:set var="textName" value="등록"/>
		</c:if>
		<div class="col-md-12 well">
		<form action="${actionName }" method="post" enctype="multipart/form-data">
		<input type="hidden" name="qna_num" value="${resultClass.qna_num }"/>
	
			<div class="form-group">
				<label for="qna_subject">제목</label>
				<input type="text" class="form-control" name="qna_subject" placeholder="제목" value="${resultClass.qna_subject }" required>
			</div>
			<div class="form-group">
				<label for="qna_content">글내용</label>
				<textarea class="form-control" rows="3" id="qna_content" name="qna_content" placeholder="글내용" required>${resultClass.qna_content }</textarea>
			</div>

			<div class="pull-right">
				<button type="submit" class="btn btn-primary">${textName }</button>
				<a href="listQna.action" class="btn btn-default">글목록</a>
			</div>
		</form>
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
