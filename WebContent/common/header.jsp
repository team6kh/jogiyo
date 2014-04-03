<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<li><a href="listRest.action">REST</a></li>
				<li><a href="/team6-struts/oard/rest/listReview.jsp">후기</a></li>
				<li><a href="/team6-struts/board/rest/listRecipe.jsp">레시피</a></li>
				<li><a href="/team6-struts/board/rest/listNotice.jsp">공지사항</a></li>
				<li><a href="/team6-struts/board/rest/listQna.jsp">문의하기</a></li>
				<!-- 테스트 -->
				<li class="dropdown">
	              <a href="#" class="dropdown-toggle" data-toggle="dropdown">테스트 <b class="caret"></b></a>
	              <ul class="dropdown-menu">
	                <li><a href="testAction.action#">테스트 메시지</a></li>
	                <li><a href="listTest.action">테스트 게시판</a></li>
	              </ul>
	            </li>
				<!-- end 테스트 -->
			</ul>
			<c:choose>
				<c:when test="${not empty sessionId}">
					<form class="navbar-form navbar-right"
						action="/jogiyo/logout.action">
						<a href=#>${sessionName} 님 환영합니다.</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="submit" class="btn btn-danger">로그아웃</button>
					</form>
				</c:when>
				<c:when test="${empty sessionId}">
					<div class="navbar-form navbar-right">						
						<button type="button" class="btn btn-default">회원가입</button>
						<a href="loginForm.action?actionName=${actionName}" class="btn btn-primary">로그인</a>
					</div>					
				</c:when>
			</c:choose>
		</div>
		<!--/.nav-collapse -->
	</div>
</div>