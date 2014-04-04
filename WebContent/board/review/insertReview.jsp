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

		<div class="common-template">
			<!-- 리뷰 쓰기 폼(review)  -->
			<form method="post" action="insertReviewPro.action"
				enctype="multipart/form-data">
				<!--  별점(review_rating) : radio 타입으로  -->
				<div>
					<label>별점</label> <input type="radio" name="review_rating"
						value="1" /> 1점 <input type="radio" name="review_rating"
						value="2" /> 2점 <input type="radio" name="review_rating"
						value="3" /> 3점 <input type="radio" name="review_rating"
						value="4" /> 4점 <input type="radio" name="review_rating"
						value="5" /> 5점
				</div>
				<!--  리뷰 content -->
				<div>
					<textarea name="review_content" rows="5" cols="50"></textarea>
				</div>
				<!--  이미지 파일 첨부  -->
				<div class="common-template">
					<input id="review_file_element" type="file" name="review_files">
					<input id="review_file_element" type="file" name="review_files">
				</div>
				<!-- 리뷰 작성 완료 버튼  -->
				<div>
					<input type="submit" value="리뷰 등록" />
				</div>
				<!-- 같이 보내줘야 할 파라미터 : 판매자 / 구매자 정보 -->
				<input type="hidden" name="review_rest" value="test_Rest" /> <input
					type="hidden" name="review_writer" value="test_Customer " />


			</form>

		</div>

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
