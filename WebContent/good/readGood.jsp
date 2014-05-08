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

<title>JOGIYO 상품목록</title>

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
	<div class="col-md-12 row" name="category" id="categoryForm" method="post" style="margin-bottom: 15px" >
	
		<label class="sr-only">상품 대분류</label> <!-- label은 클래스를 이용하여 숨김처리-->
		<select style="width: 200px;" class="form-control input-sm"
			name="good_ctgy" id="good_ctgy_Depth01" >
			<option selected="selected">판매식품 전체</option>
			<option value="01" <c:if test="${good_ctgy eq '01'}">selected</c:if>>쌀/과일/농수축산물</option>
			<option value="02" <c:if test="${good_ctgy eq '02'}">selected</c:if>>차/음료</option>
			<option value="03" <c:if test="${good_ctgy eq '03'}">selected</c:if>>과자</option>
			<option value="04" <c:if test="${good_ctgy eq '04'}">selected</c:if>>가공식품</option>
			<option value="05" <c:if test="${good_ctgy eq '05'}">selected</c:if>>건강식품</option>
			<option value="06" <c:if test="${good_ctgy eq '06'}">selected</c:if>>다이어트식품</option>
			<option value="07" <c:if test="${good_ctgy eq '07'}">selected</c:if>>유아식</option>
		</select>
				
		<label class="sr-only">상품 중분류</label>		
		<select style="width: 200px;" class="form-control input-sm"
			name="good_ctgy" id="good_ctgy_Depth02">
			<option selected="selected">쌀/과일/농수축산물</option>
	        <option>쌀/찹쌀/현미</option>
	        <option>혼합곡/잡곡류</option>
	        <option>국내산과일</option>
	        <option>수입과일</option>
	        <option>냉동/말린과일</option>
	        <option>고구마/감자/옥수수외</option>
	        <option>배추/엽채/쌈채소</option>
	        <option>양념/버섯/나물</option>
	        <option>견과류</option>
	        <option>생선류</option>
	        <option>해산물/어패류</option>
	        <option>건어물</option>
	        <option>김/해초류</option>
	        <option>한우</option>
	        <option>국내산 육우</option>
	        <option>수입소고기</option>
	        <option>국내산 돼지고기</option>
	        <option>수입돼지고기</option>
	        <option>닭고기/오리/기타</option>
	        <option>축산가공식품</option>
	        <option>김치</option>
	        <option>반찬/젓갈류</option>
	        <option>업소용/식자재코너</option>
	        <option>친환경브랜드식품관</option>
		</select>		
				
		<label class="sr-only">상품 소분류</label>
		<select style="width: 200px;" class="form-control input-sm"
			name="good_ctgy" id="good_ctgy_Depth03" >
			<option selected="selected">쌀/찹쌀/현미</option>
	        <option>일반쌀 20kg</option>
	        <option>일반쌀 10kg</option>
	        <option>소포장 쌀</option>
	        <option>현미/발아현미</option>
	        <option>흑미/보리</option>
	        <option>찹쌀</option>
	        <option>기능성 쌀</option>
	        <option>농협쌀</option>
	        <option>이천쌀</option>
		</select>
		
		</div>
		<!-- /.분류설정 -->	
		
		<!-- 상품내용 입력폼 -->
		
		<div class="row">
		
			<div class="col-md-4" >
			<img src="..." alt="상품이미지위치" class="img-thumbnail" style="width: 350px; height: 350px">
			</div>
			
		  	<div class="col-md-8" name="insertGoodInfo">		  	
		  		<table class="table">
					<tr>
						<th>상품번호</th>
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