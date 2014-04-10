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


<body onload="ratingck()">

	<!-- header -->
	<%@ include file="/common/header.jsp"%>
	<!-- end of header -->

	<!-- container -->
	<div class="container">
		<div class="common-template">
			<!-- 리뷰 수정 폼(review)  -->
			<form name="updateReviewForm" method="post"
				action="updateReviewPro.action?ccp=${ccp}&review_num=${review_num}"
				enctype="multipart/form-data">
				<div class="col-md-12 well">
					<!--  별점(review_rating) : radio 타입으로  -->
					<table class="table table-striped table-forum">

						<tr>
							<th>별점 ${reviewDTO.review_rating }</th>
							<td class="text-center"><input type="radio"
								name="review_rating" value="1" /> 1점 <input type="radio"
								name="review_rating" value="2" /> 2점 <input type="radio"
								name="review_rating" value="3" /> 3점 <input type="radio"
								name="review_rating" value="4" /> 4점 <input type="radio"
								name="review_rating" value="5" /> 5점<input type="hidden"
								name="rating_point" value="${reviewDTO.review_rating}" /></td>

						</tr>
						<!--  리뷰 content -->
						<tr>
							<td class="text-center" colspan="2"><textarea
									name="review_content" rows="5" cols="50">${reviewDTO.review_content}</textarea></td>
						</tr>
						<!--  이미지 파일 첨부  -->
						<tr>
							<td class="text-center" colspan="2">이미지파일을 첨부하시면 기존에 첨부하신
								파일은 삭제됩니다. <br /> <input id="review_file_element" type="file"
								name="review_files"> <input id="review_file_element"
								type="file" name="review_files">
							</td>
						</tr>
						<!-- 리뷰 작성 완료 버튼  -->
						<tr>
							<td class="text-center" colspan="2"><input type="button"
								value="취   소" onclick="javascript:history.go(-1)"> <input
								type="submit" value="수정 완료" /></td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</div>

	<!--  해당글의 별점과 일치하는 라디오박스에 체크 -->
	<script type="text/javascript">
		function ratingck() {
			var rating_point = "${reviewDTO.review_rating}";
			var review_rating = document.updateReviewForm.review_rating;
			for (var i = 0; i < review_rating.length; i++) {
				if (review_rating[i].value == rating_point) {
					review_rating[i].checked = true;
				}
			}
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
