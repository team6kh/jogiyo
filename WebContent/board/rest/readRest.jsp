<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<title>상품 상세보기</title>
	</head>
  
  
	<body>
		<table border="1" cellspacing="0" cellpadding="2">
			<tr>
				<td align="center"><h2>상품 상세보기</h2></td>
			</tr>
			<tr>
				<td height="20"></td>
			</tr>
		</table>
		
		<table width="600" border="0" cellspacing="0" cellpadding="0">
			<tr bgcolor="#777777">
				<td height="1" colspan="2"></td>
			</tr>
			<tr>
				<td bgcolor="#F4F4F4">  상품번호 </td>
				<td bgcolor="#FFFFFF">
				&nbsp;&nbsp;<s:property value="resultClass.rest_num" />
				</td>
			</tr>
			<tr bgcolor="#777777">
  				<td height="1" colspan="2"></td>	
			</tr>
			<tr>
				<td width="100" bgcolor="#F4F4F4">  상품명</td>
				<td width="500" bgcolor="#FFFFFF">
				&nbsp;&nbsp;<s:property value="resultClass.rest_subject" />
				</td>
			</tr>
			<tr bgcolor="#777777">
				<td height="1" colspan="2"></td>
			</tr>
			<tr>
				<td bgcolor="#F4F4F4">  판매자명 </td>
				<td bgcolor="#FFFFFF">
					&nbsp;&nbsp;<s:property value="resultClass.rest_writer_name" />
				</td>
			</tr>
			<tr bgcolor="#777777">
				<td height="1" colspan="2"></td>	
			</tr>

			<tr>
				<td bgcolor="#F4F4F4">매인사진</td>
				<td bgcolor="#FFFFFF">
					<img src = "<s:property value="resultClass.rest_destFile1" />"/>
				</td>
			</tr>
			<tr bgcolor="#777777">
				<td height="1" colspan="2"></td>
			</tr>
			<tr>
				<td bgcolor="#F4F4F4">  조회수 </td>
				<td bgcolor="#FFFFFF">
					&nbsp;&nbsp;<s:property value="resultClass.rest_readcount" />
				</td>
			</tr>
			<tr bgcolor="#777777">
				<td height="1" colspan="2"></td>	
			</tr>
			<tr>
				<td bgcolor="#F4F4F4">  등록날짜 </td>
				<td bgcolor="#FFFFFF">
					&nbsp;&nbsp;<s:property value="resultClass.rest_reg_date" />
				</td>
			</tr>
			<tr bgcolor="#777777">
				<td height="1" colspan="2"></td>	
			</tr>
			<tr>
				<td bgcolor="#F4F4F4">  상세사진 </td>
				<td bgcolor="#FFFFFF">
					<img src = "<s:property value="resultClass.rest_destFile2" />"/>
				</td>
			</tr>
			
			
			<tr bgcolor="#777777">
				<td height="1" colspan="2"></td>	
			</tr>
			<tr>
				<td height="10" colspan="2"></td>
			</tr>


			<tr>
				<td align="right" colspan="2">
					<!--
					<input name="list" type="button" value="수정" class="inputb" onClick="javascript:location.href='modifyRest.action?rest_num=<s:property value="rest_num" />&currentPage=<s:property value="currentPage" />'">
					<input name="list" type="button" value="삭제" class="inputb" onClick="javascript:location.href='deleteRest.action?rest_num=<s:property value="rest_num" />&currentPage=<s:property value="currentPage" />'">
					 -->
					<input name="list" type="button" value="목록"  onClick="javascript:location.href='listRest.action?currentPage=<s:property value="currentPage" />'">
				</td>
			</tr>

		</table>
	</body>
</html>

