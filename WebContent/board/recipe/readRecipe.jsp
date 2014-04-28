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
<link href="jogiyo.css" rel="stylesheet">
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
			<input type="hidden" id="session_id" name="session_id" value="${sessionScope.session_id}" />
            
			<body>

				<table width="600" border="0" cellspacing="0" cellpadding="2">
					<tr>
						<td align="center"><h2>${resultClass.recipe_subject}</h2></td>
					</tr>
					<tr>
					<s:if test="#session.session_id != null">
						
						<td height="20" align="right"><input name="list" type="button" value="추천" onClick="javascript:document.getElementById('isrecommand').contentWindow.location.href='recommandRecipe.action?currentPage=<s:property value="currentPage" />&recipe_num=<s:property value="recipe_num" />&session_id=<s:property value="#session.session_id"/>'"></td>
                         <iframe src="blink.html" id ="isrecommand" style="display:none;"></iframe>					
					</s:if>
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
						<td bgcolor="#FFFFFF" height="600" colspan="3" align="left">
          			&nbsp;&nbsp;<s:property value="pagingHtml" escape="false" />
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
							<s:if test="resultClass.recipe_memberwriter == NULL">
							<input name="list" type="button" value="수정" onClick="javascript:open_win_noresizable('checkFormRecipe.action?recipe_num=<s:property value="resultClass.recipe_num" />&currentPage=<s:property value="currentPage" />','update')">
							<input name="list" type="button" value="삭제" onClick="javascript:open_win_noresizable('checkFormRecipe.action?recipe_num=<s:property value="resultClass.recipe_num" />&currentPage=<s:property value="currentPage" />','delete')">
							</s:if>
							<s:elseif test="resultClass.recipe_memberwriter != NULL && resultClass.recipe_memberwriter == #session.session_id">
							<input name="list" type="button" value="수정" onClick="javascript:open_win_noresizable('checkFormRecipe.action?recipe_num=<s:property value="resultClass.recipe_num" />&currentPage=<s:property value="currentPage" />','update')">
							<input name="list" type="button" value="삭제" onClick="javascript:open_win_noresizable('checkFormRecipe.action?recipe_num=<s:property value="resultClass.recipe_num" />&currentPage=<s:property value="currentPage" />','delete')">
							</s:elseif>
							<input name="list" type="button" value="목록" onClick="javascript:location.href='listRecipe.action?currentPage=<s:property value="currentPage" />'">

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