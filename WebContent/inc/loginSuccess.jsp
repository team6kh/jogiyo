<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/team6-struts/view/index.jsp">JOGIYO</a>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="/team6-struts/view/board/rest/listRest.jsp">레스토랑</a></li>
            <li><a href="/team6-struts/view/board/rest/listReview.jsp">후기</a></li>
            <li><a href="/team6-struts/view/board/rest/listRecipe.jsp">레시피</a></li>
            <li><a href="/team6-struts/view/board/rest/listNotice.jsp">공지사항</a></li>
            <li><a href="/team6-struts/view/board/rest/listQna.jsp">문의하기</a></li>
          </ul>
          ${userId} 님 환영합니다. <br />
          modelAndView : ${modelAndView}
        </div><!--/.nav-collapse -->
      </div>
    </div>