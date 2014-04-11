<%@ page import="aegis.pgclient.*,java.text.*,java.net.*,java.lang.*" contentType="text/html; charset=euc-kr" %>

<%
	/**********************************************************************************************
	*
	* ���ϸ� : AGS_cancel_ing.jsp
	* ������������ : 2007/04/25
	* 
	* �ô�����Ʈ �÷����ο��� ���ϵ� ����Ÿ�� �޾Ƽ� ������ҿ�û�� �մϴ�.
	*
	* Copyright  AEGIS ENTERPRISE.Co.,Ltd. All rights reserved.
	*
	**********************************************************************************************/ 

	/****************************************************************************
	*
	* [1] �ô�����Ʈ ������ҽ� ����� ���� ��ż��� IP/Port ��ȣ
	*
	* ENCTYPE	: 0:�Ƚ�Ŭ��,�Ϲݰ��� 2:ISP
	* LOCALADDR	: PG������ ����� ����ϴ� ��ȣȭProcess�� ��ġ�� �ִ� IP 
	* LOCALPORT	: ��Ʈ
	*
	****************************************************************************/

	String LOCALADDR = "220.85.12.3";
	int LOCALPORT = 29760;
	String ENCTYPE = "0";
	 
	/****************************************************************************
	*
	* ����/��ҿ� ���� Ŭ���� ��ü ����
	*
	****************************************************************************/

	PgClientBean40 agspay = new PgClientBean40( LOCALADDR, LOCALPORT );

	/****************************************************************************
	*
	* [2] AGS_cancel.html �� ���� �Ѱܹ��� ����Ÿ
	*
	****************************************************************************/

	/*������*/
	//agspay.setValue("AgsPayHome","C:/htdocs/agspay");			//�ô�����Ʈ ������ġ ���丮 (������ �°� ����)
	agspay.setValue("AgsPayHome","/data2/local_docs/agspay40/jsp");		//�ô�����Ʈ ������ġ ���丮 (������ �°� ����)
	agspay.setValue("log","true");							//true : �αױ��, false : �αױ�Ͼ���.
	agspay.setValue("logLevel","INFO");						//�α׷��� : DEBUG, INFO, WARN, ERROR, FATAL (�ش� �����̻��� �α׸� ��ϵ�)
	agspay.setValue("Type", "Cancel");						//������(�����Ұ�)

	agspay.setValue("StoreId",request.getParameter("StoreId"));		//�������̵�
	agspay.setValue("AuthTy",request.getParameter("AuthTy"));			//��������
	agspay.setValue("SubTy",request.getParameter("SubTy"));			//�����������
	agspay.setValue("rApprNo",request.getParameter("rApprNo"));			//���ι�ȣ
	agspay.setValue("rApprTm",request.getParameter("rApprTm"));			//��������
	agspay.setValue("rDealNo",request.getParameter("rDealNo"));			//�ŷ���ȣ

	/****************************************************************************
	*
	* [4] �ô�����Ʈ ���������� ������ ��û�մϴ�.
	*
	****************************************************************************/
	agspay.startPay();

	/****************************************************************************
	*
	* [5] ��ҿ�û����� ���� ����DB ���� �� ��Ÿ �ʿ��� ó���۾��� �����ϴ� �κ��Դϴ�.
	*
	* �ſ�ī����� ��Ұ���� ���������� ���ŵǾ����Ƿ� DB �۾��� �� ��� 
	* ����������� �����͸� �����ϱ� �� �̺κп��� �ϸ�ȴ�.
	*
	* ���⼭ DB �۾��� �� �ּ���.
	* ��Ҽ������� : agspay.getResult("rCancelSuccYn") (����:y ����:n)
	* ��Ұ���޽��� : agspay.getResult("rCancelResMsg")
	*
	****************************************************************************/		
		
	if(agspay.getResult("rCancelSuccYn").equals("y") )
	{ 
		// ������ҿ� ���� ó���κ�
		out.println ("�ſ�ī�� ������Ұ� ����ó���Ǿ����ϴ�. [" + agspay.getResult("rCancelSuccYn")+"]"+ agspay.getResult("rCancelResMsg") );
	}
	else
	{
		// ������ҽ��п� ���� ����ó���κ�
		out.println ("�ſ�ī�� ������Ұ� ����ó���Ǿ����ϴ�. [" + agspay.getResult("rCancelSuccYn")+"]"+ agspay.getResult("rCancelResMsg") );
	}
%>
<html>
<head>
</head>
<body onload="javascript:frmAGS_cancel_ing.submit();">
<form name=frmAGS_cancel_ing method=post action=AGS_cancel_result.jsp>
<input type=hidden name=rStoreId value="<%=agspay.getResult("rStoreId")%>">
<input type=hidden name=rApprNo value="<%=agspay.getResult("rApprNo")%>">
<input type=hidden name=rApprTm value="<%=agspay.getResult("rApprTm")%>">
<input type=hidden name=rBusiCd value="<%=agspay.getResult("rBusiCd")%>">
<input type=hidden name=rCancelSuccYn value="<%=agspay.getResult("rCancelSuccYn")%>">
<input type=hidden name=rCancelResMsg value="<%=agspay.getResult("rCancelResMsg")%>">
<input type=hidden name=rOrdNo value="<%=agspay.getResult("rOrdNo")%>">
<input type=hidden name=rInstmt value="<%=agspay.getResult("rInstmt")%>">
<input type=hidden name=rAmt value="<%=agspay.getResult("rAmt")%>">
<input type=hidden name=rCardNm value="<%=agspay.getResult("rCardNm")%>">
<input type=hidden name=rCardCd value="<%=agspay.getResult("rCardCd")%>">
<input type=hidden name=rMembNo value="<%=agspay.getResult("rMembNo")%>">
<input type=hidden name=rAquiCd value="<%=agspay.getResult("rAquiCd")%>">
<input type=hidden name=rAquiNm value="<%=agspay.getResult("rAquiNm")%>">
<input type=hidden name=rDealNo value="<%=agspay.getResult("rDealNo")%>">
</form>
</body>
</html>
