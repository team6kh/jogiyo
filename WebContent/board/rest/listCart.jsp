<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page isELIgnored="false" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="assets/ico/jogiyo.png">


<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="common/common-template.css" rel="stylesheet">
</head>


<script>
function check(){
    cbox = input_form.cart_isCheck;
    if(cbox.length) {  // 여러 개일 경우
        for(var i = 0; i<cbox.length;i++) {
            cbox[i].checked=input_form.all.checked;
        }
    } else { // 한 개일 경우
        cbox.checked=input_form.all.checked;
    }
}
</script>




<form name="input_form">
	<div align="center">
		<font size=5 ><b>장 바 구 니♥</b></font>
    </div>
    
    <c:forEach var="list" items="${list}">
			<div class="col-sm-3 col-md-2">					
		    	<div class="thumbnail">
		        	<!-- 
		        	${list.cart_rest_num} <br/>
				    ${list.cart_rest_subject} <br/>
				    ${list.session_id} <br/>
				    -->
				    <input type="checkbox" name="cart_isCheck" value="checked" color="white">
				    <img src="${list.cart_restopt_destFile1}" alt="N/A" style="min-height:100px;height:100px;" /> <br/>
				   
				    <div class="caption" align="center">
			    		<font size=3 color=green>${list.cart_restopt_subject}</font> <br/>
					    <font size=3 color=red >${list.cart_restopt_priceplus}</font> <br/>
		    		</div>
		    	</div>
	      	</div>
      		      			      	
	</c:forEach>
	
	
	<!-- 장바구니 결제 버튼 -->
	<div align="center">
		<input type="checkbox" name="all" onclick="check();">전체선택하기<br/><br/>
		<button type="button" class="btn btn-success">구매하기</button>
		<button type="button" class="btn btn-danger">선택제거</button>
	</div>
</form>
