<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page isELIgnored="false" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="assets/ico/jogiyo.png">

<title>JOGIYO</title>

<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="common/common-template.css" rel="stylesheet">

</head>

<body>

	<!-- header -->
	<%@ include file="/common/header.jsp"%>
	<!-- end of header -->

	<!-- container -->
	<div class="container">

		<!-- test message -->
		<div class="common-template">
			<SCRIPT type="text/javascript">
		function validation() {
		
			var frm = document.forms(0);
			
			if(frm.recipe_foodkind.value == "") {
				alert("종류를 입력해주세요.");
				return false;
			} 
			
			else if(frm.recipe_subject.value == "") {
				alert("제목을 입력해주세요.");
				return false;
			}
			
			else if(frm.recipe_writer.value == "") {
				alert("작성자를 입력해주세요.");
				return false;
			}
			
			else if(frm.recipe_password.value == "") {
				alert("비밀번호를 입력해주세요.");
				return false;			
			} 
			
			else if(frm.recipe_method.value == "") {
				alert("비밀번호를 입력해주세요.");
				return false;			
			} 
			
			else if(frm.recipe_password.value == "") {
				alert("비밀번호를 입력해주세요.");
				return false;			
			} 
			else if(frm.recipe_password.value == "") {
				alert("비밀번호를 입력해주세요.");
				return false;			
			} 
			else if(frm.recipe_password.value == "") {
				alert("비밀번호를 입력해주세요.");
				return false;			
			} 
			
			return true;
		}
	</SCRIPT>
<table width="400" border="0" cellspacing="0" cellpadding="2"
	align="center">
	<tr>
		<td align="center"><h2>RECIPE 게시판</h2></td>
	</tr>
</table>
<form method="post" enctype="mutltipart/form-data" action="insertRecipe.action">
	<table width="400" border="1" cellspacing="0" cellpadding="0"
		bgcolor="" align="center">
		<tr>
			<td align="right">종류</td>
			<td align="left" colspan="3">&nbsp;<select
				name="recipe_foodkind">
					<option value="null">선택하세요</option>
					<option value="한식">한식</option>
					<option value="양식">양식</option>
					<option value="일식">일식</option>
					<option value="중식">중식</option>
					<option value="기타">기타</option>
			</select>
		</tr>
		<tr>
			<td width="100" bgcolor="" align="center">제목</td>
			<td width="300" colspan="3"><input type="text"
				name="recipe_subject" value="" /></td>
		</tr>
		<tr>
			<td width="100" bgcolor="" align="center">작성자</td>
			<td width="100"><input type="text" name="recipe_writer" value="" /></td>
			<td width="100" bgcolor="" align="center">비밀번호</td>
			<td width="100"><input type="password" name="recipe_password"
				value="" /></td>
		</tr>
		<tr>
			<td width="100" bgcolor="" align="center">재료</td>
			<td width="300" colspan="3"><input type="text" size="40"
				maxlength="30" name="recipe_method"></td>
		</tr>
		<tr>
			<td width="100" bgcolor="" align="center">소요 시간</td>
			<td width="100"><input type="text" name="recipe_time" value="" /></td>
			<td width="100" bgcolor="" align="center">비용</td>
			<td width="100"><input type="text" name="recipe_price" value="" /></td>
		</tr>
		<tr>
			<td width="100" bgcolor="" align="center">방법 및 상세내용</td>
			<td width="300" colspan="3"><textarea name="recipe_content"
					rows="13" cols="40"></textarea></td>
		</tr>
		<tr>
			<td width="100" bgcolor="" align="center">첨부파일</td>
			<td width="300" colspan="3"><input type="file"
				name="recipe_file" /></td>
		</tr>
		<td colspan="4" bgcolor="" align="center">
		    <input name="submit" type="submit" value="등록"> 
		    <input type="reset" value="다시작성">
			<input name="list" type="button" value="목록보기"
			OnClick="javascript:location.href='listRecipe.action?currentPage=<s:property value="currentPage" />'">
		</td>
		</tr>
	</table>
</form>
		</div>
		<!-- end of test message -->

	</div>
	<!-- /.container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="dist/js/bootstrap.min.js"></script>
</body>
</html>