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
				alert("약관에 동의 하지 않으면 상품을 등록할 수 없습니다.")
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
			위 프로그램의 저작권은 OOO에 있습니다.
			위의 사실을 동의할 경우 다음으로 이동할 수 있습니다.
		</textarea>

		<input name="agreecheck" type="checkbox" onClick="agreesubmit(this)"> <b>예, 동의 합니다</b>
			<input type="Submit" value=" 확 인 " disabled>
	</form>

	<script>
		document.forms.insertRestForm.agreecheck.checked = false
	</script>

</BODY>
</HTML>