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

<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.min.css" rel="stylesheet">

<!-- 별점 관련 js & css -->
<script type="text/javascript" src="assets/img/review/js/jquery.js"></script>
<script type="text/javascript" src="assets/img/review/js/jquery.rating.js"></script>
<link rel="stylesheet" href="assets/img/review/css/jquery.rating.css" />
<!--  파일업로드 관련 js -->
<script type="text/javascript" src="assets/img/review/js/jquery.form.js"></script>
<script type="text/javascript" src="assets/img/review/js/jquery.MultiFile.js"></script>
<title>JOGIYO</title>

</head>

<body>

	<!-- 리뷰 수정 폼(review) -->
	<form method="post" name="updateReviewForm" id="updateReviewForm" action="updateReviewPro.action" enctype="multipart/form-data" >

		<input type="hidden" id="rest_num" name="rest_num" value="${rest_num}" />
		<input type="hidden" id="review_rest_currentPage"
			name="review_rest_currentPage" value="${review_rest_currentPage}" />
		<input type="hidden" id="ccp" name="ccp" value="${ccp}" /> <input
			type="hidden" id="review_num" name="review_num" value="${review_num}" />

		<!-- 별점(review_rating) : radio 타입으로 -->

				<div class="form-group" style="margin: 10px">
					<br/>
					<font size=3 color="green"><b>후기 수정 하기</b></font>
				</div>
				<div class="form-group text-center">
					<table>
					<tr>
						<td><label>별점 &nbsp;&nbsp;&nbsp;</label></td>
						<td>
							<c:forEach var="cnt" begin="1" end="5">
								<c:if test="${cnt eq reviewDTO.review_rating}">
									<input type="radio" class="star" name="review_rating" value="${cnt}" checked="checked" />
								</c:if>
								<c:if test="${cnt ne reviewDTO.review_rating}">
									<input type="radio" class="star" name="review_rating" value="${cnt}" />
								</c:if>					
		                    </c:forEach>
		                 </td>
                    </table>
                 </div>        
      
			<!-- 리뷰 content -->
				<div class="form-group text-center">
					<textarea id="review_content" name="review_content" rows="3" cols="50">${reviewDTO.review_content}</textarea>
				</div>

			<!-- 이미지 파일 첨부 -->
				<div class="form-group text-center">
				 	<label> 새로 파일을 첨부하시면 기존에 첨부하신 파일은 삭제됩니다. </label>
                    <input type="file" name="review_files" class="multi" maxlength="2" accept="gif|jpg|png|jpeg" multiple="multiple"/>        
				</div>


			<!-- 리뷰 작성 완료 버튼 -->
				<div class="form-group text-right">
					<button type="submit" class="btn btn-primary" >수정 완료</button>
					<button type="button" class="btn btn-default" onclick="javascript:self.close()">취소</button> 					
				</div>
	</form>


	<!-- /.container -->

	<!-- Bootstrap core JavaScript
================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="dist/js/bootstrap.min.js"></script>

</body>
</html>