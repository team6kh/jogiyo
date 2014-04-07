<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<title>상품 상세보기</title>
	</head>
  
  
	<body>
		
		<table border="1" >
			<tr>
				<td align=left>
					<s:if test="resultClass.rest_localcategory == 11">
						서울특별시
					</s:if>
					<s:elseif test="resultClass.rest_localcategory == 12">
						경기/인천
					</s:elseif>
					<s:elseif test="resultClass.rest_localcategory == 13">
						부산/경남
					</s:elseif>
					<s:elseif test="resultClass.rest_localcategory == 14">
						대구/경북
					</s:elseif>
					<s:elseif test="resultClass.rest_localcategory == 15">
						대전/전북
					</s:elseif>
					<s:elseif test="resultClass.rest_localcategory == 16">
						광주/전남
					</s:elseif>
					<s:elseif test="resultClass.rest_localcategory == 17">
						그 외 지역
					</s:elseif>
				</td>
				<td align=left>
					<s:if test="resultClass.rest_typecategory == 21">
						한식
					</s:if>
					<s:elseif test="resultClass.rest_typecategory == 22">
						양식
					</s:elseif>
					<s:elseif test="resultClass.rest_typecategory == 23">
						중식
					</s:elseif>
					<s:elseif test="resultClass.rest_typecategory == 24">
						일식
					</s:elseif>
					<s:elseif test="resultClass.rest_typecategory == 25">
						기타
					</s:elseif>
				</td>
				<td></td> <td></td> <td></td> <td></td>
			</tr>
			<tr>
				<td>
					상품번호
				</td>
				<td>
					<s:property value="resultClass.rest_num" />
				</td>
				<td></td> <td></td> <td></td> <td></td>
			</tr>
			
			
			
			
			
			
			
		<TABLE border=1>
			<TR align=center>
				<td>카테고리</td>
				<td>1</TD>
				<td>2</TD>
				<td>3</TD>
			</TR>
			<TR>
				<TD>상품번호</TD>
				<td>1</TD>
				<td>2</TD>
				<td>3</TD>
			<TR>
				<TD colspan=3 align=center bgcolor=eeffff>
					매인사진
				</TD>
				<TD>
					테스트중...
				</TD>
				<td>
				</td>
			</TR>
		</TABLE>
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			


			<tr>
				<td align="right" colspan="6">
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

