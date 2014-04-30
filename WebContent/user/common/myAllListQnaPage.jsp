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
<link href="jogiyo.css" rel="stylesheet">
<link href="user/common/dashboard.css" rel="stylesheet">
<link href="common/common-template.css" rel="stylesheet">

<!-- 스크립트 -->
<script type="text/javascript">
	function searchtype() {
		var sel = document.getElementById("recipe_search_target").value;
		var frm = document.getElementById("recipe_search_input");

		if ((sel == "recipe_time" || sel == "recipe_price")) {
			frm.innerHTML = "<input type=text name=key1>~<input type=text name=key2><input type=submit value=검색>";
		} else if ((sel == "recipe_subject,content" || sel == "recipe_foodkind")) {
			frm.innerHTML = "<input type=text name=key1><input type=submit value=검색>";
		} else if ((sel == "recipe_searchselect")) {
			frm.innerHTML = "<input type=submit value=검색>";
		}

	}

	function detailsearch() {
		var sel = document.getElementById("recipe_search_target").value;

		if (sel == "recipe_detailsearch") {
			document.getElementById('detailsearch').style.display = "block";
		} else if (sel == "null") {
			document.getElementById('detailsearch').style.display = "none";
		}
	}

	function recipe_readcountarray() {
		//var click = false;
		//if(click==false){

		//click = true;
		document.location.href = 'readcountRecipeDesc.action';
		//}if(click==true){
		//	click = false;
		//	window.location.href='readcountRecipeAsc.action';
		//}
	}

	function recipe_timearray() {
		document.location.href = 'timeRecipeDesc.action';
	}

	function recipe_pricearray() {
		document.location.href = 'priceRecipeDesc.action';
	}
</script>
<!-- 스크립트 끝 -->

</head>

<body>

	<!-- header -->
	<%@ include file="/common/header.jsp"%>
	<!-- /.header -->

	<!-- container -->
	<div class="container-fluid">
		<!-- row -->
		<div class="row">

			<!-- sidebar -->
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li><a href="readUser.action?user_type=${session_type}&user_id=${session_id}">회원정보</a></li>
					<li><a href="dashBuyer.action?session_id=${session_id}">구매목록</a></li>
					<li class="active"><a href="myAllListPage.action?session_id=${session_id}">내가 작성한 글(recipe)</a></li>
					<li class="active"><a href="myAllListPageQna.action?session_id=${session_id}">내가 작성한 글(qna)</a></li>
				</ul>
			</div>
			<!-- /.sidebar -->

			<!-- main -->
			
						

				<div class="col-md-12">
					<h3>My Q&A 목록</h3>
				</div>

				<!-- searchForm -->
				<form name="searchForm" id="searchForm">
					<select name="qna_category" id="qna_category"
						onchange="fn_search();">
						<option value="">전체</option>
						<option value="01"
							<c:if test="${qna_category eq '01'}">selected</c:if>>회원가입</option>
						<option value="02"
							<c:if test="${qna_category eq '02'}">selected</c:if>>바로결제</option>
						<option value="03"
							<c:if test="${qna_category eq '03'}">selected</c:if>>리뷰</option>
						<option value="04"
							<c:if test="${qna_category eq '04'}">selected</c:if>>이용문의</option>
						<option value="05"
							<c:if test="${qna_category eq '05'}">selected</c:if>>광고문의</option>
						<option value="06"
							<c:if test="${qna_category eq '06'}">selected</c:if>>기타</option>
					</select>
				</form>
				<!-- /.searchForm -->

				<!-- col-md-12 well -->
				<div class="col-md-12 well">
					<table class="table table-striped table-forum">
						<thead>
							<tr>
								<th class="text-center" style="width: 100px;">번호</th>
								<th class="text-center" style="width: 100px;">카테고리</th>
								<th class="text-center" style="width: 100px;">제목</th>
								<th class="text-center" style="width: 100px;">작성일</th>
								<th class="text-center" style="width: 100px;">조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="list" items="${list }">
								<tr>
									<td class="text-center"><c:out value="${list.qna_num }" />
									</td>
									<td class="text-center"><c:choose>
											<c:when test="${list.qna_category eq '01' }">회원가입</c:when>
											<c:when test="${list.qna_category eq '02' }">바로결제</c:when>
											<c:when test="${list.qna_category eq '03' }">리뷰</c:when>
											<c:when test="${list.qna_category eq '04' }">이용문의</c:when>
											<c:when test="${list.qna_category eq '05' }">광고문의</c:when>
											<c:when test="${list.qna_category eq '06' }">기타</c:when>
											<c:otherwise>전체</c:otherwise>
										</c:choose></td>
									<td class="text-left"><a
										href="detailQna.action?qna_num=${list.qna_num }"> <c:out
												value="${list.qna_subject }" />
									</a></td>
									<td class="text-center"><c:out
											value="${list.qna_reg_date }" /></td>
									<td class="text-center"><c:out
											value="${list.qna_readcount }" /></td>
								</tr>
							</c:forEach>
							<c:if test="${empty list}">
								<tr>
									<td colspan="5">등록된 게시물이 없습니다.</td>
								</tr>
							</c:if>
						</tbody>
					</table>

					<div class="text-center">
						<ul class="pagination pagination-sm">
							<s:property value="qnapagingHtml" escape="false" />
						</ul>
					</div>

					<div class="pull-right">
						<a href="insertQnaForm.action" class="btn btn-primary">글쓰기</a>
					</div>

				</div>
				<!-- /.col-md-12 well -->

			</div>
			<!-- /.main -->

		</div>
		<!-- /.row -->

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