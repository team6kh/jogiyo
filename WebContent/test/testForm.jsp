<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Ajax 데이터 전송 테스트 2</title>
<script type="text/javascript">
	var xmlReq; // 전역변수로 지정.
	// Ajax 객체 생성 과정
	function createAjax() {
		xmlReq = new XMLHttpRequest();
	}

	// Ajax 객체를 이용한 데이터 전송 과정
	function ajaxSend() {
		createAjax();
		var uid = document.getElementById("uid").value;
		xmlReq.onreadystatechange = callBack; // 괄호 열고닫고가 틀리다.!
		xmlReq.open("GET", "checkDup.action?uid=" + uid, true);
		xmlReq.send(null);
		// send가 끝나고나면 비동기식이기 때문에 프로그램이 계속 진행된다.
	}

	// 콜백 함수 과정
	function callBack() {
		if (xmlReq.readyState == 4) {
			if (xmlReq.status == 200) {
				printData();
			}
		}
	}

	// 결과 출력 과정
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
			rootTag.innerHTML = "사용 가능한 아이디";
			idTag.innerHTML = "<br>" + idValue;
		} else {
			rootTag.innerHTML = "중복된 아이디";
			idTag.innerHTML = "<br>" + idValue;
		}
	}
</script>
</head>
<body>
	<div>ID 중복 확인</div>
	<div>
		아이디: <input type="text" id="uid" onkeyup="ajaxSend()"> <br>
		<span id="result" style="color: BLUE;"></span>
		<span id="idTxt" style="color: green;"></span>
	</div>
</body>
</html>