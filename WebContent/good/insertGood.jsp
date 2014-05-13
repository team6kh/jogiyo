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
		
	<!-- 2-2 category  -->

	<form class="form-inline" 
			name="categoryBox" id="categoryBox" method=post action=''>
		<div class="row" style="margin-bottom: 10px" >
		
			<div class="col-md-12">
			<select class="form-control input-sm" name="categoryBox" id="Depth1" onChange="selectSecond();">
				<option value=''>대분류선택</option>
				<option value='1'>쌀/과일/농수축산물</option>
				<option value='2'>차/음료</option>
				<option value='3'>라면/통조림/과자/조미료</option>
				<option value='4'>가공식품</option>
				<option value='5'>건강식품</option>
				<option value='6'>다이어트식품</option>
				<option value='7'>유아식</option>
			</select> 
			<select class="form-control input-sm"  name="categoryBox" id="Depth2" onChange="thirdOption();">
				<option value=''>중분류</option>
			</select> 
			<select class="form-control input-sm"  name="categoryBox" id="Depth3">
				<option value=''>소분류</option>
			</select>
			</div>
		
		</div>
	</form>
	
		<script id="selectOption"/>
		<script language="javascript">
			function secondOption() {
				selectOption.src = "category.js";
			}
			function thirdOption() {
				selectOption.src = "category.js";
			}
		</script>
	<!-- ./ category  -->
		
	<!-- 2-3. Forms 설정 및 본문  -->
	<form class="form-inline" 
			name="inputForm" id="inputForm" method="post">
	

		<!-- 상품내용 입력폼 -->
		
		<div class="row">
		
			<div class="col-md-4" >
			<img src="..." alt="상품이미지위치" class="img-thumbnail" style="width: 350px; height: 350px">
			<input class="form-control input-sm"
	 								type="text" name="good_file" />
			<input class="form-control input-sm"
					type="button" name="Find" />
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
						<th style="width: 100px;">수량</th>
						<td><input class="form-control input-sm" type="text"
							name="reg_amount" />개</td>
					</tr>
					<tr>
					<th style="width: 100px;">제조사</th>
						<td>
						<input class="form-control input-sm" style="width: 50%"
	 								type="text" name="good_name" />
						</td>
					</tr>
					<tr>
						<th style="width: 100px;">원산지</th>
						<td><input class="form-control input-sm"  style="width: 100%"
	 								type="text" name="good_name" /></td>
					</tr>
					<tr>
						<th style="width: 100px;">배송정보</th>
						<td>
						<textarea class="form-control" rows="2" style="width: 100%; margin-bottom: 5px"></textarea>
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

