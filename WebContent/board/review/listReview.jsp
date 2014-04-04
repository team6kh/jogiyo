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

		<div class="common-template">
			<h2>Review 게시판입니다</h2>


			<!-- 식당 확인: 임시조건문, 후에 필요 없어지면 삭제 -->
			<c:if test="${review_rest != 'test_Rest' }">
				<div>이 식당이 아닌뎁쇼??</div>
			</c:if>
			<c:if test="${review_rest == 'test_Rest' }">

				<!--  리뷰글이 없는 경우 -->
				<c:if test="${totalCount == '0'}">
					<div>등록된 글이 없습니다.</div>
				</c:if>
				<!--  리뷰글이 있는 경우 -->
				<c:if test="${totalCount !='0' }">

					<div class="col-md-12 well">
						<table class="table table-striped table-forum">
							<thead>
								<tr>
									<th class="text-center" style="width: 100px;">작성자</th>
									<th class="text-center" style="width: 200px;">작성일</th>
									<th class="text-center" colspan="2">내용</th>
									<th class="text-center" style="width: 100px;">별점</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="reviewDTO" items="${reviewRes}">
									<tr>
										<td class="text-center">${reviewDTO.review_writer}</td>
										<td class="text-center"><fmt:formatDate
												value="${reviewDTO.review_reg_date}" type="date" /></td>
										<!-- 별점 부분 : 나중에 이미지로 바꾸어야.. -->
										<td class="text-center">${reviewDTO.review_content}</td>
										<!-- 첨부파일 나중에 구현 : 일단 값 출력만 확인  -->
										<td class="text-center">${reviewDTO.review_file}</td>
										<td class="text-center">${reviewDTO.review_rating}</td>
									</tr>
								</c:forEach>
								<!--  반복문 종료 -->
							</tbody>
						</table>
					</div>
					<div class="text-center">
						<ul class="pagination pagination-sm">
							<s:property value="pagingHtml" escape="false" />
						</ul>
					</div>
					<div class="pull-right">
						<a href="insertReviewForm.action" class="btn btn-primary">글쓰기</a>
					</div>

				</c:if>
				<!--  리뷰 글 개수 조건문 종료 -->
			</c:if>
			<!-- 식당 확인 조건문 종료  -->

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
