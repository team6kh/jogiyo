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
<script type="text/javascript">
	function open_win_noresizable(url, name) {
		var oWin = window.open(url, name,
				"scrollbars=no,status=no,resizable=no,width=300,height=150");
	}
</script>
</head>

<body>

	<!-- header -->
	<%@ include file="/common/header.jsp"%>
	<!-- /.header -->

	<!-- container -->
	<div class="container">

		<div class="col-md-12">
			<h3>요리팁 게시판</h3>
		</div>		
			
		<input type="hidden" id="session_id" name="session_id" value="${sessionScope.session_id}" />
		
		<!-- 컨텐츠  -->
		<div class="col-md-12">
		
			<!-- 테이블 윗부분 -->
			<table class="table table-striped text-center">
				<tr>
					<td><h2>${resultClass.recipe_subject}</h2></td>
				</tr>
				<tr>
					<s:if test="#session.session_id != null">
						<td>
							<input name="list" type="button" value="추천" onClick="javascript:document.getElementById('isrecommand').contentWindow.location.href='recommandRecipe.action?currentPage=<s:property value="currentPage" />&recipe_num=<s:property value="recipe_num" />&session_id=<s:property value="#session.session_id"/>'">
						</td>
						<iframe src="blink.html" id="isrecommand" style="display: none;"></iframe>
					</s:if>
				</tr>
			</table>
			<!-- /.테이블 윗부분 -->

			<!-- 테이블 바디 -->
			<table class="table table-striped text-center">
			
				<tr>
					<td colspan="2"></td>
				</tr>

				<tr>
					<td>번호</td>
					<td><s:property value="resultClass.recipe_num" /></td>
				</tr>
				
				<tr>
					<td colspan="2"></td>
				</tr>

				<tr>
					<td>종류</td>
					<td><s:property	value="resultClass.recipe_foodkind" /></td>
				</tr>

				<tr>
					<td colspan="2"></td>
				</tr>

				<tr>
					<td>작성일</td>
					<td><s:property value="resultClass.recipe_reg_date" /></td>
				</tr>
				
				<tr>
					<td height="1" colspan="2"></td>
				</tr>

				<tr>
					<td>제목</td>
					<td><s:property value="resultClass.recipe_subject" /></td>
				</tr>

				<tr>
					<td height="1" colspan="2"></td>
				</tr>
				
				<tr>
					<td>요리명</td>
					<td><s:property value="resultClass.recipe_foodsubject" /></td>
				</tr>
				
				<tr>
					<td colspan="2"></td>
				</tr>

				<tr>
					<td>재료</td>
					<td><s:property value="resultClass.recipe_method" />
					</td>
				</tr>
				
				<tr>
					<td colspan="2"></td>
				</tr>

				<tr>
					<td>소비 시간</td>
					<td><s:property	value="resultClass.recipe_time" />
					</td>
				</tr>
				
				<tr>
					<td colspan="2"></td>
				</tr>

				<tr>
					<td>비용</td>
					<td><s:property value="resultClass.recipe_price" />
					</td>
				</tr>
				
				<tr>
					<td colspan="2"></td>
				</tr>

				<tr>
					<td>회원ID</td>
					<td><s:property value="resultClass.recipe_memberwriter" />
					</td>
				</tr>
				
				<tr>
					<td colspan="2"></td>
				</tr>

				<tr>
					<td>작성자</td>
					<td><s:property value="resultClass.recipe_writer" /></td>
				</tr>
				
				<tr>
					<td colspan="2"></td>
				</tr>

				<tr>
					<td>내용</td>
					<td colspan="3"><s:property value="pagingHtml" escape="false" /></td>
				</tr>
				
				<tr>
					<td colspan="2"></td>
				</tr>

				<tr>
					<td>조회수</td>
					<td><s:property value="resultClass.recipe_readcount" /></td>
				</tr>
				
				<tr>
					<td colspan="2"></td>
				</tr>

				<tr>
					<td>추천수</td>
					<td><s:property value="resultClass.recipe_recommand" />
					</td>
				</tr>
				
				<tr>
					<td colspan="2"></td>
				</tr>

				<tr>
					<td colspan="2"></td>
				</tr>
				
				<tr>
					<td colspan="2">
						<s:url id="updateRecipeURL" action="updateRecipeForm">
							<s:param name="recipe_num">
								<s:property value="recipe_num" />
							</s:param>
						</s:url>
						<s:url id="deleteRecipeURL" action="deleteRecipe">
							<s:param name="recipe_num">
								<s:property value="recipe_num" />
							</s:param>
						</s:url>
						<s:if test="resultClass.recipe_memberwriter == NULL">
							<input name="list" type="button" value="수정"
								onClick="javascript:open_win_noresizable('checkFormRecipe.action?recipe_num=<s:property value="resultClass.recipe_num" />&currentPage=<s:property value="currentPage" />','update')">
							<input name="list" type="button" value="삭제"
								onClick="javascript:open_win_noresizable('checkFormRecipe.action?recipe_num=<s:property value="resultClass.recipe_num" />&currentPage=<s:property value="currentPage" />','delete')">
						</s:if>
						<s:elseif
							test="resultClass.recipe_memberwriter != NULL && resultClass.recipe_memberwriter == #session.session_id">
							<input name="list" type="button" value="수정"
								onClick="javascript:open_win_noresizable('checkFormRecipe.action?recipe_num=<s:property value="resultClass.recipe_num" />&currentPage=<s:property value="currentPage" />','update')">
							<input name="list" type="button" value="삭제"
								onClick="javascript:open_win_noresizable('checkFormRecipe.action?recipe_num=<s:property value="resultClass.recipe_num" />&currentPage=<s:property value="currentPage" />','delete')">
						</s:elseif>
							<input name="list" type="button" value="목록"
								onClick="javascript:location.href='listRecipe.action?currentPage=<s:property value="currentPage" />'">
					</td>
				</tr>

			</table>
			<!-- /.테이블 바디 -->
		
		</div>			
		<!-- /.컨텐츠 -->			

	</div>
	<!-- /.container -->

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="dist/js/bootstrap.min.js"></script>
</body>
</html>