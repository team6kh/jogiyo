<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page isELIgnored="false" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>



	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="huks">
		<link rel="shortcut icon" href="assets/ico/jogiyo.png">
		
		
		<!-- Bootstrap core CSS -->
		<link href="dist/css/bootstrap.min.css" rel="stylesheet">
		
		<!-- Custom styles for this template -->
		<link href="jogiyo.css" rel="stylesheet">
		<link href="common/common-template.css" rel="stylesheet">
		<title>상품 등록 페이지</title>
	
		<script type="text/javascript">
				
			fields = 1;

			function removeInput(el) {
				if (fields > 0) {
					document.getElementById('stop').innerHTML = "";
					var parent = document.getElementById('text');
					parent.removeChild(el);
					fields -= 1;
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
		
			<table width="600" border="0" cellspacing="0" cellpadding="2" align="center">
				<tr><td colspan="2" ><br/></td></tr>
				<tr>
					<td align="center">
						<h2>상품 등록 페이지</h2><br/><br/><br/>
					</td>
				</tr>
				
			</table>
			
			
			
			
			
				<form name="updateRestForm" action="updateRest.action" method="post" enctype="multipart/form-data">
			
					<table width=75% border="0" cellspacing="0" cellpadding="0" align="center">
						<tr bgcolor="#777777">
							<td height="1" colspan="2"></td>
						</tr>
						
						
						
						<tr><td colspan="2" ><hr></td></tr>
						
						
						
						<tr align="center">
							<td align="right">
								<input type="button"  name="add" value="옵션추가" onclick="addInput()"/>
								&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td align="left">
								<div id="stop"></div>
								<font color='#BDBDBD'>옵션명, 옵션가 정의 및 옵션사진을 업로드해주세요.</font>
							</td>
						</tr>
						
						<tr><td colspan="2" ><br/></td></tr>
						
						<tr align="center">
							<td align="right">
								<input type="hidden" name="virRest_num" value="${virRest_num}" />
								&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td align="left">
								<div id="text">
									 <div>
									 	옵션명1.&nbsp&nbsp <input type="text" name="restopt_subject1" value="" /> <br/>
										옵션가1.&nbsp&nbsp <input type="text" name="restopt_priceplus1"  value="" /> 원<br/>
										&nbsp&nbsp <input type="file" name="optupload1" />
										<a href='#' onclick='removeInput(this.parentNode)' />옵션1 제거 <br/><hr></div>
								</div>
							</td>
						</tr>
						
						<tr><td colspan="2" ><br/></td></tr>
						
						
						
						<tr>
							<td align="right" colspan="2">
								<input name="submit" type="submit" value="상품 등록" disabled/>
								<input name="cancel" type="button" value="상품 등록 취소" onClick="javascript:location.href='insertCancelRestForm.action'" />
							</td>
						</tr>
						
						<tr><td colspan="2" ><br/></td></tr>
						<tr><td colspan="2" ><br/></td></tr>
					</table>
					
					
				</form>
				
				
				
				
		</div>
	</body>
</html>
