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

<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="common/common-template.css" rel="stylesheet">

</head>

<body>

	<!-- container -->
	<div class="container">

		<div class="col-md-12">
			<form method="post" onsubmit="confirm()">
				<table class="table table-striped table-forum">
					<tr>
						<th>정말 삭제하시겠습니까?</th>
					</tr>
					<tr>
						<td><input type="submit" value="확 인" /> <input type="button"
							value="취 소" onclick="javascript:self.close()"> <!-- 파라메터 -->
							<input type="hidden" name="ccp" value="${ccp}" /> <input
							type="hidden" name="review_num" value="${review_num}" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>

	<script type="text/javascript">
		function confirm() {
			opener.window.location.href = "deleteReviewPro.action?ccp=${ccp}&review_num=${review_num}";
			self.close();
		}
	</script>
	<!-- /.container -->




	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="dist/js/bootstrap.min.js"></script>
</body>
</html>