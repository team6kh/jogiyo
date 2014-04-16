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
<link href="user/common/registration/registration.css" rel="stylesheet">

<script type="text/javascript">
		
	//실시간 ID validation
	function validateRegId(userinput) {
		if (userinput.buyer_id.value == "") {
			feedbackRegId.innerHTML = "아이디를 입력해주세요.";
			regForm.feedbackRegId.value = "0";
			//alert("아이디를 입력하세요");
			$('#divRegId').removeClass('has-error'); // bootstrap validation
			$('#divRegId').removeClass('has-success'); // bootstrap validation
			$('#btnSubmit').prop('disabled', true);
			return;
		} else {
			var hanchk = hanCheck();

			if (userinput.buyer_id.value.length < 4 && hanchk) {
				feedbackRegId.innerHTML = "아이디는 4자 이상이여야합니다.";
				regForm.feedbackRegId.value = "0";
				$('#divRegId').addClass('has-error'); // bootstrap validation
				$('#btnSubmit').prop('disabled', true);
				return false;
			} else if (hanchk) {
				var reg_type = document.getElementById("reg_type").value;
				url = "checkDup.action?reg_id="+userinput.buyer_id.value+"&reg_type="+reg_type;
				document.getElementById('checkDup').contentWindow.location.href = url;
			}
			regForm.feedbackRegId.value = "1";
		}

		// open(url, "confirm", "toolbar=no,status=no,menubar=no,scrollbars=no,resizable=no,width=300, height=200");

	}
	
	function hanCheck() {
		for (i = 0; i < regForm.buyer_id.value.length; i++) {
			var a = regForm.buyer_id.value.charCodeAt(i);
			if (a > 128) {
				feedbackRegId.innerHTML = "ID 는 영문, 숫자만 사용하세요.";
				regForm.feedbackRegId.value = "0";
				regForm.buyer_id.focus();
				$('#divRegId').addClass('has-error'); // bootstrap validation
				$('#btnSubmit').prop('disabled', true);
				return false;
			}
		}
		return true;
	}
	
    function optionCheck(){
        var option = document.getElementById("reg_type").value;
        if(option == "buyer"){
        	url = "registrationForm.action?reg_type=" + option;
        	document.location.href = url;
        }
        if(option == "seller"){
        	url = "registrationForm.action?reg_type=" + option;
			document.location.href = url;
        }
    }
	
</script>

</head>

<body>

	<!-- header -->
	<%@ include file="/common/header.jsp"%>
	<!-- end of header -->

	<!-- container -->
	<div class="container">

		<form class="form-signup" method="post" action="registration.action" name="regForm">
			<input type="hidden" name="feedbackRegId" value="0" />
        	<h2 class="form-signup-heading">계정을 생성합니다.</h2>			
			<div class="form-group">
			  <label>가입유형을 선택하세요.</label>
			    <select class="form-control" id="reg_type" name="reg_type" onchange="optionCheck()">
			      <option value="buyer" selected>구매자</option>
			      <option value="seller">판매자</option>
			    </select>		  
			</div>
			<div id="divRegId" class="form-group">
			  <label>구매자 아이디</label>
			  <input type="text" class="form-control" id="buyer_id" name="buyer_id" onkeyup="validateRegId(this.form);" required autofocus>
			  <p class="help-block" id="feedbackRegId">아이디를 입력해주세요.</p>
			  <iframe src="blink.html" id="checkDup" style="display:none;"></iframe>			  
			</div>						
			<div class="form-group">
			  <label>이름</label>
			  <input type="text" class="form-control" name="buyer_name" required>
			</div>
			<div class="form-group">
			  <label>비밀번호</label>
			  <input type="password" class="form-control" placeholder="4~20자로 입력해주세요." name="buyer_pw" required>
			</div>
			<div class="form-group">
			  <label>비밀번호 확인</label>
			  <input type="password" class="form-control" placeholder="비밀번호를 재입력 해주세요." required>
			</div>
			<div class="form-group">
			  <label>휴대폰</label>
			  <input type="text" class="form-control" placeholder="'-'를 제외하고 입력해주세요." name="buyer_phonenumber" required>
			</div>			
			<div class="form-group">
			  <label>이메일</label>
			  <input type="email" class="form-control" placeholder="you@jogiyo.com" name="buyer_email" required>
			</div>
			
        	<button id="btnSubmit" class="btn btn-lg btn-primary btn-block" type="submit" disabled="disabled">회원가입</button>
      	</form>
	</div>
	<!-- /.container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->	
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="dist/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
	// disable spaces in input
	$("input#buyer_id").on({
		  keydown: function(e) {
		    if (e.which === 32)
		      return false;
		  },
		  change: function() {
		    this.value = this.value.replace(/\s/g, "");
		  }
		});
	</script>
</body>
</html>
