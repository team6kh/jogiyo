<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${isSuccess==1}">
<script type="text/javascript">
	parent.alert("인증성공");
</script>
</c:if>

<c:if test="${isSuccess==0}">
<script type="text/javascript">
	parent.alert("인증실패");
</script>
</c:if>