<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<body>
    cart페이지 for 배너
    
    <c:forEach var="list" items="${list}">
		<div class="col-sm-4 col-md-3">					
	    	<div class="thumbnail">
	        	${list.cart_rest_num} <br/>
			    ${list.cart_rest_subject} <br/>
			    <img src="${list.cart_restopt_destFile1}" /> <br/>
			    ${list.cart_restopt_subject} <br/>
			    ${list.cart_restopt_priceplus} <br/>
			    ${list.session_id} <br/>
	    	</div>
      	</div>		      			      	
	</c:forEach>
	</body>
