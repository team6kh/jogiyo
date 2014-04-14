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
					function searchtype() {
						var sel = document
								.getElementById("recipe_search_target").value;
						var frm = document.getElementById("recipe_search_input");

						if ((sel == "recipe_time" || sel == "recipe_price")) {
							frm.innerHTML = "<input type=text name=key1>~<input type=text name=key2><input type=submit value=검색>";
						} else if((sel == "recipe_subject,content" || sel=="recipe_foodkind")){
							frm.innerHTML = "<input type=text name=key1><input type=submit value=검색>";
						}else if((sel == "recipe_searchselect")){
							frm.innerHTML = "<input type=submit value=검색>";
						}
						
					}
				</SCRIPT>
			</header>
			<table width="750" border="0" cellspacing="0" cellpadding="2">
				<tr>
					<td align="center"><h2> MyRecipe 목록</h2></td>
				</tr>
				<tr>
					<td height="1" bgcolor="#BDBDBD" align="center">내가 올린 레시피~~!!</td>
				</tr>
				<tr>
					<td height="20"></td>
				</tr>
			</table>


			<table width="750" border="0" cellspacing="0" cellpadding="2">
				<tr align="center" bgcolor="">
					<td width="50"><strong>번호</strong></td>
					<td width="350"><strong>제목</strong></td>
					<td width="70"><strong>작성자</strong></td>
					<td width="80"><strong>작성일</strong></td>
					<td width="50"><strong>소비시간</strong></td>
					<td width="50"><strong>비용</strong></td>
					<td width="50"><strong>조회수</strong></td>
					<td width="50"><strong>추천수</strong></td>
				</tr>
				<tr bgcolor="#777777">
					<td height="1" colspan="8"></td>
				</tr>

				<s:iterator value="list" status="stat">

					<s:url id="viewURL" action="readRecipe">
						<s:param name="recipe_num">
							<s:property value="recipe_num" />
						</s:param>
						<s:param name="currentPage">
							<s:property value="currentPage" />
						</s:param>
					</s:url>

					<tr bgcolor="#FFFFFF" align="center">
						<td><s:property value="recipe_num" /></td>
						<td align="center">&nbsp;<s:a href="%{viewURL}">
								<s:property value="recipe_subject" />
							</s:a></td>
						<td align="center"><s:property value="recipe_writer" /></td>
						<td align="center"><s:property value="recipe_reg_date" /></td>
						<td align="center"><s:property value="recipe_time" /></td>
						<td align="center"><s:property value="recipe_price" /></td>
						<td align="center"><s:property value="recipe_readcount" /></td>
						<td align="center"><s:property value="recipe_recommand" /></td>


					</tr>
					<tr bgcolor="#777777">
						<td height="1" colspan="8"></td>
					</tr>

				</s:iterator>

				<s:if test="list.size() <= 0">

					<tr bgcolor="#FFFFFF" align="center">
						<td colspan="8">등록된 게시물이 없습니다.</td>
					</tr>
					<tr bgcolor="#777777">
						<td height="1" colspan="8"></td>
					</tr>

				</s:if>

				<tr align="center">
					<td colspan="8"><s:property value="pagingHtml" escape="false" /></td>
				</tr>

				<tr align="right">
					<td colspan="8">
					    <input type="button" value="글쓰기"  onClick="javascript:location.href='insertRecipeForm.action?currentPage=<s:property value="currentPage" />';">
						<input type="button" value="새로고침"  onClick="javascript:location.href='myListRecipe.action?currentPage=<s:property value="currentPage" />';">
					</td>
				</tr>
			</table>
			<br />

			<table width="750" border="0" cellspacing="0" cellpadding="2">
				<tr>
					<td colspan="8" align="center">
						<!-- 검색 jsp --> 
						<select name="recipe_search_target" id="recipe_search_target" title="검색" onchange="searchtype()">
							<option value="recipe_searchselect">검색[선택]</option>
							<option value="recipe_foodkind">종류[한식,양식,중식,일식,기타]</option>
							<option value="recipe_subject,content">내용+제목</option>
							<option value="recipe_time">소요시간</option>
							<option value="recipe_price">비용</option>
							
					</select> <span id="recipe_search_input" > </span><input type="button" value="전체목록보기"  onClick="javascript:location.href='listRecipe.action?currentPage=<s:property value="currentPage" />';"> 
					</td>
				</tr>
			</table>
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