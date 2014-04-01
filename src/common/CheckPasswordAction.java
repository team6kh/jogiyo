package common;

import board.test.dto.TestDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;

public class CheckPasswordAction extends ActionSupport implements ConDAOAware {
	public static SqlMapClient sqlMapper;
	
	private TestDTO paramClass = new TestDTO(); // 파라미터를 저장할 객체
	private TestDTO resultClass = new TestDTO(); // 쿼리 결과 값을 저장할 객체
	
	private int no;
	private String password;
	
	private String modalParam; // modal에서 가져오는 파라미터 : 타겟 액션 이름
	
	public void setConDAO(SqlMapClient sqlMapper){
		this.sqlMapper = sqlMapper;
	}	

	// 테스트 게시판 비밀번호 체크 액션
	public String execute() throws Exception {

		// 비밀번호 입력값 파라미터 설정.
		paramClass.setNo(getNo());
		paramClass.setPassword(getPassword());

		// 현재 글의 비밀번호 가져오기.
		resultClass = (TestDTO) sqlMapper.queryForObject("Test.selectPassword",
				paramClass);

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

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getModalParam() {
		return modalParam;
	}

	public void setModalParam(String modalParam) {
		this.modalParam = modalParam;
	}

}
