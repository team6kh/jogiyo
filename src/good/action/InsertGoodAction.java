package good.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import common.ConDAOAware;

public class InsertGoodAction implements Action, Preparable, ModelDriven, ConDAOAware {

	
	/*만들어야 할 기능
	 * 쿼리문을 운영하기 위한 sqlMapper 선언
	 * execute() 작성하기
	 * parameter 값을 받기위한 get/set 설정 
	 * (servlet 방식 : DAO에 get/set설정과 DB 쿼리문이 공존하였으나, 
	 *  struts 방식:  Action에서 get/set설정을 한다. GoodDAO.xml에서는 DB에 요청을 날리는 쿼리만이 존재한다.) */

	
	/*Action에서 FileUpload가 하는 일 
	1.환경설정 : 보여주기 위한 경로지정
	2. 임시파일 → 서버로 복사
	3. 복사된 파일 경로의 수집 및 DB에 저장 */
	
	/*파일업로드
	 * 변수명 = input form에서 받아오는 name
	 * 
	 * private List<File> 변수명  = new ArrayList<File>();   // 파일데이터의 정렬
	 * private List<String> 변수명FileName = new ArrayList<String>(); //파일명들의 정렬
	 * private List<String> 변수명ContentType = new ArrayList<String>(); //필터링하기 위한 ContentType(=확장자) 정렬

	 * */	
	
	



	public String execute() throws Exception
	{
		
		return SUCCESS;
	}
	
	
	
	
	public void setConDAO(SqlMapClient sqlMapper)
	{
		
		
	}


	public Object getModel()
	{
		
		return null;
	}


	public void prepare() throws Exception
	{
		
		
	}

	
	
}


