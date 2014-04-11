/*
������ ��� ��) javac -classpath /usr/local/resin/lib/jsdk22.jar:/usr/local/resin/lib/log4j-1.2.1.jar:. PgClientBean.java
*/

package aegis.pgclient;

import java.io.*;
import java.util.*;
import java.net.*;
import java.text.*;

import javax.servlet.jsp.JspWriter;
import org.apache.log4j.*;

/******************************************************************************
	pgŬ���̾�Ʈ ��� ���
******************************************************************************/

/****************************************************************************
* �� ���� ���� ������ ���� ���� ���� ����
*
* �� AuthTy  = "card"		�ſ�ī�����
*	 - SubTy = "isp"		��������ISP
*	 - SubTy = "visa3d"		�Ƚ�Ŭ��
*	 - SubTy = "normal"		�Ϲݰ���
*
* �� AuthTy  = "iche"		������ü
*
* �� AuthTy  = "virtual"	�������(�������Ա�)
* 
* �� AuthTy  = "hp"			�ڵ�������
* 
* �� AuthTy  = "ars"		ARS����
*
****************************************************************************/

public class PgClientBean40
{
	private Socket		socket;
	private DataInputStream   	in;
  	private DataOutputStream  	out;
	
	private String IPAddr;
	private int	   Port;
	
	private String ErrMsg;
	private int    ErrCd;
	private String SplitData[]     = new String[15];	// ���� ������ ����
	private String ISP_AuthSplitData[] = new String[9];
	private String ISP_CnclSplitData[] = new String[7];
	private String HP_SplitData[]  = new String[5];
	private String HP_CnclSplitData[]  = new String[6];
	private String ARS_SplitData[]  = new String[5];
    private String ARS_CnclSplitData[]  = new String[6];
    private String VIR_SplitData[] = new String[6];
    private String VirAcctReturnData[] = new String[6];
	private String Iche_Ibk_SplitData[] = new String[7];
	private String Iche_Ibk_CnclSplitData[] = new String[6];
	private String Iche_Tbk_SplitData[] = new String[6];
	private String Iche_Tbk_CnclSplitData[] = new String[5];
	private Hashtable requestData;
	private Hashtable resultData;
	private String ENCTYPE = "";
	private String nowDate = "";
	
	private static Layout layout ;
    private static FileAppender appender;
    private Logger logger;
    private static int processid;
    private static StringBuffer temp;
	private String pid;
    


	/****************************************************************
		������( �����ּ�, ��Ʈ )
	****************************************************************/
	public PgClientBean40(String IPAddr, int Port)
	{
		this.IPAddr = IPAddr;
		this.Port   = Port;
		requestData = new Hashtable();
		resultData = new Hashtable();
		Calendar c = Calendar.getInstance();
		String month = "" + (c.get(Calendar.MONTH) + 1);
		if(month.length()==1) month="0"+month;
		String date=""+c.get(Calendar.DATE);
		if(date.length()==1) date="0"+date;
		nowDate = ""+c.get(Calendar.YEAR) + month + date;
	}

	/****************************************************************
		Aegis ����/��� ó��
	****************************************************************/
	public boolean startPay()
	{
		pid = this.getPID();
		//logger ��ü����
		if( !createLogger() ) { 
			System.out.println("�α����� ���� ����");			
		}
		if( logger == null )
		{
			setPayResult( "rSuccYn", "n");
			setPayResult( "rResMsg",  "Log Object Create Fail");
		}
		if( getValue("Type").equals( "Pay" ) ) 
		{
			logger.info(pid + "Start Pay Transaction !!! " );
		}else if( getValue("Type").equals( "Cancel" ) ) 
		{
			logger.warn(pid + "Start Cancel Transaction !!! " );
		}
		logger.info(pid + "Start Send Data To PG  [" );
		printHashtable(requestData);
		logger.info(pid + "] End  Send Data To PG " );

				
		if( getValue("Type").equals( "Pay" ) )	// Pay Process
		{
			//Make NetCancelID
			if (!MakeNetCancID())
			{
				logger.error(pid + "MakeNetCancID Fail" );
			}

			if(!pay())
			{
				logger.error(pid + "Pay Process Fail" );
				netCancel();
			}

		}else if(getValue("Type").equals( "Cancel" ) )	// Cancel Process
		{
			//������´� ����� �� ����.
			if(getValue("AuthTy").equals( "virtual" ) ){
				setPayResult( "rCancSuccYn", "n");
				setPayResult( "rCancResMsg",  "cannot cancel AuthTy[" + getValue("AuthTy") + "]");
				logger.warn(pid + "cannot cancel AuthTy[" + getValue("AuthTy") + "]" );
				logger.warn(pid + "Cancel Process Fail" );
				return false;
			}
			
			if(!netCancel())
			{
				logger.error(pid + "Cancel Process Fail" );
			}
		}else{
			logger.error(pid + "Unknown Type[" + getValue("Type") + "]");
			setPayResult( "rSuccYn", "n");
			setPayResult( "rResMsg",  "Unknown Type[" + getValue("Type") + "]");
			return false;
		}

		logger.info(pid + "Start Result Data From PG  [" );
		printHashtable(resultData);
		logger.info(pid + "] End  Result Data From PG " );

		return true;
	}	

	/****************************************************************
		Aegis ���� ó��
	****************************************************************/
	public boolean pay()
	{	
		try{

			/****************************************************************************
			*  ����Ÿ�� ��ȿ���� �˻��մϴ�.
			****************************************************************************/

			StringBuffer ERRMSG = new StringBuffer( "" );
			boolean result = false;
			
			if( checkNull(  getValue("StoreId") ) )
			{
				ERRMSG.append( "�������̵� �Է� ���� Ȯ�ο�� <br>" );	//�������̵�
			}

			if( checkNull(  getValue("OrdNo") ) )
			{
				ERRMSG.append( "�ֹ���ȣ �Է� ���� Ȯ�ο�� <br>" );	//�ֹ���ȣ
			}

			if( checkNull(  getValue("ProdNm") ) )
			{
				ERRMSG.append( "��ǰ�� �Է� ���� Ȯ�ο�� <br>" );		//��ǰ��
			}

			if( checkNull(  getValue("Amt") ) )
			{
				ERRMSG.append( "�ݾ� �Է� ���� Ȯ�ο�� <br>" );		//�ݾ�
			}

			if( checkNull(  getValue("DeviId") ) )
			{
				ERRMSG.append( "�ܸ�����̵� �Է� ���� Ȯ�ο�� <br>" );//�ܸ�����̵�
			}

			if( checkNull(  getValue("AuthYn") ) )
			{
				ERRMSG.append( "�������� �Է� ���� Ȯ�ο�� <br>" );	//��������
			}

			if( ERRMSG.toString().equals( "" ) )
			{			
				logger.info(pid + "Start Pay[" + getValue("AuthTy") + "] process" );

				if( getValue("AuthTy").equals( "card" ) )
				{
					if( getValue("SubTy").equals( "isp" ) )
					{
						/****************************************************************************
						* [4] �ſ�ī����� - ISP
						****************************************************************************/						
						ENCTYPE = "2";						
						result = ISP_ApproveRequest( ENCTYPE, "plug15" ) ;
					}
					else if( getValue("SubTy").equals( "visa3d" ) || getValue("SubTy").equals( "normal" ) )
					{
						/****************************************************************************
						* [5] �ſ�ī����� - VISA3D, �Ϲ�
						****************************************************************************/						
						ENCTYPE = "0";						
						result = ApproveRequest( ENCTYPE, "plug15") ;							
					}
				}
				else if( getValue("AuthTy").equals( "iche" ) && getValue("ICHE_SOCKETYN").equals( "Y" ))
				{
					/****************************************************************************
					* [6] ���ͳݹ�ŷ ������ü(����) ó��
					****************************************************************************/
					ENCTYPE = "R";						
					result = Iche_Ibk_ApproveRequest( ENCTYPE, "RB-PayReq") ;							
					
				}else if( getValue("AuthTy").equals( "iche" ) && getValue("ICHEARS_SOCKETYN").equals( "Y" ))
				{
					/****************************************************************************
					* [7] �ڷ���ŷ ������ü(����) ó��
					****************************************************************************/
					ENCTYPE = "B";						
					result = Iche_Tbk_ApproveRequest( ENCTYPE, "TB-PayReq") ;							
					
				}else if( getValue("AuthTy").equals( "virtual" ) )
				{
					/****************************************************************************
					* [8] ������� ����
					****************************************************************************/					
					ENCTYPE = "V";					
					result = VIR_ApproveRequest( ENCTYPE, "vir_n" );						
				}
				
				else if( getValue("AuthTy").equals( "hp" ) )
				{
					/***************************************************************************
					* [9]�ڵ�������
					****************************************************************************/					
					ENCTYPE = "h";					
					result = HP_ApproveRequest( ENCTYPE, "Bill" );						
				}
				else if( getValue("AuthTy").equals( "ars" ) )
				{
					/***************************************************************************
					* [10]ARS����
					****************************************************************************/					
					ENCTYPE = "A";					
					result = ARS_ApproveRequest( ENCTYPE, "ABill" );						
				}

				if( result == false)
				{
					if( getErrCd() == -1 )
					{
						setPayResult( "rSuccYn", "n");
						setPayResult( "rResMsg", "��ſ���(sock)�� ���� ���ΰ���");
						return false;
					}
					else if( getErrCd() == -2 )
					{
						setPayResult( "rSuccYn", "n");
						setPayResult( "rResMsg", "��ſ���(msg)�� ���� ���ΰ���");
						return false;
					}
				}else{
					/** ����޼����� Parsing ó���Ͽ� ������� ������ �Ҵ��Ѵ�. **/
					parseMsg();
				}

				logger.info(pid + "End   Pay[" + getValue("AuthTy") + "] process" );
			}
			else
			{
				logger.error(pid + "Pay Transaction Fail [" + ERRMSG.toString() + "]" );
				setPayResult( "rSuccYn", "n");
				setPayResult( "rResMsg",  ERRMSG.toString());
			}

		}catch( Exception e )
		{
			/** ��Ÿ ������ ���� ��û ���� ó�� **/
			
			setPayResult( "rSuccYn", "n");
			setPayResult( "rResMsg", "�ý��ۿ����� ���� ���� ����");
			return false;
		}
		return true;
	}	/* startpay() End. */


	/****************************************************************
		Aegis ��� ó��
	****************************************************************/

	public boolean netCancel()
	{	
		logger.warn(pid + "Start netCancel" );
		try{
			boolean result = false;
		
			if( !(getValue("UseNetCancel") == "true" || getValue("Type") == "Cancel") )
			{
				logger.error(pid + "UseNetCancel Value is false" );
				setPayResult( "rCancelSuccYn", "n");
				setPayResult( "rCancelResMsg",  "UseNetCancel Value is false");
				return false;
			}

			
			if( getValue("AuthTy").equals( "card" ) && getValue("SubTy").equals( "isp" ) )
			{
				/****************************************************************************
				* [1] �ſ�ī�������� - ISP
				****************************************************************************/
				ENCTYPE = "2";	
				result = ISP_CancelRequest( ENCTYPE, getValue("AuthTy")) ;
					
			}
			else if( getValue("AuthTy").equals( "card" ) && (getValue("SubTy").equals( "visa3d" ) || getValue("SubTy").toString().equals( "normal" )) )
			{
				/****************************************************************************
				* [2] �ſ�ī�������� - VISA3D, �Ϲ�
				****************************************************************************/			
				ENCTYPE = "0";	
				result = CancelRequest( ENCTYPE, getValue("AuthTy") ) ;
								
			}
			else if( getValue("AuthTy").equals( "iche" ) && getValue("ICHE_SOCKETYN").equals("Y"))
			{
				/****************************************************************************
				* [3] ������ü��� - ���ͳݹ�ŷ
				****************************************************************************/			
				ENCTYPE = "R";	
				result = Iche_Ibk_CancelRequest( ENCTYPE, "RB-CanReq" ) ;
			
			}else if( getValue("AuthTy").equals( "iche" ) && getValue("ICHEARS_SOCKETYN").equals("Y"))
			{
				/****************************************************************************
				* [4] ������ü��� - �ڷ���ŷ
				****************************************************************************/	
				ENCTYPE = "B";	
				result = Iche_Tbk_CancelRequest( ENCTYPE, "TB-CanReq" ) ;
				
			}else if( getValue("AuthTy").equals( "hp" ) )
			{
				/****************************************************************************
				* [5] �ڵ����������
				****************************************************************************/			
				ENCTYPE = "h";	
				result = HP_CancelRequest( ENCTYPE, getValue("AuthTy") ) ;
				
			}else if( getValue("AuthTy").equals( "ars" ) )
			{
				/****************************************************************************
				* [6] ARS�������
				****************************************************************************/			
				ENCTYPE = "A";	
				result = ARS_CancelRequest( ENCTYPE, getValue("AuthTy") ) ;
				
			}else{
				logger.warn(pid + "Cannot Cancel AuthTy[" + getValue("AuthTy") + "]");
				setPayResult( "rCancelSuccYn", "n");
				setPayResult( "rCancelResMsg", "Cannot Cancel AuthTy[" + getValue("AuthTy") + "]");
				return false;
			}

			if( result == false)
			{
				if( getErrCd() == -1 )
				{
					logger.error(pid + "��ſ���(sock)�� ���� ���ΰ���" );
					setPayResult( "rCancelSuccYn", "n");
					setPayResult( "rCancelResMsg", "��ſ���(sock)�� ���� ���ΰ���");
					return false;
				}
				else if( getErrCd() == -2 )
				{
					logger.error(pid + "��ſ���(msg)�� ���� ���ΰ���" );
					setPayResult( "rCancelSuccYn", "n");
					setPayResult( "rCancelResMsg", "��ſ���(msg)�� ���� ���ΰ���");
					return false;
				}
			}else{
				parseCancelMsg();
			}
		}
		catch( Exception e )
		{
			/** ��Ÿ ������ ���� ��û ���� ó�� **/
			setPayResult( "rCancelSuccYn", "n");
			setPayResult( "rCancelResMsg",  "�ý��ۿ����� ���� ���� ���� [" + e.toString() + "]");
			return false;
		}
		logger.warn(pid + "End netCancel" );
		
		//��������� ���з� ����
		setPayResult( "rSuccYn", "n" );
		setPayResult( "rResMsg", getValue("CancelMsg") );
		
		return true;

	}	/*	netCancel() End	*/

	/****************************************************************
		���Ÿ޼��� Parsing
	****************************************************************/
	public boolean parseMsg()
	{	
		try{
			logger.info(pid + "Start parseMsg[" + getValue("AuthTy") + "] " );

			if( getValue("AuthTy").equals( "card" ) )
			{
				if( getValue("SubTy").equals( "isp" ) )
				{
					/****************************************************************************
					*
					* [4] �ſ�ī����� - ISP
					* 
					* -- ���� ���� ���� ����
					* + �����ͱ���(6) + ������
					* + ������ ����(������ ������ "|"�� �Ѵ�.
					* ��üID(20)		| �����ڵ�(4)	| �ŷ�������ȣ(6)	| ���ι�ȣ(8)		| 
					* �ŷ��ݾ�(12)	| ��������(1)	| ���л���(20)	| ���νð�(14)	| 
					* ī����ڵ�(4) 	|
					*    
					****************************************************************************/
					
						
					String rSplitData[] = new String[9];
					rSplitData = getISP_AuthRecvData();
	
					setPayResult( "rStoreId", rSplitData[0] );
					setPayResult( "rBusiCd", rSplitData[1] );
					setPayResult( "rOrdNo", getValue("OrdNo") );
					setPayResult( "rOrdNm", getValue("OrdNm") );
					setPayResult( "rDealNo", rSplitData[2] );
					setPayResult( "rApprNo", rSplitData[3] );
					setPayResult( "rProdNm", getValue("ProdNm") );
					setPayResult( "rAmt", rSplitData[4] );
					setPayResult( "rInstmt", getValue("KVP_QUOTA") );
					setPayResult( "rSuccYn", rSplitData[5] );
					setPayResult( "rResMsg", rSplitData[6] );
					setPayResult( "rApprTm", rSplitData[7] );
					setPayResult( "rCardCd", rSplitData[8] );
							

				}
				else if( getValue("SubTy").equals( "visa3d" ) || getValue("SubTy").equals( "normal" ) )
				{
					/****************************************************************************
					* 
					* [5] �ſ�ī����� - VISA3D, �Ϲ�
					* 
					* -- ���� ���� ���� ����
					* + �����ͱ���(6) + ������
					* + ������ ����(������ ������ "|"�� �ϸ� ��ȣȭProcess���� �ص����� �ǵ����͸� �����ϰ� �ȴ�.
					* ��üID(20)		| �����ڵ�(4)		 | �ֹ���ȣ(40)	| ���ι�ȣ(8)	 	| �ŷ��ݾ�(12)  |
					* ��������(1)		| ���л���(20)	  | ī����(20) 	| ���νð�(14)	| ī����ڵ�(4) |
					* ��������ȣ(15)	| ���Ի��ڵ�(4)	 | ���Ի��(20)	| ��ǥ��ȣ(6)	 	|
					* 
					****************************************************************************/
					
						
					String rSplitData[] = new String[14];
					rSplitData = getRecvData();
					
					setPayResult( "rStoreId", rSplitData[0] );
					setPayResult( "rBusiCd", rSplitData[1] );
					setPayResult( "rOrdNo", rSplitData[2] );
					setPayResult( "rOrdNm", getValue("OrdNm") );
					setPayResult( "rApprNo", rSplitData[3] );
					setPayResult( "rInstmt", getValue("Instmt") );
					setPayResult( "rAmt", rSplitData[4] );
					setPayResult( "rSuccYn", rSplitData[5] );
					setPayResult( "rResMsg", rSplitData[6] );
					setPayResult( "rCardNm", rSplitData[7] );
					setPayResult( "rApprTm", rSplitData[8] );
					setPayResult( "rCardCd", rSplitData[9] );
					setPayResult( "rMembNo", rSplitData[10] );
					setPayResult( "rAquiCd", rSplitData[11] );
					setPayResult( "rAquiNm", rSplitData[12] );
					setPayResult( "rDealNo", rSplitData[13] );

				}
			}
			else if( getValue("AuthTy").equals( "iche" ) && getValue("ICHE_SOCKETYN").equals( "Y" ))
			{
				/****************************************************************************
				* 
				* [6] ���ͳݹ�ŷ ������ü(����) ó��
				* 
				* -- ���ͳݹ�ŷ ���� ��û ���� ���� ����
				* + �����ͱ���(6) + ������
				* + ������ ����(������ ������ "|"�� �ϸ� ��ȣȭProcess���� �ص����� �ǵ����͸� �����ϰ� �ȴ�.
				* ��������(10)	| �������̵�(20)	| �ֹ���ȣ(40)	| �̿����ֹ���ȣ(50)	| ����ڵ�(4)  | ����޽���(300)  |
				* 
				****************************************************************************/

				String rSplitData[] = new String[7];
				rSplitData = getIche_Ibk_RecvData();
				
				setPayResult("rStoreId", rSplitData[1] );
				setPayResult("rOrdNo", rSplitData[2] );
				setPayResult("rMTid", rSplitData[3] );
				setPayResult("rOrdNm", getValue("OrdNm") );
				setPayResult("ES_SENDNO", rSplitData[4] );
				setPayResult("rSuccYn", rSplitData[5] );
				setPayResult("rResMsg", rSplitData[6] );
				setPayResult("rAmt", getValue("Amt") );
			}				
			else if( getValue("AuthTy").equals( "iche" ) && getValue("ICHEARS_SOCKETYN").equals( "Y" ))
			{
				/****************************************************************************
				*
				* [7] �ڷ���ŷ ������ü(����) ó�� 
				*
				* -- �ڷ���ŷ ���� ��û ���� ���� ����
				* + �����ͱ���(6) + ������
				* + ������ ����(������ ������ "|"�� �ϸ� ��ȣȭProcess���� �ص����� �ǵ����͸� �����ϰ� �ȴ�.
				* ��������(10)	| �������̵�(20)	| �ֹ���ȣ(40)	| �̿����ֹ���ȣ(50)	| ����ڵ�(4)  | ����޽���(300)  |
				* 
				****************************************************************************/
				String rSplitData[] = new String[6];
				rSplitData = getIche_Tbk_RecvData();
				
				setPayResult("rStoreId", rSplitData[1] );
				setPayResult("rOrdNo", rSplitData[2] );
				setPayResult("rOrdNm", getValue("OrdNm") );
				setPayResult("rMTid", rSplitData[3] );
				setPayResult("rSuccYn", rSplitData[4] );
				setPayResult("rResMsg", rSplitData[5] );
				setPayResult("rAmt", getValue("Amt") );
				setPayResult("rProdNm", getValue("ProdNm") );
				
				int strPos = 0;				
				if(  ( strPos = getValue("rResMsg").indexOf( ":" ) ) != -1 	)
				{
					setPayResult("ES_SENDNO", getValue("rResMsg").substring( strPos+1,6 ) );
				}
			}

			else if( getValue("AuthTy").equals( "virtual" ) )
			{
				/****************************************************************************
				*
				* [8] ������� ����
				* 
				* -- ���� ���� ���� ����
				* + �����ͱ���(6) + ��ȣȭ ����(1) + ������
				* + ������ ����(������ ������ "|"�� �Ѵ�.
				* ��������(10)	| ��üID(20)		| ��������(14)	| ������¹�ȣ(20)	| ����ڵ�(1)		| ����޽���(100)	 | 
				* ������� �Ϲ� : vir_n ��Ŭ�� : vir_u ����ũ�� : vir_s   
				* ������¹�ȣ �� ��ǰ�� �߰� 2005-11-10
				*
				****************************************************************************/
				
				String rSplitData[] = new String[6];
				rSplitData = getVIR_RecvData();
				
				setPayResult( "rAuthTy",rSplitData[0] );
				setPayResult( "rStoreId",rSplitData[1] );
				setPayResult( "rApprTm",rSplitData[2] );
				setPayResult( "rVirNo",rSplitData[3] );
				setPayResult( "rSuccYn",rSplitData[4] );
				setPayResult( "rResMsg",rSplitData[5] );
				
				setPayResult( "rOrdNo", getValue("OrdNo") );
				setPayResult( "rOrdNm", getValue("OrdNm") );
				setPayResult( "rProdNm", getValue("ProdNm") );
				setPayResult( "rAmt", getValue("Amt") );

				int strPos = 0;				
				if(  ( strPos = getValue("rResMsg").indexOf( ":" ) ) != -1 	)
				{
					setPayResult("ES_SENDNO", getValue("rResMsg").substring( strPos+1,6 ) );
				}
						
			}
			
			else if( getValue("AuthTy").equals( "hp" ) )
			{
				/***************************************************************************
				*
				* [9]�ڵ�������
				*
				* -- ���� ���� ���� ����
				* + �����ͱ���(6) + ������
				* + ������ ����(������ ������ "|"�� �Ѵ�.
				*
				****************************************************************************/
				
				String rSplitData[] = new String[5];
				rSplitData = getHP_RecvData();

				setPayResult( "rStoreId", rSplitData[0] );
				setPayResult( "rSuccYn", rSplitData[1] );
				setPayResult( "rResMsg", rSplitData[2] );
				setPayResult( "rHP_DATE", rSplitData[3] );
				setPayResult( "rHP_TID", rSplitData[4] );
				setPayResult( "rOrdNo", getValue("OrdNo") );
				setPayResult( "rOrdNm", getValue("OrdNm") );
				setPayResult( "rAmt", getValue("Amt") );
				setPayResult( "rProdNm", getValue("ProdNm") );
						
			}
			else if( getValue("AuthTy").equals( "ars" ) )
			{
				/***************************************************************************
				*
				* [10]ARS����
				*
				* -- ���� ���� ���� ����
				* + �����ͱ���(6) + ������
				* + ������ ����(������ ������ "|"�� �Ѵ�.
				*
				****************************************************************************/
				
				String rSplitData[] = new String[5];
				rSplitData = getARS_RecvData();

				setPayResult( "rStoreId", rSplitData[0] );
				setPayResult( "rSuccYn", rSplitData[1] );
				setPayResult( "rResMsg", rSplitData[2] );
				setPayResult( "rHP_DATE", rSplitData[3] );
				setPayResult( "rHP_TID", rSplitData[4] );
				setPayResult( "rOrdNo", getValue("OrdNo") );
				setPayResult( "rOrdNm", getValue("OrdNm") );
				setPayResult( "rAmt", getValue("Amt") );
				setPayResult( "rProdNm", getValue("ProdNm") );
						
			}
			
			logger.info(pid + "End   parseMsg" );
		}catch ( Exception e )
		{
			logger.error(pid + e.toString() );	
			setPayResult( "rSuccYn", "n");
			setPayResult( "rResMsg",  "Parse ResultMsg Fail");
			return false;
		}			
			
		return true;
	}	/* parseMsg() End. */



	public boolean parseCancelMsg()
	{	
		logger.info(pid + "Start parseCancelMsg" );
		if( getValue("AuthTy").equals( "card" ) && getValue("SubTy").equals( "isp" ) )
		{
			/****************************************************************************
			*
			* [1] �ſ�ī�������� - ISP
			*
			* -- ��� ���� ���� ���� ����
			* + �����ͱ���(6) + ������
			* + ������ ����(������ ������ "|"�� �Ѵ�.
			* ��üID(20)	| ���ι�ȣ(20)	| ���νð�(8)	| �����ڵ�(4)	| �ŷ�������ȣ(6)	| ��������(1)	|
			*		   
			****************************************************************************/
			
			String rSplitData[] = new String[7];
			rSplitData = getISP_CnclRecvData();	
			
			setPayResult("rStoreId", rSplitData[0]);
			setPayResult("rApprNo", rSplitData[1]);
			setPayResult("rApprTm", rSplitData[2]);
			setPayResult("rBusiCd", rSplitData[3]);
			setPayResult("rDealNo", rSplitData[4]);
			setPayResult("rCancelSuccYn", rSplitData[5]);
			setPayResult("rCancelResMsg", rSplitData[6]);
					
		}
		else if( getValue("AuthTy").equals( "card" ) && (getValue("SubTy").equals( "visa3d" ) || getValue("SubTy").equals( "normal" )) )
		{
			/****************************************************************************
			*
			* [2] �ſ�ī�������� - VISA3D, �Ϲ�
			*
			* -- ��� ���� ���� ���� ����
			* + �����ͱ���(6) + ������
			* + ������ ����(������ ������ "|"�� �ϸ� ��ȣȭProcess���� �ص����� �ǵ����͸� �����ϰ� �ȴ�.
			* ��üID(20)	| ���ι�ȣ(8)	| ���νð�(14)	| �����ڵ�(4)	| ��������(1)	|
			* �ֹ���ȣ(20)	| �Һΰ���(2)	| �����ݾ�(20)	| ī����(20)	| ī����ڵ�(4) 	|
			* ��������ȣ(15)	| ���Ի��ڵ�(4)	| ���Ի��(20)	| ��ǥ��ȣ(6)
			*		   
			****************************************************************************/
			
			String rSplitData[] = new String[15];
			rSplitData = getRecvData();
			
			setPayResult("rStoreId", rSplitData[0]);
			setPayResult("rApprNo", rSplitData[1]);
			setPayResult("rApprTm", rSplitData[2]);
			setPayResult("rBusiCd", rSplitData[3]);
			setPayResult("rCancelSuccYn", rSplitData[4]);
			setPayResult("rOrdNo", rSplitData[5]);
			setPayResult("rInstmt", rSplitData[6]);
			setPayResult("rAmt", rSplitData[7]);
			setPayResult("rCardNm", rSplitData[8]);
			setPayResult("rCardCd", rSplitData[9]);
			setPayResult("rMembNo", rSplitData[10]);
			setPayResult("rAquiCd", rSplitData[11]);
			setPayResult("rAquiNm", rSplitData[12]);
			setPayResult("rDealNo", rSplitData[13]);
			setPayResult("rCancelResMsg", rSplitData[14]);

			if (getResult("rCancelSuccYn").equals("y") ){
				setPayResult("rCancelResMsg", "�������");
			}else{
				setPayResult("rCancelResMsg", "��ҽ���");
			}


							
		}else if( getValue("AuthTy").equals( "hp" )  )
		{
			/****************************************************************************
			*
			* [3] �ڵ��� ���� ���
			*
			* -- ��� ���� ���� ����
			* + �����ͱ���(6) + ������
			* + ������ ����(������ ������ "|"�� �ϸ� ��ȣȭProcess���� �ص����� �ǵ����͸� �����ϰ� �ȴ�.
			* |	MobileCanRes	|	��üID(20)	| ��������(1)	|	����޼���	|	���ó���Ͻ�	|	�̿����ֹ���ȣ	|
			*		   
			****************************************************************************/
			
			String rSplitData[] = new String[6];
			rSplitData = getHP_CnclRecvData();
			
			setPayResult("rStoreId", rSplitData[1]);
			setPayResult("rCancelSuccYn", rSplitData[2]);
			setPayResult("rCancelResMsg", rSplitData[3]);
			setPayResult("rCancelDate", rSplitData[4]);
			setPayResult("rTid", rSplitData[5]);
			
			
		}else if( getValue("AuthTy").equals( "ars" )  )
		{
			/****************************************************************************
			*
			* [4] ARS ���� ���
			*
			* -- ��� ���� ���� ����
			* + �����ͱ���(6) + ������
			* + ������ ����(������ ������ "|"�� �ϸ� ��ȣȭProcess���� �ص����� �ǵ����͸� �����ϰ� �ȴ�.
			* |	ARSCanRes	|	��üID(20)	| ��������(1)	|	����޼���	|	���ó���Ͻ�	|	�̿����ֹ���ȣ	|
			*		   
			****************************************************************************/
			
			String rSplitData[] = new String[6];
			rSplitData = getARS_CnclRecvData();
			
			setPayResult("rStoreId", rSplitData[1]);
			setPayResult("rCancelSuccYn", rSplitData[2]);
			setPayResult("rCancelResMsg", rSplitData[3]);
			setPayResult("rCancelDate", rSplitData[4]);
			setPayResult("rTid", rSplitData[5]);
			
			
		}else if( getValue("AuthTy").equals( "iche" ) && getValue("ICHE_SOCKETYN").equals( "Y" ) )
		{
			/****************************************************************************
			*
			* [5] ������ü ���� ��� -���ͳ� ��ŷ
			*
			* -- ��� ���� ���� ����
			* + �����ͱ���(6) + ������
			* + ������ ����(������ ������ "|"�� �ϸ� ��ȣȭProcess���� �ص����� �ǵ����͸� �����ϰ� �ȴ�.
			* |	MobileCanRes	|	��üID(20)	| ��������(1)	|	����޼���	|	���ó���Ͻ�	|	�̿����ֹ���ȣ	|
			*		   
			****************************************************************************/
			
			String rSplitData[] = new String[6];
			rSplitData = getIche_Ibk_CnclRecvData();
			
			setPayResult("rStoreId", rSplitData[1]);
			setPayResult("ICHE_POSMTID", rSplitData[2]);
			setPayResult("rOrdNo", rSplitData[3]);
			setPayResult("rCancelSuccYn", rSplitData[4]);
			setPayResult("rCancelResMsg", rSplitData[5]);
			
														
		}else if( getValue("AuthTy").equals( "iche" ) && getValue("ICHEARS_SOCKETYN").equals( "Y" ) )
		{
			/****************************************************************************
			*
			* [6] ������ü ���� ��� -�ڷ���ŷ
			*
			* -- ��� ���� ���� ����
			* + �����ͱ���(6) + ������
			* + ������ ����(������ ������ "|"�� �ϸ� ��ȣȭProcess���� �ص����� �ǵ����͸� �����ϰ� �ȴ�.
			* |	TB-PayRes	|	��üID(20)	| �̿����ֹ���ȣ	|	��������(1)	|	����޼���	|
			*		   
			****************************************************************************/
			
			String rSplitData[] = new String[5];
			rSplitData = getIche_Tbk_CnclRecvData();
			
			setPayResult("rStoreId", rSplitData[1]);
			setPayResult("rMTid", rSplitData[2]);
			setPayResult("rCancelSuccYn", rSplitData[3]);
			setPayResult("rCancelResMsg", rSplitData[4]);
			
													
		}else{
			logger.error(pid + "AuthTy[" +getValue("AuthTy") + "]" + "SubTy[" +getValue("SubTy") + "] Can't Used for Cancel");
			return false;
		}
		logger.info(pid + "End parseCancelMsg" );
		return true;

	}	/*	parseCancelMsg() End	*/


	/****************************************************************
		���ο�û ������ �����ϰ� ��ȣȭ ���� ���μ����� �����͸� �����ϰ� ������ �����Ѵ�.

		< ������ ���� >
		��������(6)	| ��üID(20)		| ȸ��ID(20)		| �����ݾ�(12)	| �ֹ���ȣ(40) |
		�ܸ����ȣ(10) | ī���ȣ(37)	| ��ȿ�Ⱓ(6)		| �ҺαⰣ(4)		| ��������(1)	| 
		ī���й�ȣ(2) | �ֹε�Ϲ�ȣ(10) | ������(40)	| ��������ȣ(21)	| �����(100)	|
		�ֹ��ڸ�(40)	| �ֹ��ڿ���ó(100) | ��Ÿ�䱸����(350) | \n(1)
		< �������� ������ ���� >
		��üID(20)	| �����ڵ�(4)	 | �ŷ�������ȣ(20) | ���ι�ȣ(4)	 | �ŷ��ݾ�(12) |
		��������(1)	| ���л���(20) | ī����(20)	| ���νð�(14)	| ī����ڵ�(4) |
		��������ȣ(15)	 | ���Ի��ڵ�(4) | ���Ի��(20)| ��ǥ��ȣ(6)	| \n(1)
	****************************************************************/
	public boolean ApproveRequest(String EncType, String AuthTy)
	{
		logger.info(pid + "Start ApproveRequest [ " + getValue("AuthTy") + "] " );

		String DataMsg = "";
		
		// ���� ��û ���� ����.
		DataMsg = EncType + 
			AuthTy + "|" + 
			getValue("StoreId") + "|" + 
			getValue("UserId") + "|" + 
			getValue("Amt") + "|" + 
			getValue("OrdNo") + "|" + 
			getValue("DeviId") + "|" + 
			encryptAegis( getValue("CardNo") ) + "|" + 
			encryptAegis( getValue("ExpYear") + getValue("ExpMon") ) + "|" + 
			getValue("Instmt") + "|" + 
			getValue("AuthYn") + "|" + 
			encryptAegis( getValue("Passwd") ) + "|" + 
			encryptAegis( getValue("SocId") ) + "|" + 
			getValue("RcpNm") + "|" + 
			getValue("RcpPhone") + "|" + 
			getValue("DlvAddr") + "|" + 
			getValue("OrdNm") + "|" +
			getValue("UserIp") + ";" + 
			getValue("OrdPhone") + "|" + 
			getValue("UserEmail") + ";" + 
			getValue("Remark") + "|" + 
			getValue("ProdNm") + "|" + 
			getValue("MPI_CAVV") + "|" + 
			getValue("MPI_MD64") + "|" + 
			getValue("MPI_ECI") + "|" +
			getValue("UserEmail") + "|" +
			getValue("NetCancID") + "|" ;

		if (!msgSend(DataMsg))
		{
			logger.error(pid + "msgSend Fail" );
			setPayResult( "rSuccYn", "n");
			setPayResult( "rResMsg",  "MsgSend Fail");
			return false;
		}

		logger.info(pid + "End   ApproveRequest " );
		
		return true;
	}

	/****************************************************************
	  ��ҿ�û ������ �����ϰ� ��ȣȭ ���� ���μ����� �����͸� �����ϰ� ������ �����Ѵ�.
		<���� ����>
			�����ͱ���(6) + ��ȣȭ����(1) + ������
		<������ ����>
			��������(6) + ��ü���̵�(20) + ���ι�ȣ(20) + ���νð�(14) + ī���ȣ(16) + Terminate(1)
		
		��ҽ��� ���� ������
		< ������ ���� >
		��üID(20)	| ���ι�ȣ(4)		| ���νð�(14)	| �����ڵ�(4)		| ��������(1)	| 
		�ֹ���ȣ(20)	| �Һΰ���(2)		| �����ݾ�(20	)	| ī����(20)	| ī����ڵ�(4) |
		��������ȣ(15)	 | ���Ի��ڵ�(4)	| ���Ի��(20)	| ��ǥ��ȣ(6)		| Terminate(1)
	****************************************************************/
	public boolean CancelRequest(String EncType, String AuthTy )
	{
		logger.info(pid + "Start CancelRequest [" + getValue("AuthTy") + "] " );
		String DataMsg = "";
		String ApprTm = "";

		if(getValue("rApprTm").length() > 7) ApprTm = getValue("rApprTm").substring(0,8);
		
		// ��� ��û ���� ����.
		DataMsg = EncType + 
			"cancel" + "|" + 
			getValue("StoreId") + "|" +
			getValue("rApprNo") + "|" + 
			ApprTm + "|" + 
			getValue("rDealNo") + "|" +
			getValue("NetCancID") + "|" ;

		if (!cancelMsgSend(DataMsg))
		{
			logger.error(pid + "msgSend Fail" );
			setPayResult( "rSuccYn", "n");
			setPayResult( "rResMsg",  "MsgSend Fail");
			return false;
		}
		logger.info(pid + "End   CancelRequest " );
		return true;
	}

	/****************************************************************
		ISP���ο�û ������ �����ϰ� ��ȣȭ ���� ���μ����� �����͸� �����ϰ� ������ �����Ѵ�.

	-- ���� ��û ���� ����
	   + �����ͱ���(6) + ISP�����ڵ�(1) + ������
	   + ������ ����(������ ������ "|"�� �Ѵ�.)
	      ��������(6)	| ��üID(20)		| ȸ��ID(20)	 		| �����ݾ�(12)	| 
		  �ֹ���ȣ(40)	| �ܸ����ȣ(10)	| ������(40)			| ��������ȭ(21)	| 
		  �����(100)	| �ֹ��ڸ�(40)	| �ֹ��ڿ���ó(100)	| ��Ÿ�䱸����(350)|
		  ��ǰ��(300)	| ��ȭ�ڵ�(3)	| �Ϲ��ҺαⰣ(2)	| �������ҺαⰣ(2)| 
		  KVPī���ڵ�(22)| ����Ű(256)	| ��ȣȭ������(2048) | ī���(50)		|
		  ȸ�� IP(20)	| ȸ�� Email(50 ) |

	****************************************************************/
	public boolean ISP_ApproveRequest(String EncType, String AuthTy)
	{
		logger.info(pid + "Start ISP_ApproveRequest [" + getValue("AuthTy") + "] " );
		String DataMsg = "";
		
		// ���� ��û ���� ����.
		DataMsg = EncType + 
			AuthTy + "|" + 
			getValue("StoreId") + "|" + 
			getValue("UserId") + "|" + 
			getValue("Amt") + "|" + 
			getValue("OrdNo") + "|" + 
			getValue("DeviId") + "|" + 
			getValue("RcpNm")    + "|" + 
			getValue("RcpPhone") + "|" + 
			getValue("DlvAddr") + "|" + 
			getValue("OrdNm")  + "|" + 
			getValue("OrdPhone") + "|" + 
			getValue("Remark")   + "|" + 
			getValue("ProdNm")  + "|" + 
			getValue("KVP_CURRENCY") + "|" + 
			getValue("partial_mm") + "|" + 
			getValue("noIntMonth") + "|" + 
			getValue("KVP_CARDCODE") + "|" + 
			getValue("KVP_SESSIONKEY") + "|" + 
			getValue("KVP_ENCDATA") + "|" + 
			getValue("KVP_CONAME") + "|" + 
			getValue("UserIp") + "|" + 
			getValue("UserEmail") + "|" +
			getValue("NetCancID") + "|" ;

		if (!msgSend(DataMsg))
		{
			logger.error(pid + "msgSend Fail" );
			setPayResult( "rSuccYn", "n");
			setPayResult( "rResMsg",  "MsgSend Fail");
			return false;
		}
		logger.info(pid + "End   ISP_ApproveRequest " );
		
		return true;
	}

	/****************************************************************************
	'	ISP��� ���� ó�� START
	'	-- �̺κ��� ��� ���� ó���� ���� PG����Process�� Socket����ϴ� �κ��̴�.
	'	   ���� �ٽ��� �Ǵ� �κ��̹Ƿ� �����Ŀ��� ���� ���������� ������ �׽�Ʈ�� �Ͽ��� �Ѵ�.
	'   -- ������ ���̴� �Ŵ��� ����
	'	    
	'	-- ��� ���� ��û ���� ����
	'	   + �����ͱ���(6) + ��ȣȭ����(1) + ������
	'	   + ������ ����(������ ������ "|"�� �Ѵ�.
	'		  ��������(6)	| ��ü���̵�(20) | ���ι�ȣ(20) |	 ���νð�(8)	 | �ŷ�������ȣ(6) |
	'
	****************************************************************************/
	public boolean ISP_CancelRequest(String EncType, String AuthTy )
	{
		logger.info(pid + "Start ISP_CancelRequest [" + getValue("AuthTy") + "] " );
		String DataMsg = "";
		String ApprTm = "";

		if(getValue("rApprTm").length() > 7) ApprTm = getValue("rApprTm").substring(0,8);
		
		// ��� ��û ���� ����.
		DataMsg = EncType + 
			"cancel" + "|" + 
			getValue("StoreId") + "|" + 
			getValue("rApprNo") + "|" + 
			ApprTm + "|" + 
			getValue("rDealNo") + "|" +
			getValue("NetCancID") + "|" ;

		if (!cancelMsgSend(DataMsg))
		{
			logger.error(pid + "msgSend Fail" );
			setPayResult( "rSuccYn", "n");
			setPayResult( "rResMsg",  "MsgSend Fail");
			return false;
		}
		logger.info(pid + "End   ISP_CancelRequest " );
		return true;
	}

	/****************************************************************
		�ڵ��� ������û ������ �����ϰ� ��ȣȭ ���� ���μ����� �����͸� �����ϰ� ������ �����Ѵ�.

	-- ���� ��û ���� ����
	   + �����ͱ���(6) + �ڵ��������ڵ�(1) + ������
	   + ������ ����(������ ������ "|"�� �Ѵ�.)
	      ��������	| ��������		| ���񽺾��̵� 		| ����INFO		| 
		
	****************************************************************/
	public boolean HP_ApproveRequest(String EncType, String AuthTy)
	{
		logger.info(pid + "Start HP_ApproveRequest [" + getValue("AuthTy") + "] " );
		String DataMsg = "";
		
		// ���� ��û ���� ����.
		DataMsg = EncType + 
			AuthTy + "|" + 
			getValue("StoreId") + "|" + 
			getValue("HP_SERVERINFO") + "|"+
			getValue("HP_ID") + "|"+
			getValue("HP_SUBID") + "|"+
			getValue("OrdNo") + "|"+
			getValue("Amt") + "|"+
			getValue("HP_UNITType") + "|"+
			getValue("HP_HANDPHONE") + "|"+
			getValue("HP_COMPANY") + "|"+
			getValue("HP_IDEN") + "|"+
			getValue("UserId") + "|"+
			getValue("UserEmail") + "|"+
			getValue("HP_IPADDR") + "|"+
			getValue("ProdNm") + "|"+
			getValue("NetCancID") +"|";

		if (!msgSend(DataMsg))
		{
			logger.error(pid + "msgSend Fail" );
			setPayResult( "rSuccYn", "n");
			setPayResult( "rResMsg",  "MsgSend Fail");
			return false;
		}
		logger.info("End   HP_ApproveRequest " );
		return true;
	}
	/****************************************************************
		�ڵ��� ������ҿ�û ������ �����ϰ� ��ȣȭ ���� ���μ����� �����͸� �����ϰ� ������ �����Ѵ�.

	-- ��ҿ�û ���� ����
	   + �����ͱ���(6) + �ڵ��������ڵ�(1) + ������
	   + ������ ����(������ ������ "|"�� �Ѵ�.)
	      ��������	| ���񽺾��̵� 	| NetCancID		| 
		
	****************************************************************/
	public boolean HP_CancelRequest(String EncType, String AuthTy)
	{
		logger.info(pid + "Start HP_CancelRequest [" + getValue("AuthTy") + "] " );
		String DataMsg = "";
		
		// ���� ��û ���� ����.
		DataMsg = EncType + 
			"MobileCanReq" + "|" + 
			getValue("StoreId") + "|" + 
			getValue("NetCancID") +"|";

		if (!cancelMsgSend(DataMsg))
		{
			logger.error(pid + "cancelMsgSend Fail" );
			setPayResult( "rSuccYn", "n");
			setPayResult( "rResMsg",  "cancelMsgSend Fail");
			return false;
		}
		logger.info("End   HP_CancelRequest " );
		return true;
	}
	/****************************************************************
		ARS ������û ������ �����ϰ� ��ȣȭ ���� ���μ����� �����͸� �����ϰ� ������ �����Ѵ�.

	-- ���� ��û ���� ����
	   + �����ͱ���(6) + ARS�����ڵ�(1) + ������
	   + ������ ����(������ ������ "|"�� �Ѵ�.)
	      ��������	| ��������		| ���񽺾��̵� 		| ����INFO		| 
		
	****************************************************************/
	public boolean ARS_ApproveRequest(String EncType, String AuthTy)
	{
		logger.info(pid + "Start ARS_ApproveRequest [" + getValue("AuthTy") + "] " );
		String DataMsg = "";
		// ���� ��û ���� ����.
		DataMsg = EncType + 
			AuthTy + "|" + 
			getValue("StoreId") + "|" + 
			getValue("HP_SERVERINFO") + "|"+
			getValue("HP_ID") + "|"+
			getValue("HP_UNITType") + "|"+
			getValue("Amt") + "|"+
			getValue("ProdNm") + "|"+
			getValue("UserEmail") + "|"+
			getValue("HP_SUBID") + "|"+
			getValue("OrdNo") + "|"+
			getValue("UserId") + "|"+
			getValue("ARS_PHONE") + "|"+
			getValue("HP_IDEN") + "|"+
			getValue("ARS_NAME") + "|"+
			getValue("HP_COMPANY") + "|"+
			getValue("HP_IPADDR") + "|"+
			getValue("NetCancID") +"|";

		if (!msgSend(DataMsg))
		{
			logger.error(pid + "msgSend Fail" );
			setPayResult( "rSuccYn", "n");
			setPayResult( "rResMsg",  "MsgSend Fail");
			return false;
		}
		logger.info(pid + "End   ARS_ApproveRequest "  );
		return true;
	}

	/****************************************************************
		ARS ������ҿ�û ������ �����ϰ� ��ȣȭ ���� ���μ����� �����͸� �����ϰ� ������ �����Ѵ�.

	-- ��ҿ�û ���� ����
	   + �����ͱ���(6) + ARS�����ڵ�(1) + ������
	   + ������ ����(������ ������ "|"�� �Ѵ�.)
	      ��������	| ���񽺾��̵� 	| NetCancID		| 
		
	****************************************************************/
	public boolean ARS_CancelRequest(String EncType, String AuthTy)
	{
		logger.info(pid + "Start ARS_CancelRequest [" + getValue("AuthTy") + "] " );
		String DataMsg = "";
		
		// ���� ��û ���� ����.
		DataMsg = EncType + 
			"ARSCanReq" + "|" + 
			getValue("StoreId") + "|" + 
			getValue("NetCancID") +"|";

		if (!cancelMsgSend(DataMsg))
		{
			logger.error(pid + "cancelMsgSend Fail" );
			setPayResult( "rSuccYn", "n");
			setPayResult( "rResMsg",  "cancelMsgSend Fail");
			return false;
		}
		logger.info("End   ARS_CancelRequest " );
		return true;
	}

	 /****************************************************************
		*
			* [8] ������� ����
			* 
			* -- �̺κ��� ���� ó���� ���� ��ȣȭProcess�� Socket����ϴ� �κ��̴�.
			* ���� �ٽ��� �Ǵ� �κ��̹Ƿ� �����Ŀ��� �׽�Ʈ�� �Ͽ��� �Ѵ�.
			* -- ������ ���̴� �Ŵ��� ����
			* 
			* -- ���� ��û ���� ����
			* + �����ͱ���(6) + ��ȣȭ ����(1) + ������
			* + ������ ����(������ ������ "|"�� �Ѵ�.)
			* ��������(10)	| ��üID(20)	| �ֹ���ȣ(40)	 	| �����ڵ�(4)		| ������¹�ȣ(20)
			* �ŷ��ݾ�(13)	| �Աݿ�����(8)	| �����ڸ�(20)		| �ֹι�ȣ(13)		| 
			* �̵���ȭ(21)	| �̸���(50)	| �������ּ�(100)	| �����ڸ�(20)	|
			* �����ڿ���ó(21)	| ������ּ�(100)	| ��ǰ��(100) | ��Ÿ�䱸����(300)		| ���� ������(50)|���� ������(100)|
			* 
	****************************************************************/
	public boolean VIR_ApproveRequest(String EncType, String AuthTy)
	{
		logger.info(pid + "Start VIR_ApproveRequest [" + getValue("AuthTy") + "] " );
		
		String DataMsg = "";
		
		// ���� ��û ���� ����.
		DataMsg = EncType+
			AuthTy + "|" +
			getValue("StoreId") +"|"+
			getValue("OrdNo") +"|"+
			getValue("VIRTUAL_CENTERCD") +"|"+
			getValue("VIRTUAL_NO") +"|"+ 
			getValue("Amt") +"|"+
			getValue("VIRTUAL_DEPODT") +"|"+
			getValue("OrdNm") +"|"+
			getValue("ZuminCode") +"|"+
			getValue("OrdPhone") +"|"+
			getValue("UserEmail") +"|"+
			getValue("OrdAddr") +"|"+
			getValue("RcpNm") +"|"+
			getValue("RcpPhone") +"|"+
			getValue("DlvAddr") +"|"+
			getValue("ProdNm") +"|"+
			getValue("Remark") +"|"+
			getValue("MallUrl") +"|"+
			getValue("MallPage") +"|"+
			getValue("NetCancID") +"|";

		if (!msgSend(DataMsg))
		{
			logger.error(pid + "msgSend Fail" );
			setPayResult( "rSuccYn", "n");
			setPayResult( "rResMsg",  "MsgSend Fail");
			return false;
		}
		logger.info(pid + "End   VIR_ApproveRequest " );
		
		return true;
	}


    /****************************************************************************
	'	������� ȯ�� �뺸
	'	-- ȯ�� ó��
	'	-- ȯ�� ó�� ��� �۽� ���� ����
	'	   + �����ͱ���(6) + ��ȣȭ����(1) + ������
	'	   + ������ ����(������ ������ "|"�� �Ѵ�.
	'		  �������̵�(20)	| Return Ip(20) | Retrun Port (20) |	 �ŷ��ڵ�(8)	 | �ֹ��ð�(14) | �ֹ���ȣ(6) |
	'		   
	****************************************************************************/
	public boolean VirAcct_ReturnRequest(String EncType, String AuthTy)
	{
		logger.info(pid + "Start VirAcct_ReturnRequest [" + getValue("AuthTy") + "] " );
		String DataMsg = "";

		// ��� ��û ���� ����.
		DataMsg = EncType + 
			AuthTy + "|" + 
			getValue("StoreId") + "|" + 
			getValue("ReturnIp") + "|" + 
			getValue("ReturnPort") + "|" + 
			getValue("TrCode") + "|" +
			getValue("Deal_time") + "|" +
			getValue("OrdNo") + "|";

		if (!msgSend(DataMsg))
		{
			logger.error(pid + "msgSend Fail" );
			setPayResult( "rSuccYn", "n");
			setPayResult( "rResMsg",  "MsgSend Fail");
			return false;
		}
		logger.info(pid + "End   VirAcct_ReturnRequest " );
		
		return true;
	}

	/****************************************************************
	*
	* [9] ������ü ���� ���� - ���ͳݹ�ŷ
	* 
	* -- �̺κ��� ���� ó���� ���� ��ȣȭProcess�� Socket����ϴ� �κ��̴�.
	* ���� �ٽ��� �Ǵ� �κ��̹Ƿ� �����Ŀ��� �׽�Ʈ�� �Ͽ��� �Ѵ�.
	* -- ������ ���̴� �Ŵ��� ����
	* 
	* -- ���ͳݹ�ŷ ���� ��û ���� ����
	* + �����ͱ���(6) + ��ȣȭ����(1) + ������
	* + ������ ����(������ ������ "|"�� �Ѵ�.)
	* ��������(10)			| ��üID(20)			| �ֹ���ȣ(40)			| �����ָ�(20)			| �ŷ��ݾ�(8)		|
	* �����ڵ�(2)			| �������ֹι�ȣ(13)	| �ֹ��ڿ���ó(16)		| �̸����ּ�(50)		| ��ǰ��(100)		| 
	* �̿����ֹ���ȣ(50)	| FNBC �ŷ���ȣ(20)		| ��ü�ð�(14)			| ���ݿ��������࿩��(1)	| ȸ�����̵�(20)	|
	* �ŷ��ڱ���(2)			| �ź�Ȯ�ι�ȣ(13)		| ����ũ�λ�뿩��(1)	| ����ũ��ȸ����ȣ(17)	| ����ũ�ΰ����ݾ�(8)|
	* ����ũ�μ�����ݾ�(8) |
	* 
	****************************************************************/
	public boolean Iche_Ibk_ApproveRequest(String EncType, String AuthTy)
	{
		logger.info(pid + "Start Iche_Ibk_ApproveRequest [" + getValue("AuthTy") + "] " );
		String DataMsg = "";

		// ���� ��û ���� ����.
		DataMsg = EncType+
			AuthTy + "|" +
			getValue("StoreId") +"|"+
			getValue("OrdNo") +"|"+
			getValue("ICHE_OUTBANKMASTER") +"|"+
			getValue("Amt") +"|"+ 
			getValue("ICHE_OUTBANKNAME") +"|"+
			getValue("ICHE_OUTACCTNO") +"|"+
			getValue("OrdPhone") +"|"+
			getValue("UserEmail") +"|"+
			getValue("ProdNm") +"|"+
			getValue("ICHE_POSMTID") +"|"+
			getValue("ICHE_FNBCMTID") +"|"+
			getValue("ICHE_APTRTS") +"|"+
			getValue("ICHE_CASHYN") +"|"+
			getValue("UserId") +"|"+
			getValue("ICHE_CASHGUBUN_CD") +"|"+
			getValue("ICHE_CASHID_NO") +"|"+
			getValue("ICHE_ECWYN") +"|"+
			getValue("ICHE_ECWID") +"|"+
			getValue("ICHE_ECWAMT1") +"|"+
			getValue("ICHE_ECWAMT2") +"|"+
			getValue("NetCancID") +"|";

		if (!msgSend(DataMsg))
		{
			logger.error(pid + "msgSend Fail" );
			setPayResult( "rSuccYn", "n");
			setPayResult( "rResMsg",  "MsgSend Fail");
			return false;
		}
		logger.info(pid + "End   Iche_Ibk_ApproveRequest " );
		
		return true;
	}


	/****************************************************************
	*
	* [10] ������ü ���� ���� - �ڷ���ŷ
	* 
	* -- �̺κ��� ���� ó���� ���� ��ȣȭProcess�� Socket����ϴ� �κ��̴�.
	* ���� �ٽ��� �Ǵ� �κ��̹Ƿ� �����Ŀ��� �׽�Ʈ�� �Ͽ��� �Ѵ�.
	* -- ������ ���̴� �Ŵ��� ����
	* 
	* -- �ڷ���ŷ ���� ��û ���� ����
	* + �����ͱ���(6) + ��ȣȭ����(1) + ������
	* + ������ ����(������ ������ "|"�� �Ѵ�.)
	* ��������(10)			| ��üID(20)			| �ֹ���ȣ(40)			| �����ָ�(20)			| �ŷ��ݾ�(8)		|
	* �����ڵ�(2)			| �������ֹι�ȣ(13)	| �ֹ��ڿ���ó(16)		| �̸����ּ�(50)		| ��ǰ��(100)		| 
	* �̿����ֹ���ȣ(50)	| FNBC �ŷ���ȣ(20)		| ��ü�ð�(14)			| ���ݿ��������࿩��(1)	| ȸ�����̵�(20)	|
	* �ŷ��ڱ���(2)			| �ź�Ȯ�ι�ȣ(13)		| ����ũ�λ�뿩��(1)	| ����ũ��ȸ����ȣ(17)	| ����ũ�ΰ����ݾ�(8)|
	* ����ũ�μ�����ݾ�(8) |
	* 
	****************************************************************/
	public boolean Iche_Tbk_ApproveRequest(String EncType, String AuthTy)
	{
		logger.info(pid + "Start Iche_Tbk_ApproveRequest [" + getValue("AuthTy") + "] " );
		String DataMsg = "";

		// ���� ��û ���� ����.
		DataMsg = EncType+
			AuthTy + "|" +
			getValue("StoreId") +"|"+
			getValue("OrdNo") +"|"+
			getValue("ICHEARS_POSMTID") +"|"+
			getValue("ICHEARS_ADMNO") +"|"+ 
			getValue("ICHEARS_CENTERCD") +"|"+
			getValue("Amt") +"|"+
			getValue("ICHE_OUTACCTNO") +"|"+
			getValue("ICHE_OUTBANKMASTER") +"|"+
			getValue("ICHEARS_HPNO") +"|"+
			getValue("UserEmail") +"|"+
			getValue("ProdNm") +"|"+
			getValue("ICHE_ECWYN") +"|"+
			getValue("ICHE_ECWID") +"|"+
			getValue("ICHE_ECWAMT1") +"|"+
			getValue("ICHE_ECWAMT2") +"|"+
			getValue("ICHE_CASHYN") +"|"+
			getValue("ICHE_CASHGUBUN_CD") +"|"+
			getValue("ICHE_CASHID_NO") +"|"+
			getValue("NetCancID") +"|";

		if (!msgSend(DataMsg))
		{
			logger.error(pid + "msgSend Fail" );
			setPayResult( "rSuccYn", "n");
			setPayResult( "rResMsg",  "MsgSend Fail");
			return false;
		}
		logger.info(pid + "End   Iche_Tbk_ApproveRequest " );
		
		return true;
	}


	/****************************************************************
	*
	* [9] ������ü ���� ��� - ���ͳݹ�ŷ
	* 
	* -- �̺κ��� ��� ó���� ���� ��ȣȭProcess�� Socket����ϴ� �κ��̴�.
	* ���� �ٽ��� �Ǵ� �κ��̹Ƿ� �����Ŀ��� �׽�Ʈ�� �Ͽ��� �Ѵ�.
	* -- ������ ���̴� �Ŵ��� ����
	* 
	* -- ���ͳݹ�ŷ ��� ��û ���� ����
	* + �����ͱ���(6) + ��ȣȭ����(1) + ������
	* + ������ ����(������ ������ "|"�� �Ѵ�.)
	* ��������(10)	| ��üID(20)	| �̿����ֹ���ȣ(50)	| �ŷ��ݾ�(8)	|	* �����ڵ�(2)	|	NetCancID	|
	* 
	****************************************************************/
	public boolean Iche_Ibk_CancelRequest(String EncType, String AuthTy)
	{
		logger.info(pid + "Start Iche_Ibk_CancelRequest [" + getValue("AuthTy") + "] " );
		String DataMsg = "";

		// ���� ��û ���� ����.
		DataMsg = EncType+
			AuthTy + "|" +
			getValue("StoreId") +"|"+
			getValue("ICHE_POSMTID") +"|"+
			getValue("Amt") +"|"+
			getValue("ICHE_OUTBANKNAME") +"|"+
			getValue("NetCancID") +"|";

		if (!cancelMsgSend(DataMsg))
		{
			logger.error(pid + "cancelMsgSend Fail" );
			setPayResult( "rSuccYn", "n");
			setPayResult( "rResMsg",  "cancelMsgSend Fail");
			return false;
		}
		logger.info(pid + "End   Iche_Ibk_CancelRequest " );
		
		return true;
	}

	/****************************************************************
	*
	* [10] ������ü ���� ��� - �ڷ���ŷ
	* 
	* -- �̺κ��� ��� ó���� ���� ��ȣȭProcess�� Socket����ϴ� �κ��̴�.
	* ���� �ٽ��� �Ǵ� �κ��̹Ƿ� �����Ŀ��� �׽�Ʈ�� �Ͽ��� �Ѵ�.
	* -- ������ ���̴� �Ŵ��� ����
	* 
	* -- �ڷ���ŷ ��� ��û ���� ����
	* + �����ͱ���(6) + ��ȣȭ����(1) + ������
	* + ������ ����(������ ������ "|"�� �Ѵ�.)
	* ��������(10)	| ��üID(20)	| �̿����ֹ���ȣ(50)	| �ŷ��ݾ�(8)	|	* �����ڵ�(2)	|	NetCancID	|
	* 
	****************************************************************/
	public boolean Iche_Tbk_CancelRequest(String EncType, String AuthTy)
	{
		logger.info(pid + "Start Iche_Tbk_CancelRequest [" + getValue("AuthTy") + "] " );
		String DataMsg = "";

		// ���� ��û ���� ����.
		DataMsg = EncType+
			AuthTy + "|" +
			getValue("StoreId") +"|"+
			"" +"|"+						//send_dt
			"" +"|"+						//mTid
			getValue("NetCancID") +"|";

		if (!cancelMsgSend(DataMsg))
		{
			logger.error(pid + "cancelMsgSend Fail" );
			setPayResult( "rSuccYn", "n");
			setPayResult( "rResMsg",  "cancelMsgSend Fail");
			return false;
		}
		logger.info(pid + "End   Iche_Tbk_CancelRequest " );
		
		return true;
	}


	/****************************************************************
		��ȣȭ �������� ���� ��û/��� ������ �����ϰ� ����� �����ؼ� ���������� �����Ѵ�..
	****************************************************************/
	public boolean msgSend(String DataMsg )
	{
		logger.info(pid + "Start msgSend " );

		String SendMsg = "";
		String RecvMsg = "";
		
		try
		{

			SendMsg = formatMsg( Integer.toString( DataMsg.getBytes().length ), 6, '9' ) + DataMsg;
			
			logger.info(pid + "SendMsg : [" + SendMsg + "]" );
		
			// ������ ����|����.
			RecvMsg = ProcessRequest( IPAddr, Port, SendMsg );

			// ������ �Ľ�.			
			if( getValue("AuthTy").equals( "card" ) )
			{
				if( getValue("SubTy").equals( "isp" ) )
				{
					ISP_AuthSplitData = splitRecvMsg( RecvMsg, 9, "|" );
				}
				else if( getValue("SubTy").equals( "visa3d" ) || getValue("SubTy").equals( "normal" ) )
				{
					SplitData = splitRecvMsg( RecvMsg, 14, "|" );
				}
			}
			else if( getValue("AuthTy").equals( "iche" ) && getValue("ICHE_SOCKETYN").equals( "Y" ))
			{
				Iche_Ibk_SplitData = splitRecvMsg( RecvMsg, 7, "|" );					
			}
			else if( getValue("AuthTy").equals( "iche" ) && getValue("ICHEARS_SOCKETYN").equals( "Y" ))
			{
				Iche_Tbk_SplitData = splitRecvMsg( RecvMsg, 6, "|" );					
			}
			else if( getValue("AuthTy").equals( "virtual" ) )
			{
				VIR_SplitData = splitRecvMsg( RecvMsg, 6, "|" );						
			}
			
			else if( getValue("AuthTy").equals( "hp" ) )
			{
				HP_SplitData = splitRecvMsg( RecvMsg, 5, "|" );
			}
			else if( getValue("AuthTy").equals( "ars" ) )
			{
				ARS_SplitData = splitRecvMsg( RecvMsg, 5, "|" );						
			}			
			logger.info(pid + "End   msgSend " );
		}
		catch( IOException ie )
		{
			ErrMsg = "��ſ����� ���� ���ο�û ����";
			ErrCd  = -1;
			
			setPayResult( "rSuccYn", "n");
			setPayResult( "rResMsg",  ie.toString() + "���ο�û ����");
			
			return false;
		}
		catch( NumberFormatException nfe )
		{
			ErrMsg = "�����Ϳ����� ���� ���ο�û ����";
			ErrCd  = -2;

			setPayResult( "rSuccYn", "n");
			setPayResult( "rResMsg",  nfe.toString() + "���ο�û ����");
			
			return false;
		}
		catch( Exception e )
		{
			ErrMsg = "������ ���� ���ο�û ����";
			ErrCd  = -99;

			setPayResult( "rSuccYn", "n");
			setPayResult( "rResMsg",  e.toString() + "���ο�û ����");

			return false;
		}
		
		return true;
	}	


	/****************************************************************
		��ȣȭ �������� ���� ��û/��� ������ �����ϰ� ����� �����ؼ� ���������� �����Ѵ�..
	****************************************************************/
	public boolean cancelMsgSend(String DataMsg )
	{
		logger.info(pid + "Start cancelMsgSend " );
		String SendMsg = "";
		String RecvMsg = "";
		
		try
		{
			SendMsg = formatMsg( Integer.toString( DataMsg.getBytes().length ), 6, '9' ) + DataMsg;
			logger.info(pid + "SendMsg : [" + SendMsg + "]" );
		
			// ������ ����|����.
			RecvMsg = ProcessRequest( IPAddr, Port, SendMsg );

			// ������ �Ľ�.	
			if( getValue("AuthTy").equals( "card" ) )
			{
				if( getValue("SubTy").equals( "isp" ) )
				{
					ISP_CnclSplitData = splitRecvMsg( RecvMsg, 7, "|" );
				}
				else if( getValue("SubTy").equals( "visa3d" ) || getValue("SubTy").equals( "normal" ) )
				{
					SplitData = splitRecvMsg( RecvMsg, 15, "|" );
				}
			}else if( getValue("AuthTy").equals( "hp" ) )
			{
				HP_CnclSplitData = splitRecvMsg( RecvMsg, 6, "|" );
			}else if( getValue("AuthTy").equals( "ars" ) )
			{
				ARS_CnclSplitData = splitRecvMsg( RecvMsg, 6, "|" );
			}else if( getValue("AuthTy").equals( "iche" ) && getValue("ICHE_SOCKETYN").equals( "Y" ))
			{
				Iche_Ibk_CnclSplitData = splitRecvMsg( RecvMsg, 6, "|" );
			}else if( getValue("AuthTy").equals( "iche" ) && getValue("ICHEARS_SOCKETYN").equals( "Y" ))
			{
				Iche_Tbk_CnclSplitData = splitRecvMsg( RecvMsg, 5, "|" );
			}
			logger.info(pid + "End   cancelMsgSend " );
		}
		catch( IOException ie )
		{
			ErrMsg = "��ſ����� ���� ��ҿ�û ����";
			ErrCd  = -1;
			
			setPayResult( "rCancelSuccYn", "n");
			setPayResult( "rCancelResMsg",  ie.toString() + "��ҿ�û ����");
			
			return false;
		}
		catch( NumberFormatException nfe )
		{
			ErrMsg = "�����Ϳ����� ���� ��ҿ�û ����";
			ErrCd  = -2;

			setPayResult( "rCancelSuccYn", "n");
			setPayResult( "rCancelResMsg",  nfe.toString() + "��ҿ�û ����");
			
			return false;
		}
		catch( Exception e )
		{
			ErrMsg = "������ ���� ��ҿ�û ����";
			ErrCd  = -99;

			setPayResult( "rCancelSuccYn", "n");
			setPayResult( "rCancelResMsg",  e.toString() + "��ҿ�û ����");

			return false;
		}
		
		return true;
	}	

	/****************************************************************
		��ȣȭ �������� ���� ��û/��� ������ �����ϰ� ����� �����ؼ� ���������� �����Ѵ�..
	****************************************************************/
	private String ProcessRequest(String addr, int port, String SendMsg) 
		throws IOException, NumberFormatException
	{
		logger.info(pid + "Start ProcessRequest " );
		
		int    iRecvLen = 0;
		String RecvLen = "", RecvMsg = "";
		

		// ��ȣȭ ���� ���� ������ ����/����
		this.connectSocket(addr, port);	
		
		logger.info(pid +  "connect IP : "+ addr + " port : " + port );
		

		// Server�� ����/��ҿ�û ����Ÿ�� ������.
		this.writeMsg(SendMsg.getBytes());	
		
		try
		{
			RecvLen = new String( readMsg(6) );
			
			iRecvLen = Integer.parseInt( RecvLen );

			RecvMsg = new String( readMsg( iRecvLen ) );


			// ���� ������ üũ
			logger.info(pid +  "RecvData Length : " + RecvLen );
			logger.info(pid +  "RecvData : "   + RecvMsg );

		}
		catch( NumberFormatException nfe )
		{
			throw new NumberFormatException( "���� �������� ��ȯ ����!!" );
		}
		finally
		{
			closeSocket();
		}


		closeSocket();
		logger.info(pid + "End   ProcessRequest " );
		return RecvMsg;
	}

    public static String getPID()
    {
        DateFormat df = new SimpleDateFormat("SSS");
		processid = Integer.parseInt(df.format(new Date()) + String.valueOf(Math.random()).substring(2, 5));
        temp = new StringBuffer();
        temp.append("<");
        for(int i = 0; i < 6 - ("" + processid).length(); i++)
            temp.append("0");

        temp.append(processid);
        temp.append("> ");
        return temp.toString();
    }

	public void printHashtable(Hashtable ht)
	{
		Enumeration keyEnum = ht.keys();
		Enumeration valueEnum = ht.elements();
		while(valueEnum.hasMoreElements()){
		String key=(String)keyEnum.nextElement();
		String value=(String)valueEnum.nextElement();
		logger.info(pid + key + ":" + value );
		}
	}

	/****************************************************************
		createLogger
	****************************************************************/
    public boolean createLogger()
	{
		/*
			�α׷��� : debug, info, warn, error, fatal
		*/
	    String filename = "";
	    String logType = "AgsPay40_jsp";	
		layout = new PatternLayout("[%d{yyyy-MM-dd HH:mm:ss:SSS }]%5p %m%n");
		try{
			filename = getValue("AgsPayHome") + "/log/" + logType + "_" + getValue("StoreId") + "_" + nowDate + ".log";
			logger = Logger.getLogger(logType);
			appender = (FileAppender)logger.getAppender(logType);
			if(appender == null)
			{
				appender = new FileAppender(layout, filename);
				appender.setName(logType);
				logger.addAppender(appender);

			}else if(!appender.getFile().endsWith(nowDate + ".log"))
			{
				logger.removeAppender(logType);
				appender.close();            
				appender = new FileAppender(layout, filename);
				appender.setName(logType);
				logger.addAppender(appender);

			}else if(!appender.getFile().equals(filename))
			{
				logger.removeAppender(logType);
				appender.close();            
				appender = new FileAppender(layout, filename);
				appender.setName(logType);
				logger.addAppender(appender);

			}
			if(getValue("logLevel").toUpperCase().equals("DEBUG"))
			{
				logger.setLevel(Level.DEBUG);
			}else if(getValue("logLevel").toUpperCase().equals("INFO"))
			{
				logger.setLevel(Level.INFO);
			}else if(getValue("logLevel").toUpperCase().equals("WARN"))
			{
				logger.setLevel(Level.WARN);
			}else if(getValue("logLevel").toUpperCase().equals("ERROR"))
			{
				logger.setLevel(Level.ERROR);
			}else if(getValue("logLevel").toUpperCase().equals("FATAL"))
			{
				logger.setLevel(Level.FATAL);
			}else{
				logger.setLevel(Level.INFO);
			}

			logger.info("===========================================================");
			logger.info(pid + "START " + logType + "(OS:" + System.getProperty("os.name") + ", JAVA:" + System.getProperty("java.version") + ")");
			logger.info(pid + "log file : " + filename );
			
			return true;
	    }catch(Exception e){
			System.out.println("�α׼����� ������ �ֽ��ϴ�. ������� üũ���� �غ��� �ٶ��ϴ�.");
			return false;
		}	    
	}

	/****************************************************************
		Make NetCancel ID();
	****************************************************************/

	public boolean MakeNetCancID()
	{
		logger.info(pid + "Start Make NetCancel ID" );
		String TidTp = "";
		String NetCancelID = "";

		if(getValue("AuthTy").equals("card")) {	// �ſ�ī��
		  	if( getValue("SubTy").equals("isp") )	TidTp = "ISP_"; else  TidTp = "VISA"; 
		}else if(getValue("AuthTy").equals("iche") ) { 		// ���� ���� ��ü (IBK:���ͳݹ�ŷ, TBK:�ڷ���ŷ)
			if( getValue("ICHE_SOCKETYN").equals("Y") ){
				TidTp = "IBK_";			
			}else if( getValue("ICHEARS_SOCKETYN").equals("Y") ) {
				TidTp = "TBK_";
			}else{
				TidTp = "IBK_";	
			}
		}else if(getValue("AuthTy").equals("virtual") ) {	// �������
			TidTp = "VIR_";
		}else if(getValue("AuthTy").equals("hp") ) {	// �޴��� ����
			TidTp = "HPP_";
		}else if(getValue("AuthTy").equals("ars") ) {	// ARS ��ȭ����
			TidTp = "ARS_";
		}else{
		  TidTp = "UNKW";	//��Ȯ�� �������
		}

		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");

		//TID�� �ּ� 31�ڸ� ,�ִ� 51�ڸ��� ���� �ʴ´�.
		setPayResult("NetCancID", "AEGIS_"+TidTp + getValue("StoreId") + "_" + df.format(new Date()) + String.valueOf(Math.random()).substring(2, 5));
		if( !(getResult("NetCancID").length() >= 31 && getResult("NetCancID").length() <= 51) )
		{
			logger.error(pid + getResult("NetCancID") );
			return false;
		}
		logger.info(pid + "NetCancID[" + getResult("NetCancID")+ "]" );
		logger.info(pid + "End Make NetCancel ID " );
		return true;
	}

	/****************************************************************
		������ Ȯ�ο�û
	****************************************************************/
 	public boolean checkPayResult( String netCancID ) 
	{
		logger.info("===========================================================");
		logger.info(pid + "[" + netCancID + "] Check PayResult Start !!");
		
		String AuthTy = "";
		String DataMsg = "";

		if(netCancID == null || netCancID.equals(""))	netCancID = getResult("NetCancID");
			
		if (netCancID != null && netCancID.length() > 10){
			AuthTy = netCancID.substring(6,10);
		}else{
			logger.error(pid + "NetCancID Error[" + netCancID + "] ");
			return false;
		}
		
		if( AuthTy.equals("ISP_") ){ 			// �ſ�ī�� ISP
			setValue("AuthTy","card") ;
			setValue("SubTy","isp") ;
		}else if( AuthTy.equals("VISA") ){ 			// �ſ�ī�� �Ƚ�Ŭ��
		  	setValue("AuthTy","card") ;
			setValue("SubTy","visa3d") ;
		}else if( AuthTy.equals("IBK_") ){ 			// ������ü(���ͳݹ�ŷ)
		  	setValue("AuthTy","iche") ;
		}else if( AuthTy.equals("TBK_") ){ 			// ������ü(�ڷ���ŷ)
			setValue("AuthTy","iche") ;
		}else if( AuthTy.equals("VIR_") ){ 			// �������
		  	setValue("AuthTy","virtual") ;
		}else if( AuthTy.equals("HPP_") ){ 			// �ڵ���
		  	setValue("AuthTy","hp") ;
		}else if( AuthTy.equals("ARS_") ){ 			// ARS
		  	setValue("AuthTy","ars") ;
		}else{ 										// ��Ȯ�ΰ������
		  	logger.error(pid + "Check PayResult Fail for Unknown AuthTy[" + AuthTy + "]");
			return false;
		}  
	
		//������´� payCheck ó������ ����.
		/*
		if(getValue("AuthTy").equals("virtual"))
		{
			logger.info(pid + "Check PayResult Passed AuthTy[" + getValue("AuthTy") + "]");
			return false;
		}
		*/

		/****************************************************************************
		* ���� ���� Make
		****************************************************************************/
		ENCTYPE = "I";
		
		DataMsg = ENCTYPE +
			"PayInfo"+"|"+
			getValue("StoreId")+"|"+
			netCancID+"|";
		
		if (!msgSend(DataMsg))
		{
			if( getErrCd() == -1 )
			{
				setPayResult( "rSuccYn", "n");
				setPayResult( "rResMsg", "��ſ���(sock)�� ���� ���ΰ���");
				return false;
			}
			else if( getErrCd() == -2 )
			{
				setPayResult( "rSuccYn", "n");
				setPayResult( "rResMsg", "��ſ���(msg)�� ���� ���ΰ���");
				return false;
			}else{
				setPayResult( "rSuccYn", "n");
				setPayResult( "rResMsg",  "MsgSend Fail");
			}
			logger.error(pid + "Check PayResult msgSend Fail" );
			
			return false;
		}

		/** ����޼����� Parsing ó���Ͽ� ������� ������ �Ҵ��Ѵ�. **/
		if( !(parseMsg()) ){
			logger.error(pid + "Check PayResult parseMsg Fail" );
			return false;
		}

		logger.info(pid + "Check PayResult End");
		
		logger.info(pid + "Start Result Data From PG  [" );
		printHashtable(resultData);
		logger.info(pid + "] End  Result Data From PG " );

		return true;
	}

	/****************************************************************
		���� ����
	****************************************************************/
	private void connectSocket(String addr, int port) throws IOException 
	{
		try
		{
			logger.info(pid + "Start connectSocket " );

			socket = new Socket(addr, port);
			
			in     = new DataInputStream(socket.getInputStream());
			
			out    = new DataOutputStream(socket.getOutputStream());

			logger.info(pid + "End   connectSocket " );

		}
		catch( IOException e )
		{
			throw new IOException("Cannot connect the server : " + addr + ":" + port + "!!");
		}
	}

	/****************************************************************
		������ ����
	****************************************************************/
	private void writeMsg(byte[] msg) throws IOException
	{
		try
		{
			out.write(msg);
			out.flush();
		}
		catch( IOException e )
		{
			throw new IOException("Cannot write to socket!!");
		}
	}

	/****************************************************************
		������ ����
	****************************************************************/
	private byte[] readMsg(int size) throws IOException
	{
		try
		{
			byte[] msg = new byte[size];
			in.read(msg);
			return msg;
		}
		catch( IOException e )
		{
			throw new IOException("Cannot read from socket!!");
		}
	}

	/****************************************************************
		���� Close
	****************************************************************/
	private void closeSocket() throws IOException
	{
		try
		{
			socket.close();
		}
		catch( IOException e )
		{
			throw new IOException("Cannot close socket!!");
		}
	}

	/****************************************************************
		���ڿ��� ������ ���̷� ������ ���ڷ� ä���� ������ �Ѵ�.
	****************************************************************/
	private String formatMsg(String str, int len, char ctype)
	{
		String formattedstr = new String();
		byte[] buff;
		int filllen = 0;

		buff = str.getBytes();

		filllen = len - buff.length;
		formattedstr = "";
		if(ctype == '9')
		{
			// ���ڿ��� ���
			for(int i = 0; i<filllen;i++)
			{
					formattedstr += "0";
			}

			formattedstr = formattedstr + str;
		}
		else 
		{
			// ���ڿ��� ���
			for(int i = 0; i<filllen;i++)
			{
					formattedstr += " ";
			}

			formattedstr = str + formattedstr;
		}

		return formattedstr;
	}

	private String setTrim(String str, int len)
	{
		byte[] subbytes;
		String tmpStr;
		
		subbytes = new byte[len];
		
		System.arraycopy(str.getBytes(), 0, subbytes, 0, len);
		
		tmpStr = new String(subbytes);
		
		if(tmpStr.length() == 0) 
		{
			subbytes = new byte[len-1];
			System.arraycopy(str.getBytes(), 0, subbytes, 0, len-1);
			tmpStr = new String(subbytes);
		}

		return tmpStr;
	}

	/****************************************************************
		������ ���� ������� ����� �����͸� ����
	****************************************************************/
	public String[] getRecvData( )
	{
		return SplitData;
	}

	public String[] getISP_AuthRecvData( )
	{
		return ISP_AuthSplitData;
	}

	public String[] getISP_CnclRecvData( )
	{
		return ISP_CnclSplitData;
	}

	public String[] getHP_RecvData( )
	{
		return HP_SplitData;
	}
	
	public String[] getHP_CnclRecvData( )
	{
		return HP_CnclSplitData;
	}

	public String[] getARS_RecvData( )
	{
		return ARS_SplitData;
	}

	public String[] getARS_CnclRecvData( )
	{
		return ARS_CnclSplitData;
	}

	public String[] getVIR_RecvData( )
	{
		return VIR_SplitData;
	}

	public String[] getIche_Ibk_RecvData( )
	{
		return Iche_Ibk_SplitData;
	}

	public String[] getIche_Ibk_CnclRecvData( )
	{
		return Iche_Ibk_CnclSplitData;
	}

	public String[] getIche_Tbk_RecvData( )
	{
		return Iche_Tbk_SplitData;
	}

	public String[] getIche_Tbk_CnclRecvData( )
	{
		return Iche_Tbk_CnclSplitData;
	}

	public String[] getVirAcctReturnData()
    {
        return VirAcctReturnData;
    }
	
	public String getErrMsg()
	{
		return ErrMsg;
	}

	public int getErrCd()
	{
		return ErrCd;
	}


	/*
		���������� Get                      
	*/
    public String getResult(String name)
    {
		String result = "";
        if(resultData.get(name) != null)
            result = (String)resultData.get(name);
        else
            result = (String)requestData.get(name);

		if (result == null)	result = "";

		return result;
    }

	/*
		���������� Get                      
	*/
    public String getValue(String name)
    {
		String result = "";
        if(requestData.get(name) != null)
            result = (String)requestData.get(name);
        else
            result = (String)resultData.get(name);

		if (result == null)	result = "";

		return result;
    }

	/*
		���������� Set                      
	*/
    public void setValue(String name, String value)
    {
        	
		if(value != null){
			requestData.put(name, value);
        }else{
            requestData.put(name, "");
		}
    }

	/*
		������� rSuccYn Set                      
	*/
 	public void setPayResult( String name, String value ) 
	{
		if(value != null)
            resultData.put(name, value);
        else
            resultData.put(name, "");
	}


	/****************************************************************
		���ڿ��� �ѱ��� �ִ��� üũ
	****************************************************************/
	public boolean checkHangul( String OrgData )
	{
		char oneChar;

		for( int i = 0; i < OrgData.length(); i++ )
		{
			oneChar = OrgData.charAt( i );
			
			if( oneChar >= '\uAC00' && oneChar <= '\uD7A3' )
			{
				return true;
			}
		}

		return false;
	}

	/****************************************************************
		��Ʈ���� �������� üũ
	****************************************************************/
	public boolean checkNumber( String OrgData )
	{
		long iNum;
		
		try 
		{
			iNum = Long.parseLong( OrgData );
		}
		catch( NumberFormatException nfe )
		{
			return false;
		}

		return true;
	}

	/****************************************************************
		����,null�� üũ
	****************************************************************/
	public boolean checkNull( String OrgData )
	{
		if( OrgData == null || OrgData.equals( "null" ) || OrgData.equals( "NULL" ) || OrgData.equals( "" ) )
		{
			return true;
		}

		return false;
	}
	
	public String null2Space( String OrgData )
	{

		if( checkNull( OrgData ) ) return "";
		
		return OrgData.trim();

	}

	/****************************************************************
		���â�� ���� �ɼǿ����� ������ �̵�
	****************************************************************/
	public void alertMsg( JspWriter out, String ErrMsg, String Go )
	{
		try
		{
			out.println( "<script language='javascript'>" );
			out.println( "alert( '" + ErrMsg + "' );" );

			if( Go != null )
			{
				out.println( "history.go( " + Go + " );" );
			}

			out.println( "</script>" );
		}
		catch( IOException ie )
		{
			System.out.println( ie.toString() );
		}

	}

	public void alertGoBack( JspWriter out, String ErrMsg )
	{
		alertMsg( out, ErrMsg, "-1" );
	}
	
	/****************************************************************
		������PG���� ����ϴ� ī�� ������ Encrypt�޼ҵ�
	****************************************************************/
	private String encryptAegis( String OrgData ) throws NumberFormatException 
	{
		int    iDataLen, iOneEnc;
		String ReverseData = "", EncData = "";

		if( OrgData == null || OrgData.equals("") )
			return "";
		

		//ReverseData = new String();
		iDataLen = OrgData.length();

		for( int i = 0; i < iDataLen ; i++ )
		{
			ReverseData += OrgData.substring( ( iDataLen - 1 ) - i , ( iDataLen  ) - i );
		}

		//System.out.println( "Reverse Data : " + ReverseData );

		try
		{
			//EncData = new String();
			iDataLen = ReverseData.length();

			for( int i = 0; i < iDataLen; i++ )
			{
				iOneEnc = ( Integer.parseInt( ReverseData.substring( i, i + 1 ) ) + i * 77 ) % 10;
				EncData += Integer.toString( iOneEnc );
			}
		}
		catch ( NumberFormatException nfe )
		{
			throw new NumberFormatException( "������(" + OrgData + ") Encrypt ����" );			
			//throw NumberFormatException( nfe.toString() );
		}

		return EncData;
	}

	

	/****************************************************************
		���� ������ Delimeter�� �����Ѵ�.
	****************************************************************/
	private String[] splitRecvMsg( String RecvMsg, int RegexNum, String Regex)
	{
		logger.info(pid + "Start splitRecvMsg" );
		String SplitData[];
		int    iRegexNum   = 0;
		int    iRegexPosOld = 0;
		int    iRegexPosNew = 0;

		//
		//	���ڿ��� Regex���� �������� ����
		//	Regex ������ ī��Ʈ�ؼ� RegexNum�� ���� ������ �޼��� ���� ������ ���� false ����
		//
	
		SplitData = new String[RegexNum];

		while( ( iRegexPosNew = RecvMsg.indexOf( Regex, iRegexPosOld ) ) != -1 )
		{
			SplitData[iRegexNum] = RecvMsg.substring( iRegexPosOld, iRegexPosNew );

			if( checkNull( SplitData[iRegexNum] ) )
			{
				SplitData[iRegexNum] = "";
			}
			if( checkNumber( SplitData[iRegexNum] ) )
			{
				if( Long.parseLong( SplitData[iRegexNum] ) == 0 )
				{
					SplitData[iRegexNum] = "";
				}
			}

			//System.out.println( "SplitData " + Integer.toString(iRegexNum) + 
			//					  " : " + SplitData[iRegexNum] );

			iRegexPosOld = iRegexPosNew + 1;		
			iRegexNum++;
		}

		if( iRegexNum != RegexNum ) {
			logger.error(pid + "splitRecvMsg Fail " );
		}
		logger.info(pid + "End   splitRecvMsg" );

		return SplitData;
	}



}
