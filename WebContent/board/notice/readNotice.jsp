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
 
	<!-- 밑으로 view 꾸미기 -->
	<div class="col-md-12">
		<h3>공지사항/이벤트</h3>
	</div>
		
	<div class="col-md-12 well">
		<table class="table table-striped table-forum">
			<thead>
	  			<tr>
      				<th class="text-center" width="50"><strong><s:property value="notice_num" /></strong></th>
					<th class="text-center"><strong>&nbsp;&nbsp;
						<s:if test="resultClass.notice_headtag == '-----------------'" >
							<s:property value="resultClass.notice_subject" />
						</s:if>
						<s:else>
							<s:property value="resultClass.notice_headtag" />&nbsp;
							<s:property value="resultClass.notice_subject" />
						</s:else></strong>
					</th>
        			<th width="100" class="text-center"><strong><s:property value="resultClass.notice_reg_date" /></strong></th>
      			</tr>
      	    </thead>
      	    <tbody>
      			<tr>
        			<td bgcolor="#FFFFFF" height="600" colspan="3" align="left">
          			&nbsp;&nbsp;<s:property value="pagingHtml" escape="false" />
        			</td>
      			</tr>
      			<tr>
        			<td align="right" colspan="3">
        				<s:url id="updateNoticeURL" action="UpdateNoticeAction" >
						<s:param name="notice_num">
						<s:property value="notice_num" />
						</s:param>
	        			</s:url>
					
	        			<s:url id="deleteNoticeURL" action="DeleteNoticeAction" >
						<s:param name="notice_num">
						<s:property value="notice_num" />
						</s:param>
	        			</s:url>
	        			<div style="float:left;">
	        			<s:if test="prev_num == min_num">
	        				<a href="javascript:alert('이전글이 없습니다.')" class="btn btn-default">이전글</a>
	        				<a href="readNoticeAction.action?notice_num=${next_num}&currentPage=${currentPage}" class="btn btn-default">다음글</a>
						</s:if>
						<s:elseif test="next_num == max_num">
	        				<a href="readNoticeAction.action?notice_num=${prev_num}&currentPage=${currentPage}" class="btn btn-default">이전글</a>
	        				<a href="javascript:alert('다음글이 없습니다.')" class="btn btn-default">다음글</a>
						</s:elseif>
						<s:else>
							<a href="readNoticeAction.action?notice_num=${next_num}&currentPage=${currentPage}" class="btn btn-default">이전글</a>
	        				<a href="readNoticeAction.action?notice_num=${prev_num}&currentPage=${currentPage}" class="btn btn-default">다음글</a>
						</s:else>
						</div>
	        			<a href="updateNoticeForm.action?notice_num=${notice_num}&currentPage=${currentPage}" class="btn btn-default">수정</a>
	        			<a href="deleteNoticeAction.action?notice_num=${notice_num}&currentPage=${currentPage}" class="btn btn-default">삭제</a>
	        			<a href="listNotice.action?currentPage=${currentPage}" class="btn btn-default">목록</a>
					</td>
      			</tr>
      		</tbody>
		</table>
		<!-- 꾸미기 끝 -->
		</div>

	</div>
	<!-- /.container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="dist/js/bootstrap.min.js"></script>
</body>
