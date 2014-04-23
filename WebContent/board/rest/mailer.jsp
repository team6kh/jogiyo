<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page isELIgnored="false" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">


<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<link rel="shortcut icon" href="assets/ico/jogiyo.png">


<title>JOGIYO</title>


<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="jogiyo.css" rel="stylesheet">
<link href="common/common-template.css" rel="stylesheet">


<style type="text/css">
	html {
		height: 100%
	}
	body {
		height: 100%;
		margin: 3% 0% 0% 1.5%;
		padding: 0
	}
</style>


<script type="text/javascript">
	function btnSubmit() {
		var sendMail = document.sendMail;
		sendMail.submit();
	}
</script>

<body>
	<!-- header -->
	<%@ include file="/common/header.jsp"%>
	<!-- end of header -->
	
	
	<!-- container -->
	<div class="container">
		
		<div class="col-md-12">
			<br/><br/>
			<h3>문의하기</h3>
			<img src="assets/img/common/Jogiyo_main.png" width="250px" height="100px">
		</div>
		
		<div class="col-md-12" align="center">
			<font color=green size=5 ><b>판매자에게 메일을 보낼 수 있습니다.</b></font><br/><br/>
		</div>
		
		<div class="col-md-12 well">
			<form id="sendMail" action="sendMailPro.action" method="POST" > 
				<input type="hidden" name="rest_writer_email" value="${rest_writer_email}" />
				<input type="hidden" name="session_id" value="${sessionScope.session_id}" />
				
				<table align="center">
					<tr>
						<td>
							<font size=3><b>이메일</b></font><br/>
							<font size=2 color=gray>답장받으실 이메일 주소를 입력하세요</font>
						</td>
					</tr>
					<tr align="center">
						<td align="left"><input type=text name= "email" ></input></td>
					</tr>
					<tr>
						<td>
							<font size=3><b>문의사항</b></font>
						</td>
					</tr>
					<tr align="center">
						<td align="left">
							<textarea cols="50" rows="7" name="content"> 
	                    	</textarea> 
	                    </td>
					</tr>
					<tr><td><br/><td></tr>
					<tr><td><br/><td></tr>
					<tr align="center">
						<td></td>
						<td>
							<button class="btn btn-primary" name="btnSubmit" onclick="return btnSubmit()">문의메일 보내기</button>
							<a href="readRest.action?rest_num=${rest_num}&currentPage=${currentPage}" class="btn btn-danger">취소</a>
						</td>
					</tr> 
				</table>
			</form>
			
		</div>
		
	</div>
	<!-- end of container -->
				
	
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="dist/js/bootstrap.min.js"></script>

</body>	