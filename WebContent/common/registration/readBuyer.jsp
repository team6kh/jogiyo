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
		<div class="form-signup">
	       	<h2 class="form-signup-heading">${buyerDTO.buyer_name} 님의 정보</h2>			
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
			</div>						
			<div class="form-group">
			  <label>이름</label>
			   <input type="text" class="form-control" value="${buyerDTO.buyer_name}" disabled>
			</div>
			<div class="form-group">
			  <label>비밀번호</label>
			   <input type="password" class="form-control" value="${buyerDTO.buyer_pw}" disabled>
			</div>			
			<div class="form-group">
			  <label>이메일</label>
			   <input type="text" class="form-control" value="${buyerDTO.buyer_email}" disabled>
			</div>
			<div class="form-group">
			  <label>휴대폰</label>
			   <input type="text" class="form-control" value="${buyerDTO.buyer_phonenumber}" disabled>
			</div>
			<div class="form-group">
			  <label>성별</label>
			    <select class="form-control" id="userGender" disabled>
			      <option value="female">여성</option>
			      <option value="male">남성</option>
			    </select>			  
			</div>
			
			<div class="pull-right">
				<!-- Button trigger modal : 수정 -->
				<button class="btn btn-default insertModalParam" data-toggle="modal"
					data-target="#checkModal" data-id="updateBuyerForm">수정</button>
					
				<!-- Button trigger modal : 삭제 -->
				<button class="btn btn-default insertModalParam" data-toggle="modal"
					data-target="#checkModal" data-id="deleteTest">삭제</button>					
				
				<!-- Modal -->
				<div class="modal fade" id="checkModal" tabindex="-1" role="dialog" aria-labelledby="checkModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<form action="checkPass.action" method="post">
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
									<button type="submit" class="btn btn-primary">입력</button>
								</div>
							</form>
						</div>
					</div>
				</div><!-- END of Modal -->					
										
			</div><!-- END of pull-right -->
			
       	</div><!-- END of form-signup -->
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
		
		$(document).on("click", ".insertModalParam", function() {
			var thisModalParam = $(this).data('id');
			$(".modal-footer #modalParam").val(thisModalParam);
		});
	</script>
</body>
</html>
