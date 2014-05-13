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
			<h3>상품목록</h3>
		</div>
		
		
		
		
	<!-- 2-2. search Area  -->
	<form 
			name="searchForm" id="searchInfo" method="post">	
	<!-- search Area Start -->	
	<div class="col-md-12 well"> 
		<div class="row form-group">
    		<!-- 상품번호 -->
				<label class="col-md-1">상품번호</label>
				<div class="col-md-2">
					<input class="form-control input-sm"
							type="text" name="good_name">
				</div>
			<!-- category 선택 검색  -->
				<label class="col-md-1">상품분류</label>
			    <div class="col-md-2">
			      <input class="form-control input-sm"  placeholder="write category code"
			      		type="text" name="GoodCategory" id="WriteGoodCategory">
			    </div>
   			<!-- 상품명+상품설명 검색 -->
				<label class="col-md-2">상품명 또는 상품설명</label>
				<div class="col-md-4">
					<input class="form-control input-sm"
							type="text" name="good_name">
				</div>
		</div>
		
		<div class="row form-group">
			<!-- 글번호 선택 검색  -->
	    	<label class="col-md-1">글번호</label>
		    <div class="col-md-2">
		      <input class="form-control input-sm" 
		      		type="text" name="GoodCategory" id="WriteGoodCategory">
		    </div>
   			<!-- 글제목 검색 -->	
			<label class="col-md-1">글제목</label>
			<div class="col-md-5">
				<input class="form-control input-sm"
						type="text" name="subject">
			</div>
			<!-- 작성자 검색 -->	
			<label class="col-md-1">작성자</label>
			<div class="col-md-2">
				<input class="form-control input-sm"
						type="text" name="writer">
			</div>
		</div>	
		
		<div class="row form-group">
			<!-- 기간 선택 검색  -->
	    	<label class="col-md-1">기간검색</label>
		    <div class="col-md-2" placeholder="start Date">
			      <input class="form-control input-sm" 
			      		type="text" name="startDate" id="startDate">
			      		</div>
      		 <div class="col-md-2" placeholder="end Date">
	      		  <input class="form-control input-sm" 
			      		type="text" name="endDate" id="endDate">
		    </div>		
			<div class="col-md-2">
			<button type="submit"  class="btn btn-info btn-sm">Search</button>
			</div>
		</div>
		
		</div>
		<!-- End : search Area  -->			
		
	</form>
		
		
		
		<!-- 상품목록 : list Area-->
		<div class="col-md-12" style="margin-top: 10px">
			<table class="table table-condensed"> <!-- CSS 좁은폭 테이블 -->
				<thead>
					<tr>
						<th class="text-center" style="width: 5%;">번호</th>
						<th class="text-center" style="width: 10%;">상품이미지</th>
						<th class="text-center" style="width: 25%;">상품명</th>
						<th class="text-center" style="width: 10%;">판매가</th>
						<th class="text-center" style="width: 10%;">제조사</th>
						<th class="text-center" style="width: 10%;">원산지</th>
						<th class="text-center" style="width: 10%;">배송정보</th>
						<th class="text-center" style="width: 10%;">보관유형</th>
						<th class="text-center" style="width: 10%;">등록자</th>
					</tr>
				</thead>

				<tbody>
				<tr>
				<td class="text-center" style="width: 5%;">1</td>
				<td class="text-center" style="width: 10%;"><img src="..." alt="상품이미지위치" class="img-thumbnail" style="width: 70px; height: 70px"></td>
				<td class="text-left" style="width: 25%;">토마토페이스트</td>
				<td class="text-center" style="width: 10%;">5000원</td>
				<td class="text-center" style="width: 10%;">KH식품</td>
				<td class="text-center" style="width: 10%;">서울 당산</td>
				<td class="text-center" style="width: 10%;">선불</td>
				<td class="text-center" style="width: 10%;">냉동보관</td>
				<td class="text-center" style="width: 10%;">강민정</td>
				</tr>
				</tbody>
				
			</table>
		</div>	
		
		
	</div> <!-- end container --> 





	
	
</body>
</html>