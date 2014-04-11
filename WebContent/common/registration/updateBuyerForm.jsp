<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page isELIgnored="false" %> --%>
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
<link href="common/registration/registration.css" rel="stylesheet">

</head>

<body>

	<!-- header -->
	<%@ include file="/common/header.jsp"%>
	<!-- end of header -->

	<!-- container -->
	<div class="container">

		<form class="form-signup" method="post" action="updateBuyer.action" name="regForm">
        	<h2 class="form-signup-heading">계정을 수정합니다.</h2>			
			<div class="form-group">
			  <label>가입유형</label>
			    <select class="form-control" id="userType" disabled>
			      <option value="buyer">구매자</option>
			      <option value="seller">판매자</option>
			    </select>			  
			</div>
			<div id="div_regid" class="form-group">
			  <label>아이디</label>
			  <input type="text" class="form-control" value="${buyerDTO.buyer_id}" disabled>
			  <input type="hidden" name="buyer_id" value="${buyerDTO.buyer_id}">		  
			</div>						
			<div class="form-group">
			  <label>이름</label>
			   <input type="text" class="form-control" value="${buyerDTO.buyer_name}" disabled>
			</div>
			<div class="form-group">
			  <label>비밀번호</label>
			  <input type="password" class="form-control" placeholder="4~20자로 입력해주세요." name="buyer_pw" value="${buyerDTO.buyer_pw}" required>
			</div>
			<div class="form-group">
			  <label>휴대폰</label>
			  <input type="text" class="form-control" placeholder="'-'를 제외하고 입력해주세요." name="buyer_phonenumber" value="${buyerDTO.buyer_phonenumber}" required>
			</div>			
			<div class="form-group">
			  <label>이메일</label>
			  <input type="email" class="form-control" placeholder="you@jogiyo.com" name="buyer_email" value="${buyerDTO.buyer_email}" required>
			</div>			
			<div class="form-group">
			  <label>성별</label>
			    <select class="form-control" id="userGender" disabled>
			      <option value="female">여성</option>
			      <option value="male">남성</option>
			    </select>			  
			</div>
			
        	<button class="btn btn-lg btn-primary btn-block" type="submit">수정</button>
      	</form>
	</div>
	<!-- /.container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->	
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="dist/js/bootstrap.min.js"></script>
	<script>
		$("#userType").val("seller");
		$("#userGender").val("${buyerDTO.buyer_gender}");
	</script>
</body>
</html>
