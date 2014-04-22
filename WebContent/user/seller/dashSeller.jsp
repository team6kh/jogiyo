<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ page isELIgnored="false" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<link href="common/common-template.css" rel="stylesheet">
<link href="user/common/dashboard.css" rel="stylesheet">

</head>

<body>

	<!-- header -->
	<%@ include file="/common/header.jsp"%>
	<!-- END of header -->

	<!-- container -->
	<div class="container">
		<div class="col-md-12">
			<!--  상단 탭 -->
			<ul class="nav nav-tabs" style="margin:20px">
			  <li><a href="#home" data-toggle="tab">홈</a></li>
			  <li><a href="#insertRest" data-toggle="tab">상품 등록</a></li>
			</ul>
			<!--  상단 탭 끝 -->
			
			<!--  탭 컨텐츠  -->
			<div class="col-md-12 tab-content">
				<!--  홈 탭 컨텐츠 -->
			 	<div class="tab-pane active" id="home"> 
			 		<div class="col-md-12">
			 				차트?
			 		</div>
			 		<div class="col-md-12">
			 		
			 			<div class="col-md-6"> 전체</div>
			 			<div class="col-md-6"> 
			 				<table class="table table-bordered table-hover">
			 					<tr>
			 						<th> 메뉴명 </th>
			 						<th> 매출 건수 </th>
			 						<th> 매출 금액 </th>
			 					</tr>
			 					<c:forEach var="salesDTO" items="${salesRes }">
			 					<tr>
			 						<td>${salesDTO.paid_restopt_subject}</td>
			 						<td>${salesDTO.paid_sales_volume }</td>
			 						<td>${salesDTO.paid_sales_price }</td>
			 					</tr>	
			 					</c:forEach>	
			 				</table>
			 				
			 			</div>
			 		</div>
			 	</div>
			  	
			  	<!-- 상품 등록 컨텐츠 -->
			  	<div class="tab-pane" id="insertRest">상품 등록</div>
			</div>
			<!--  탭 컨텐츠 끝  -->
		</div>
	</div>
	
	
	
 
<script>
  $(function () {
    $('#myTab a:last').tab('show')
  })
</script>
	<!-- /.container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="dist/js/bootstrap.min.js"></script>
</body>
</html>