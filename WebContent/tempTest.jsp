<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>


<script type="text/javascript">
	fields = 1;

	function addInput() {
		if (fields != 16) {
			
			document.getElementById('text').innerHTML += "<div>�ɼǸ�"+fields+".&nbsp&nbsp <input type='text' name='restopt_subject"+fields+"' value=''/> <br/>"
														+"�ɼǰ�"+fields+".&nbsp&nbsp <input type='text' name='restopt_priceplus"+fields+"' value=''/> ��<br/>"
														+"�ɼǻ���"+fields+".&nbsp&nbsp <input type='file' name='optupload"+fields+"' />"
														+ " <a href='#' onclick='removeInput(this.parentNode)' />�ɼ�����</div>";
			fields += 1;
			//optMap.put("restopt_subject"+fields,"restopt_priceplus"+fields);
		} else {
			document.getElementById('stop').innerHTML += "<font color='#FF0000'>�ɼ��� �ִ� 15���� ����� �� �ֽ��ϴ�.</font><br/>";
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


<input type="button"  name="add" value="�ɼ��߰�" onclick="addInput()"/>


<div id="text">
	text
	<!-- �ɼ��߰� Ŭ���� ���⿡ �±� �߰� -->
</div>
<div id="stop">
	warning
	<!-- �ɼ��߰� Ŭ���� ���⿡ �±� �߰� -->
</div>