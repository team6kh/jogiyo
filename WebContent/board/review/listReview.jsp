<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page isELIgnored="false" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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


		<div class="col-md-12">
			<h3>review 게시판</h3>
		</div>

		<div class="col-md-12 well">

			<!--  리뷰 쓰기 권한에 관한 조건문 미구현 : 필요한 값 - 회원이 이 식당에서 주문한 적이 있는지 없는지에 대한 논리값 -->

			<!-- 리뷰 쓰기 폼 시작  -->
			<form name="insertReview" method="post"
				action="insertReviewPro.action" enctype="multipart/form-data">
				<table class="table table-striped table-forum">
					<tr>
						<th>별점</th>
						<td class="text-center"><input type="radio"
							name="review_rating" value="1" /> 1점 <input type="radio"
							name="review_rating" value="2" /> 2점 <input type="radio"
							name="review_rating" value="3" /> 3점 <input type="radio"
							name="review_rating" value="4" /> 4점 <input type="radio"
							name="review_rating" value="5" /> 5점</td>
					</tr>
					<!--  리뷰 content -->
					<tr>
						<td class="text-center" colspan="2"><textarea
								name="review_content" rows="5" cols="50"></textarea></td>
					</tr>
					<!--  이미지 파일 첨부(2개)  -->
					<tr>
						<td class="text-center" colspan="2"><input
							id="review_file_element" type="file" name="review_files">
							<input id="review_file_element" type="file" name="review_files"></td>
					</tr>
					<!-- 리뷰 작성 완료 버튼  -->
					<tr>
						<th class="text-center" colspan="2"><input type="submit"
							value="리뷰 등록" /></th>
					</tr>
				</table>
				<!-- 보내줘야 할 파라미터 : 식당코드(식당 테이블) / 구매자 정보(로그인 아이디 세션값 or 구매(결제) 테이블의 주문자 정보 -->
				<input type="hidden" name="review_rest" value="test_Rest" /> <input
					type="hidden" name="review_writer" value="test_Customer" />
			</form>
		</div>
		<!-- 리뷰 쓰기 폼 끝  -->

		<!-- 리뷰 글 보기 시작 -->
		<div class="col-md-12 well">
			<table class="table table-striped table-forum">
				<c:forEach var="reviewDTO" items="${reviewRes}">
					<!--  리뷰글 작성자 & 작성일 -->
					<tr>
						<td class="text-center">${reviewDTO.review_writer}</td>
						<td class="text-center"><fmt:formatDate
								value="${reviewDTO.review_reg_date}" pattern="yyyy-MM-dd" /></td>
					</tr>
					<!--  리뷰글 별점 : 후에 이미지로 대체해야 -->
					<tr>
						<td class="text-center" colspan="2">${reviewDTO.review_rating}</td>
					</tr>
					<!-- 리뷰글 내용 -->
					<tr>
						<td class="text-center" colspan="2">${reviewDTO.review_content }</td>
					</tr>
					<!-- 리뷰글 첨부사진 : 첨부사진이 있을 때만 보이도록 -->
					<c:if test="${!empty reviewDTO.review_file}">
						<c:forTokens var="reviewFileNames"
							items="${reviewDTO.review_file }" delims="' '">
							<c:forEach var="reviewFileName" items="${reviewFileNames}">
								<tr>
									<td class="text-center" colspan="2">
										${reviewFile_Path}${reviewFileName}<img
										src="${reviewFile_Path}${reviewFileName}" width="400px">
										<br />
									</td>
								</tr>
							</c:forEach>
						</c:forTokens>
					</c:if>

					<!--  리뷰글 수정/ 삭제  -->
					<!-- 작성자인지 확인하는 조건문 임시로 test_Customer  -->
					<c:if test="${reviewDTO.review_writer == 'test_Customer'}">
						<tr>
							<th class="text-center" colspan="2"><a
								href="updateReviewForm.action?ccp=${ccp}&review_num=${reviewDTO.review_num}">
									수정 </a> &nbsp;&nbsp; <a
								href="deleteReviewForm.action?ccp=${ccp}&review_num=${reviewDTO.review_num}">
									삭제 </a></th>
						</tr>
					</c:if>
				</c:forEach>
			</table>
			<!--  리뷰 글 페이지 -->
			<div class="text-center">
				<ul class="pagination pagination-sm">
					<s:property value="pagingHtml" escape="false" />
				</ul>
			</div>
		</div>
		<!-- 리뷰 글 보기 끝 -->



















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
