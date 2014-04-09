<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
.dropdown:hover .dropdown-menu {
    display: block;
 }
</style>

<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="home.action">JOGIYO</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="dropdown">
	              <a href="listRest.action" class="dropdown-toggle disabled" data-toggle="dropdown">지역별<b class="caret"></b></a>
	              <ul class="dropdown-menu">
	                <li><a href="#">서울특별시</a></li>
	                <li><a href="#">경기/인천</a></li>
	                <li><a href="#">부산/경남</a></li>
	                <li><a href="#">대구/경북</a></li>
	                <li><a href="#">대전/전북</a></li>
	                <li><a href="#">광주/전남</a></li>
	                <li><a href="#">그 외 지역</a></li>
	              </ul>
	            </li>
	            <li class="dropdown">
	              <a href="listRest.action" class="dropdown-toggle disabled" data-toggle="dropdown">종류별<b class="caret"></b></a>
	              <ul class="dropdown-menu">
	                <li><a href="#">한식</a></li>
	                <li><a href="#">양식</a></li>
	                <li><a href="#">중식</a></li>
	                <li><a href="#">일식</a></li>
	                <li><a href="#">기타</a></li>
	              </ul>
	            </li>
				<li><a href="listReview.action">후기</a></li>
				<li><a href="listRecipe.action">레시피</a></li>
				<li><a href="listNotice.action">공지사항</a></li>
				<li><a href="listQna.action">문의하기</a></li>
				<!-- 테스트 -->
				<li class="dropdown">
	              <a href="#" class="dropdown-toggle" data-toggle="dropdown">테스트<b class="caret"></b></a>
	              <ul class="dropdown-menu">
	                <li><a href="pageTest.action">테스트 페이지</a></li>
	                <li><a href="listTest.action">테스트 게시판</a></li>
	                <li><a href="dashAdmin.action">관리자 대시보드</a></li>
	              </ul>
	            </li>
				<!-- end 테스트 -->
			</ul>
			<c:choose>
				<c:when test="${not empty sessionId}">
					<form class="navbar-form navbar-right" action="/jogiyo/logout.action">
						<a href=#>${sessionName} 님 환영합니다.</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="submit" class="btn btn-danger">로그아웃</button>
					</form>
				</c:when>
				<c:when test="${empty sessionId}">
					<div class="navbar-form navbar-right">						
						<a href="registrationForm.action" class="btn btn-default">회원가입</a>
						<a href="loginForm.action?actionName=${actionName}" class="btn btn-primary">로그인</a>
					</div>					
				</c:when>
			</c:choose>
		</div>
		<!--/.nav-collapse -->
	</div>
</div>