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

<!-- 실시간 아이디 중복체크 -->
<script type="text/javascript">
	var xmlReq; // 전역변수로 지정.
	// Ajax 객체 생성 과정
	function createAjax() {
		xmlReq = new XMLHttpRequest();
	}

	// Ajax 객체를 이용한 데이터 전송 과정
	function checkDupAjax() {
		createAjax();
		var reg_id = document.getElementById("reg_id").value;
		xmlReq.onreadystatechange = callBack; // 괄호 열고닫고가 틀리다.!
		xmlReq.open("GET", "checkDup.action?reg_id=" + reg_id, true);
		xmlReq.send(null);
		// send가 끝나고나면 비동기식이기 때문에 프로그램이 계속 진행된다.
	}

	// 콜백 함수 과정
	function callBack() {
		if (xmlReq.readyState == 4) {
			if (xmlReq.status == 200) {
				printData();
			}
		}
	}

	// 결과 출력 과정
	function printData() {
		var result = xmlReq.responseXML;

		var rootNode = result.documentElement;
		// <root>true</root> , <root>false</root>
		var rootValue = rootNode.firstChild.nodeValue;
		var rootTag = document.getElementById("result");

		var idNode = rootNode.getElementsByTagName("id");
		var idValue = idNode.item(0).firstChild.nodeValue;
		var idTag = document.getElementById("idTxt");

		if (rootValue == "true") {
			rootTag.innerHTML = "사용 가능한 아이디";
			idTag.innerHTML = "<br>" + idValue;
		} else {
			rootTag.innerHTML = "중복된 아이디";
			idTag.innerHTML = "<br>" + idValue;
		}
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

		<form class="form-signup" method="post" action="registration.action">
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
			  <input type="text" class="form-control" id="reg_id" name="reg_id" onkeyup="checkDupAjax()" required autofocus>
			</div>
			<!-- testing here -->
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
