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
								<tr>
									<td colspan="4" class="text-center">${reviewDTO.review_content}</td>
								</tr>
								<!-- 첨부파일을 위한 반복문  -->
								<c:if test="${reviewDTO.review_file !=null }">
									<tr>
										<td colspan="4" class="text-center"><c:forEach
												var="review_file_path" items="${File_Path_List}">
												<img src="${review_file_path}" width="300px" height="400px">
											</c:forEach> <!-- 첨부파일 반복문 끝  --></td>
									</tr>
								</c:if>
							</c:forEach>
							<!--  리뷰글 목록 반복문 종료 -->
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
