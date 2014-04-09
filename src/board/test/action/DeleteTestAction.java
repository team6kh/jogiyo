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

	private TestDTO testDTO = new TestDTO();
	
	private int test_num;		// 현재 글 고유넘버	
	private int currentPage;	// 현재 페이지		
		
	public void setConDAO(SqlMapClient sqlMapper){
		this.sqlMapper = sqlMapper;
	}

	// 게시글 DELETE 액션.
	public String execute() throws Exception {
		
		// 삭제할 항목 설정.
		testDTO.setTest_num(getTest_num());
				
		// 삭제 쿼리 수행.
		sqlMapper.update("Test.deleteBoard", testDTO);

		return SUCCESS;
	}

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

}