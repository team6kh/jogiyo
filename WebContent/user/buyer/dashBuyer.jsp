<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="huks">
<link rel="shortcut icon" href="assets/ico/jogiyo.png">

<title>JOGIYO</title>

<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="jogiyo.css" rel="stylesheet">
<link href="user/common/dashboard.css" rel="stylesheet">

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
					<li><a
						href="readUser.action?user_type=${session_type}&user_id=${session_id}">회원정보</a></li>
					<li class="active"><a
						href="dashBuyer.action?session_id=${session_id}">구매목록</a></li>
					<li><a href="myAllListPage.action?session_id=${session_id}">내가
							작성한 글</a></li>
				</ul>
			</div>
			<!-- /.sidebar -->

			<!-- main -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<!-- 여기에 작성해주시면 됩니다. -->
				<div>
				
					<table>
						<tr>
							<td align="left"><select id="search_start_year"
								name="search_start_year">
									<c:forEach var="i" begin="2010" end="2014" step="1">
										<option value=${i}>${i}</option>
									</c:forEach>
							</select>년 <select id="search_start_mon" name="search_start_mon">
									<c:forEach var="i" begin="1" end="12" step="1">
										<option value=${i}>${i}</option>
									</c:forEach>
							</select>월 <select id="search_start_day" name="search_start_day">
									<c:forEach var="i" begin="1" end="31" step="1">
										<option value=${i}>${i}</option>
									</c:forEach>
							</select>일 ~ <select id="search_end_year" name="search_end_year">
									<c:forEach var="i" begin="2010" end="2014" step="1">
										<option value=${i}>${i}</option>
									</c:forEach>
							</select>년 <select id="search_end_mon" name="search_end_mon">
									<c:forEach var="i" begin="1" end="12" step="1">
										<option value=${i}>${i}</option>
									</c:forEach>
							</select>월 <select id="search_end_day" name="search_end_day">
									<c:forEach var="i" begin="1" end="31" step="1">
										<option value=${i}>${i}</option>
									</c:forEach>
							</select>일 <input type="button" value="조회하기" onClick="javascript:location.href='mylistCouponTime.action?search_start_year<s:property value="search_start_year" />?search_start_mon<s:property value="search_start_mon" />?search_start_day<s:property value="search_start_day" />?search_end_year<s:property value="search_end_year" />?search_end_mon<s:property value="search_end_mon" />?search_end_day<s:property value="search_end_day" />'" />
					</table>
					
				</div>
				<div>
					<table class="table table-stiped">
						<tr align="center" bgcolor="">
							<td><strong>번호</strong></td>
							<td><strong>매장</strong></td>
							<td><strong>품목</strong></td>
							<td><strong>가격</strong></td>
							<td><strong>품목이미지</strong></td>
							<td><strong>쿠폰</strong></td>
							<td><strong>사용유무</strong></td>
							<td><strong>구매자</strong></td>
							<td><strong>구매날짜</strong></td>
						</tr>
						
						<c:forEach var="list" items="${list}">
							<tr>
								<td>${list.paid_num} </td>
								<td>${list.paid_rest_subject} </td>
								<td>${list.paid_restopt_subject} </td>
								<td>${list.paid_restopt_priceplus} </td>
								<td>이미지 사진뜨는곳. </td>
								<td>${list.paid_cpn} </td>
								<td>${list.paid_cpn_used} </td>
								<td>${list.session_id} </td>
								<td>${list.paid_reg_date} </td>
							</tr>
						</c:forEach>

<%-- 
						<s:iterator value="list" status="stat">
							<s:url id="viewURL" action="readRecipe">
								<s:param name="paid_num">
									<s:property value="paid_num" />
								</s:param>
								<s:param name="currentPage">
									<s:property value="currentPage" />
								</s:param>
							</s:url>

							<tr bgcolor="#FFFFFF" align="center">
								<td><s:property value="list.paid_num" /></td>
								<td align="center">&nbsp;<s:a href="%{viewURL}"><s:property value="list.paid_rest_subject" /></s:a></td>
								<td align="center"><s:property value="list.paid_restopt_subject" /></td>
								<td align="center"><s:property value="list.paid_restopt_priceplus" /></td>
								<td align="center"><s:property value="list.paid_restopt_destfile1" /></td>
								<td align="center"><s:property value="list.paid_cpn" /></td>
								<td align="center"><s:property value="list.paid_cpn_used" /></td>
								<td align="center"><s:property value="list.session_id" /></td>
								<td align="center"><s:property value="list.paid_reg_date" /></td>
							</tr>
						</s:iterator> --%>

						<s:if test="list.size() <= 0">

							<tr bgcolor="#FFFFFF" align="center">
								<td colspan="10">등록된 게시물이 없습니다.</td>
							</tr>
							<tr bgcolor="#777777">
								<td height="1" colspan="10"></td>
							</tr>

						</s:if>
					</table>
				</div>
				<!-- /게시판 바디 -->

				<!-- 페이징 -->
				<div class="text-center">
					<ul class="pagination pagination-sm">
						<s:property value="pagingHtml" escape="false" />
					</ul>
				</div>
				<!-- /페이징 -->

				<!-- 버튼 -->
				<!-- /버튼 -->

			</div>

		</div>
		<!-- /.main -->
	</div>
	<!-- /.row	-->
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
