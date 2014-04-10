<%@ page language="java" contentType="text/html; charset=EUC-KR"%>

    
    <%
    	response.sendRedirect("agspay/AGS_pay.jsp");
    %>

    <!-- 차후에 forward로 파라미터 값 넘기기~~ -->
    
    <!-- 이 페이지에서 받아야 할 필요 파라미터 목록들
    	
    	액션에서 해당 rest_num인 레코드 다..
    	
    	rest_num // 상품번호  ㅇㅇ
    	rest_subject//상품명 ㅇㅇ
    	rest_price//상품가격
    	
    	restopt_subject//상품옵션 ㅇㅇ
    	restopt_priceplus//옵션가격
    	
    	rest_price + restopt_priceplus//합계ㅇㅇ
    	
    	//세션 회원 아이디
    	//세션 로그인자 의 이메일
    	
    
    
    
    
    
    
     -->
    
    <!-- 
    <form method="post" action="payAction.action" name="readyPaymentForm" >
   		<input type="submit"  value="결제" />
   	</form>
   	 -->