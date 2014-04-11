<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>

<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
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
		height: 100%
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
</script>
</head>

<body onload="initialize()">
	
	<form name="readRestForm" action="payRest.action" method="post" >
		<TABLE border=0 width=90%>
			<tr align=center>
				<td align=left width=10%> <font color="#FF3636"><b>카테고리</b></font></td>
				<td align=left width=12%>
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
				</td>
				<td align=left width=10%>
					<s:if test="resultClass.rest_typecategory == 21">
						한식
					</s:if>
					<s:elseif test="resultClass.rest_typecategory == 22">
						양식
					</s:elseif>
					<s:elseif test="resultClass.rest_typecategory == 23">
						중식
					</s:elseif>
					<s:elseif test="resultClass.rest_typecategory == 24">
						일식
					</s:elseif>
					<s:elseif test="resultClass.rest_typecategory == 25">
						기타
					</s:elseif>
				</td>
				<td width=68%></td>
			</tr>
			<tr>
				<td align=left><font color="#FF3636"><b>상품번호</b></font></td>
				<td align=left>
					<s:property value="resultClass.rest_num" />
					<input type="hidden" name="rest_num" value=<s:property value="resultClass.rest_num" /> /> 
				</TD>
				<td colspan=2></TD>
			<tr></tr>
			<tr></tr>
			<tr>
				<td colspan=3 align=left>
					<img src = "<s:property value="resultClass.rest_destFile1"/>"/>
				</td>
				<td align=right>
					<table width=70%>
						<tr>
							<td align="right" width=30%>
								<font color="#FF3636"><b>상품명</b></font>
							</td>
							<td align="left" >
								&nbsp;&nbsp;&nbsp;&nbsp;<font size="7"><s:property value="resultClass.rest_subject" /></font>
								<input type="hidden" name="rest_subject" value=<s:property value="resultClass.rest_subject" />  />
							</td>
						</tr>
						<tr>
							<td align="right" width=30%>
								<font color="#FF3636"><b>상품가</b></font>
							</td>
							<td align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;<font size="7"><s:property value="resultClass.rest_price" /></font>
								<input type="hidden" name="rest_price" value=<s:property value="resultClass.rest_price" /> />
							</td>
						</tr>
						<tr>
							<td align="right" width=30%>
								<font color="#FF3636"><b>구매개수</b></font>
							</td>
							<td align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield name="rest_amount" theme="simple" value="%{resultClass.rest_amount}" size="2" maxlength="4"/>
							</td>
						</tr>
						<tr>
							<td align="right" width=30%>
								<font color="#FF3636"><b>옵션</b></font>
							</td>
							<td align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;
								
								<td>
									<select name="restopt_set">
										<s:iterator value="list" status="stat">
											<option value="<s:property value="restopt_subject"/>a<s:property value="restopt_priceplus" />">
												<s:property value="restopt_subject" /> (가격 : <s:property value="restopt_priceplus" />) 
											</option>
										</s:iterator>
									</select>
									
								</td>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr></tr>
			<tr>
				<td colspan=4 align="right">
					<input name="submit" type="submit" value="구매하기"  />
					<input name="list" type="button" value="장바구니담기" onClick="javascript:location.href='ListRest.action?currentPage=<s:property value="currentPage"/>'" />
				</td>
			</tr>
			<tr>
				<td colspan=2>
					<font color="#FF3636"><b>상품 상세설명</b></font>				
				</td>
				<td></td><td></td>
			</tr>
			<tr></tr>
			<tr>
				<td colspan=4>
					<img src = "<s:property value="resultClass.rest_destFile2"/>"/>
					<input type ="hidden" id="rest_writer_address" value="<s:property value="resultClass.rest_writer_address" />" />
				</td>
			</tr>
		</table>
		
		
		<table>
			<tr>
				<td align="right" colspan="6">
					<!--
					<input name="list" type="button" value="수정" class="inputb" onClick="javascript:location.href='modifyRest.action?rest_num=<s:property value="rest_num" />&currentPage=<s:property value="currentPage" />'">
					<input name="list" type="button" value="삭제" class="inputb" onClick="javascript:location.href='deleteRest.action?rest_num=<s:property value="rest_num" />&currentPage=<s:property value="currentPage" />'">
					 -->
					<input name="list" type="button" value="목록"  onClick="javascript:location.href='listRest.action?currentPage=<s:property value="currentPage" />'">
				</td>
			</tr>
		</table>
	</form>
	
	<div id="map_canvas" style="width: 80%; height: 60%"></div>
</body>

