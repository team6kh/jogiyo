package board.test.action;

import board.test.dto.TestDTO;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import common.ConDAOAware;

import java.util.*;
import java.io.Reader;
import java.io.IOException;

import org.apache.struts2.interceptor.SessionAware;

import user.buyer.dto.BuyerDTO;

public class InsertTestAction implements Action, Preparable, ModelDriven, ConDAOAware, SessionAware {

	public static SqlMapClient sqlMapper; // SqlMapClient API를 사용하기 위한 sqlMapper 객체.
	
	TestDTO paramClass;    
	TestDTO resultClass;

	private int currentPage; // 현재 페이지
	private int test_num;	 // 현재 글 고유넘버
	Calendar today = Calendar.getInstance(); // 오늘 날짜 구하기.
	
	Map sessionMap;

	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;
	}
	
	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}

	// 등록 폼.
	public String insertForm() throws Exception {
		if (sessionMap != null) {
			resultClass = new TestDTO();			
			resultClass.setTest_writer_name((String) sessionMap.get("sessionName"));	// 세션이름을 작성자로 설정.
			resultClass.setTest_writer_pw((String) sessionMap.get("sessionPw"));		// 세션비번을 글비번으로 설정..
		}
		return SUCCESS;
	}

	// Preparable 인터페이스의 prepare
	public void prepare() throws Exception {
		paramClass = new TestDTO();
	}

	// ModelDriven 인터페이스의 getModel 구현
	public Object getModel() {
		return paramClass;
	}

	// 게시판 WRITE 액션.
	public String execute() throws Exception {

		// 등록할 항목 수동 설정.
		paramClass.setTest_reg_date(today.getTime());

		// 등록 쿼리 수행.
		sqlMapper.insert("Test.insertBoard", paramClass);

		return SUCCESS;
	}
	
	public TestDTO getResultClass() {
		return resultClass;
	}

	public void setResultClass(TestDTO resultClass) {
		this.resultClass = resultClass;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}	

	public int getTest_num() {
		return test_num;
	}

	public void setTest_num(int test_num) {
		this.test_num = test_num;
	}	

}