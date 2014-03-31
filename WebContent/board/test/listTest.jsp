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
<link href="board/test/test-template.css" rel="stylesheet">

</head>

<body>

	<!-- header -->
	<%@ include file="/inc/header.jsp"%>
	<!-- end of header -->

	<div class="container test-template">		

		<!-- test board pretty -->
		<div class="well">

			<table class="table table-striped table-forum">
				<thead>
					<tr>
						<th class="text-center" colspan="2">제목</th>
						<th class="text-center hidden-xs hidden-sm" style="width: 100px;">글쓴이</th>
						<th class="text-center hidden-xs hidden-sm" style="width: 200px;">날짜</th>
						<th class="text-center hidden-xs hidden-sm" style="width: 100px;">조회</th>
					</tr>
				</thead>
				<tbody>

					<!-- TR -->
					<tr>
						<td colspan="2"><a href=#>테스트 제목입니다.</a></td>
						<td>테스트</td>
						<td>14. 3. 31</td>
						<td>22</td>
					</tr>
					<!-- end TR -->

					<s:iterator value="list" status="stat">

						<s:url id="viewURL" action="readTestAction">
							<s:param name="no">
								<s:property value="no" />
							</s:param>
							<s:param name="currentPage">
								<s:property value="currentPage" />
							</s:param>
						</s:url>

						<tr>
							<td colspan="2"><s:a href="%{viewURL}">
									<s:property value="subject" />
								</s:a></td>
							<td><s:property value="name" /></td>
							<td><s:property value="regdate" /></td>
							<td><s:property value="readhit" /></td>
						</tr>

					</s:iterator>

					<s:if test="list.size() <= 0">

						<tr>
							<td colspan="5">등록된 게시물이 없습니다.</td>
						</tr>

					</s:if>

				</tbody>
			</table>

			<div class="text-center">
				<ul class="pagination pagination-sm">
					<s:property value="pagingHtml" escape="false" />
					<li>
				</ul>
			</div>
			
			<div class="text-right">
				<a href="createTestForm.action?currentPage=<s:property value="currentPage"/>" 
				 class="btn btn-primary">글쓰기</a>
			</div>			

		</div><!-- end of test board pretty -->
		
		<div>
			<h1>test template</h1>
			<p class="lead">currentActionName:${currentActionName}</p>
			<p class="lead">sessionBuyerId:${sessionBuyerId}</p>
			<p class="lead">sessionScope.sessionBuyerId:${sessionScope.sessionBuyerId}</p>
			<c:set var="sId" value="${sessionScope.sessionBuyerId}"></c:set>
			<p class="lead">sId:${sId}</p>
			<p class="lead">sessionBuyerName:"${sessionBuyerName}" 님 환영합니다.</p>
			<p class="lead">
				resultClass.buyer_name:
				<s:property value="resultClass.buyer_name" />
				님 환영합니다.
			</p>
		</div>
		<!-- end of test-template -->
		
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
