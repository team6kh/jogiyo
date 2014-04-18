<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>


<script type="text/javascript">
	fields = 1;

	function addInput() {
		if (fields != 16) {
			
			document.getElementById('text').innerHTML += "<div>옵션명"+fields+".&nbsp&nbsp <input type='text' name='restopt_subject"+fields+"' value=''/> <br/>"
														+"옵션가"+fields+".&nbsp&nbsp <input type='text' name='restopt_priceplus"+fields+"' value=''/> 원<br/>"
														+"옵션사진"+fields+".&nbsp&nbsp <input type='file' name='optupload"+fields+"' />"
														+ " <a href='#' onclick='removeInput(this.parentNode)' />옵션제거</div>";
			fields += 1;
			//optMap.put("restopt_subject"+fields,"restopt_priceplus"+fields);
		} else {
			document.getElementById('stop').innerHTML += "<font color='#FF0000'>옵션은 최대 15개만 등록할 수 있습니다.</font><br/>";
			document.form.add.disabled=true;
		}
	}
	
	function removeInput(el) {
		if (fields > 0) {
			document.getElementById('stop').innerHTML = "";
			var parent = document.getElementById('text');
			parent.removeChild(el);
			fields -= 1;
		}
	}
</script>


<input type="button"  name="add" value="옵션추가" onclick="addInput()"/>


<div id="text">
	text
	<!-- 옵션추가 클릭시 여기에 태그 추가 -->
</div>
<div id="stop">
	warning
	<!-- 옵션추가 클릭시 여기에 태그 추가 -->
</div>