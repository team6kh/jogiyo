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
	
	private SqlMapClient sqlMapper; // SqlMapClient API를 사용하기 위한 sqlMapper 객체

	private TestDTO testDTO = new TestDTO();
	
	private int modalParam_num;
	private int test_num;		// 해당 글 고유넘버	
	private int currentPage;	// 현재 페이지		
		
	public void setConDAO(SqlMapClient sqlMapper){
		this.sqlMapper = sqlMapper;
	}

	// 게시글 DELETE 액션
	public String execute() throws Exception {
		
		setTest_num(getModalParam_num()); // modalParam_num을 Test_num에...
		
		// 삭제할 항목 설정
		testDTO.setTest_num(getTest_num());
				
		// 삭제 쿼리 수행
		sqlMapper.update("Test.deleteBoard", testDTO);

		return SUCCESS;
	}

	// getter & setter
	public int getTest_num() {
		return test_num;
	}
	public void setTest_num(int test_num) {
		this.test_num = test_num;
	}	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	// modalParam
	public int getModalParam_num() {
		return modalParam_num;
	}
	public void setModalParam_num(int modalParam_num) {
		this.modalParam_num = modalParam_num;
	}

}