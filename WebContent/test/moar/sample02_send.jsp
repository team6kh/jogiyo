<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Ajax ������ ���� �׽�Ʈ 2</title>
<script type="text/javascript">
	var xmlReq; // ���������� ����.
	// Ajax ��ü ���� ����
	function createAjax() {
		xmlReq = new XMLHttpRequest();
	}

	// Ajax ��ü�� �̿��� ������ ���� ����
	function ajaxSend() {
		createAjax();
		var uid = document.getElementById("uid").value;
		xmlReq.onreadystatechange = callBack; // ��ȣ ����ݰ� Ʋ����.!
		xmlReq.open("GET", "checkDup.action?uid=" + uid, true);
		xmlReq.send(null);
		// send�� �������� �񵿱���̱� ������ ���α׷��� ��� ����ȴ�.
	}

	// �ݹ� �Լ� ����
	function callBack() {
		if (xmlReq.readyState == 4) {
			if (xmlReq.status == 200) {
				printData();
			}
		}
	}

	// ��� ��� ����
	function printData() {
		var result = xmlReq.responseXML;

		var rootNode = result.documentElement;
		// <root>true</root> , <root>false</root>
		var rootValue = rootNode.firstChild.nodeValue;
		var rootTag = document.getElementById("result");

		var idNode = rootNode.getElementsByTagName("id");
		var idValue = idNode.item(0).firstChild.nodeValue;
		var idTag = document.getElementById("idTxt");

		if (rootValue == "true") {
			rootTag.innerHTML = "��� ������ ���̵�";
			idTag.innerHTML = "<br>" + idValue;
		} else {
			rootTag.innerHTML = "�ߺ��� ���̵�";
			idTag.innerHTML = "<br>" + idValue;
		}
	}
</script>
</head>
<body>
	<div>ID �ߺ� Ȯ��</div>
	<div>
		���̵�: <input type="text" id="uid" onkeyup="ajaxSend()"> <br>
		<span id="result" style="color: BLUE;"></span>
		<span id="idTxt" style="color: green;"></span>
	</div>
</body>
</html>