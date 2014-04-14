<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
</HEAD>

<BODY>
	<script>
		function numcheck(val, jaego) {
			if (val > jaego - 1) {
				alert("재고량이 없습니다")
				document.frm.ea.value = jaego
			} else if (val < 2) {
				document.frm.ea.value = 1
			}
		}
		function up(val, jaego) {
			if (val > jaego - 1) {
				alert("재고량이 없습니다")
				document.frm.ea.value = jaego
			} else {
				document.frm.ea.value = (val / 1) + 1
			}
		}

		function down(val) {
			if (val < 2) {
				document.frm.ea.value = 1
			} else {
				document.frm.ea.value = val - 1
			}
		}
	</script>



	<table>
		<form name=frm>
			<tr>
				<td><input type="text" name="ea" class="form" size="2" value=1
					maxlength=2 onblur="numcheck(this.form.ea.value,10)"
					onkeyup="if(isNaN(this.value)) {alert('숫자만 입력해 주세요.');this.value=''};">
				</td>
				<td valign=middle height=25><input type=button value="+"
					onclick="up(this.form.ea.value,10)"
					STYLE="background-color: white; border: 0; height: 10px;; font-size: 12px"
					name=plus><br>
				<input type=button value="-" onclick="down(this.form.ea.value)"
					STYLE="background-color: white; border: 0; height: 10px; font-size: 12px"
					name=minus></td>
			</tr>
		</form>
	</table>

</BODY>
</HTML>
