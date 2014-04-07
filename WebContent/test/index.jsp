<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Employee Form</title>
<script type="text/javascript">
	var xmlReq; // 전역변수로 지정.
	// Ajax 객체 생성 과정
	function createAjax() {
		xmlReq = new XMLHttpRequest();
	}

	// Ajax 객체를 이용한 데이터 전송 과정
	function checkDupAjax() {
		createAjax();
		var reg_id = document.getElementById("reg_id").value;
		xmlReq.onreadystatechange = callBack; // 괄호 열고닫고가 틀리다.!
		xmlReq.open("GET", "empinfo.action?name=" + reg_id, true);
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
   <s:form method="post">
      <s:textfield name="name" label="Name" size="20" id="reg_id" onkeyup="checkDupAjax()" />
      <s:textfield name="age" label="Age" size="20" />
      <s:submit name="submit" label="Submit" align="center" />
   </s:form>
</body>
</html>