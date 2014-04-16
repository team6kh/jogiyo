<%@ page import="java.util.*,java.text.*,java.net.*" contentType="text/html; charset=utf-8" %>
<%@ page import="java.security.MessageDigest" %>

<%
request.setCharacterEncoding("UTF-8");
String rOrdNo = request.getParameter("rOrdNo");	
%>

<script language=javascript>//"지불처리중"팝업창 닫는 부분 (AGS_pay.html에서 submit 전에 띄운 팝업 창을 닫는 스크립트)
	<!--
	var openwin = window.open("AGS_progress.html","popup","width=300,height=160");
	openwin.close();
	-->
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Refresh" content="0; URL=http://localhost:8000/jogiyo/payRestResult.action?rest_num=<%=rOrdNo%>&session_id=${sessionScope.sessionId}">

