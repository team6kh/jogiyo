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
<script type="text/javascript" src="<%=request.getContextPath()%>/board/se2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/board/se2/photo_uploader/plugin/hp_SE2M_AttachQuickPhoto.js" charset="utf-8"></script>
<title>JOGIYO</title>

<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="common/common-template.css" rel="stylesheet">

</head>

<body >

	<!-- header -->
	<%@ include file="/common/header.jsp"%>
	<!-- end of header -->

	<!-- container -->
	<div class="container">
 
		<div class="common-template">
		<!-- 밑으로 view 꾸미기 -->
		<table align="center" width="800" border="0" cellspacing="0" cellpadding="2">
			<tr>
  				<td align="left"><h1>공지사항/이벤트</h1></td>
  			</tr>
  		</table>
  	
  		<s:if test="resultClass == NULL">
			<form name="f" action="insertNoticeAction.action" method="post" enctype="multipart/form-data" onSubmit="submitContents(this);">
		</s:if>
		
		<s:else>
		  <form name="u" action="updateNoticeAction.action" method="post" enctype="multipart/form-data" onSubmit="submitContents(this);">
		  <s:hidden name="notice_num" value="%{notice_num}" />
		  <s:hidden name="currentPage" value="%{currentPage}" />
		  <s:hidden name="notice_file" value="%{notice_file}" />
		</s:else>
		
  		<table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
  			<tr bgcolor="#777777">
        		<td height="2" colspan="3"></td>
      	    </tr>
  			<tr>
  				<td width="100" bgcolor="#F3F3F3" align="center">제목</td>
  				<td width="100" align="center">
  					<select name="notice_headtag" theme="simple" value="%{resultClass.notice_headtag}">
  						<option>-----------------</option>
  						<option>[공지]</option>
  						<option>[이벤트]</option>
  						<option>[스마트팁]</option>
  					</select>
  				</td>
  				<td width="600" align="center">
  					<s:textfield name="notice_subject" theme="simple" value="%{resultClass.notice_subject}" cssStyle="width:600px" maxlength="100"/>
  				</td>
  			</tr>
  			<tr bgcolor="#777777">
        		<td height="2" colspan="3"></td>
      	    </tr>
  			<tr>
  			<td width="100" bgcolor="#F3F3F3" align="center">내용</td>
  				<td width="700" colspan="2">
  					<s:textarea name="notice_content" id="notice_content" theme="simple" value="%{resultClass.notice_content}" cssStyle="width:700px" rows="20" />
  				</td>
  			</tr>
      	    
  			<tr bgcolor="#777777">
        		<td height="2" colspan="3"></td>
      	    </tr>
  			<tr>
  				<td align="right" colspan="3" width="471">
  					<input type="submit" name="submit" value="완료" />
  					<input type="button" name="notice" value="목록" class="inputb" onClick="javascript:location.href='listNotice.action?currentPage=<s:property value="currentPage" />'"/>
  				</td>
  			</tr>
  	</table>
  	
  	
		<!-- 꾸미기 끝 -->
		</div>

	</div>
	<!-- /.container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<script type="text/javascript">
		var oEditors = [];
			nhn.husky.EZCreator.createInIFrame({
    		oAppRef: oEditors,
    		elPlaceHolder: "notice_content",
    		sSkinURI: "<%=request.getContextPath()%>/board/se2/SmartEditor2Skin.html",
    		fCreator: "createSEditor2"
		});
			
		function submitContents(elClickedObj){ 
			oEditors.getById["notice_content"].exec("UPDATE_CONTENTS_FIELD", []);
			try{ 
				elClickedObj.f.submit(); 
			}catch(e){} 
		} 
		
		// textArea에 이미지 첨부
		function pasteHTML(filepath){
		    var sHTML = '<img src="<%=request.getContextPath()%>/board/se2/upload/'+filepath+'">';
		    oEditors.getById["notice_content"].exec("PASTE_HTML", [sHTML]); 
		}
	</script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="dist/js/bootstrap.min.js"></script>
</body>