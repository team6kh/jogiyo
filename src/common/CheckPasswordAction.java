package common;

import board.test.dto.TestDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

public class CheckPasswordAction implements Action, ConDAOAware {
	
	private SqlMapClient sqlMapper;
	
	private String modalParam; // modal에서 가져오는 파라미터 : 타겟 액션 이름
	
	private TestDTO testDTO = new TestDTO();	
	private int test_num;
	private String test_writer_pw;		
	
	public void setConDAO(SqlMapClient sqlMapper){
		this.sqlMapper = sqlMapper;
	}	

	// 테스트 게시판 비밀번호 체크 액션
	public String execute() throws Exception {

		// 파라미터 설정.
		testDTO.setTest_num(getTest_num());
		testDTO.setTest_writer_pw(getTest_writer_pw());

		// 해당 글의 비밀번호 가져오기.
		testDTO = (TestDTO) sqlMapper.queryForObject("Test.selectTestWriterPw",	testDTO);

		// 입력한 비밀번호가 틀리면(없으면) ERROR 리턴.
		if (testDTO == null)
			return ERROR;

		return SUCCESS;
	}
	
	public String getModalParam() {
		return modalParam;
	}
	public void setModalParam(String modalParam) {
		this.modalParam = modalParam;
	}	
	public TestDTO getTestDTO() {
		return testDTO;
	}
	public void setTestDTO(TestDTO testDTO) {
		this.testDTO = testDTO;
	}	
	public int getTest_num() {
		System.out.println("CheckPasswordAction.return test_num:"+test_num);
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

}
