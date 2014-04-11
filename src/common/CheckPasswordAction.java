package common;

import board.test.dto.TestDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

public class CheckPasswordAction implements Action, ConDAOAware {
	
	private SqlMapClient sqlMapper;
	
	private String modalParam; // modal에서 가져오는 파라미터 : 타겟 액션 이름
	
	// private TestDTO testDTO = new TestDTO();	
	private int modalParam_num;
	private String modalParam_pw;		
	
	public void setConDAO(SqlMapClient sqlMapper){
		this.sqlMapper = sqlMapper;
	}	

	// 테스트 게시판 글 비밀번호 체크 액션
	public String execute() throws Exception {
		
		// 테스트 게시판 글 수정/삭제 요청
		if (getModalParam().equals("updateTestForm") || getModalParam().equals("deleteTest") ) {
			
			// 객체 생성
			TestDTO testDTO = new TestDTO();
			
			// 파라미터 설정
			testDTO.setTest_num(getModalParam_num());
			testDTO.setTest_writer_pw(getModalParam_pw());
			
			// 해당 글의 비밀번호 가져오기
			testDTO = (TestDTO) sqlMapper.queryForObject("Test.selectTestWriterPw",	testDTO);

			// 입력한 비밀번호가 틀리면(없으면) ERROR 리턴
			if (testDTO != null) {
				return SUCCESS;
			} else { return ERROR; }
		}
		return ERROR;
	}
	
	// getter & setter	
	public String getModalParam() {
		return modalParam;
	}
	public void setModalParam(String modalParam) {
		this.modalParam = modalParam;
	}	
//	public TestDTO getTestDTO() {
//		return testDTO;
//	}
//	public void setTestDTO(TestDTO testDTO) {
//		this.testDTO = testDTO;
//	}	
	public int getModalParam_num() {
		return modalParam_num;
	}
	public void setModalParam_num(int modalParam_num) {
		this.modalParam_num = modalParam_num;
	}
	public String getModalParam_pw() {
		return modalParam_pw;
	}
	public void setModalParam_pw(String modalParam_pw) {
		this.modalParam_pw = modalParam_pw;
	}

}
