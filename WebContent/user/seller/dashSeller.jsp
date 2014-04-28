<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ page isELIgnored="false" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="huks">
<link rel="shortcut icon" href="assets/ico/jogiyo.png">

<title>JOGIYO</title>

<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="jogiyo.css" rel="stylesheet">
<link href="common/common-template.css" rel="stylesheet">
<link href="user/common/dashboard.css" rel="stylesheet">

</head>

<body>

    <!-- header -->
    <%@ include file="/common/header.jsp"%>
    <!-- END of header -->

    <!-- container -->
    <div class="container">
        <div class="col-md-12">
            <!--  탭 -->
            <ul class="nav nav-tabs" style="margin:20px">
              <li><a href="#home" data-toggle="tab">홈</a></li>
              <li><a href="#insertRest" data-toggle="tab">상품 등록</a></li>
              <li><a href="#stats" data-toggle="tab">통 계</a>
            </ul>
            <!--  탭  -->
            
            <!--  탭 컨텐츠  -->
            <div class="col-md-12 tab-content">
                <!--  홈탭 컨텐츠 -->
                <div class="tab-pane active" id="home"> 
                    <!-- 홈탭 상단 : 차트? -->
                    <div class="col-md-12">
                            차트?
                            
                            
                            
                            
                    </div>
                    <div class="col-md-12">
                      <!-- 하단 좌측 : 결제 내역 -->
                        <div class="col-md-6">                          
                                 <table class="table table-bordered table-hover">
                                 <caption class="text-left"> <font size="3px"><B>결제 내역</B></font>
                                    <font size="2px">&nbsp;&nbsp;&nbsp;어제 기준</font> </caption>
                                    <tr>
                                        <th class="text-center">결제코드    </th>
                                        <th class="text-center">메      뉴 </th>
                                        <th class="text-center">메뉴가격 </th>
                                        <th class="text-center"> 구 매 자 </th>
                                        <th class="text-center"> 주 문 일 </th>
                                    </tr>
                                    <c:forEach var="paidDTO" items="${paidRes}">
                                    <tr>
                                        <td class="text-center">${paidDTO.paid_num} </td>
                                        <td class="text-center">${paidDTO.paid_restopt_subject }</td>
                                        <td class="text-center">${paidDTO.paid_restopt_subject}</td>
                                        <td class="text-center">${paidDTO.session_id}</td>
                                        <td class="text-center"><fmt:formatDate value="${paidDTO.paid_reg_date}" pattern="yyyy.MM.dd  HH:mm:ss"/>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                        
                        </table>
                        
                        </div>
                        <!-- 하단 우측 : 인기 메뉴 -->
                        <div class="col-md-6"> 
                            <table class="table table-bordered table-hover">
                                <caption class="text-left"> <font size="3px"><B>많이 팔린 상품</B></font>
                                <font size="2px">&nbsp;&nbsp;&nbsp;어제 기준</font> </caption>
                                <tr>
                                    <th class="text-center"> 메뉴명 </th>
                                    <th class="text-center"> 매출 건수 </th>
                                    <th class="text-center"> 매출 금액 </th>
                                </tr>
                                <c:forEach var="menuDTO" items="${menuRes }">
                                <tr>
                                    <td class="text-center">${menuDTO.paid_restopt_subject}</td>
                                    <td class="text-center">${menuDTO.paid_sales_volume }</td>
                                    <td class="text-center">${menuDTO.paid_sales_price }</td>
                                </tr>   
                                </c:forEach>    
                            </table>
                            
                        </div>
                    </div>
                </div>
                
                <!-- 상품등록탭 컨텐츠 -->
                <div class="tab-pane" id="insertRest">상품 등록</div>
            
                <!-- 통계탭 컨텐츠 -->
                <div class="tab-pane" id="stats">
                    <!-- 검색조건 창 -->
                    <div class="col-md-12">
                        <form name="search" action="stats.action">
                            <table class="table table-bordered">
                            <tr>
                                <td>
                                    <div class="col-md-2">조회일</div>
                                    <div class="col-md-4">
                                        <button type="button" class="btn btn-default" name="todayStats" onclick="setDate()">오늘</button> &nbsp;&nbsp;
                                        <button type="button" class="btn btn-default" name="weekStats" onclick="setDate()">이번 주</button> &nbsp;&nbsp;
                                        <button type="button" class="btn btn-default" name="monthStats" onclick="setDate()">이번 달</button>&nbsp;&nbsp;
                                        <button type="button" class="btn btn-default" name="mon6Stats" onclick="setDate()">최근 6개월</button>&nbsp;&nbsp;                             
                                    </div>
                                    
                                    <div class="input-group">
                                            <div class="col-md-3">
                                            <input type="date" class="form-control" name="startDate" /> ~
                                            </div>
                                            <div class="col-md-3">
                                            <input type="date" class="form-control" name="endDate"/>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" class="text-right">
                                    <button type="reset" class="btn btn-default">지우기</button>
                                    <button type="submit" class="btn btn-primary"onclick="validSearch()">검 색</button>
                                </td>
                            </tr>
                        </table>                     
                        </form>                    
                    </div> 
                   
                </div>
            
            </div>
            <!--  탭 컨텐츠 끝  -->
        </div>
    </div>
    
    
    
 
<script>
  $(function () {
    $('#myTab a:last').tab('show');
  });
</script>
    <!-- /.container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="dist/js/bootstrap.min.js"></script>
</body>
</html>