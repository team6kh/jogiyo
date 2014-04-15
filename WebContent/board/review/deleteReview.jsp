<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page isELIgnored="false" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
</head>

<body>

	<form method="post" action="deleteReviewPro.action" >
	
		<input type="hidden" id="rest_num" name="rest_num" value="${rest_num}" />
		<input type="hidden" id="review_rest_currentPage" name="review_rest_currentPage" value="${review_rest_currentPage}" />
		<input type="hidden" id="ccp" name="ccp" value="${ccp}" /> 
		<input type="hidden" id="review_num" name="review_num" value="${review_num}" />
					
		<table class="table table-striped table-forum">
			<tr>
				<th>정말 삭제하시겠습니까?</th>
			</tr>
			<tr>
				<td>
					<input type="submit" value="확 인" />
					<input type="button" value="취 소" onclick="javascript:self.close()"> <!-- 파라메터 -->
				</td>
			</tr>
		</table>
	</form>

</body>
</html>