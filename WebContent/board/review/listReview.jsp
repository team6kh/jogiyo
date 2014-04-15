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

		<!--  리뷰 쓰기 권한에 관한 조건문 : 필요한 값 - 회원이 이 식당에서 주문한 적이 있는지 없는지에 대한 논리값 -->
		<!--  일단은 로그인을 하지 않으면 리뷰 쓰기 폼이 보이지 않도록 조건문 설정  -->

		<!-- 리뷰 쓰기 폼 시작  -->


		<div class="col-md-12 well">
			<c:if test="${!empty sessionScope.sessionId}">
				<div class="text-center">
					<button onclick="return reviewForm()">리뷰 글 쓰기</button>
				</div>
				<div class="text-center">
					<form name="insertReviewForm" method="post"
						action="insertReviewPro.action" enctype="multipart/form-data"
						style="display: none">
						<table class="table table-striped table-forum">
							<tr>
								<th>별점</th>
								<td class="text-center"><input type="radio"
									name="review_rating" value="1" /> 1점 <input type="radio"
									name="review_rating" value="2" />2점 <input type="radio"
									name="review_rating" value="3" />3점 <input type="radio"
									name="review_rating" value="4" />4점 <input type="radio"
									name="review_rating" value="5" />5점</td>
							</tr>
							<!--  리뷰 content -->
							<tr>
								<td class="text-center" colspan="2"><textarea
										name="review_content" rows="5" cols="50"></textarea></td>
							</tr>
							<!--  이미지 파일 첨부 : 첨부 개수 제한/ 용량 제한 필요  -->
							<tr>
								<td class="text-center" colspan="2"><input
									id="review_file_element" type="file" name="review_files"
									multiple="multiple" />
							</tr>
							<!-- 리뷰 작성 완료 버튼  -->
							<tr>
								<th class="text-center" colspan="2"><input type="submit"
									value="리뷰 등록" /></th>
							</tr>
						</table>
						<!-- 보내줘야 할 파라미터 : 식당코드(식당 테이블) / 구매자(= 회원 = 글 작성자) 정보 -->
						<input type="hidden" name="review_rest" value="test_Rest" /> <input
							type="hidden" name="review_writer"
							value="${sessionScope.sessionId }" />
					</form>
				</div>
			</c:if>
		</div>
		<!-- 리뷰 쓰기 폼 끝  -->

		<!-- 리뷰 글 보기 시작 -->
		<c:forEach var="reviewDTO" items="${reviewRes}">
			<div class="text-center">
				<table class="table table-striped table-forum">

					<!--  리뷰글 작성자 & 작성일 -->
					<tr>
						<td class="text-center"><label> 작성자 </label>
							&nbsp;&nbsp;&nbsp; ${reviewDTO.review_writer} <input
							type="hidden" name="review_num" value="${reviewDTO.review_num}" /></td>
						<td class="text-center"><label>작성일</label> &nbsp;&nbsp;&nbsp;
							<fmt:formatDate value="${reviewDTO.review_reg_date}"
								pattern="yyyy-MM-dd" /></td>
					</tr>
					<!-- 해당글 작성자일 경우 수정/삭제 버튼  -->
					<!-- 임시값 "test_Customer" sessionId 값으로 교체 -->
					<c:if test="${reviewDTO.review_writer == sessionScope.sessionId}">
						<tr>
							<td class="text-right" colspan="2"><input type="button"
								value="수정"
								onclick="javascript:open('updateReviewForm.action?ccp=${ccp}&review_num=${reviewDTO.review_num}','confirm','toolbar=no, location=no, status= no, menubar=no, scrollbars=no, resizeable=no, width=500, height=500')">
								<input type="button" value="삭제"
								onclick="javascript:open('deleteReviewForm.action?ccp=${ccp}&review_num=${reviewDTO.review_num}','confirm','toolbar=no, location=no, status= no, menubar=no, scrollbars=no, resizeable=no, width=300, height=200')" /></td>
						</tr>
					</c:if>
					<!--  리뷰글 별점 -->

					<tr>
						<td class="text-center" colspan="2"><label>별점</label>
							&nbsp;&nbsp;&nbsp; <c:forEach begin="1"
								end="${reviewDTO.review_rating}">
								<img src="assets/img/review/ratingimage/ico.png" width="25px"
									height="25px">
							</c:forEach></td>
					</tr>
					<!-- 리뷰글 내용 -->
					<tr>
						<td class="text-center" colspan="2">${reviewDTO.review_content }</td>
					</tr>
					<!-- 리뷰글 첨부사진 : 첨부사진이 있을 때만 보이도록 -->
					<c:if test="${!empty reviewDTO.review_file}">
						<c:forTokens var="reviewFileNames"
							items="${reviewDTO.review_file }" delims="' '">
							<tr>
								<td class="text-center" colspan="2"><c:forEach
										var="reviewFileName" items="${reviewFileNames}">
										<img src="${reviewFile_Path}${reviewFileName}" width="400px">
										<br />
									</c:forEach></td>
							</tr>
						</c:forTokens>
					</c:if>

				</table>
			</div>
		</c:forEach>
		<!--  리뷰 글 페이지 -->
		<div class="text-center">
			<ul class="pagination pagination-sm">
				<s:property value="pagingHtml" escape="false" />
			</ul>
		</div>
	</div>


	<!-- 리뷰 글 보기 끝 -->



	<script type="text/javascript">
	<!-- 리뷰 글 쓰기 폼 접기/펴기 -->
		function reviewForm() {

			var reviewform = document.insertReviewForm;
			if (reviewform.style.display == "none") {
				reviewform.style.display = "block";
			} else {
				reviewform.style.display = "none";
			}
		}

		<!--
		function reviewUpdate() {

		}
	</script>


	<!-- /.container -->





	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="dist/js/bootstrap.min.js"></script>
</body>
</html>