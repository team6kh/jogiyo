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

		<!-- test form pretty -->
		<div class="col-md-12">
			<h3>테스트 게시판 > 읽기</h3>
		</div>
		
		<div class="well col-md-12">

			<table class="table table-striped table-forum">
				<thead>
					<tr>
						<th colspan="2">제목</th>
					</tr>
				</thead>
				<tbody>
					<!-- Post -->
					<tr>
						<td class="text-center">
							<span class="glyphicon glyphicon-user"></span> &nbsp; <strong>${resultClass.name}</strong></td>
						<td>등록일 : <em>${resultClass.regdate}</em></td>
					</tr>
					<tr>
						<td class="text-center" style="width: 12%;">
							<!-- 공백 -->
						</td>
						<td>
							<p>이 글의 비밀번호는 ${resultClass.password} 입니다. 밑에서부터는 글 내용입니다.</p>
							<p>${resultClass.content}</p>
						</td>
					</tr>
					<!-- end Post -->					

				</tbody>
			</table>
			
			<div class="pull-right">

				<!-- Button trigger modal : 수정 -->
				<button class="btn btn-default" data-toggle="modal"
					data-target="#checkModal">수정</button>

				<!-- Modal -->
				<div class="modal fade" id="checkModal" tabindex="-1" role="dialog"
					aria-labelledby="checkModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<form action="check.action">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="checkModalLabel">비밀번호를 입력하세요.</h4>
								</div>
								<div class="modal-body">
									<div class="form-group">
										<label for="password">비밀번호</label> <input type="password"
											class="form-control" name="password">
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">닫기</button>
									<input type="hidden" name="no" value="${resultClass.no}">
									<input type="hidden" name="currentPage" value="${currentPage}">
									<button type="submit" class="btn btn-primary">입력</button>
								</div>
							</form>
						</div>
					</div>
				</div>

				<!-- Button trigger modal : 삭제 -->
				<button class="btn btn-default" data-toggle="modal"
					data-target="#checkModal">삭제</button>

				<a
					href="listTest.action?currentPage=<s:property value="currentPage"/>"
					class="btn btn-default">목록</a>
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
