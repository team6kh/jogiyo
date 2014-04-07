<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>quiz</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<s:head />
</head>
<body>
<b>당신이 가장 좋아하는 색은?</b>
<p/>
<s:form action="quiz" namespace="/validation" validate="true" method="post">
    <s:textfield label="이름" name="name"/>
    <s:textfield label="나이" name="age"/>
    <s:textfield label="좋아하는 색" name="answer"/>
    <s:submit/>
</s:form>
</body>
</html>
