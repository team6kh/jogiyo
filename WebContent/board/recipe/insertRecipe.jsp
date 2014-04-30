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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/board/se2/js/HuskyEZCreator.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/board/se2/photo_uploader/plugin/hp_SE2M_AttachQuickPhoto.js"
	charset="utf-8"></script>
<title>JOGIYO</title>

<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="jogiyo.css" rel="stylesheet">
<link href="common/common-template.css" rel="stylesheet">

<SCRIPT type="text/javascript">
							
		function nomember(){
			return validation();
			submitContents(this);
			
		}
		
		function member(){
			return membervalidation();
		    submitContents(this);
		}
	
		function validation() {
			
	
			var frm = document.inputWarning; 
			
			if (frm.recipe_foodkind.value == "") {
				alert("종류를 입력해주세요.");
				return false;
			}
	
			 if (frm.recipe_subject.value == "") {
				alert("제목을 입력해주세요.");
				return false;
			}
	
			 if (frm.recipe_foodsubject.value == "") {
				alert("요리명을 입력해주세요.");
				return false;
			}
	
			 if (frm.recipe_writer.value == "") {
				alert("작성자를 입력해주세요.");
				return false;
			}
	
			 if (frm.recipe_password.value == "") {
				alert("비밀번호를 입력해주세요.");
				return false;
			}
	
			 if (frm.recipe_method.value == "") {
				alert("재료를 입력해주세요.");
				return false;
			}
	
			 if (frm.recipe_time.value == "") {
				alert("소요시간을 입력해주세요.");
				return false;
			} 
			 if (frm.recipe_price.value == "") {
				alert("비용을 입력해주세요.");
				return false;
			} 
			 if (document.getElementById("recipe_content") == "") {
				alert("방법 및 상세 내용을 입력해주세요.");
				return false;
			}
			return true;	
		}
		
		function membervalidation(){
			
			
			var frm = document.inputWarning;
			
			if (frm.recipe_foodkind.value == "") {
				alert("종류를 입력해주세요.");
				
				return false;
			}
	
			if (frm.recipe_subject.value == "") {
				alert("제목을 입력해주세요.");
				return false;
			}
	
			if (frm.recipe_foodsubject.value == "") {
				alert("요리명을 입력해주세요.");
				return false;
			}
	
			if (frm.recipe_password.value == "") {
				alert("비밀번호를 입력해주세요.");
				return false;
			}
	
			if (frm.recipe_method.value == "") {
				alert("재료를 입력해주세요.");
				return false;
			}
	
			if (frm.recipe_time.value == "") {
				alert("소요시간을 입력해주세요.");
				return false;
			}
			
			if (frm.recipe_price.value == "") {
				alert("비용을 입력해주세요.");
				return false;
			}
			
			if (document.getElementById("recipe_content") == "") {
				alert("방법 및 상세 내용을 입력해주세요.");
				return false;
			}
			return true;

			
		}
		
	</SCRIPT>



</head>

<body>



	<!-- header -->
	<%@ include file="/common/header.jsp"%>
	<!-- end of header -->

	<!-- container -->
	<div class="container">

		<!-- test message -->
		<div class="common-template">

			<!-- insert 게시판 윗부분 -->
			<div class="col-md-12">
				<h2>RECIPE 게시판</h2>
			</div>

			<!-- insert 게시판 body -->
			<!-- 게시판 바디 -->


			<!-- 폼 하나로 테스트 해봄 -->
			<!-- 폼 아이디 넣어서 테스트 해봄 -->
			<!-- 폼 아이디 바꿔서 테스트 해봄 -->
			<div class="col-md-12">

				<c:if test="${resultClass == NULL && empty sessionScope.session_id}">
					1번
					<form id="inputWarning" name="inputWarning"
						action="insertRecipe.action" method="post"
						enctype="multipart/form-data" onSubmit="return nomember();">
				</c:if>
				<c:if
					test="${resultClass == NULL && !(empty sessionScope.session_id)}">
					2번
				    <form id="inputWarning" name="inputWarning"
						action="insertRecipe.action" method="post"
						enctype="multipart/form-data" onSubmit="return member();">
				</c:if>
				<c:if test="${resultClass != NULL && empty sessionScope.session_id}">
					3번
					<form id="inputWarning" name="inputWarning"
						action="updateRecipe.action" method="post"
						enctype="multipart/form-data" onSubmit="return nomember();">
						<s:hidden name="recipe_num" value="%{resultClass.recipe_num}" />
						<s:hidden name="currentPage" value="%{currentPage}" />
				</c:if>
				<c:if
					test="${resultClass != NULL && !(empty sessionScope.session_id)}">
					4번
				    <form id="inputWarning" name="inputWarning"
						action="updateRecipe.action" method="post"
						enctype="multipart/form-data" onSubmit="return member();">
						<s:hidden name="recipe_num" value="%{resultClass.recipe_num}" />
						<s:hidden name="currentPage" value="%{currentPage}" />
				</c:if>

				<table class="table table-stiped">
					<tr>
						<td align="center">종류</td>
						<td align="left" colspan="3">&nbsp; <select
							name="recipe_foodkind" id="recipe_foodkind"
							value="${resultClass.recipe_foodkind}">
								<option value="${resultClass.recipe_foodkind}">${resultClass.recipe_foodkind}</option>
								<option value="한식">한식</option>
								<option value="양식">양식</option>
								<option value="일식">일식</option>
								<option value="중식">중식</option>
								<option value="기타">기타</option>
						</select>
					</tr>
					<tr>
						<td width="100" bgcolor="" align="center">제목</td>
						<td colspan="3" align="left"><input type="text"
							id="recipe_subject" name="recipe_subject"
							value="${resultClass.recipe_subject}" /></td>
					</tr>
					<tr>
						<td width="100" bgcolor="" align="center">요리명</td>
						<td width="300" colspan="3" align="left"><input type="text"
							id="recipe_foodsubject" name="recipe_foodsubject"
							value="${resultClass.recipe_foodsubject}" /></td>
					</tr>
					<s:if test="#session.session_id != null">
						<tr>
							<td width="100" bgcolor="" align="center">작성자</td>
							<td width="100" align="left"><p>${sessionScope.session_id}</p>
								<input type="hidden" name="recipe_memberwriter"
								value="${sessionScope.session_id}" /> <input type="hidden"
								name="recipe_writer" value="${sessionScope.session_id}" /></td>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<td width="100" bgcolor="" align="center">작성자</td>
							<td width="100" align="left"><input type="text"
								id="recipe_writer" name="recipe_writer"
								value="${resultClass.recipe_writer}" /></td>
						</tr>
					</s:else>
					<tr>
						<td width="100" bgcolor="" align="center">비밀번호</td>
						<td width="100" align="left"><input type="password"
							id="recipe_password" name="recipe_password"
							value="${resultClass.recipe_password}" /></td>
					</tr>
					<tr>
						<td width="100" bgcolor="" align="center">재료</td>
						<td width="300" colspan="3" align="left"><input type="text"
							id="recipe_method" size="40" maxlength="30" name="recipe_method"
							value="${resultClass.recipe_method}"></td>
					</tr>
					<tr>
						<td width="100" bgcolor="" align="center">소요 시간</td>
						<td width="100" align="left"><input type="text"
							id="recipe_time" name="recipe_time"
							value="${resultClass.recipe_time}" /></td>
						<td width="100" bgcolor="" align="center">비용</td>
						<td width="100" align="left"><input type="text"
							id="recipe_price" name="recipe_price"
							value="${resultClass.recipe_price}" /></td>
					</tr>
					<tr>
						<td width="100" bgcolor="" align="center">방법 및 상세내용</td>
						<td width="300" colspan="3" align="left"><s:textarea
								name="recipe_content" id="recipe_content" theme="simple"
								value="%{resultClass.recipe_content}" cssStyle="width:800px" />
						</td>
					</tr>

					<tr>
						<td colspan="4" bgcolor="" align="center"><input
							name="submit" type="submit" value="등록"> <input
							type="reset" value="다시작성"> <input name="list"
							type="button" value="목록보기"
							OnClick="javascript:location.href='listRecipe.action?currentPage=<s:property value="currentPage" />'">
						</td>
					</tr>
				</table>
				</form>
			</div>
		</div>
		<!-- end of test message -->

	</div>
	<!-- /.container -->




	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript">
		var oEditors = [];
		nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: "recipe_content",
		sSkinURI: "<%=request.getContextPath()%>/board/se2/SmartEditor2Skin.html",
		fCreator: "createSEditor2",
			htParams :
            { 
                      fOnBeforeUnload : function()
                      { 
                      }
            } 
	});
		
	function submitContents(elClickedObj){ 
		
		alert("네이버에디터");
		oEditors.getById["recipe_content"].exec("UPDATE_CONTENTS_FIELD", []);
		try{ 
			elClickedObj.inputWarning.submit(); //submit (form 정의된 action)
		}catch(e){} 
	} 
	
	// textArea에 이미지 첨부
	function pasteHTML(filepath){
	    var sHTML = '<img src="<%=request.getContextPath()%>/board/se2/recipe_upload/'+ filepath + '">';
			oEditors.getById["recipe_content"].exec("PASTE_HTML", [ sHTML ]);
		}
	</script>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="dist/js/bootstrap.min.js"></script>
</body>
</html>