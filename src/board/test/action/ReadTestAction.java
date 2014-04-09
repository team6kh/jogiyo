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

import java.io.Reader;
import java.io.InputStream;
import java.io.IOException;

public class ReadTestAction implements Action, ConDAOAware {
	
	private SqlMapClient sqlMapper; // SqlMapClient API를 사용하기 위한 sqlMapper 객체

	private TestDTO testDTO = new TestDTO();

	private int test_num;		// 해당 글 고유넘버	
	private int currentPage;	// 현재 페이지		
	
	public void setConDAO(SqlMapClient sqlMapper){
		this.sqlMapper = sqlMapper;
	}

	// 게시글 상세보기 액션
	public String execute() throws Exception {

		// 해당 글의 조회수 +1
		testDTO.setTest_num(getTest_num());
		sqlMapper.update("Test.updateReadcount", testDTO);

		// 해당 번호의 글을 가져온다.
		testDTO = (TestDTO) sqlMapper.queryForObject("Test.selectWhereTestNum", getTest_num());

		return SUCCESS;
	}
	
	// getter & setter
	public TestDTO getTestDTO() {
		return testDTO;
	}
	public void setTestDTO(TestDTO testDTO) {
		this.testDTO = testDTO;
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