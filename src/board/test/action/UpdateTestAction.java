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
	public static SqlMapClient sqlMapper;

	private TestDTO paramClass; // 파라미터를 저장할 객체
	private TestDTO resultClass; // 쿼리 결과 값을 저장할 객체

	private int currentPage;	//현재 페이지
	
	private int no;
	private String subject;
	private String name;
	private String password;
	private String content;
		
	public void setConDAO(SqlMapClient sqlMapper){
		this.sqlMapper = sqlMapper;
	}

	// 게시글 수정
	public String execute() throws Exception {
				
		// 파라미터와 리절트 객체 생성.
		paramClass = new TestDTO();
		resultClass = new TestDTO();

		// 수정할 항목 설정.
		paramClass.setNo(getNo());
		paramClass.setSubject(getSubject());
		paramClass.setName(getName());
		paramClass.setPassword(getPassword());
		paramClass.setContent(getContent());

		// 일단 항목만 수정한다.
		sqlMapper.update("Test.updateBoard", paramClass);		

		// 수정이 끝나면 view 페이지로 이동.
		resultClass = (TestDTO) sqlMapper.queryForObject("Test.selectWhereNo", getNo());

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}