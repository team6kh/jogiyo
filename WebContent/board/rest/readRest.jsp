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
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<link rel="shortcut icon" href="assets/ico/jogiyo.png">

<title>JOGIYO</title>

<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="common/common-template.css" rel="stylesheet">

<style type="text/css">
	html {
		height: 100%
	}
	body {
		height: 100%;
		margin: 3% 0% 0% 1.5%;
		padding: 0
	}
	#map_canvas {
		height: 400px
	}
	#cartFrame {
		height: 450px
	}
	
</style>
<script type="text/javascript"
	src="http://maps.googleapis.com/maps/api/js?sensor=true">
</script>

<script type="text/javascript">
	var map;
	var infowindow = new google.maps.InfoWindow();
	var marker = [];
	var geocoder;
	var geocodemarker = [];

	var GreenIcon = new google.maps.MarkerImage(
			"http://labs.google.com/ridefinder/images/mm_20_green.png",
			new google.maps.Size(12, 20), new google.maps.Point(0, 0),
			new google.maps.Point(6, 20));

	function initialize() {
		var latlng = new google.maps.LatLng(37.5240220, 126.9265940);
		var myOptions = {
			zoom : 15,
			center : latlng,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};

		map = new google.maps.Map(document.getElementById("map_canvas"),
				myOptions);
		geocoder = new google.maps.Geocoder();
		google.maps.event.addListener(map, 'click', codeCoordinate);
		codeAddress();
	}

	function Setmarker(latLng) {
		if (marker.length > 0) {
			marker[0].setMap(null);
		}
		// marker.length는 marker라는 배열의 원소의 개수입니다.
		// 만약 이 개수가 0이 아니라면 marker를 map에 표시되지 않게 합니다.
		// 이는 다른 지점을 클릭할 때 기존의 마커를 제거하기 위함입니다.
		marker = [];
		marker.length = 0;
		// marker를 빈 배열로 만들고, marker 배열의 개수를 0개로 만들어 marker 배열을 초기화
		marker.push(new google.maps.Marker({
			position : latLng,
			map : map
		}));
		// marker 배열에 새 marker object를 push 함수로 추가
	}

	function codeAddress(event) {
		if (geocodemarker.length > 0) {
			for (var i = 0; i < geocodemarker.length; i++) {
				geocodemarker[i].setMap(null);
			}

			geocodemarker = [];
			geocodemarker.length = 0;
		}

		//var address = "대한민국 서울특별시 영등포구 당산로49길"; // rest_writer_address 변수(판매자 테이블의 주소 컬럼)
		var address = document.getElementById("rest_writer_address").value;


		geocoder.geocode({
			'address' : address
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				for (var i = 0; i < results.length; i++) {
					map.setCenter(results[i].geometry.location);
					geocodemarker.push(new google.maps.Marker({
						center : results[i].geometry.location,
						position : results[i].geometry.location,
						icon : GreenIcon,
						map : map
					}));
				}
			} else {
				alert("Geocode was not successful for the following reason: "
						+ status);
			}

		});

	}

	function codeCoordinate(event) {
		Setmarker(event.latLng);
		geocoder.geocode({
			'latLng' : event.latLng
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				if (results[1]) {
					infowindow.setContent(results[1].formatted_address);
					infowindow.open(map, marker[0]);
				}
			}
		});
	}
	
	function insertCart(form) {
		alert("insertCart");
		var rest_num = document.getElementById("rest_num").value;
		var rest_subject = document.getElementById("rest_subject").value;
		var session_id = document.getElementById("session_id").value;
		var restopt_destFile1 = form.restopt_destFile1.value;
		var restopt_subject = form.restopt_subject.value;
		var restopt_priceplus = form.restopt_priceplus.value;
		
		var url = "insertCart.action?cart_rest_num="+rest_num+"&cart_rest_subject="+rest_subject+"&cart_restopt_destFile1="+restopt_destFile1+"&cart_restopt_subject="+restopt_subject+"&cart_restopt_priceplus="+restopt_priceplus+"&session_id="+session_id;
		document.getElementById("cartFrame").contentWindow.location.href=url;
		return false;
	}
	
	function abc(aa){
		alert("aa>"+aa.restopt_subject.value);
	}
	
	function reviewForm() {

		var reviewform = document.insertReviewForm;
		if (reviewform.style.display == "none") {
			reviewform.style.display = "block";
		} else {
			reviewform.style.display = "none";
		}
	}
</script>
</head>

<body onload="initialize()">
	<!-- header -->
	<%@ include file="/common/header.jsp"%>
	<!-- end of header -->


	<!-- container -->
	<div class="container">

		<!-- test board pretty -->
		<div class="col-md-12">
			<h3>READ</h3>
		</div>

		<div class="col-md-9 well">
		
			<div class="col-md-12 well">
				<div class="col-md-2">
					<font color="#FF3636"><b>카테고리</b></font>
				</div>
				<div class="col-md-10">
					<s:if test="resultClass.rest_localcategory == 11">
						서울특별시
					</s:if>
					<s:elseif test="resultClass.rest_localcategory == 12">
						경기/인천
					</s:elseif>
					<s:elseif test="resultClass.rest_localcategory == 13">
						부산/경남
					</s:elseif>
					<s:elseif test="resultClass.rest_localcategory == 14">
						대구/경북
					</s:elseif>
					<s:elseif test="resultClass.rest_localcategory == 15">
						대전/전북
					</s:elseif>
					<s:elseif test="resultClass.rest_localcategory == 16">
						광주/전남
					</s:elseif>
					<s:elseif test="resultClass.rest_localcategory == 17">
						그 외 지역
					</s:elseif>

					<s:if test="resultClass.rest_typecategory == 21">
						- 한식
					</s:if>
					<s:elseif test="resultClass.rest_typecategory == 22">
						- 양식
					</s:elseif>
					<s:elseif test="resultClass.rest_typecategory == 23">
						- 중식
					</s:elseif>
					<s:elseif test="resultClass.rest_typecategory == 24">
						- 일식
					</s:elseif>
					<s:elseif test="resultClass.rest_typecategory == 25">
						- 기타
					</s:elseif>
				</div>

				<div class="col-md-2">
					<font color="#FF3636"><b>상품번호</b></font>
				</div>
				<div class="col-md-10">
					<s:property value="resultClass.rest_num" />
					<input type="hidden" id="rest_num" name="rest_num" value=<s:property value="resultClass.rest_num" /> />
				</div>
			</div>

			<div class="col-md-12 well">
				<div class="col-sm-4 col-md-5">
					<img src="${resultClass.rest_destFile1}" alt="N/A" style="min-height:125px;height:125px;">
				</div>
				<div class="col-md-7">
					<br/>
					<font size="4">상품명</font> <br/>
					<font size="7" color = "red"><s:property value="resultClass.rest_subject" /></font>
					<input type="hidden" id="rest_subject" name="rest_subject" value=<s:property value="resultClass.rest_subject" /> />
					<input type="hidden" id="session_id" name="session_id" value="test" />
				</div>
			</div>

			<div class="col-md-12 well">
				<font size="3" color = "red"><b>&nbsp;&nbsp;&nbsp;상품 상세 설명</b></font>

				<!-- 여기서 부터 -->
				<!-- start -->
				<div class="panel-group" id="accordion">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapseOne"> 상품 상세 설명 </a>
							</h4>
						</div>

						<div id="collapseOne" class="panel-collapse collapse">
							<div class="panel-body">

								<div class="thumbnail">
									<img src="${resultClass.rest_destFile2}" alt="N/A" >
								</div>

							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapseTwo"> 상품 옵션 </a>
							</h4>
						</div>
						<div id="collapseTwo" class="panel-collapse collapse in">
							<div class="panel-body">

								<div class="col-md-12">
									<c:forEach var="list" items="${list}">
										<form class="col-sm-4 col-md-3" id="cartForm" name="test">					
									    	<div class="thumbnail">

									    		<!-- 옵션 -->
									      		<a href="${list.restopt_destFile1}">
									      			<img src="${list.restopt_destFile1}" alt="N/A" style="min-height:125px;height:125px;">
									      		</a>
									      		<input type="hidden" id="restopt_destFile1" name="restopt_destFile1" value="${list.restopt_destFile1}" />

									      		<div class="caption" align="center">
									        		<font size="2" color="green"><b>${list.restopt_subject}</b></font> <br/>
									        		<font size="3" color="red">${list.restopt_priceplus}</font> 원
									        		<input type="hidden" id="restopt_subject" name="restopt_subject" value="${list.restopt_subject}" />
									        		<input type="hidden" id="restopt_priceplus" name="restopt_priceplus" value="${list.restopt_priceplus}" />
									        		
									      		</div>
							
									      		<!-- 장바구니 담기 버튼 -->
									      		<button type="button" class="btn btn-default btn-lg" onclick="insertCart(this.form)">
													<span class="glyphicon glyphicon-shopping-cart"></span>
												</button>

									    	</div>
								      	</form>		      			      	
									</c:forEach>

									</div>

									<c:if test="${list eq null}">
										<div class="text-center">
											<p>등록된 게시물이 없습니다.</p>
										</div>
									</c:if>

							</div>
						</div>
					</div>
				</div>
				<!-- end -->

			</div>
			
			

			<!-- HERE -->
			<div class="tabbable">
				<!-- Only required for left/right tabs -->
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#tab1" data-toggle="tab">
							지도보기
						</a>
					</li>
					<li>
						<a href="#tab2" data-toggle="tab">
							판매자 정보
						</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="tab1">						
							<div class="col-md-7 well">
								<!-- Here's map area -->
								<div id="map_canvas" class="map col-md-12"></div>
							</div>	
					</div>
					<div class="tab-pane" id="tab2">

							<div class="col-md-12 well">
								<div class="col-md-12">
									<font size="3" color = "red"><b>판매자 정보</b></font>
								</div>

								<div class="col-md-3">
									<font color="#FF3636"><b>판매자</b></font>
								</div>
								<div class="col-md-9">
									${resultClass.rest_writer_name}
								</div>

								<div class="col-md-3">
									<font color="#FF3636"><b>전화번호</b></font>
								</div>
								<div class="col-md-9">
									${resultClass.rest_writer_telnum}
								</div>

								<div class="col-md-3">
									<font color="#FF3636"><b>핸드폰</b></font>
								</div>
								<div class="col-md-9">
									${resultClass.rest_writer_mobilenum}
								</div>

								<div class="col-md-3">
									<font color="#FF3636"><b>반품주소</b></font>
								</div>
								<div class="col-md-9">
									${resultClass.rest_writer_address}
								</div>
							</div>
					</div>
				</div>
			</div>

		</div>
		
		
		

		
		
		
		
		<!-- 장바구니 -->
	    <div  id="cart" class="col-md-3">
	    	<div id="sidebar" data-spy="affix" data-offset-top="0" data-offset-bottom="0">
	    		<iframe id="cartFrame" src="listCart.action" frameborder=0 framespacing=0 marginheight=0 marginwidth=0 scrolling=yes vspace=0></iframe>
	        </div>      
	    </div>	     

	</div>
	
	<!-- review -->
		<div class="container well">
	
	
			<div class="col-md-12">
				<h3>review 게시판</h3>
			</div>
	
			<!--  리뷰 쓰기 권한에 관한 조건문 : 필요한 값 - 회원이 이 식당에서 주문한 적이 있는지 없는지에 대한 논리값 -->
			<!--  일단은 로그인을 하지 않으면 리뷰 쓰기 폼이 보이지 않도록 조건문 설정  -->
	
			<!-- 리뷰 쓰기 폼 시작  -->
	
	
			<div class="col-md-12 well">
				<c:if test="${!empty sessionScope.sessionId}">
					<div class="text-center">
						<button onclick="return reviewForm()">리뷰 글 쓰기</button>
					</div>
					<div class="text-center">
						<form name="insertReviewForm" method="post" action="insertReviewPro.action" enctype="multipart/form-data" style="display: none">
							<table class="table table-striped table-forum">
								<tr>
									<th>별점</th>
									<td class="text-center"><input type="radio"
										name="review_rating" value="1" /> 1점 <input type="radio"
										name="review_rating" value="2" />2점 <input type="radio"
										name="review_rating" value="3" />3점 <input type="radio"
										name="review_rating" value="4" />4점 <input type="radio"
										name="review_rating" value="5" />5점</td>
								</tr>
								<!--  리뷰 content -->
								<tr>
									<td class="text-center" colspan="2"><textarea
											name="review_content" rows="5" cols="50"></textarea></td>
								</tr>
								<!--  이미지 파일 첨부 : 첨부 개수 제한/ 용량 제한 필요  -->
								<tr>
									<td class="text-center" colspan="2"><input
										id="review_file_element" type="file" name="review_files" multiple="multiple" />
								</tr>
								<!-- 리뷰 작성 완료 버튼  -->
								<tr>
									<th class="text-center" colspan="2">
									<input type="submit" value="리뷰 등록" /></th>
								</tr>
							</table>
							
							<!-- 보내줘야 할 파라미터 : 식당코드(식당 테이블) / 구매자(= 회원 = 글 작성자) 정보 -->
							<input type="hidden" name="review_rest_currentPage" value="${currentPage}" />
							<input type="hidden" name="review_rest" value="${rest_num}" />
							 <input type="hidden" name="rest_num" value="${rest_num}" />
							<input type="hidden" name="review_writer" value="${sessionScope.sessionId }" />
							
						</form>
					</div>
				</c:if>
			</div>
			<!-- 리뷰 쓰기 폼 끝  -->
	
			
			
		</div>
		
		
		
		<!-- 리뷰 글 보기 시작 -->
		<c:forEach var="reviewDTO" items="${reviewRes}">
			<div class="text-center">
				<table class="table table-striped table-forum">

					<!--  리뷰글 작성자 & 작성일 -->
					<tr>
						<td class="text-center"><label> 작성자 </label>
							&nbsp;&nbsp;&nbsp; ${reviewDTO.review_writer} <input
							type="hidden" name="review_num" value="${reviewDTO.review_num}" /></td>
						<td class="text-center"><label>작성일</label> &nbsp;&nbsp;&nbsp;
							<fmt:formatDate value="${reviewDTO.review_reg_date}"
								pattern="yyyy-MM-dd" /></td>
					</tr>
					<!-- 해당글 작성자일 경우 수정/삭제 버튼  -->
					<!-- 임시값 "test_Customer" sessionId 값으로 교체 -->
					<c:if test="${reviewDTO.review_writer == sessionScope.sessionId}">
						<tr>
							<td class="text-right" colspan="2">
								<input type="button"value="수정" onclick="javascript:open('updateReviewForm.action?rest_num=${rest_num}&review_rest_currentPage=${currentPage}&ccp=${ccp}&review_num=${reviewDTO.review_num}','confirm','toolbar=no, location=no, status= no, menubar=no, scrollbars=no, resizeable=no, width=500, height=500')">
								<input type="button" value="삭제" onclick="javascript:open('deleteReviewForm.action?rest_num=${rest_num}&review_rest_currentPage=${currentPage}&ccp=${ccp}&review_num=${reviewDTO.review_num}','confirm','toolbar=no, location=no, status= no, menubar=no, scrollbars=no, resizeable=no, width=300, height=200')" />
							</td>
						</tr>
					</c:if>
					<!--  리뷰글 별점 -->

					<tr>
						<td class="text-center" colspan="2"><label>별점</label>
							&nbsp;&nbsp;&nbsp; <c:forEach begin="1"
								end="${reviewDTO.review_rating}">
								<img src="assets/img/review/ratingimage/ico.png" width="25px"
									height="25px">
							</c:forEach></td>
					</tr>
					<!-- 리뷰글 내용 -->
					<tr>
						<td class="text-center" colspan="2">${reviewDTO.review_content }</td>
					</tr>
					<!-- 리뷰글 첨부사진 : 첨부사진이 있을 때만 보이도록 -->
					<c:if test="${!empty reviewDTO.review_file}">
						<c:forTokens var="reviewFileNames"
							items="${reviewDTO.review_file }" delims="' '">
							<tr>
								<td class="text-center" colspan="2"><c:forEach
										var="reviewFileName" items="${reviewFileNames}">
										<img src="${reviewFile_Path}${reviewFileName}" width="400px">
										<br />
									</c:forEach></td>
							</tr>
						</c:forTokens>
					</c:if>

				</table>
			</div>
		</c:forEach>
		
		<div class="text-center">
				<ul class="pagination pagination-sm">
					<s:property value="pagingHtml" escape="false" />
				</ul>
		</div>
		<!--  리뷰 글 페이지 -->
		<!-- 리뷰 글 보기 끝 -->






























	<!-- 임시 사용 -->
	<form name="readRestForm" action="payRest.action" method="post" >
		<TABLE border=0 width=90%>
			<tr>
				<td>
					-테스트용- <br/>
					레스트넘${rest_num} 커런트페이지${currentPage}
				
				</td>
			</tr>
			<tr>
				<td>
					옵션<br/>
					<select name="restopt_set">
						<s:iterator value="list" status="stat">
							<option value="<s:property value="restopt_subject"/>a<s:property value="restopt_priceplus" />">
								<s:property value="restopt_subject" /> (가격 : <s:property value="restopt_priceplus" />) 
							</option>
						</s:iterator>
					</select>
				</td>
			</tr>
			<tr></tr>
			<tr>
				<td>
					<input name="submit" type="submit" value="구매하기"  />
					<input name="list" type="button" value="장바구니담기" onClick="javascript:location.href='ListRest.action?currentPage=<s:property value="currentPage"/>'" />

					<input type ="hidden" id="rest_writer_address" value="<s:property value="resultClass.rest_writer_address" />" />
				</td>
			</tr>
		</table>


		<table>
			<tr>
				<td align="right">
					<input name="list" type="button" value="수정" class="inputb" onClick="javascript:location.href='modifyRest.action?rest_num=<s:property value="rest_num" />&currentPage=<s:property value="currentPage" />'">
					<input name="list" type="button" value="삭제" class="inputb" onClick="javascript:location.href='deleteRest.action?rest_num=<s:property value="rest_num" />&currentPage=<s:property value="currentPage" />'">
					<input name="list" type="button" value="목록"  onClick="javascript:location.href='listRest.action?currentPage=<s:property value="currentPage" />'">
				</td>
			</tr>
		</table>
	</form>


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="dist/js/bootstrap.min.js"></script>


</body>



