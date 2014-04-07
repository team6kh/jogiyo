<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page isELIgnored="false" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
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

<script type="text/javascript">

	//실시간 ID validation
	function validateRegId(userinput) {
		if (userinput.reg_id.value == "") {
			feedbackRegId.innerHTML = "<font color=red>아이디를 입력해주세요.</font>";
			regForm.feedbackRegId.value = "0";
			//alert("아이디를 입력하세요");
			return;
		} else {
			var hanchk = hanCheck();

			if (userinput.reg_id.value.length < 7 && hanchk) {
				feedbackRegId.innerHTML = "<font color=red>아이디는 7자 이상이여야합니다.</font>";
				regForm.feedbackRegId.value = "0";
				return false;
			} else if (hanchk) {
				url = "checkDup.action?reg_id=" + userinput.reg_id.value;
				document.getElementById('resultCheckDup').contentWindow.location.href = url;
			}
			regForm.feedbackRegId.value = "1";
		}

		// open(url, "confirm", "toolbar=no,status=no,menubar=no,scrollbars=no,resizable=no,width=300, height=200");

	}
	
	function hanCheck() {
		for (i = 0; i < regForm.reg_id.value.length; i++) {
			var a = regForm.reg_id.value.charCodeAt(i);
			if (a > 128) {
				feedbackRegId.innerHTML = "ID 는 영문, 숫자만 사용하세요.";
				regForm.feedbackRegId.value = "0";
				regForm.reg_id.focus();
				return false;
			}
		}
		return true;
	}
	
</script>

<s:head /> 
<sx:head />
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
			    <select class="form-control" name="reg_type">
			      <option value="buyer">구매자</option>
			      <option value="seller">판매자</option>
			    </select>			  
			</div>
			<div class="form-group">
			  <label>아이디</label>
			  <!-- testing here -->
			  <input type="text" class="form-control" name="reg_id" onkeyup="validateRegId(this.form);">
			  <p class="help-block" id="feedbackRegId">아이디를 입력해주세요.</p>
			  <iframe src="blink.html" id="resultCheckDup" style="display:none;"></iframe>			  
			</div>						
			<div class="form-group">
			  <label>이름</label>
			  <input type="text" class="form-control" name="reg_name" required>
			</div>
			<div class="form-group">
			  <label>비밀번호</label>
			  <input type="password" class="form-control" placeholder="4~20자로 입력해주세요." name="reg_pw" required>
			</div>			
			<div class="form-group">
			  <label>이메일</label>
			  <input type="email" class="form-control" placeholder="you@jogiyo.com" name="reg_email" required>
			</div>
			<div class="form-group">
			  <label>휴대폰</label>
			  <input type="text" class="form-control" placeholder="'-'를 제외하고 입력해주세요." name="reg_phonenumber" required>
			</div>
			<div class="form-group">
			  <label>성별을 선택하세요.</label>
			    <select class="form-control" name="reg_gender">
			      <option value="female">여성</option>
			      <option value="male">남성</option>
			    </select>			  
			</div>
			
        	<button class="btn btn-lg btn-primary btn-block" type="submit">회원가입</button>
      	</form>
	</div>
	<!-- /.container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->	
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="dist/js/bootstrap.min.js"></script>
</body>
</html>
