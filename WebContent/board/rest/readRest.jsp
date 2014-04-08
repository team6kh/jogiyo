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
			zoom : 10,
			center : latlng,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};

		map = new google.maps.Map(document.getElementById("map_canvas"),
				myOptions);
		geocoder = new google.maps.Geocoder();
		google.maps.event.addListener(map, 'click', codeCoordinate);
		/*아랫글에서 설명한 event를 이용 지도를 'click'하면 codeCoordinate함수를 실행합니다.
		   codeCoordinate함수는 클릭한 지점의 좌표를 가지고 주소를 찾는 함수입니다. */
		//function codeAddress();
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
		// marker를 빈 배열로 만들고, marker 배열의 개수를 0개로 만들어 marker 배열을 초기화합니다.

		marker.push(new google.maps.Marker({
			position : latLng,
			map : map
		}));
		// marker 배열에 새 marker object를 push 함수로 추가합니다.
	}

	/*클릭한 지점에 마커를 표시하는 함수입니다.
	   그런데 이 함수를 잘 봐야 하는 것이 바로 marker를 생성하지 않고 marker라는 배열 안에 Marker 
	 obejct  원소를 계속 추가하고 있습니다. 이는 매번 클릭할 때마다 새로운 마커를 생성하기 위함입니다. */

	//입력 받은 주소를 지오코딩 요청하고 결과를 마커로 지도에 표시합니다.
	function codeAddress(event) {
		if (geocodemarker.length > 0) {
			for (var i = 0; i < geocodemarker.length; i++) {
				geocodemarker[i].setMap(null);
			}

			geocodemarker = [];
			geocodemarker.length = 0;
		}
		//이 부분도 위와 같이 주소를 입력할 때 표시되는 marker가 매번 새로 나타나게 하기 위함입니다.

		var address = "대전"; // rest_writer_address 변수(판매자 테이블의 주소 컬럼)
		
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

	//클릭 이벤트 발생 시 그 좌표를 주소로 변환하는 함수입니다.
	function codeCoordinate(event) {
		Setmarker(event.latLng);
		//이벤트 발생 시 그 좌표에 마커를 생성합니다.
		// 좌표를 받아 reverse geocoding(좌표를 주소로 바꾸기)를 실행합니다.

		geocoder.geocode({
			'latLng' : event.latLng
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				if (results[1]) {
					infowindow.setContent(results[1].formatted_address);
					infowindow.open(map, marker[0]);
					//infowindow로 주소를 표시합니다.
				}
			}
		});
	}
</script>
</head>

<body onload="initialize()">
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
			<td align=left><s:property value="resultClass.rest_num" /></TD>
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
						</td>
					</tr>
					<tr>
						<td align="right" width=30%>
							<font color="#FF3636"><b>상품가</b></font>
						</td>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;<font size="7"><s:property value="resultClass.rest_price" /></font>
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
								<select name="optionPrice">
									<s:iterator value="list" status="stat">
										<option value="<s:property value="restopt_priceplus"/>"> 
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
				<input name="submit" type="submit" value="구매하기" />
				<input name="list" type="button" value="장바구니담기" onClick="javascript:location.href='ListRestAction.action?currentPage=<s:property value="currentPage"/>'" />
			</td>
		</tr>
		<tr>
			<td colspan=2>
				<font color="#FF3636"><b>상품 상세 설명</b></font>				
			</td>
			<td></td><td></td>
		</tr>
		<tr></tr>
		<tr>
			<td colspan=4>
				<img src = "<s:property value="resultClass.rest_destFile2"/>"/>
			</td>
		</tr>
	</table>
	
	<div id="map_canvas" style="width: 80%; height: 60%"></div>
		<input name="submit" type="submit" value="매장위치보기" onclick='codeAddress(); return false;' />
	<br>
	
	
		
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
</body>

