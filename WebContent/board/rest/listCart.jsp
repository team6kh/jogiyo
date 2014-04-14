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



    <h2>cart페이지 for 배너</h2>
    
    
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
	
