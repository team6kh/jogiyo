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
		alert("requstEv");
		$('#alert_div').removeClass('alert-info');
		$('#alert_div').addClass('alert-warning');
		document.getElementById('alert_place_holder').innerHTML = "Loading...";
		form=document.getElementById('form-signup');
		form.action="emailer.action";
		form.submit();
		
		return false;
	}
	
	function checkEv()
	{
		//document.getElementById('btnCheckEv').innerHTML = "인증 중";		
		//form=document.getElementById('form-signup');
		//form.action="checkEv.action";
		//form.submit();
		var ev_requested = document.getElementById("buyer_email").value;
		var ev_code_input = document.getElementById("ev_code_input").value;
		url = "checkEv.action?ev_requested="+ev_requested+"&ev_code_input="+ev_code_input;
		document.getElementById('checkEv').contentWindow.location.href = url;
		
		return true;
	}
	
	function checkPass(form)
	{
		//var modalParam = document.getElementById("modalParam").value;
		//var modalParam_key = document.getElementById("modalParam_key").value;
		//var modalParam_pw = document.getElementById("modalParam-pw").value;		
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
			<div class="alert alert-info alert-dismissable" id="alert_div">			  
			  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			  <a href="#" class="alert-link" id="alert_placeholder" onclick="requestEv()">이메일을 인증해주세요.</a>			  
			</div>
			</c:if>
			<c:if test="${buyerDTO.buyer_verification eq 'no' && actionStatus eq 'evRequested'}">
			<div class="alert alert-success alert-dismissable">
			  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			  <a href="#" class="alert-link">이메일을 확인해주세요.</a>
			</div>
			</c:if>
			<!-- END 인증이 되지 않았을 시 뜬다. -->
			<!-- 숨겨진 iframe -->
			<iframe src="blink.html" id="checkEv" style="display:none;"></iframe>
			<!-- END 숨겨진 iframe -->
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
			  <input type="hidden" id="buyer_email" name="buyer_email" value="${buyerDTO.buyer_email}">
			</div>
			<!-- 인증번호 -->
			<c:if test="${buyerDTo.buyer_verification eq no}">
			<div class="form-group">
			  <label>인증번호</label>
			  <div class="input-group">
			    <input type="text" class="form-control" id="ev_code_input" name="ev_code_input">
			    <span id="btnCheckEv" class="input-group-addon btn btn-default" onclick="checkEv()">인증</span>
			  </div>
			</div>
			</c:if>	
			<div class="form-group">
			  <label>휴대폰</label>
			  <input type="text" class="form-control" value="${buyerDTO.buyer_phonenumber}" disabled>
			</div>			
		</form><!-- END of form-signup -->
		
		<!-- form-signup looklike div -->
		<div class="form-signup">			
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
						<form id="checkPass" method="post">
							<div class="modal-content">							
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="checkModalLabel">비밀번호를 입력하세요.</h4>
								</div>
								<div class="modal-body">
									<div class="form-group">
										<label>비밀번호</label>
										<input type="password" class="form-control" id="modalParam_pw" name="modalParam_pw">										
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>		
									<input type="hidden" id="modalParam" name="modalParam">						
									<input type="hidden" id="modalParam_key" name="modalParam_key" value="${buyerDTO.buyer_id}">								
									<button class="btn btn-primary" onclick="checkPass(this.form)">입력</button>
								</div>							
							</div>
						</form>
					</div>
				</div><!-- END of Modal -->					
										
			</div><!-- END of pull-right -->
			
       	</div><!-- END of form-signup looklike div -->
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
