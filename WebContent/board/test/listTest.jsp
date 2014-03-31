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
    <link href="board/test/test-template.css" rel="stylesheet">
    
  </head>

  <body>

    <div id="header">
		<%@ include file="/inc/header.jsp"%>
	</div>

    <div class="container">

      <div class="test-template">
        <h1>test template</h1>
        <p class="lead">currentActionName:${currentActionName}</p>
        <p class="lead">sessionBuyerId:${sessionBuyerId}</p>
        <p class="lead">sessionScope.sessionBuyerId:${sessionScope.sessionBuyerId}</p>
        <c:set var="sId" value="${sessionScope.sessionBuyerId}"></c:set>        
        <p class="lead">sId:${sId}</p>
        <p class="lead">sessionBuyerName:"${sessionBuyerName}" 님 환영합니다.</p>
        <p class="lead">resultClass.buyer_name:<s:property value="resultClass.buyer_name"/> 님 환영합니다.</p>
      </div>

    </div><!-- /.container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="dist/js/bootstrap.min.js"></script>
  </body>
</html>
