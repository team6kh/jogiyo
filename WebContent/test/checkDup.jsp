<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${check!=1}">
<script type="text/javascript">
	parent.feedbackRegId.innerHTML = "�̹� ������� ���̵��Դϴ�..";
</script>

</c:if>
<c:if test="${check==1}">
<script type="text/javascript">
		parent.feedbackRegId.innerHTML = "<font color=green>��� ������ ���̵��Դϴ�.</font>";
</script>

</c:if>
</body>