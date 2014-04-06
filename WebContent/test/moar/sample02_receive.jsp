<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
	String uid = request.getParameter("uid");

	StringBuffer str = new StringBuffer();
	str.append("<?xml version='1.0' encoding='euc-kr'?>");
	str.append("<root>");
	if (!(uid.equals("admin") || uid.equals("test"))) {
		str.append("true");
	} else {
		str.append("false");
	}
	str.append("<id>" + uid + "</id>");
	str.append("</root>");

	response.setContentType("text/xml;charset=euc-kr");
	response.getWriter().write(str.toString());
%>