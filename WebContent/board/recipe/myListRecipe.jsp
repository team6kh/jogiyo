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
						var frm = document
								.getElementById("recipe_search_input");

						if ((sel == "recipe_time" || sel == "recipe_price")) {
							frm.innerHTML = "<input type=text name=key1>~<input type=text name=key2><input type=submit value=검색>";
						} else if ((sel == "recipe_subject,content" || sel == "recipe_foodkind")) {
							frm.innerHTML = "<input type=text name=key1><input type=submit value=검색>";
						} else if ((sel == "recipe_searchselect")) {
							frm.innerHTML = "<input type=submit value=검색>";
						}

					}

					function detailsearch() {
						var sel = document
								.getElementById("recipe_search_target").value;

						if (sel == "recipe_detailsearch") {
							document.getElementById('detailsearch').style.display = "block";
						} else if (sel == "null") {
							document.getElementById('detailsearch').style.display = "none";
						}
					}
				</SCRIPT>
			</header>
			<table width="900" border="0" cellspacing="0" cellpadding="2">
				<tr>
					<td align="center"><h2>MyRecipe 목록</h2></td>
				</tr>
				<tr>
					<td height="1" bgcolor="#BDBDBD" align="center">내가 올린 레시피~~!!</td>
				</tr>
				<tr>
					<td height="20"></td>
				</tr>
			</table>


			<table width="900" border="0" cellspacing="0" cellpadding="2">
				<tr align="center" bgcolor="">
					<td width="50"><strong>번호</strong></td>
					<td width="50"><strong>종류</strong></td>
					<td width="350"><strong>제목</strong></td>
					<td width="100"><strong>요리명</strong></td>
					<td width="70"><strong>작성자</strong></td>
					<td width="80"><strong>작성일</strong></td>
					<td width="50"><strong>소비시간</strong></td>
					<td width="50"><strong>비용</strong></td>
					<td width="50"><strong>조회수</strong></td>
					<td width="50"><strong>추천수</strong></td>
				</tr>
				<tr bgcolor="#777777">
					<td height="1" colspan="10"></td>
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
						<td align="center"><s:property value="recipe_foodkind" /></td>
						<td align="center">&nbsp;<s:a href="%{viewURL}">
								<s:property value="recipe_subject" />
							</s:a></td>
						<td align="center"><s:property value="recipe_foodsubject" /></td>
						<td align="center"><s:property value="recipe_writer" /></td>
						<td align="center"><s:property value="recipe_reg_date" /></td>
						<td align="center"><s:property value="recipe_time" /></td>
						<td align="center"><s:property value="recipe_price" /></td>
						<td align="center"><s:property value="recipe_readcount" /></td>
						<td align="center"><s:property value="recipe_recommand" /></td>


					</tr>
					<tr bgcolor="#777777">
						<td height="1" colspan="10"></td>
					</tr>

				</s:iterator>

				<s:if test="list.size() <= 0">

					<tr bgcolor="#FFFFFF" align="center">
						<td colspan="10">등록된 게시물이 없습니다.</td>
					</tr>
					<tr bgcolor="#777777">
						<td height="1" colspan="10"></td>
					</tr>

				</s:if>

				<tr align="center">
					<td colspan="10"><s:property value="pagingHtml" escape="false" /></td>
				</tr>

				<tr align="right">
					<td colspan="10"><input type="button" value="글쓰기" onClick="javascript:location.href='insertRecipeForm.action?currentPage=<s:property value="currentPage" />';">
						<input type="button" value="새로고침" 	onClick="javascript:location.href='myListRecipe.action?session_id=<s:property value="#session.session_id" />';">
					</td>
				</tr>
			</table>
			<br />

			<table width="900" border="0" cellspacing="0" cellpadding="2">
				<tr>
					<td colspan="10" align="center">
						<!-- 검색 jsp -->
						<form name="recipe_search" action="recipe_search.action" enctype="multipart/form-data">
							<select name="recipe_search_target" id="recipe_search_target"
								title="검색" onchange="detailsearch()">
								<option value="null">검색[선택]</option>
								<option value="recipe_detailsearch">상세검색</option>
							</select> <input name="mylist" type="button" value="전체글목록보기"
								onClick="javascript:location.href='listRecipe.action?currentPage=<s:property value="currentPage"  />';">

							<div id="detailsearch" style="display: none">
								<table width="667" border="1" cellpadding="0" cellspacing="0">


									<tr>
										<td align="center">종류</td>
										<td><select name="recipe_foodkind" style="width: 120px"
											id="statsType">
												<option value="">선택하세요</option>
												<option value="한식">한식</option>
												<option value="중식">중식</option>
												<option value="일식">일식</option>
												<option value="양식">양식</option>
												<option value="기타">기타</option>
										</select></td>
										<td align="center">작성자</td>
										<td><input type="text" name="recipe_writerinput"></td>
									</tr>
									<tr>
										<td align="center">요리명</td>
										<td colspan="3"><input type="text" name="recipe_foodnameinput"></td>

									</tr>
									<tr>
										<td align="center">제목+내용</td>
										<td colspan="3"><input type="text" name="recipe_subjectinput"></td>

									</tr>
									<tr>
										<td align="center">소요시간</td>
										<td colspan="3"><input type="text" id="recipe_timeinput1" size="5">&nbsp;~<input
											type="text" id="recipe_timeinput2" size="5"></td>
									</tr>
									<tr>
										<td align="center">비용</td>
										<td colspan="3"><input type="text" id="recipe_priceinput1" size="5">&nbsp;~<input
											type="text" id="recipe_priceinput2" size="5"></td>
									</tr>
									<tr>

										<td align="center" colspan="4"><input type="reset" value="초기화" />&nbsp;<input
											type="submit" value="검색" /></td>
									</tr>
								</table>
							</div>
						</form>
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