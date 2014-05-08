<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	
<html>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="author" content="">
<!-- bootstrap : 렌더링과 확대/축소를 위해, viewport 메타태그 추가 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- shortcut icon을 통한 home 이동 설정 --> 
<link rel="shortcut icon" href="assets/ico/jogiyo.png">
<!-- bootstrap core CSS -->
<link rel="stylesheet" href="dist/css/bootstrap.min.css">

<!-- script : jQuery & bootstrap jQuery -->
<script type="text/javascript" src="assets/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="dist/js/bootstrap.min.js"></script>

<!-- Custom styles for this template -->
<link href="jogiyo.css" rel="stylesheet">
<link href="common/common-template.css" rel="stylesheet">

<!-- JAVA Script 
Login check. 회원 유형(buyer,seller)에 따른 사용 제한, input area check, search 연동
-->

<title>JOGIYO 상품등록</title>

</head>

<body>
	<!-- 1. header 	
	=========================================================================-->
	<%@ include file="/common/header.jsp"%>
	<!-- end of header -->

	<!-- 2. start container
	 =========================================================================-->
	<div class="container">	
	
	<!-- 2-1. 메뉴타이틀 -->
		<div class="col-md-12">
			<h3>상품등록</h3>
		</div>
		
	<!-- 2-2. Forms 설정 및 본문  -->
	<form class="form-inline" 
			name="inputForm" id="inputForm" method="post">
	
	<!-- category  -->
	<%-- <s:action namespace="/" name="actionName" executeResult="true"/>	카테고리 결과만 가져오기 --%>
	<%@ include file="/good/category.jsp"%>
	<!-- /.분류설정 -->	
		
		<!-- 상품내용 입력폼 -->
		
		<div class="row">
		
			<div class="col-md-4" >
			<img src="..." alt="상품이미지위치" class="img-thumbnail" style="width: 350px; height: 350px">
			</div>
			
		  	<div class="col-md-8" name="insertGoodInfo">		  	
		  		<table class="table">
					<tr>
						<th>상품분류코드 <!-- 카테고리 선택시 자동 로드 --></th>
						<td>
	 						<input class="form-control input-sm" 
	 								type="text" name="good_name" />
	   					</td>
					</tr>
					<tr>
						<th style="width: 100px;">상품명</th>
						<td>
						<input class="form-control input-sm" 
	 								type="text" name="good_name" />
						</td>
					</tr>
					<tr>
						<th style="width: 100px;">판매가</th>
						<td>
						<input class="form-control input-sm" 
	 								type="text" name="good_name" />원
						</td>
					</tr>
					<tr>
						<th style="width: 100px;">제조사</th>
						<td><input class="form-control input-sm" style="width: 50%"
	 								type="text" name="good_name" /></td>
					</tr>
					<tr>
						<th style="width: 100px;">원산지</th>
						<td><input class="form-control input-sm"  style="width: 100%"
	 								type="text" name="good_name" /></td>
					</tr>
					<tr>
						<th style="width: 100px;">배송정보</th>
						<td>
						<textarea class="form-control" rows="3" style="width: 100%; margin-bottom: 5px"></textarea>
						<select class="form-control input-sm" name="shp_fee_type">
						<option>주문시결제(선결제)</option>
						<option>상품수령시결제(착불)</option>
						</select>
						</td>
					</tr>
					<tr>
						<th style="width: 100px;">보관유형</th>
						<td>
						<select class="form-control input-sm" name="methodOfKeeping">
						<option>상온제품</option>
						<option>냉장제품</option>
						<option>냉동제품</option>
						</select>
						</td>
					</tr>
			</table>
		  	</div>
		  
		</div>	
		
		<div class="col-md-12 well">
		<labal style="font-weight:bold">제품상세설명</labal>
		<textarea class="form-control" rows="15" style="width: 100%"></textarea>
		</div>
		<!-- /.상품내용 입력폼 -->
		
	</form><!-- /.form -->
		
	</div> <!-- end container --> 

	
</body>
</html>

