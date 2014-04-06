package board.test.action;

import board.test.dto.TestDTO;

import com.opensymphony.xwork2.ActionSupport;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import common.ConDAOAware;

import java.io.File;
import java.io.Reader;
import java.io.IOException;

public class DeleteTestAction extends ActionSupport implements ConDAOAware {
	
	private SqlMapClient sqlMapper;

	private TestDTO paramClass = new TestDTO();		// 파라미터를 저장할 객체
	private TestDTO resultClass = new TestDTO();	// 쿼리 결과 값을 저장할 객체
	
	private int currentPage;	// 현재 페이지		
	private int test_num;		// 현재 글 고유넘버	
		
	public void setConDAO(SqlMapClient sqlMapper){
		this.sqlMapper = sqlMapper;
	}

	// 게시글 DELETE 액션.
	public String execute() throws Exception {
		
		// 해당 번호의 글을 가져온다.
		resultClass = (TestDTO) sqlMapper.queryForObject("Test.selectWhereTestNum", getTest_num());

		// 삭제할 항목 설정.
		paramClass.setTest_num(getTest_num());
				
		// 삭제 쿼리 수행.
		sqlMapper.update("Test.deleteBoard", paramClass);

		return SUCCESS;
	}

	public TestDTO getParamClass() {
		return paramClass;
	}

	public void setParamClass(TestDTO paramClass) {
		this.paramClass = paramClass;
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