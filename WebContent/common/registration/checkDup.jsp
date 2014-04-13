<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${isDup==1}">
<script type="text/javascript">
	parent.feedbackRegId.innerHTML = "이미 사용중인 아이디입니다..";
	parent.$('#divRegId').addClass('has-error'); // bootstrap validation
</script>
</c:if>

<c:if test="${isDup==0}">
<script type="text/javascript">
	parent.feedbackRegId.innerHTML = "사용 가능한 아이디입니다.";
	parent.$('#divRegId').removeClass('has-error'); // bootstrap validation
	parent.$('#divRegId').addClass('has-success'); // bootstrap validation
</script>
</c:if>

</body>