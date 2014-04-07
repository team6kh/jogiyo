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
			<h3>Review 게시판입니다</h3>


			<!-- 해당 식당에 구매내역이 있는 회원이라면 리뷰글 쓸 수 있도록  -->



			<!-- 식당 확인: 임시조건문, 후에 삭제 -->
			<!--  식당코드가 맞지 않는 경우 -->
			<c:if test="${review_rest != 'test_Rest' }">
				<div>이 식당이 아닌뎁쇼??</div>
			</c:if>
			<!-- 식당코드가 맞는 경우 -->
			<c:if test="${review_rest == 'test_Rest' }">

				<!--  리뷰글이 없는 경우 -->
				<c:if test="${totalCount == '0'}">
					<div class="col-md-12 well">등록된 글이 없습니다.</div>
				</c:if>
				<!--  리뷰글이 있는 경우 -->
				<c:if test="${totalCount !='0' }">

					<div class="col-md-12 well">
						<table class="table table-striped table-forum">
							<!--  리뷰글 목록 -->
							<c:forEach var="reviewDTO" items="${reviewRes}">
								<tr>
									<th class="text-center" style="width: 100px;">작성자</th>
									<td class="text-center">${reviewDTO.review_writer}</td>
									<th class="text-center" style="width: 200px;">작성일</th>
									<td class="text-center"><fmt:formatDate
											value="${reviewDTO.review_reg_date}" type="date" /></td>
								</tr>
								<th class="text-center" style="width: 100px;">별점</th>
								<td colspan="3">${reviewDTO.review_rating }</td>

								<!-- 첨부파일 관련 코드  -->
								<c:if test="${reviewDTO.review_file != null }">
									<tr>
										<td class="text-center" colspan="4"><c:forTokens
												var="reviewFileNames" items="${reviewDTO.review_file}"
												delims="' '">
												<c:forEach var="reviewFileName" items="${reviewFileNames}">
												<img src="${reviewFile_Path}${reviewFileName}" width="300px"><br/>
												</c:forEach>

											</c:forTokens></td>
									</tr>
								</c:if>
								<!-- 첨부파일 관련 코드 끝  -->
								<tr>
									<td colspan="4" class="text-center">${reviewDTO.review_content}</td>
								</tr>
							</c:forEach>
							<!--  리뷰글 목록 반복문 종료 -->

						</table>
					</div>
				</c:if>
				<!--  리뷰글이 있는지 확인하는 조건문 종료-->
			</c:if>
			<!--  식당코드가 맞는 경우의 조건문 종료 -->

			<!--  리뷰 글 목록 페이지 -->
			<div class="text-center">
				<ul class="pagination pagination-sm">
					<s:property value="review_pagingHtml" escape="false" />
				</ul>
			</div>
			<!--  리뷰글 목록 페이지 종료 -->


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
