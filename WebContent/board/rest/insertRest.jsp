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
		<link href="common/common-template.css" rel="stylesheet">
		<title>상품 등록 페이지</title>
	
		<script type="text/javascript">
				fields = 1;
		
				function addInput() {
					if (fields != 16) {
						
						document.getElementById('text').innerHTML += "<div>옵션명"+fields+".&nbsp&nbsp <input type='text' name='restopt_subject"+fields+"' value=''/> <br/>"
																	+"옵션가"+fields+".&nbsp&nbsp <input type='text' name='restopt_priceplus"+fields+"' value=''/> 원<br/>"
																	+"옵션사진"+fields+".&nbsp&nbsp <input type='file' name='optupload"+fields+"' />"
																	+ " <a href='#' onclick='removeInput(this.parentNode)' />옵션제거</div>";
						fields += 1;
						//optMap.put("restopt_subject"+fields,"restopt_priceplus"+fields);
					} else {
						document.getElementById('stop').innerHTML += "<font color='#FF0000'>옵션은 최대 15개만 등록할 수 있습니다.</font><br/>";
						document.form.add.disabled=true;
					}
				}
				
				function removeInput(el) {
					if (fields > 0) {
						document.getElementById('stop').innerHTML = "";
						var parent = document.getElementById('text');
						parent.removeChild(el);
						fields -= 1;
					}
				}
			
				
		
			function validation() {
				var frm = document.getElementById("insertRestForm");
				
				if(frm.rest_subject.value == "") {
					alert("상품명을 입력해주세요.");
					return false;
				} 
				else if(frm.mainphoto.value == "") {
					alert("매인사진을 업로드해주세요.");
					return false;			
				}
				else if(frm.contentphoto.value == "") {
					alert("상세설명사진을 업로드해주세요.");
					return false;			
				} 
				return true;
			}
			
			function optvalidation() {
				var frm = document.getElementById("insertOptRestForm");
				
				if(frm.restopt_subject.value == "") {
					alert("옵션명을 입력해주세요.");
					return false;
				} 
				else if(frm.restopt_priceplus.value == "") {
					alert("옵션가격을 입력해주세요.");
					return false;
				}
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
		
			<table width="600" border="0" cellspacing="0" cellpadding="2">
				<tr>
					<td align="center">
						<h2>상품 등록 페이지</h2>
					</td>
				</tr>
			</table>
			
			<s:if test="rest_num == 0">
				<form name="insertRestForm" action="insertRest.action" method="post" enctype="multipart/form-data" onsubmit="return validation();">
					<!-- 임시 히든값 -->
					<input type="hidden" name="session_id" value="${sessionScope.session_id}" />
				
			</s:if>
			<s:else>
				<form name="updateRestForm" action="updateRest.action" method="post" enctype="multipart/form-data">
					<s:hidden name="rest_num" value="%{resultClass.rest_num}" />
					<s:hidden name="currentPage" value="%{currentPage}" />
					
					<s:hidden name="old_file1" value="%{resultClass.file_savname1}" />
					<s:hidden name="old_file2" value="%{resultClass.file_savname2}" />
			</s:else>
			
					<table width="600" border="0" cellspacing="0" cellpadding="0">
						<tr bgcolor="#777777">
							<td height="1" colspan="2"></td>
						</tr>
						
						<tr>
							<td width="150"><font color="#FF0000">*</font>상품명</td>
							<td width="450">
								<s:textfield name="rest_subject" theme="simple" value="%{resultClass.rest_subject}" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<td height="1" colspan="2"></td>
						</tr>
						
						<tr>
							<td>
								<input type="button"  name="add" value="옵션추가" onclick="addInput()"/>
							</td>
							<td>
								<div id="stop"></div>
								<font color='#BDBDBD'>옵션명과 옵션가를 정의하세요.</font>
							</td>
						</tr>
						
						<tr>
							<td>
								<input type="hidden" name="virRest_num" value="${virRest_num}" />
							</td>
							<td>
								<div id="text">
									<!-- 옵션추가 클릭시 여기에 태그 추가 -->
								</div>
							</td>
						</tr>
						
						
						<tr>
							<td height="1" colspan="2"></td>	
						</tr>
						
						<tr>
							<td><font color="#FF0000">*</font>지역 카테고리</td>
							<td>
								<select name = "rest_localcategory">
									<option value = "11">서울 지역</option>
									<option value = "12">경기/인천</option>
									<option value = "13">부산/경남</option>
									<option value = "14">대구/경북</option>
									<option value = "15">대전/전북</option>
									<option value = "16">광주/전남</option>
									<option value = "16">그외 지역</option>
								</select>
							</td>
						</tr>
						<tr>
							<td height="1" colspan="2"></td>	
						</tr>
						    
						<tr>
							<td><font color="#FF0000">*</font>종류 카테고리</td>
							<td>
								<select name = "rest_typecategory">
									<option value = "21">한식</option>
									<option value = "22">양식</option>
									<option value = "23">중식</option>
									<option value = "24">일식</option>
									<option value = "25">기타</option>
								</select>
							</td>
						</tr>
						<tr>
							<td height="1" colspan="2"></td>	
						</tr>
						
						
						<tr>
							<td>매인 사진 업로드</td>
							<td>
								<s:file name="upload1" theme="simple"/>
								<s:if test="resultClass.file_orgname1 != NULL">
									&nbsp; * <s:property value="resultClass.file_orgname1" /> 재업로드시 기존파일은 삭제됩니다.
								</s:if>
							</td>
						</tr>
						<tr>
							<td height="1" colspan="2"></td>	
						</tr>
						
						<tr>
							<td>상세 사진 업로드</td>
							<td>
								<s:file name="upload2" theme="simple"/>
								<s:if test="resultClass.file_orgname2 != NULL">
									&nbsp; * <s:property value="resultClass.file_orgname2" /> 재업로드시 기존파일은 삭제됩니다.
								</s:if>
							</td>
						</tr>
						<tr>
							<td height="1" colspan="2"></td>	
						</tr>
						
						
						<tr>
							<td align="right" colspan="2">
								<input name="submit" type="submit" value="상품 등록" />
								<input name="cancel" type="button" value="상품 등록 취소" onClick="javascript:location.href='insertCancelRestForm.action'" />
							</td>
						</tr>
					</table>
					
					
				</form>
				
				
				
				
		</div>
	</body>
</html>
