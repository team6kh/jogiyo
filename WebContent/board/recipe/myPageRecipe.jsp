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
<link href="common/common-template.css" rel="stylesheet">


</head>

<body>

	<!-- header -->
	<%@ include file="/common/header.jsp"%>
	<!-- end of header -->

	<!-- container -->
	
	<input type="hidden" id="session_id" name="session_id" value="${sessionScope.session_id}" />
	<div class="container">
	
		<div align="left">
	<h2>마이페이지</h2>
	<ul>
		<li><a href="mypageFormRecipe.action">구매목록</a></li>
		<li><a href="myAllListPage.action?session_id=<s:property value="#session.session_id"/>">내가 작성한 글</a></li>
		<li><a href="listQna.action">상품 Q&A</a></li>
		<li><a href="">1:1 문의</a></li>
		<li><a href="">회원정보수정</a></li>
		
	</ul>

</div>


<div align="center">
	<h2>컨텐츠영역</h2>
	<div>
		<p>
			<strong>${session_id}</strong>님 반갑습니다!
		</p>

	</div>



	<script type="text/javascript">
		
	</script>





	<h3>구매목록</h3>

	<div>
		<fieldset>
			<legend>조회기간 검색</legend>
			<strong>조회기간</strong>
			<div>
				<div>
					<label id="search_period_7D" for="radio01" onclick="">1주일</label>
					<label id="search_period_15D" for="radio02" onclick="">15일</label>
					<label id="search_period_1M" for="radio03" onclick="">1개월</label> 
					<label id="search_period_3M" for="radio04" onclick="">3개월</label> 
					<label id="search_period_6M" for="radio05" onclick="">6개월</label>
				</div>
				<div>
					<select id="search_start_year" name="search_start_year">
					    <option value="2010">2010</option>
						<option value="2011">2011</option>
						<option value="2012">2012</option>
						<option value="2013">2013</option>
						<option value="2014">2014</option></select> 년
					 <select id="search_start_month" name="search_start_month">
					    <option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option></select> 월
					<select id="search_start_date" name="search_start_date">
					    <option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
						<option value="24">24</option>
						<option value="25">25</option>
						<option value="26">26</option>
						<option value="27">27</option>
						<option value="28">28</option>
						<option value="29">29</option>
						<option value="30">30</option>
						<option value="31">31</option></select> 일  ~  
					<select id="search_end_year" name="search_end_year">
					    <option value="2010">2010</option>
						<option value="2011">2011</option>
						<option value="2012">2012</option>
						<option value="2013">2013</option>
						<option value="2014">2014</option></select> 년 
					<select id="search_end_month" name="search_end_month">
					    <option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option></select> 월
					<select	id="search_end_date" name="search_end_date">
					    <option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
						<option value="24">24</option>
						<option value="25">25</option>
						<option value="26">26</option>
						<option value="27">27</option>
						<option value="28">28</option>
						<option value="29">29</option>
						<option value="30">30</option>
						<option value="31">31</option></select> 일
					<button type="submit" onclick="">
						<span>조회하기</span>
					</button>
				</div>
			</div>
		</fieldset>
	</div>
	<div >
		<ul>
			<li><a href="" >전체 구매목록</a></li>
			<li><a href="" >쿠폰</a></li>
			
		</ul>
	</div>
	<table cellspacing="0" width="100%" summary="구매목록의 주문일자(주문번호),주문 상품 정보, 총 결제금액,상태등을 노출하는 테이블">
		<caption>구매목록</caption>
		<colgroup>
			<col width="115">
			<col width="413">
			<col width="125">
			<col width="99">
		</colgroup>
		<thead>
			<tr>
				<th scope="col">주문일자<br>(주문번호)
				</th>
				<th scope="col">주문 상품 정보</th>
				<th scope="col">총 결제 금액</th>
				<th scope="col">상태</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td colspan="4">구매한 상품이 없습니다.</td>
			</tr>
		</tbody>
	</table>
	<div></div>

	</div>
</div>

<div id="social_div" style="display: none" height="1000"></div>

<div id="buylist_div" style="display: none" height="1000">
	<div id="buylist_title">
		<h1>
			구매목록
		</h1>
		<p>
			메인 &gt; 마이페이지 &gt; <span>구매목록</span>
		</p>
	</div>
	<div id="buylist_content_area"></div>
</div>

<div id="buydetail_div" style="display: none" height="1000">
	<div id="buydetail_title">
		<h1>
			<img
				src="http://image.wemakeprice.com/images/mypage/title_ticket.gif"
				alt="구매목록">
		</h1>
		<p class="location">
			메인 &gt; 마이페이지 &gt; 구매목록 &gt; <span class="location_on">티켓/상품정보</span>
		</p>
	</div>
	<div id="buylist_content_area"></div>
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