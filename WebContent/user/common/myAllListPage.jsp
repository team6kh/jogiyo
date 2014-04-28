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
<link href="user/common/dashboard.css" rel="stylesheet">
<link href="common/common-template.css" rel="stylesheet">

<!-- 스크립트 -->
<script type="text/javascript">
	function searchtype() {
		var sel = document.getElementById("recipe_search_target").value;
		var frm = document.getElementById("recipe_search_input");

		if ((sel == "recipe_time" || sel == "recipe_price")) {
			frm.innerHTML = "<input type=text name=key1>~<input type=text name=key2><input type=submit value=검색>";
		} else if ((sel == "recipe_subject,content" || sel == "recipe_foodkind")) {
			frm.innerHTML = "<input type=text name=key1><input type=submit value=검색>";
		} else if ((sel == "recipe_searchselect")) {
			frm.innerHTML = "<input type=submit value=검색>";
		}

	}

	function detailsearch() {
		var sel = document.getElementById("recipe_search_target").value;

		if (sel == "recipe_detailsearch") {
			document.getElementById('detailsearch').style.display = "block";
		} else if (sel == "null") {
			document.getElementById('detailsearch').style.display = "none";
		}
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
</script>
<!-- 스크립트 끝 -->

</head>

<body>

	<!-- header -->
	<%@ include file="/common/header.jsp"%>
	<!-- /.header -->

	<!-- container -->
	<div class="container-fluid">
		<!-- row -->
		<div class="row">

			<!-- sidebar -->
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li><a
						href="readUser.action?user_type=${session_type}&user_id=${session_id}">회원정보</a></li>
					<li><a href="#">구매목록</a></li>
					<li class="active"><a
						href="myAllListPage.action?session_id=${session_id}">내가 작성한 글</a></li>
				</ul>
			</div>
			<!-- /.sidebar -->

			<!-- main -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<!-- 게시판 윗부분 -->
				<div class="col-md-12">
					<h3>MyRecipe 목록</h3>
					<p>내가 올린 레시피~~!!</p>
				</div>
				<!-- /.게시판 윗부분 -->

				<!-- 게시판 바디 -->
				<div class="col-md-12">
					<table class="table table-stiped">
						<tr align="center" bgcolor="">
							<td><strong>번호</strong></td>
							<td><strong>종류</strong></td>
							<td><strong>제목</strong></td>
							<td><strong>요리명</strong></td>
							<td><strong>작성자</strong></td>
							<td><strong>작성일</strong></td>
							<td><strong><a href="javascript:return false;"
									onClick="recipe_timearray()">소비시간</strong></td>
							<td><strong><a href="javascript:return false;"
									onClick="recipe_pricearray()">비용</strong></td>
							<td><strong><a href="javascript:return false;"
									onClick="recipe_readcountarray()">조회수</a></strong></td>
							<td><strong><a href="javascript:return false;"
									onClick="recipe_recommandarray()">추천수</strong></td>
						</tr>

						<s:iterator value="recipelist" status="stat">

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

						</s:iterator>

						<s:if test="recipelist.size() <= 0">

							<tr bgcolor="#FFFFFF" align="center">
								<td colspan="10">등록된 게시물이 없습니다.</td>
							</tr>
							<tr bgcolor="#777777">
								<td height="1" colspan="10"></td>
							</tr>

						</s:if>
					</table>
				</div>
				<!-- /.게시판 바디 -->

				<!-- 페이징 -->
				<div class="text-center">
					<ul class="pagination pagination-sm">
						<s:property value="recipepagingHtml" escape="false" />
					</ul>
				</div>
				<!-- /.페이징 -->

				<!-- 버튼 -->
				<div class="pull-right">
					<input type="button" value="글쓰기"
						onClick="javascript:location.href='insertRecipeForm.action?currentPage=<s:property value="currentPage" />';">
					<input type="button" value="새로고침"
						onClick="javascript:location.href='myAllListPage.action?session_id=<s:property value="#session.session_id" />';">
				</div>
				<!-- /.버튼 -->

				<br />

				<table width="900" border="0" cellspacing="0" cellpadding="2">
					<tr>
						<td colspan="10" align="center">
							<!-- 검색 jsp -->
							<form name="recipe_search" action="recipe_search.action"
								enctype="multipart/form-data">
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
											<td colspan="3"><input type="text"
												name="recipe_foodnameinput"></td>
										</tr>
										<tr>
											<td align="center">제목+내용</td>
											<td colspan="3"><input type="text"
												name="recipe_subjectinput"></td>
										</tr>
										<tr>
											<td align="center">소요시간</td>
											<td colspan="3"><input type="text"
												id="recipe_timeinput1" size="5">&nbsp;~<input
												type="text" id="recipe_timeinput2" size="5"></td>
										</tr>
										<tr>
											<td align="center">비용</td>
											<td colspan="3"><input type="text"
												id="recipe_priceinput1" size="5">&nbsp;~<input
												type="text" id="recipe_priceinput2" size="5"></td>
										</tr>
										<tr>
											<td align="center" colspan="4"><input type="reset"
												value="초기화" />&nbsp;<input type="submit" value="검색" /></td>
										</tr>

									</table>
								</div>
							</form> <!-- /.검색 jsp -->
						</td>
					</tr>
				</table>

				<div class="col-md-12">
					<h3>My Q&A 목록</h3>
				</div>

				<!-- searchForm -->
				<form name="searchForm" id="searchForm">
					<select name="qna_category" id="qna_category"
						onchange="fn_search();">
						<option value="">전체</option>
						<option value="01"
							<c:if test="${qna_category eq '01'}">selected</c:if>>회원가입</option>
						<option value="02"
							<c:if test="${qna_category eq '02'}">selected</c:if>>바로결제</option>
						<option value="03"
							<c:if test="${qna_category eq '03'}">selected</c:if>>리뷰</option>
						<option value="04"
							<c:if test="${qna_category eq '04'}">selected</c:if>>이용문의</option>
						<option value="05"
							<c:if test="${qna_category eq '05'}">selected</c:if>>광고문의</option>
						<option value="06"
							<c:if test="${qna_category eq '06'}">selected</c:if>>기타</option>
					</select>
				</form>
				<!-- /.searchForm -->

				<!-- col-md-12 well -->
				<div class="col-md-12 well">
					<table class="table table-striped table-forum">
						<thead>
							<tr>
								<th class="text-center" style="width: 100px;">번호</th>
								<th class="text-center" style="width: 100px;">카테고리</th>
								<th class="text-center" style="width: 100px;">제목</th>
								<th class="text-center" style="width: 100px;">작성일</th>
								<th class="text-center" style="width: 100px;">조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="qnalist" items="${qnalist }">
								<tr>
									<td class="text-center"><c:out value="${qnalist.qna_num }" />
									</td>
									<td class="text-center"><c:choose>
											<c:when test="${qnalist.qna_category eq '01' }">회원가입</c:when>
											<c:when test="${qnalist.qna_category eq '02' }">바로결제</c:when>
											<c:when test="${qnalist.qna_category eq '03' }">리뷰</c:when>
											<c:when test="${qnalist.qna_category eq '04' }">이용문의</c:when>
											<c:when test="${qnalist.qna_category eq '05' }">광고문의</c:when>
											<c:when test="${qnalist.qna_category eq '06' }">기타</c:when>
											<c:otherwise>전체</c:otherwise>
										</c:choose></td>
									<td class="text-left"><a
										href="detailQna.action?qna_num=${qnalist.qna_num }"> <c:out
												value="${qnalist.qna_subject }" />
									</a></td>
									<td class="text-center"><c:out
											value="${qnalist.qna_reg_date }" /></td>
									<td class="text-center"><c:out
											value="${qnalist.qna_readcount }" /></td>
								</tr>
							</c:forEach>
							<c:if test="${empty qnalist}">
								<tr>
									<td colspan="5">등록된 게시물이 없습니다.</td>
								</tr>
							</c:if>
						</tbody>
					</table>

					<div class="text-center">
						<ul class="pagination pagination-sm">
							<s:property value="qnapagingHtml" escape="false" />
						</ul>
					</div>

					<div class="pull-right">
						<a href="insertQnaForm.action" class="btn btn-primary">글쓰기</a>
					</div>

				</div>
				<!-- /.col-md-12 well -->

			</div>
			<!-- /.main -->

		</div>
		<!-- /.row -->

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