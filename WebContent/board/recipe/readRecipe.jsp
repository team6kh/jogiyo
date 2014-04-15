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
			<head>
<title>스트럿츠2 게시판</title>
<link rel="stylesheet" href="/StrutsBoard/board/common/css/css.css"
	type="text/css">
<script type="text/javascript">
	function open_win_noresizable(url, name) {
		var oWin = window.open(url, name,
				"scrollbars=no,status=no,resizable=no,width=300,height=150");
	}
</script>
			</head>

			<body>

				<table width="600" border="0" cellspacing="0" cellpadding="2">
					<tr>
						<td align="center"><h2>${resultClass.recipe_subject}</h2></td>
					</tr>

					<tr>
						<td height="20" align="right"><input name="list" type="button" value="추천" onClick="javascript:location.href='recommand.action?currentPage=<s:property value="currentPage" />&recipe_num=<s:property value="recipe_num" />'"></td>
					</tr>
				</table>

				<table width="600" border="0" cellspacing="0" cellpadding="0">

					<tr bgcolor="#777777">
						<td height="1" colspan="2"></td>
					</tr>

					<tr>
						<td bgcolor="#F4F4F4">번호</td>
						<td bgcolor="#FFFFFF">&nbsp;&nbsp;<s:property value="resultClass.recipe_num" />
						</td>
					</tr>
					<tr bgcolor="#777777">
						<td height="1" colspan="2"></td>
					</tr>

					<tr>
						<td width="100" bgcolor="#F4F4F4">종류</td>
						<td width="500" bgcolor="#FFFFFF">&nbsp;&nbsp;<s:property
								value="resultClass.recipe_foodkind" />
						</td>
					</tr>

					<tr bgcolor="#777777">
						<td height="1" colspan="2"></td>
					</tr>

					<tr>
						<td bgcolor="#F4F4F4">작성일</td>
						<td bgcolor="#FFFFFF">&nbsp;&nbsp;<s:property
								value="resultClass.recipe_reg_date" />
						</td>
					</tr>
					<tr bgcolor="#777777">
						<td height="1" colspan="2"></td>
					</tr>

					<tr>
						<td bgcolor="#F4F4F4">제목</td>
						<td bgcolor="#FFFFFF">&nbsp;&nbsp;<s:property
								value="resultClass.recipe_subject" />
						</td>
					</tr>

					<tr bgcolor="#777777">
						<td height="1" colspan="2"></td>
					</tr>
					<tr>
						<td bgcolor="#F4F4F4">요리명</td>
						<td bgcolor="#FFFFFF">&nbsp;&nbsp;<s:property
								value="resultClass.recipe_foodsubject" />
						</td>
					</tr>
					<tr bgcolor="#777777">
						<td height="1" colspan="2"></td>
					</tr>

					<tr>
						<td bgcolor="#F4F4F4">재료</td>
						<td bgcolor="#FFFFFF">&nbsp;&nbsp;<s:property
								value="resultClass.recipe_method" />
						</td>
					</tr>
					<tr bgcolor="#777777">
						<td height="1" colspan="2"></td>
					</tr>

					<tr>
						<td bgcolor="#F4F4F4">소비 시간</td>
						<td bgcolor="#FFFFFF">&nbsp;&nbsp;<s:property
								value="resultClass.recipe_time" />
						</td>
					</tr>
					<tr bgcolor="#777777">
						<td height="1" colspan="2"></td>
					</tr>

					<tr>
						<td bgcolor="#F4F4F4">비 용</td>
						<td bgcolor="#FFFFFF">&nbsp;&nbsp;<s:property
								value="resultClass.recipe_price" />
						</td>
					</tr>
					<tr bgcolor="#777777">
						<td height="1" colspan="2"></td>
					</tr>

					<tr>
						<td bgcolor="#F4F4F4">회원ID</td>
						<td bgcolor="#FFFFFF">&nbsp;&nbsp;<s:property
								value="resultClass.recipe_memberwriter" />
						</td>
					</tr>
					<tr bgcolor="#777777">
						<td height="1" colspan="2"></td>
					</tr>

					<tr>
						<td bgcolor="#F4F4F4">작성자</td>
						<td bgcolor="#FFFFFF">&nbsp;&nbsp;<s:property
								value="resultClass.recipe_writer" />
						</td>
					</tr>
					<tr bgcolor="#777777">
						<td height="1" colspan="2"></td>
					</tr>

					<tr>
						<td bgcolor="#F4F4F4">내용</td>
						<td bgcolor="#FFFFFF">&nbsp;&nbsp;<s:property
								value="resultClass.recipe_content" />
						</td>
					</tr>
					<tr bgcolor="#777777">
						<td height="1" colspan="2"></td>
					</tr>

					<tr>
						<td bgcolor="#F4F4F4">조회수</td>
						<td bgcolor="#FFFFFF">&nbsp;&nbsp;<s:property
								value="resultClass.recipe_readcount" />
						</td>
					</tr>
					<tr bgcolor="#777777">
						<td height="1" colspan="2"></td>
					</tr>

					<tr>
						<td bgcolor="#F4F4F4">추천수</td>
						<td bgcolor="#FFFFFF">&nbsp;&nbsp;<s:property
								value="resultClass.recipe_recommand" />
						</td>
					</tr>
					<tr bgcolor="#777777">
						<td height="1" colspan="2"></td>
					</tr>

					<tr>
						<td bgcolor="#F4F4F4">첨부파일</td>
						<td bgcolor="#FFFFFF">&nbsp;&nbsp; <s:url id="download"
								action="fileDownloadAction">
								<s:param name="recipe_num">
									<s:property value="recipe_num" />
								</s:param>
							</s:url> <s:a href="%{download}">
								<s:property value="resultClass.recipe_orgfile" />
							</s:a>
						</td>
					</tr>
					<tr bgcolor="#777777">
						<td height="1" colspan="2"></td>
					</tr>

					<tr>
						<td height="10" colspan="2"></td>
					</tr>


					<tr>
						<td align="right" colspan="2"><s:url id="updateRecipeURL"
								action="updateRecipeForm">
								<s:param name="recipe_num">
									<s:property value="recipe_num" />
								</s:param>
							</s:url> <s:url id="deleteRecipeURL" action="deleteRecipe">
								<s:param name="recipe_num">
									<s:property value="recipe_num" />
								</s:param>
							</s:url> 
							<input name="list" type="button" value="수정" class="inputb" onClick="javascript:open_win_noresizable('checkForm.action?recipe_num=<s:property value="resultClass.recipe_num" />&currentPage=<s:property value="currentPage" />','update')">
							<input name="list" type="button" value="삭제" class="inputb" 	onClick="javascript:open_win_noresizable('checkForm.action?recipe_num=<s:property value="resultClass.recipe_num" />&currentPage=<s:property value="currentPage" />','delete')">
							<input name="list" type="button" value="목록" class="inputb" 	onClick="javascript:location.href='listRecipe.action?currentPage=<s:property value="currentPage" />'">

						</td>
					</tr>

				</table>
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