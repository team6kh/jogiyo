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

<!-- 스크립트 -->
<SCRIPT type="text/javascript">
	function detailsearch() {
		var sel = document.getElementById("recipe_search_target").value;

		if (sel == "recipe_detailsearch") {
			document.getElementById('detailsearch').style.display = "block";
		} else if (sel == "null") {
			document.getElementById('detailsearch').style.display = "none";
		}
	}

	function recipeboardrules() {

		alert("해당 게시판은 Recipe 게시판으로 요리 Recipe만을 공유하는 곳입니다.~ 다른 사항으로 이용하지 말아주세요~!! 다른 내용 이용시 바로 삭제 조치 합니다.!!");
	}

	function recipe_readcountarray() {
		//var click = false;
		//if(click==false){

		//click = true;
		document.location.href = 'readcountRecipeDesc.action';
		//}if(click==true){
		//	click = false;
		//	window.location.href='readcountRecipeAsc.action';
		//}
	}

	function recipe_timearray() {
		document.location.href = 'timeRecipeDesc.action';
	}

	function recipe_pricearray() {
		document.location.href = 'priceRecipeDesc.action';
	}

	function recipe_recommendarray() {
		document.location.href = 'recommendRecipeDesc.action';
	}
</SCRIPT>

</head>

<body>

	<!-- header -->
	<%@ include file="/common/header.jsp"%>
	<!-- end of header -->

	<!-- container -->
	<div class="container">
	
		<!-- 요리팁 게시판 -->
		<div class="col-md-12">
			<h3>요리팁 게시판</h3>
		</div>
		<!-- /.요리팁 게시판 -->

		<!-- 게시판 사용 지침 -->
		<div id=topOfRecipe class="col-md-12">
			<!-- 알고 있는 요리 레시피를 올려주세요~~! 혼자만 알고 있음, 안~돼!!<br/> -->
			<input type="button" class="btn btn-danger pull-right" value="게시판 사용 지침" onClick="recipeboardrules()"><br /><br />
		</div>
		<!-- /.게시판 사용 지침 -->

		<!-- 게시판 바디 -->
		<div class="col-md-12">
			<table class="table table-hover text-center">
				<tr>
					<td><strong>번호</strong></td>
					<td><strong>종류</strong></td>
					<td><strong>제목</strong></td>
					<td><strong>요리명</strong></td>
					<td><strong>작성자</strong></td>
					<td><strong>작성일</strong></td>
					<td><strong><a href="javascript:return false;" onClick="recipe_timearray()">소비시간</strong></td>
					<td><strong><a href="javascript:return false;" onClick="recipe_pricearray()">비용</strong></td>
					<td><strong><a href="javascript:return false;" onClick="recipe_readcountarray()">조회수</a></strong></td>
					<td><strong><a href="javascript:return false;" onClick="recipe_recommendarray()">추천수</strong></td>
				</tr>

				<s:iterator value="list" status="stat">
					<s:url id="viewURL" action="readRecipe">
						<s:param name="recipe_num">
							<s:property value="recipe_num" />
						</s:param>
						<s:param name="currentPage">
							<s:property value="currentPage" />
						</s:param>
						<s:param name="session_id">
							<s:property value="#session.session_id" />
						</s:param>
					</s:url>

					<tr>
						<td><s:property value="recipe_num" /></td>
						<td><s:property value="recipe_foodkind" /></td>
						<td><s:a href="%{viewURL}"><s:property value="recipe_subject" /></s:a></td>
						<td><s:property value="recipe_foodsubject" /></td>
						<td><s:property value="recipe_writer" /></td>
						<td><s:property value="recipe_reg_date" /></td>
						<td><s:property value="recipe_time" />&nbsp;분</td>
						<td><s:property value="recipe_price" />&nbsp;원</td>
						<td><s:property value="recipe_readcount" /></td>
						<td><s:property value="recipe_recommend" /></td>
					</tr>

				</s:iterator>

				<!-- 글이 없을 때 -->
				<s:if test="list.size() <= 0">
					<tr bgcolor="#FFFFFF" align="center">
						<td colspan="10">등록된 게시물이 없습니다.</td>
					</tr>
					<tr bgcolor="#777777">
						<td height="1" colspan="10"></td>
					</tr>
				</s:if>
				<!-- /.글이 없을 때 -->

			</table>
		</div>
		<!-- /.게시판 바디 -->

		<!-- 페이징 -->
		<div class="text-center">
			<ul class="pagination pagination-sm">
				<s:property value="pagingHtml" escape="false" />
			</ul>
		</div>
		<!-- /.페이징 -->

		<!-- 버튼 -->
		<div class="col-md-12">
			<div class="form-inline pull-right">
				<!-- 검색[선택] -->
				<select class="form-control" name="recipe_search_target" id="recipe_search_target" title="검색" onchange="detailsearch()">
					<option value="null">검색[선택]</option>
					<option value="recipe_detailsearch">상세검색</option>
				</select>
				<!-- /.검색[선택] -->
				<!-- 내가 쓴 글 -->
				<s:if test="#session.session_id != null">
					<input name="mylist" type="button" class="btn btn-default" value="마이 레시피" onClick="javascript:location.href='listMyRecipe.action?session_id=<s:property value="#session.session_id" />';">
				</s:if>
				<!-- /.내가 쓴 글 -->
				<input type="button" class="btn btn-primary" value="글쓰기" onClick="javascript:location.href='insertRecipeForm.action?currentPage=<s:property value="currentPage" />';">
				<!-- <input type="button" value="새로고침" onClick="javascript:location.href='listRecipe.action?currentPage=<s:property value="currentPage" />';">  -->
			</div>
		</div>
		<!-- /.버튼 -->

		<br />
		<br />

		<!-- 상세검색 폼 -->
		<form name="recipe_search" action="searchRecipe.action" enctype="multipart/form-data">

			<!-- 상세검색 시에 나타난다. -->
			<div id="detailsearch" style="display: none">
				<div class="col-md-12">
					<table class="table table-condensed">
						<tr>
							<td class="text-center">종류</td>
							<td>
								<select name="recipe_foodkind" style="width: 120px" id="recipe_foodkind">
									<option value="">선택하세요</option>
									<option value="한식">한식</option>
									<option value="중식">중식</option>
									<option value="일식">일식</option>
									<option value="양식">양식</option>
									<option value="기타">기타</option>
								</select>
							</td>
							<td>작성자</td>
							<td><input type="text" name="recipe_writerinput"></td>
						</tr>
						<tr>
							<td class="text-center">요리명</td>
							<td colspan="3"><input type="text" name="recipe_foodnameinput"></td>
						</tr>
						<tr>
							<td class="text-center">제목+내용</td>
							<td colspan="3"><input type="text" name="recipe_subjectinput"></td>
						</tr>
						<tr>
							<td class="text-center">소요시간</td>
							<td colspan="3">
								<input type="text" name="recipe_timeinput1" size="5">&nbsp;~&nbsp;<input type="text" name="recipe_timeinput2" size="5">
							</td>
						</tr>
						<tr>
							<td class="text-center">비용</td>
							<td colspan="3">
								<input type="text" name="recipe_priceinput1" size="5">&nbsp;~&nbsp;<input type="text" name="recipe_priceinput2" size="5">
							</td>
						</tr>
						<tr>
							<td class="text-right" colspan="4">
								<input type="reset" class="btn btn-default" value="초기화" />&nbsp;<input type="submit" class="btn btn-primary" value="검색">
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!--/.상세검색 시에 나타난다. -->
			
		</form>
		<!-- /.상세검색 폼 -->
		
	</div>
	<!-- /.container -->

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="dist/js/bootstrap.min.js"></script>
</body>
</html>