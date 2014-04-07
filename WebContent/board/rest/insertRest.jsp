<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


	<head>
		<title>상품 등록 페이지</title>
	
		<script type="text/javascript">
			var fields = 1;
		
			function addInput() {
				if (fields != 16) {
					document.getElementById('text').innerHTML += "옵션명"+fields+".&nbsp&nbsp <input type='text' name='restopt_subject"+fields+"' value=''/> <br/>"
																+"옵션가"+fields+".&nbsp&nbsp <input type='text' name='restopt_priceplus"+fields+"' value=''/> 원<br/>";
					fields += 1;
					optMap.put("restopt_subject"+fields,"restopt_priceplus"+fields);
				} else {
					document.getElementById('stop').innerHTML += "<font color='#FF0000'>옵션은 최대 15개만 등록할 수 있습니다.</font><br/>";
					document.form.add.disabled=true;
				}
			}
		
		
			function validation() {
				var frm = document.getElementById("insertRestForm");
				
				if(frm.rest_subject.value == "") {
					alert("상품명을 입력해주세요.");
					return false;
				} 
				else if(frm.rest_price.value == "0") {
					alert("상품가격을 입력해주세요.");
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
				
				if(frm.rest_subject.value == "") {
					alert("옵션명을 입력해주세요.");
					return false;
				} 
				else if(frm.rest_price.value == "") {
					alert("옵션가격을 입력해주세요.");
					return false;
				}
				return true;
			}
			
			
			
			
		</script>
	</head>
  
	<body>
		<table width="600" border="0" cellspacing="0" cellpadding="2">
			<tr>
				<td align="center">
					<h2>상품 등록 페이지</h2>
				</td>
			</tr>
		</table>
		
		
		
		<s:if test="rest_price == 0">
			<form name="insertRestForm" action="insertRest.action" method="post" enctype="multipart/form-data" onsubmit="return validation();">
				<!-- 임시 히든값 -->
				<s:hidden name="rest_writer_name" value="히든판매자이름" />
				<s:hidden name="rest_writer_telnum" value="히든02-123-1234" />
				<s:hidden name="rest_writer_mobilenum" value="히든010-1234-1234" />
				<s:hidden name="rest_writer_address" value="히든서울시 영등포구" />
			
			
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
						<td><font color="#FF0000">*</font>상품가격</td>
						<td>
							<s:textfield name="rest_price" theme="simple" value="%{resultClass.rest_price}" maxlength="20"/>
						</td>
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
							<input name="list" type="button" value="상품 등록 취소" onClick="javascript:location.href='ListRestAction.action?currentPage=<s:property value="currentPage"/>'" />
						</td>
					</tr>
				</table>
			</form>
			
			
			
	</body>
</html>

