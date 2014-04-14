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
<link href="user/common/registration/registration.css" rel="stylesheet">

<script type="text/javascript">
	function requestEv()
	{				
		$('#divAlert').removeClass('alert-info');
		$('#divAlert').addClass('alert-warning');
		document.getElementById('aAlert').innerHTML = "인증번호 전송 중...";
		form=document.getElementById('form-signup');
		form.action="emailer.action";
		form.submit();
		
		return false;
	}
	
	function checkEv()
	{
		form=document.getElementById('form-signup');
		form.action="checkEv.action";
		form.submit();
		
		return true;
	}
	
	function checkPass()
	{
		form=document.getElementById('form-signup');
		form.action="checkPass.action";
		form.submit();
		
		return true;
	}
</script>

</head>

<body>

	<!-- header -->
	<%@ include file="/common/header.jsp"%>
	<!-- end of header -->
	
	<!-- container -->
	<div class="container">	
		<form class="form-signup" id="form-signup" method="post">
			<br />
		  	<!-- 인증이 되지 않았을 시 뜬다. -->		  	
			<c:if test="${buyerDTO.buyer_verification eq 'no' && actionStatus eq null}">						
			<div class="alert alert-info alert-dismissable" id="divAlert">			  
			  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			  <a href="#" class="alert-link" id="aAlert" onclick="requestEv()">이메일을 인증해주세요.</a>
			  <iframe src="blink.html" id="insertEv" style="display:none;"></iframe>
			</div>
			</c:if>
			<c:if test="${buyerDTO.buyer_verification eq 'no' && actionStatus eq 'evRequested'}">
			<div class="alert alert-success alert-dismissable">
			  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			  <a href="#" class="alert-link">인증번호를 전송했습니다.</a>
			</div>
			</c:if>
	       	<h2 class="form-signup-heading">${buyerDTO.buyer_name} 님의 정보</h2>			
			<div class="form-group">
			  <label>가입유형</label>
			    <select class="form-control" id="userType" disabled>
			      <option value="buyer">구매자</option>
			      <option value="seller">판매자</option>
			    </select>
			    <input type="hidden" name="userType" value="${sessionType}">			  
			</div>
			<div id="div_regid" class="form-group">
			  <label>아이디</label>
			  <input type="text" class="form-control" value="${buyerDTO.buyer_id}" disabled>
			  <input type="hidden" name="userId" value="${buyerDTO.buyer_id}">		  
			</div>						
			<div class="form-group">
			  <label>이름</label>
			  <input type="text" class="form-control" value="${buyerDTO.buyer_name}" disabled>
			  <input type="hidden" name="buyer_name" value="${buyerDTO.buyer_name}">
			</div>
			<div class="form-group">
			  <label>비밀번호</label>
			  <input type="password" class="form-control" value="${buyerDTO.buyer_pw}" disabled>
			</div>			
			<div class="form-group">
			  <label>이메일</label>
			  <input type="text" class="form-control" value="${buyerDTO.buyer_email}" disabled>
			  <input type="hidden" name="buyer_email" value="${buyerDTO.buyer_email}">
			</div>
			<!-- 인증번호 -->
			<c:if test="${buyerDTo.buyer_verification eq no}">
			<div class="form-group">
			  <label>인증번호</label>
			  <div class="input-group">
			    <input type="text" class="form-control">
			    <span class="input-group-addon btn btn-default" onclick="checkEv()">전송</span>
			  </div>
			</div>
			</c:if>	
			<div class="form-group">
			  <label>휴대폰</label>
			  <input type="text" class="form-control" value="${buyerDTO.buyer_phonenumber}" disabled>
			</div>
			
			<div class="pull-right">
				<!-- Button trigger modal : 수정 -->
				<button class="btn btn-default insertModalParam" data-toggle="modal"
					data-target="#checkModal" data-id="updateBuyerForm">수정</button>
					
				<!-- Button trigger modal : 탈퇴 -->
				<button class="btn btn-default insertModalParam" data-toggle="modal"
					data-target="#checkModal" data-id="deleteBuyer">탈퇴</button>					
				
				<!-- Modal -->
				<div class="modal fade" id="checkModal" tabindex="-1" role="dialog" aria-labelledby="checkModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">							
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="checkModalLabel">비밀번호를 입력하세요.</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label>비밀번호</label>
									<input type="password" class="form-control" name="modalParam_pw">										
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>		
								<input type="hidden" name="modalParam" id="modalParam">						
								<input type="hidden" name="modalParam_key" value="${buyerDTO.buyer_id}">								
								<button class="btn btn-primary" onclick="checkPass()">입력</button>
							</div>							
						</div>
					</div>
				</div><!-- END of Modal -->					
										
			</div><!-- END of pull-right -->
			
       	</form><!-- END of form-signup -->
	</div>
	<!-- /.container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->	
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="dist/js/bootstrap.min.js"></script>
	<script>
		$("#userType").val("seller");
		
		$(document).on("click", ".insertModalParam", function() {
			var thisModalParam = $(this).data('id');
			$(".modal-footer #modalParam").val(thisModalParam);
		});
	</script>
</body>
</html>