<%@ page import="aegis.pgclient.*,java.text.*,java.net.*,java.lang.*" contentType="text/html; charset=euc-kr" %>

<%
/**********************************************************************************************
*
* ���ϸ� : AGS_pay_ing.jsp
* ������������ : 2009/5/12
*
* �ô�����Ʈ �÷����ο��� ���ϵ� ����Ÿ�� �޾Ƽ� ���ϰ�����û�� �մϴ�.
*
* Copyright AEGIS ENTERPRISE.Co.,Ltd. All rights reserved.
*
*
*  �� ���ǻ��� ��
*  1.  "|"(������) ���� ����ó�� �� �����ڷ� ����ϴ� �����̹Ƿ� ���� �����Ϳ� "|"�� �������
*   ������ ���������� ó������ �ʽ��ϴ�.(���� ������ ���� ���� ���� ����)	
**********************************************************************************************/

	/****************************************************************************
	*
	* [1] �ô�����Ʈ ������ ����� ���� ��ż��� IP/Port ��ȣ
	*
	* ENCTYPE		: ���� ������ ���� ����(0:�Ƚ�Ŭ��,�Ϲݰ��� 2:ISP)
	* LOCALADDR		: PG������ ����� ����ϴ� ��ȣȭProcess�� ��ġ�� �ִ� IP
	* LOCALPORT		: ��Ʈ
	*
	****************************************************************************/

	String LOCALADDR = "220.85.12.3";
	int LOCALPORT = 29760;
	String ENCTYPE = "0";

	/****************************************************************************
	*
	* [2]����/��ҿ� ���� Ŭ���� ��ü ����
	*
	****************************************************************************/

	PgClientBean40 agspay = new PgClientBean40( LOCALADDR, LOCALPORT );

	/****************************************************************************
	*
	* [3] AGS_pay.html �� ���� �Ѱܹ��� ����Ÿ
	*
	****************************************************************************/

	/*������*/
	//agspay.setValue("AgsPayHome","C:/htdocs/agspay40/jsp");			//�ô�����Ʈ ������ġ ���丮 (������ �°� ����)
	agspay.setValue("AgsPayHome","/home/local_docs/agspay40/jsp");		//�ô�����Ʈ ������ġ ���丮 (������ �°� ����)
	agspay.setValue("log","true");										//true : �αױ��, false : �αױ�Ͼ���.
	agspay.setValue("logLevel","INFO");									//�α׷��� : DEBUG, INFO, WARN, ERROR, FATAL (�ش� �����̻��� �α׸� ��ϵ�)
	agspay.setValue("UseNetCancel","true");								//true : ����� ���. false: ����� �̻��
	agspay.setValue("Type", "Pay");										//������(�����Ұ�)
	agspay.setValue("AuthTy", request.getParameter("AuthTy"));			//��������
	agspay.setValue("SubTy", request.getParameter("SubTy"));			//�����������
	agspay.setValue("StoreId", request.getParameter("StoreId"));		//�������̵�
	agspay.setValue("OrdNo", request.getParameter("OrdNo"));			//�ֹ���ȣ
	agspay.setValue("Amt", request.getParameter("Amt"));				//�ݾ�
	agspay.setValue("UserEmail", request.getParameter("UserEmail"));	//�ֹ����̸���
	agspay.setValue("ProdNm", request.getParameter("ProdNm"));			//��ǰ��

	/*�ſ�ī��&������»��*/
	agspay.setValue("MallUrl", request.getParameter("MallUrl"));		//����URL�ּ�
	agspay.setValue("UserId", request.getParameter("UserId"));			//ȸ�����̵�

	/*�ſ�ī����*/
	agspay.setValue("OrdNm", request.getParameter("OrdNm"));			//�ֹ��ڸ�
	agspay.setValue("OrdPhone", request.getParameter("OrdPhone") );		//�ֹ��ڿ���ó
	agspay.setValue("OrdAddr", request.getParameter("OrdAddr"));		//�ֹ����ּ�
	agspay.setValue("RcpNm", request.getParameter("RcpNm"));			//�����ڸ�
	agspay.setValue("RcpPhone", request.getParameter("RcpPhone") );		//�����ڿ���ó
	agspay.setValue("DlvAddr", request.getParameter("DlvAddr"));		//������ּ�
	agspay.setValue("Remark", request.getParameter("Remark"));			//��Ÿ�䱸����
	agspay.setValue("DeviId", request.getParameter("DeviId") );			//�ܸ�����̵�
	agspay.setValue("AuthYn", request.getParameter("AuthYn") );			//��������
	agspay.setValue("Instmt", request.getParameter("Instmt") );			//�Һΰ�����
	agspay.setValue("UserIp",request.getRemoteAddr());					//ȸ�� IP

	/*�ſ�ī��(ISP)*/
	agspay.setValue("partial_mm", request.getParameter("partial_mm") );			//�Ϲ��ҺαⰣ
	agspay.setValue("noIntMonth", request.getParameter("noIntMonth") );			//�������ҺαⰣ
	agspay.setValue("KVP_CURRENCY", request.getParameter("KVP_CURRENCY") );		//KVP_��ȭ�ڵ�
	agspay.setValue("KVP_CARDCODE", request.getParameter("KVP_CARDCODE") );		//KVP_ī����ڵ�
	agspay.setValue("KVP_SESSIONKEY", request.getParameter("KVP_SESSIONKEY") );	//KVP_SESSIONKEY
	agspay.setValue("KVP_ENCDATA", request.getParameter("KVP_ENCDATA") );		//KVP_ENCDATA
	agspay.setValue("KVP_CONAME", request.getParameter("KVP_CONAME") );			//KVP_ī���
	agspay.setValue("KVP_NOINT", request.getParameter("KVP_NOINT") );			//KVP_������=1 �Ϲ�=0
	agspay.setValue("KVP_QUOTA", request.getParameter("KVP_QUOTA") );			//KVP_�Һΰ���

	/*�ſ�ī��(�Ƚ�)*/
	agspay.setValue("CardNo", request.getParameter("CardNo") );			//ī���ȣ
	agspay.setValue("MPI_CAVV",request.getParameter("MPI_CAVV") );		//MPI_CAVV
	agspay.setValue("MPI_ECI",request.getParameter("MPI_ECI") );		//MPI_ECI
	agspay.setValue("MPI_MD64",request.getParameter("MPI_MD64") );		//MPI_MD64

	/*�ſ�ī��(�Ϲ�)*/
	agspay.setValue("ExpMon", request.getParameter("ExpMon") );			//��ȿ�Ⱓ(��)
	agspay.setValue("ExpYear", request.getParameter("ExpYear") );		//��ȿ�Ⱓ(��)
	agspay.setValue("Passwd", request.getParameter("Passwd") );			//��й�ȣ
	agspay.setValue("SocId", request.getParameter("SocId") );			//�ֹε�Ϲ�ȣ/����ڵ�Ϲ�ȣ

	/*������ü���*/
	agspay.setValue("ICHE_OUTBANKNAME", request.getParameter("ICHE_OUTBANKNAME"));		//��ü�����
	agspay.setValue("ICHE_OUTACCTNO", request.getParameter("ICHE_OUTACCTNO") );			//��ü���¹�ȣ
	agspay.setValue("ICHE_OUTBANKMASTER", request.getParameter("ICHE_OUTBANKMASTER"));	//��ü���¼�����
	agspay.setValue("ICHE_AMOUNT", request.getParameter("ICHE_AMOUNT") );													//��ü�ݾ�

	/*�ڵ������*/
	agspay.setValue("HP_SERVERINFO", request.getParameter("HP_SERVERINFO") );	//SERVER_INFO(�ڵ�������)
	agspay.setValue("HP_HANDPHONE", request.getParameter("HP_HANDPHONE") );		//HANDPHONE(�ڵ�������)
	agspay.setValue("HP_COMPANY", request.getParameter("HP_COMPANY") );			//COMPANY(�ڵ�������)
	agspay.setValue("HP_ID", request.getParameter("HP_ID") );					//HP_ID(�ڵ�������)
	agspay.setValue("HP_SUBID", request.getParameter("HP_SUBID") );				//HP_SUBID(�ڵ�������)
	agspay.setValue("HP_UNITType", request.getParameter("HP_UNITType") );		//HP_UNITType(�ڵ�������)
	agspay.setValue("HP_IDEN", request.getParameter("HP_IDEN") );				//HP_IDEN(�ڵ�������)
	agspay.setValue("HP_IPADDR", request.getParameter("HP_IPADDR") );			//HP_IPADDR(�ڵ�������)

	/*ARS���*/
	agspay.setValue("ARS_NAME", request.getParameter("ARS_NAME"));			//ARS_NAME(ARS����)
	agspay.setValue("ARS_PHONE", request.getParameter("ARS_PHONE") );		//ARS_PHONE(ARS����)

	/*������»��*/
	agspay.setValue("VIRTUAL_CENTERCD", request.getParameter("VIRTUAL_CENTERCD"));	//�����ڵ�
	agspay.setValue("VIRTUAL_DEPODT", request.getParameter("VIRTUAL_DEPODT") );		//�Աݿ�����
	agspay.setValue("ZuminCode", request.getParameter("ZuminCode") );				//�ֹι�ȣ
	agspay.setValue("MallPage", request.getParameter("MallPage"));					//���� ��/��� �뺸 ������
	agspay.setValue("VIRTUAL_NO", request.getParameter("VIRTUAL_NO") );				//������¹�ȣ

	/*����������ũ�λ��*/
	agspay.setValue("ES_SENDNO", request.getParameter("ES_SENDNO") );				//����������ũ��(����������ȣ)

	/*������ü(����) ���� ��� ����*/
	agspay.setValue("ICHE_SOCKETYN", request.getParameter("ICHE_SOCKETYN"));		//������ü(����) ��� ����
	agspay.setValue("ICHE_POSMTID", request.getParameter("ICHE_POSMTID"));			//������ü(����) �̿����ֹ���ȣ
	agspay.setValue("ICHE_FNBCMTID", request.getParameter("ICHE_FNBCMTID"));		//������ü(����) FNBC�ŷ���ȣ
	agspay.setValue("ICHE_APTRTS", request.getParameter("ICHE_APTRTS"));			//������ü(����) ��ü �ð�
	agspay.setValue("ICHE_REMARK1", request.getParameter("ICHE_REMARK1"));			//������ü(����) ��Ÿ����1
	agspay.setValue("ICHE_REMARK2", request.getParameter("ICHE_REMARK2"));			//������ü(����) ��Ÿ����2
	agspay.setValue("ICHE_ECWYN", request.getParameter("ICHE_ECWYN"));				//������ü(����) ����ũ�ο���
	agspay.setValue("ICHE_ECWID", request.getParameter("ICHE_ECWID"));				//������ü(����) ����ũ��ID
	agspay.setValue("ICHE_ECWAMT1", request.getParameter("ICHE_ECWAMT1"));			//������ü(����) ����ũ�ΰ����ݾ�1
	agspay.setValue("ICHE_ECWAMT2", request.getParameter("ICHE_ECWAMT2"));			//������ü(����) ����ũ�ΰ����ݾ�2
	agspay.setValue("ICHE_CASHYN", request.getParameter("ICHE_CASHYN"));			//������ü(����) ���ݿ��������࿩��
	agspay.setValue("ICHE_CASHGUBUN_CD", request.getParameter("ICHE_CASHGUBUN_CD"));	//������ü(����) ���ݿ���������
	agspay.setValue("ICHE_CASHID_NO", request.getParameter("ICHE_CASHID_NO"));		//������ü(����) ���ݿ������ź�Ȯ�ι�ȣ

	/*������ü-�ڷ���ŷ(����) ���� ��� ����*/
	agspay.setValue("ICHEARS_SOCKETYN", request.getParameter("ICHEARS_SOCKETYN"));		//�ڷ���ŷ������ü(����) ��� ����
	agspay.setValue("ICHEARS_ADMNO", request.getParameter("ICHEARS_ADMNO"));			//�ڷ���ŷ������ü ���ι�ȣ       
	agspay.setValue("ICHEARS_POSMTID", request.getParameter("ICHEARS_POSMTID"));		//�ڷ���ŷ������ü �̿����ֹ���ȣ
	agspay.setValue("ICHEARS_CENTERCD", request.getParameter("ICHEARS_CENTERCD"));		//�ڷ���ŷ������ü �����ڵ�      
	agspay.setValue("ICHEARS_HPNO", request.getParameter("ICHEARS_HPNO"));				//�ڷ���ŷ������ü �޴�����ȣ   
	agspay.setValue("AGS_HASHDATA", request.getParameter("AGS_HASHDATA"));				// ���� �ؽ� ���� 
	
	/****************************************************************************
	*
	* [4] �ô�����Ʈ ���������� ������ ��û�մϴ�.
	*
	****************************************************************************/

	agspay.startPay();

	/****************************************************************************
	*
	* [5] ��������� ���� ����DB ���� �� ��Ÿ �ʿ��� ó���۾��� �����ϴ� �κ��Դϴ�.
	*
	*	�Ʒ��� ��������� ���Ͽ� �� �������ܺ� ����������� ����Ͻ� �� �ֽ��ϴ�.
	*	
	*	-- ������ --
	*	��üID : agspay.getResult("rStoreId")
	*	�ֹ���ȣ : agspay.getResult("rOrdNo")
	*	��ǰ�� : agspay.getResult("rProdNm")
	*	�ŷ��ݾ� : agspay.getResult("rAmt")
	*	�������� : agspay.getResult("rSuccYn") (����:y ����:n)
	*	����޽��� : agspay.getResult("rResMsg")
	*
	*	1. �ſ�ī��
	*	
	*	�����ڵ� : agspay.getResult("rBusiCd")
	*	�ŷ���ȣ : agspay.getResult("rDealNo")
	*	���ι�ȣ : agspay.getResult("rApprNo")
	*	�Һΰ��� : agspay.getResult("rInstmt")
	*	���νð� : agspay.getResult("rApprTm")
	*	ī����ڵ� : agspay.getResult("rCardCd")
	*
	*	2.������ü(���ͳݹ�ŷ/�ڷ���ŷ)
	*	����ũ���ֹ���ȣ : agspay.getResult("ES_SENDNO") (����ũ�� ������)
	*
	*	3.�������
	*	��������� ���������� ������¹߱��� �������� �ǹ��ϸ� �Աݴ����·� ���� ���� �Ա��� �Ϸ��� ���� �ƴմϴ�.
	*	���� ������� �����Ϸ�� �����Ϸ�� ó���Ͽ� ��ǰ�� ����Ͻø� �ȵ˴ϴ�.
	*	������ ���� �߱޹��� ���·� �Ա��� �Ϸ�Ǹ� MallPage(���� �Ա��뺸 ������(�������))�� �Աݰ���� ���۵Ǹ�
	*	�̶� ��μ� ������ �Ϸ�ǰ� �ǹǷ� �����Ϸῡ ���� ó��(��ۿ�û ��)��  MallPage�� �۾����ּž� �մϴ�.
	*	�������� : agspay.getResult("rAuthTy") (������� �Ϲ� : vir_n ��Ŭ�� : vir_u ����ũ�� : vir_s)
	*	�������� : agspay.getResult("rApprTm")
	*	������¹�ȣ : agspay.getResult("rVirNo")
	*
	*	4.�ڵ�������
	*	�ڵ��������� : agspay.getResult("rHP_DATE")
	*	�ڵ������� TID : agspay.getResult("rHP_TID")
	*
	*	5.ARS����
	*	ARS������ : agspay.getResult("rHP_DATE")
	*	ARS���� TID : agspay.getResult("rHP_TID")
	*
	****************************************************************************/
	
	if(agspay.getResult("rSuccYn").equals("y"))
	{ 
		if( agspay.getResult("AuthTy").equals("virtual") ){
			//������°����� ��� �Ա��� �Ϸ���� ���� �Աݴ�����(������� �߱޼���)�̹Ƿ� ��ǰ�� ����Ͻø� �ȵ˴ϴ�. 

		}else{
			// ���������� ���� ����ó���κ�
			//out.println ("������ ����ó���Ǿ����ϴ�. [" + agspay.getResult("rSuccYn") + "]" + agspay.getResult("rResMsg"));
		}
	}
	else
	{
		// �������п� ���� ����ó���κ�
		//out.println ("������ ����ó���Ǿ����ϴ�. [" + agspay.getResult("rSuccYn") + "]" + agspay.getResult("rResMsg"));
	}
	

	/*******************************************************************
	* [6] ������ ����ó������ ������ ��� agspay.getResult("NetCancID") ���� �̿��Ͽ�                                     
	* ��������� ���� ��Ȯ�ο�û�� �� �� �ֽ��ϴ�.
	* 
	* �߰� �����ͼۼ����� �߻��ϹǷ� ������ ����ó������ �ʾ��� ��쿡�� ����Ͻñ� �ٶ��ϴ�. 
	*
	* ����� :
	* agspay.checkPayResult(agspay.getResult("NetCancID"));
	* 
	*******************************************************************/

	
	//agspay.setValue("Type", "Pay"); // ����
	//agspay.checkPayResult(agspay.getResult("NetCancID"));
	
	
	/*******************************************************************
	* [7] ����DB ���� �� ��Ÿ ó���۾� ������н� �������                                      
	*   
	* cancelReq : "true" ������ҽ���, "false" ������ҽ������.
	*
	* ��������� ���� ����ó���κ� ���� �� �����ϴ� ���    
	* �Ʒ��� �ڵ带 �����Ͽ� �ŷ��� ����� �� �ֽ��ϴ�.
	*	��Ҽ������� : agspay.getResult("rCancelSuccYn") (����:y ����:n)
	*	��Ұ���޽��� : agspay.getResult("rCancelResMsg")
	*                                                      
	* ���ǻ��� :
	* �������(virtual)�� ������� ����� �������� �ʽ��ϴ�.
	*******************************************************************/
	
	// ����ó���κ� ������н� cancelReq�� "true"�� �����Ͽ� 
	// ������Ҹ� ����ǵ��� �� �� �ֽ��ϴ�.
	// cancelReq�� "true"������ ���������� �������� �Ǵ��ϼž� �մϴ�.
	/*
	String cancelReq = "false";

	if( cancelReq.equals("true") )
	{
		agspay.setValue("Type", "Cancel"); // ����
		agspay.setValue("CancelMsg", "DB FAIL"); // ��һ���
		agspay.startPay();
	}
	*/
	
%>
<html>
<head>
</head>
<!-- <body > -->
<body onload="javascript:frmAGS_pay_ing.submit();">
<form name=frmAGS_pay_ing method=post action=AGS_pay_result.jsp>

<!-- �� ���� ���� ��� ���� -->
<input type=hidden name=AuthTy value="<%=agspay.getResult("AuthTy")%>">		<!-- �������� -->
<input type=hidden name=SubTy value="<%=agspay.getResult("SubTy")%>">		<!-- ����������� -->
<input type=hidden name=rStoreId value="<%=agspay.getResult("rStoreId")%>">	<!-- �������̵� -->
<input type=hidden name=rOrdNo value="<%=agspay.getResult("rOrdNo")%>">		<!-- �ֹ���ȣ -->
<input type=hidden name=rProdNm value="<%=agspay.getResult("ProdNm")%>">	<!-- ��ǰ�� -->
<input type=hidden name=rAmt value="<%=agspay.getResult("rAmt")%>">			<!-- �����ݾ� -->
<input type=hidden name=rOrdNm value="<%=agspay.getResult("OrdNm")%>">		<!-- �ֹ��ڸ� -->

<input type=hidden name=AGS_HASHDATA value="<%=agspay.getResult("AGS_HASHDATA")%>">		<!-- ���� �ؽ� ���� -->

<input type=hidden name=rSuccYn value="<%=agspay.getResult("rSuccYn")%>">	<!-- �������� -->
<input type=hidden name=rResMsg value="<%=agspay.getResult("rResMsg")%>">	<!-- ����޽��� -->
<input type=hidden name=rApprTm value="<%=agspay.getResult("rApprTm")%>">	<!-- �����ð� -->

<!-- �ſ�ī�� ���� ��� ���� -->
<input type=hidden name=rBusiCd value="<%=agspay.getResult("rBusiCd")%>">	<!-- (�ſ�ī�����)�����ڵ� -->
<input type=hidden name=rApprNo value="<%=agspay.getResult("rApprNo")%>">	<!-- (�ſ�ī�����)���ι�ȣ -->
<input type=hidden name=rCardCd value="<%=agspay.getResult("rCardCd")%>">	<!-- (�ſ�ī�����)ī����ڵ� -->
<input type=hidden name=rDealNo value="<%=agspay.getResult("rDealNo")%>">	<!-- (�ſ�ī�����)�ŷ���ȣ -->

<input type=hidden name=rCardNm value="<%=agspay.getResult("rCardNm")%>">	<!-- (�Ƚ�Ŭ��,�Ϲݻ��)ī���� -->
<input type=hidden name=rMembNo value="<%=agspay.getResult("rMembNo")%>">	<!-- (�Ƚ�Ŭ��,�Ϲݻ��)��������ȣ -->
<input type=hidden name=rAquiCd value="<%=agspay.getResult("rAquiCd")%>">	<!-- (�Ƚ�Ŭ��,�Ϲݻ��)���Ի��ڵ� -->
<input type=hidden name=rAquiNm value="<%=agspay.getResult("rAquiNm")%>">	<!-- (�Ƚ�Ŭ��,�Ϲݻ��)���Ի�� -->


<!-- ������ü ���� ��� ���� -->
<input type=hidden name=ICHE_OUTBANKNAME value="<%=agspay.getResult("ICHE_OUTBANKNAME")%>">		<!-- ��ü����� -->
<input type=hidden name=ICHE_OUTBANKMASTER value="<%=agspay.getResult("ICHE_OUTBANKMASTER")%>">	<!-- ��ü���¿����� -->
<input type=hidden name=ICHE_AMOUNT value="<%=agspay.getResult("ICHE_AMOUNT")%>">				<!-- ��ü�ݾ� -->

<!-- �ڵ��� ���� ��� ���� -->
<input type=hidden name=rHP_HANDPHONE value="<%=agspay.getResult("HP_HANDPHONE")%>">			<!-- �ڵ�����ȣ -->
<input type=hidden name=rHP_COMPANY value="<%=agspay.getResult("HP_COMPANY")%>">				<!-- ��Ż��(SKT,KTF,LGT) -->
<input type=hidden name=rHP_TID value="<%=agspay.getResult("rHP_TID")%>">						<!-- ����TID -->
<input type=hidden name=rHP_DATE value="<%=agspay.getResult("rHP_DATE")%>">						<!-- �������� -->

<!-- ARS ���� ��� ���� -->
<input type=hidden name=rARS_PHONE value="<%=agspay.getResult("ARS_PHONE")%>">							<!-- ARS��ȣ -->

<!-- ������� ���� ��� ���� -->
<input type=hidden name=rVirNo value="<%=agspay.getResult("rVirNo")%>">							<!-- ������¹�ȣ -->
<input type=hidden name=VIRTUAL_CENTERCD value="<%=agspay.getResult("VIRTUAL_CENTERCD")%>">		<!-- �Աݰ�����������ڵ�(�츮����:20,��������:88) -->

<input type=hidden name=mTId value="<%=agspay.getResult("mTId")%>">								

<!-- ����������ũ�� ���� ��� ���� -->
<input type=hidden name=ES_SENDNO value="<%=agspay.getResult("ES_SENDNO")%>">					<!-- ����������ũ��(������ȣ) -->

</form>
</body>
</html>
