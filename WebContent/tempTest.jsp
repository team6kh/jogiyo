<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>




<HTML>
<HEAD>
<TITLE>Script</TITLE>

<script> 

	var checkobj

	function agreesubmit(el) {
		checkobj = el
		if (document.all || document.getElementById) {
			for (i = 0; i < checkobj.form.length; i++) {
				var tempobj = checkobj.form.elements[i]
				if (tempobj.type.toLowerCase() == "submit")
					tempobj.disabled = !checkobj.checked
			}
		}
	}

	function defaultagree(el) {
		if (!document.all && !document.getElementById) {
			if (window.checkobj && checkobj.checked)
				return true
			else {
				alert("����� ���� ���� ������ ��ǰ�� ����� �� �����ϴ�.")
				return false
			}
		}
	}
</script>

</HEAD>

<BODY>

	<p>
	<form name="insertRestForm" onSubmit="return defaultagree(this)">
		<textarea cols=30 rows=5> 
			�� ���α׷��� ���۱��� OOO�� �ֽ��ϴ�.
			���� ����� ������ ��� �������� �̵��� �� �ֽ��ϴ�.
		</textarea>

		<input name="agreecheck" type="checkbox" onClick="agreesubmit(this)"> <b>��, ���� �մϴ�</b>
			<input type="Submit" value=" Ȯ �� " disabled>
	</form>

	<script>
		document.forms.insertRestForm.agreecheck.checked = false
	</script>

</BODY>
</HTML>