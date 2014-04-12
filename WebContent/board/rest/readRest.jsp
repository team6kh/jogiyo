<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page isELIgnored="false" %> --%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link href="common/common-template.css" rel="stylesheet">

<!-- 구글 맵 API -->
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=true"></script>

<!-- 구글 맵 스크립트 -->
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
</script>

</head>

<body onload="initialize()">

	<!-- header -->
	<%@ include file="/common/header.jsp"%>
	<!-- end of header -->

	<!-- container -->
	<div class="container">

		<!-- test message -->
		<div class="common-template">
		
			<div class="col-md-12">
			 	<div class="col-md-6"><s:property value="resultClass.rest_localcategory" /></div>	
				<div class="col-md-6"><s:property value="resultClass.rest_typecategory" /></div>	
			</div>
			<div class="col-md-12">
				<div class="col-md-2"><s:property value="resultClass.rest_num" /></div>
				<div class="col-md-10"><s:property value="resultClass.rest_subject" /></div>
			</div>
			<div class="col-md-12">
				<p class="lead"><s:property value="resultClass.rest_destFile1" /></p>
				<p class="lead"><s:property value="resultClass.rest_destFile2" /></p>
			</div>
			<div class="col-md-12">
				<!-- 업소상세정보 및 장바구니 영역 : S -->
				<div class="side-info col-xs-12 col-sm-12 col-md-3 col-lg-3 col-md-push-9 col-lg-push-9">
					<!-- 우측 업소상세정보 : S -->
					<section class="shop-det-info box">
						<h2 class="bold tog" onclick="toggleLayer('sinfo','tog');">업소상세정보</h2>
						<u class="mod-info" id="mod_info">정보수정요청</u>
						<dl class="dl-horizontal sinfo xsmall">
							<dt>최소금액</dt>
							<dd>15,000원 부터</dd>
							<dt>운영시간</dt>
							<dd>24시간 운영</dd>
	<dt>전화번호</dt>
	<dd><strong class="text-primary">050-4822-2619</strong></dd>
							<dt>업소소개</dt>
							<dd><div id="intro"></div>
						</dl>
						<!-- 정보수정요청 : S -->
						<fieldset class="mod-info-area">
							<h2 class="small">정보수정요청</h2>
							<button type="button" class="close-mod-area" onclick="fn_closeModInfo();return false;">닫기</button>
							<div class="modify small">
								<span class="tit">홍차이나</span> <em class="text-muted">| 서울특별시 마포구 용강동</em>
								<div class="row">
								  <label for="tel" class="col-lg-4 p0"><input type="checkbox" name="claim" id="tel" value="1"> 전화번호</label>
								  <label for="shopnm" class="col-lg-8 p0"><input type="checkbox" name="claim" id="shopnm" value="2"> 상호</label>
								  <label for="delivery" class="col-lg-4 p0"><input type="checkbox" name="claim" id="delivery" value="4"> 배달여부</label>
								  <label for="addr" class="col-lg-4 p0"><input type="checkbox" name="claim" id="addr" value="8"> 주소</label>
								  <label for="etc" class="col-lg-4 p0"><input type="checkbox" name="claim" id="etc" value="16"> 기타</label>
								</div>
								<textarea class="form-control" rows="4" id="claim_cont"></textarea>
								<p class="mod-info-note">배달시간, 카드결제유무 등 자세한<br>수정 내용을 적어주세요.<br>사진은 <a href="mailto:CS@woowahan.com">CS@woowahan.com</a> 으로 보내주세요.<br>수정내용은 확인하여 빠른 시간 내에<br>반영하겠습니다.</p>
							</div> <!--/.modify -->
							<div class="btn-group p0">
 								<button type="submit" class="btn btn-success" onclick="fn_shop_claim_opt();"><span class="btn-modify">수정요청하기</span></button>
 								<button type="button" class="btn btn-default" onclick="fn_closeModInfo();return false;"><span class="btn-mod-cancel">취소</span></button>
							</div> <!--./btn-group -->
						</fieldset> <!--/.mod-info-area -->
					</section> <!--/.shop-det-info -->

					<!-- 장바구니 // 내역 불러오기 : S -->
					<section class="cart box" id="cart_pause" style="display:none">
						<h2 class="bold" onclick="$(this).next().toggleClass('hidden-xs');">장바구니</h2>
						<div class="login hidden-xs hidden-sm">
							<p class="notice small text-center" >장바구니 내역을 불러오고 있습니다.</p>
						</div>
					</section>
					<section class="cart box" id="cart_ui" style="display:none">
					</section> <!--/.cart -->

				</div> <!--/.side-info -->
			</div>
			<p class="lead"><s:property value="resultClass.rest_price" /></p>
			<p class="lead"><s:property value="resultClass.rest_amount" /></p>
			<p class="lead"><s:property value="resultClass.rest_writer_address" /></p>
			<p class="lead">option?
			<select name="restopt_set">
				<s:iterator value="list" status="stat">
					<option value="<s:property value="restopt_subject"/>a<s:property value="restopt_priceplus" />">
						<s:property value="restopt_subject" /> (가격 : <s:property value="restopt_priceplus" />) 
					</option>
				</s:iterator>
			</select>
			<input name="submit" type="submit" value="구매하기"  />
					<input name="list" type="button" value="장바구니담기" onClick="javascript:location.href='ListRest.action?currentPage=<s:property value="currentPage"/>'" />
		</div>
		<!-- end of test message -->

	</div>
	<!-- /.container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="dist/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
	//------------------------------------------------------------------------------
	//PURPOSE : 우측 따라다니는 장바구니
	//CREATE  : 2013-11-20
	//MODIFY  :
	//------------------------------------------------------------------------------
	var cw, cl;
	line = 660;
	$(document).ready(function(){
		$(window).on('resize', function() {
			fn_getPosCart();
		}).trigger('resize');
	});
	
	function fn_getPosCart() {
		cw = $('.side-info').width() + 2;
		cl = $('.side-info').offset().left;
	
		$('#cart_ui').css('width',cw);
	}
	
	var browser = "Chrome";
	
	$(window).scroll(function() {
		//사파리 안되게
		if ( browser != 'Safari' )
		{
			if($(window).width() > 768) {
				if($(window).scrollTop() > line) {
					$('#cart_ui').css({
						'position' : 'fixed',
						'left' : cl,
						'top' : 10
					});
				} else {
					$('#cart_ui').css({
						'position' : 'inherit',
						'left' : 0,
						'top' : 0
					});
				}
			}
		}
	});
	</script>
	
</body>
</html>