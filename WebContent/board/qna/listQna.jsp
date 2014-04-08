<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
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
	<%@ include file="/common/header.jsp"%>
	<!-- container -->
	<div class="container">

		<!-- test board pretty -->
		<div class="col-md-12">
			<h3>글목록</h3>
		</div>

		<div class="col-md-12 well">
			<table class="table table-striped table-forum">
				<thead>
					<tr>
						<th class="text-center" style="width: 100px;">번호</th>
						<th class="text-center" style="width: 100px;" >제목</th>
						<th class="text-center" style="width: 100px;">작성일</th>
						<th class="text-center" style="width: 100px;">조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${list }">
						<tr>
							<td class="text-center">
								<c:out value="${list.qna_num }" />
							</td>
							<td class="text-center">
								<a href="detailQna.action?qna_num=${list.qna_num }">
								<c:out value="${list.qna_subject }" />
								</a>
							</td>
							<td class="text-center"><c:out value="${list.qna_reg_date }" /></td>
							<td class="text-center"><c:out value="${list.qna_readcount }" /></td>
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
					<s:property value="pagingHtml" escape="false" />
				</ul>
			</div>

			<div class="pull-right">
				<a href="insertQnaForm.action" class="btn btn-primary">글쓰기</a>
			</div>

		</div>
		<!-- end of test board pretty -->

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