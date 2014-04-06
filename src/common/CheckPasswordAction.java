package common;

import board.test.dto.TestDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

public class CheckPasswordAction implements Action, ConDAOAware {
	
	private SqlMapClient sqlMapper;
	
	private TestDTO paramClass = new TestDTO(); // 파라미터를 저장할 객체
	private TestDTO resultClass = new TestDTO(); // 쿼리 결과 값을 저장할 객체
	
	private int test_num;
	private String test_writer_pw;	

	private String modalParam; // modal에서 가져오는 파라미터 : 타겟 액션 이름
	
	public void setConDAO(SqlMapClient sqlMapper){
		this.sqlMapper = sqlMapper;
	}	

	// 테스트 게시판 비밀번호 체크 액션
	public String execute() throws Exception {

		// 비밀번호 입력값 파라미터 설정.
		paramClass.setTest_num(getTest_num());
		paramClass.setTest_writer_pw(getTest_writer_pw());

		// 현재 글의 비밀번호 가져오기.
		resultClass = (TestDTO) sqlMapper.queryForObject("Test.selectTestWriterPw",	paramClass);

		// 입력한 비밀번호가 틀리면 ERROR 리턴.
		if (resultClass == null)
			return ERROR;

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

	public String getModalParam() {
		return modalParam;
	}

	public void setModalParam(String modalParam) {
		this.modalParam = modalParam;
	}

}
