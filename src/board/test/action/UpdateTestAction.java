package board.test.action;

import board.test.dto.TestDTO;

import com.opensymphony.xwork2.ActionSupport;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import common.ConDAOAware;

import java.io.Reader;
import java.io.IOException;

public class UpdateTestAction extends ActionSupport implements ConDAOAware {
	
	private SqlMapClient sqlMapper;

	private TestDTO paramClass = new TestDTO();		// 파라미터를 저장할 객체
	private TestDTO resultClass = new TestDTO();	// 쿼리 결과 값을 저장할 객체

	private int currentPage;	// 현재 페이지	
	private int test_num;		// 현재 글 고유넘버
	
	private String test_writer_pw;
	private String test_subject;
	private String test_content;
		
	public void setConDAO(SqlMapClient sqlMapper){
		this.sqlMapper = sqlMapper;
	}

	// 게시글 UPDATE 액션.
	public String execute() throws Exception {

		// 수정할 항목 설정. 여기서는 인터셉터를 사용하지 않고 직접(?) 설정해보자.
		paramClass.setTest_num(getTest_num());
		paramClass.setTest_writer_pw(getTest_writer_pw());
		paramClass.setTest_subject(getTest_subject());
		paramClass.setTest_content(getTest_content());

		// 일단 항목만 수정한다.
		sqlMapper.update("Test.updateBoard", paramClass);		

		// 수정이 끝나면 view 페이지로 이동.
		resultClass = (TestDTO) sqlMapper.queryForObject("Test.selectWhereTestNum", getTest_num());

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

	public String getTest_writer_pw() {
		return test_writer_pw;
	}

	public void setTest_writer_pw(String test_writer_pw) {
		this.test_writer_pw = test_writer_pw;
	}

	public String getTest_subject() {
		return test_subject;
	}

	public void setTest_subject(String test_subject) {
		this.test_subject = test_subject;
	}

	public String getTest_content() {
		return test_content;
	}

	public void setTest_content(String test_content) {
		this.test_content = test_content;
	}
	
}