<%@ page language="java" contentType="text/html; charset=UTF-8"%>

    
    ${rest_num} <br/>
    ${rest_subject} <br/>
    ${restopt_subject} <br/>
    ${restopt_priceplus} <br/>
    
    
    <jsp:forward page="/agspay/AGS_pay.jsp"> 
       <jsp:param name="rest_num" value="${rest_num}" />
       <jsp:param name="rest_subject" value="${rest_subject}" /> 
       <jsp:param name="restopt_subject" value="${restopt_subject}" /> 
       <jsp:param name="restopt_priceplus" value="${restopt_priceplus}" /> 
	</jsp:forward>
    
    
  
