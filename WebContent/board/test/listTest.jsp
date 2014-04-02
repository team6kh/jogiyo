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

	<!-- container -->
	<div class="container">

		<!-- test board pretty -->
		<div class="col-md-12">
			<h3>테스트 게시판</h3>
		</div>

		<div class="well col-md-12">
			<table class="table table-striped table-forum">
				<thead>
					<tr>
						<th class="text-center" colspan="2">제목</th>
						<th class="text-center" style="width: 200px;">작성자</th>
						<th class="text-center" style="width: 100px;">작성일</th>
						<th class="text-center" style="width: 100px;">조회</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="list" status="stat">
						<s:url id="viewURL" action="readTest">
							<s:param name="test_num">
								<s:property value="test_num" />
							</s:param>
							<s:param name="currentPage">
								<s:property value="currentPage" />
							</s:param>
						</s:url>
						<tr>
							<td colspan="2"><s:a href="%{viewURL}">
									<s:property value="test_subject" />
								</s:a></td>
							<td class="text-center"><s:property value="test_writer_name" /></td>
							<td class="text-center"><s:property value="test_reg_date" /></td>
							<td class="text-center"><s:property value="test_readcount" /></td>
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
				</ul>
			</div>

			<div class="pull-right">
				<a
					href="insertTestForm.action?currentPage=<s:property value="currentPage"/>"
					class="btn btn-primary">글쓰기</a>
			</div>

		</div>
		<!-- end of test board pretty -->

		<!-- test message -->
		<div class="test-template">
			<h1>test template</h1>
			<p class="lead">당신은 ${sessionType} 입니다.</p>
			<p class="lead">${sessionName} 님 환영합니다.</p>
			<p class="lead">sessionType : ${sessionType}</p>
			<p class="lead">sessionId : ${sessionId}</p>
			<p class="lead">sessionPw : ${sessionPw}</p>
			<p class="lead">sessionName : ${sessionName}</p>
			<p class="lead">actionName : ${actionName}</p>
			<p class="lead">sessionScope.sessionId : ${sessionScope.sessionId}</p>
			<c:set var="sId" value="${sessionScope.sessionId}"></c:set>
			<p class="lead">sId : ${sId}</p>
		</div>
		<!-- end of test message -->

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
