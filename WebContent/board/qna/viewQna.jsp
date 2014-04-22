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
			<h3>Q&A 게시판 > 상세 </h3>
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
		<form action="updateQnaForm.action" method="post" enctype="multipart/form-data">
		<input type="hidden" name="qna_num" value="${resultClass.qna_num }"/>
		<div class="form-group">
				<label for="qna_category"><font color="blue">카테고리:</label></font>
					<c:choose>
						<c:when test="${resultClass.qna_category eq '01' }">회원가입</c:when>
						<c:when test="${resultClass.qna_category eq '02' }">바로결제</c:when>
						<c:when test="${resultClass.qna_category eq '03' }">리뷰</c:when>
						<c:when test="${resultClass.qna_category eq '04' }">이용문의</c:when>
						<c:when test="${resultClass.qna_category eq '05' }">광고문의</c:when>
						<c:when test="${resultClass.qna_category eq '06' }">기타</c:when>
						<c:otherwise>전체</c:otherwise>
					</c:choose>
			</div>
			<div class="form-group">
				<label for="qna_subject"><font color="green">제목:</label></font>
				${resultClass.qna_subject }
			</div>
			<div class="form-group">
				<label for="qna_content"><font color="orange">내용:</label></font>
				${resultClass.qna_content }
			</div>

			<div class="pull-right">
				<button type="submit" class="btn btn-primary">수정하기</button>
				<a href="deleteQna.action?qna_num=${resultClass.qna_num }" class="btn btn-primary">삭제</a>
				<a href="listQna.action" class="btn btn-default">목록</a>
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