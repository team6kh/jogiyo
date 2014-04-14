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
			<header>
				<SCRIPT type="text/javascript">
					function validation() {

						var frm = document.forms(0);

						if (frm.recipe_foodkind.value == "") {
							alert("종류를 입력해주세요.");
							return false;
						}

						else if (frm.recipe_subject.value == "") {
							alert("제목을 입력해주세요.");
							return false;
						}

						else if (frm.recipe_writer.value == "") {
							alert("작성자를 입력해주세요.");
							return false;
						}

						else if (frm.recipe_password.value == "") {
							alert("비밀번호를 입력해주세요.");
							return false;
						}

						else if (frm.recipe_method.value == "") {
							alert("재료를 입력해주세요.");
							return false;
						}

						else if (frm.recipe_time.value == "") {
							alert("소요시간을 입력해주세요.");
							return false;
						} else if (frm.recipe_price.value == "") {
							alert("비용을 입력해주세요.");
							return false;
						} else if (frm.recipe_content.value == "") {
							alert("방법 및 상세 내용을 입력해주세요.");
							return false;
						}

						return true;
					}
				</SCRIPT>
			</header>

			<body>
				<table width="400" border="0" cellspacing="0" cellpadding="2"
					align="center">
					<tr>
						<td align="center"><h2>RECIPE 게시판</h2></td>
					</tr>
				</table>

				<s:if test="resultClass == NULL">
					<form action="insertRecipe.action" method="post" enctype="multipart/form-data" onsubmit="return validation();">
				</s:if>

				<s:else>
					<form action="updateRecipe.action" method="post" enctype="multipart/form-data">
						<s:hidden name="recipe_num" value="%{resultClass.recipe_num}" />
						<s:hidden name="currentPage" value="%{currentPage}" />
						<s:hidden name="old_file" value="%{resultClass.recipe_file}" />
				</s:else>

				<table width="400" border="1" cellspacing="0" cellpadding="0" bgcolor="" align="center">
					<tr>
						<td align="right">종류</td>
						<td align="left" colspan="3">&nbsp;<select name="recipe_foodkind" id="recipe_foodkind" value="${resultClass.recipe_foodkind}">
								<option value="">선택하세요</option>
								<option value="한식">한식</option>
								<option value="양식">양식</option>
								<option value="일식">일식</option>
								<option value="중식">중식</option>
								<option value="기타">기타</option>
	    					</select>
					</tr>
					<tr>
						<td width="100" bgcolor="" align="center">제목</td>
						<td width="300" colspan="3" align="left">
						   <input type="text" name="recipe_subject" value="${resultClass.recipe_subject}" /></td>
					</tr>
					<tr>
						<td width="100" bgcolor="" align="center">작성자</td>
						<td width="100" align="left">
						 
						   <input type="text" name="recipe_writer" value="${resultClass.recipe_writer}" /></td>
						<td width="100" bgcolor="" align="center">비밀번호</td>
						<td width="100" align="left">
						 
						   <input type="password" name="recipe_password" value="${resultClass.recipe_password}" /></td>
					</tr>
					<tr>
						<td width="100" bgcolor="" align="center">재료</td>
						<td width="300" colspan="3" align="left">
						   <input type="text" size="40" maxlength="30" name="recipe_method" value="${resultClass.recipe_method}"></td>
					</tr>
					<tr>
						<td width="100" bgcolor="" align="center">소요 시간</td>
						<td width="100" align="left">
						   <input type="text" name="recipe_time" value="${resultClass.recipe_time}" /></td>
						<td width="100" bgcolor="" align="center">비용</td>
						<td width="100" align="left">
						   <input type="text" name="recipe_price" value="${resultClass.recipe_price}" /></td>
					</tr>
					<tr>
						<td width="100" bgcolor="" align="center">방법 및 상세내용</td>
						<td width="300" colspan="3" align="left">
						    <s:textarea name="recipe_content" theme="simple" value="%{resultClass.recipe_content}" rows="13" cols="40"  /></td>
					</tr>
					<tr>
						<td width="100" bgcolor="" align="center">첨부파일</td>
						<td width="300" colspan="3" align="left">
						        <s:file name="recipe_file" theme="simple" /> 
								
								<s:if test="resultClass.recipe_orgfile != NULL">
		                     &nbsp;  <s:property value="resultClass.recipe_orgfile" /> 파일이 등록되어 있습니다. 다시 업로드하면 기존의 파일은 삭제됩니다.
	                            </s:if>
	                    </td>
					</tr>
					<td colspan="4" bgcolor="" align="center"><input name="submit" type="submit" value="등록"> <input type="reset" value="다시작성">
						<input name="list" type="button" value="목록보기" OnClick="javascript:location.href='listRecipe.action?currentPage=<s:property value="currentPage" />'">
					</td>
					</tr>
				</table>
				</form>
			</body>
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