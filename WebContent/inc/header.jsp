<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
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
            <li><a href="listRest.action">카테고리</a></li>
            <li><a href="/team6-struts/oard/rest/listReview.jsp">후기</a></li>
            <li><a href="/team6-struts/board/rest/listRecipe.jsp">레시피</a></li>
            <li><a href="/team6-struts/board/rest/listNotice.jsp">공지사항</a></li>
            <li><a href="/team6-struts/board/rest/listQna.jsp">문의하기</a></li>
            <li><a href="listTest.action">테스트</a></li>
          </ul>
          <c:choose>
			<c:when test="${not empty sessionBuyerId}">
			<form class="navbar-form navbar-right" role="form" action="/jogiyo/logout.action">
	          <a href=#>${sessionBuyerName} 님 환영합니다.</a>&nbsp;&nbsp;&nbsp;&nbsp;
	          <button type="submit" class="btn btn-danger">로그아웃</button>
	        </form>  
			</c:when>
			<c:when test="${empty sessionBuyerId}">
			<form class="navbar-form navbar-right" role="form" action="/jogiyo/login.action">
	          <div class="form-group">
	            <input type="text" placeholder="아이디" class="form-control" name="buyer_id">
	          </div>
	          <div class="form-group">
	            <input type="password" placeholder="비밀번호" class="form-control" name="buyer_pw">
	          </div>	          
	          <input type="hidden" name="actionName" value="${actionName}">
	          <button type="submit" class="btn btn-primary">로그인</button>
	        </form>  
			</c:when>
		  </c:choose>             
        </div><!--/.nav-collapse -->
      </div>
    </div>