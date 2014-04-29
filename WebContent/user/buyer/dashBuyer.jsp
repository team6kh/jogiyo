<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="huks">
<link rel="shortcut icon" href="assets/ico/jogiyo.png">

<title>JOGIYO</title>

<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="jogiyo.css" rel="stylesheet">
<link href="user/common/dashboard.css" rel="stylesheet">

</head>

<body>

	<!-- header -->
	<%@ include file="/common/header.jsp"%>
	<!-- /.header -->
	
	<!-- container -->	
	<div class="container-fluid">
	  	<!-- row -->
    	<div class="row">
    	
        	<!-- sidebar -->
        	<div class="col-sm-3 col-md-2 sidebar">
          		<ul class="nav nav-sidebar">
            		<li><a href="readUser.action?user_type=${session_type}&user_id=${session_id}">회원정보</a></li>
            		<li class="active"><a href="dashBuyer.action?sesssion_id=${session_id}">구매목록</a></li>
            		<li><a href="myAllListPage.action?session_id=${session_id}">내가 작성한 글</a></li>
          		</ul>
        	</div>
        	<!-- /.sidebar -->
        	
        	<!-- main -->
        	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        	
        		<!-- 여기에 작성해주시면 됩니다. -->
        		<p>여기에 작성해주시면 됩니다.</p>
        		        		
		    </div>
		    <!-- /.main -->
		</div>
		<!-- /.row	-->
	</div>
	<!-- /.container -->

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->	
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="dist/js/bootstrap.min.js"></script>
	
</body>
</html>
