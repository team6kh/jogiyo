package board.test.action;

import board.test.dto.TestDTO;

import com.opensymphony.xwork2.Action;
import com.ibatis.sqlmap.client.SqlMapClient;

import common.ConDAOAware;

public class UpdateTestAction implements Action, ConDAOAware
{
    private SqlMapClient sqlMapper; // SqlMapClient API를 사용하기 위한 sqlMapper 객체

    private TestDTO testDTO = new TestDTO();

    private String modalParam_key;
    private int test_num; // 현재 글 고유넘버
    private int currentPage; // 현재 페이지

    private String test_writer_pw;
    private String test_subject;
    private String test_content;

    public void setConDAO(SqlMapClient sqlMapper)
    {
        this.sqlMapper = sqlMapper;
    }

    // 게시글 UPDATE 폼
    public String updateForm() throws Exception
    {
        setTest_num(Integer.parseInt(getModalParam_key())); // modalParam_key를 Test_num에...

        // 해당 번호의 글을 가져온다.
        testDTO = (TestDTO) sqlMapper.queryForObject("Test.selectTestNum", getTest_num());

        return SUCCESS;
    }

    // 게시글 UPDATE 액션
    public String execute() throws Exception
    {
        // 수정할 항목 설정. 여기서는 인터셉터를 사용하지 않고 직접(?) 설정해보자.
        testDTO.setTest_num(getTest_num());
        testDTO.setTest_writer_pw(getTest_writer_pw());
        testDTO.setTest_subject(getTest_subject());
        testDTO.setTest_content(getTest_content());

        // 일단 항목만 수정한다.
        sqlMapper.update("Test.updateTest", testDTO);

        // 수정이 끝나면 view 페이지로 이동.
        testDTO = (TestDTO) sqlMapper.queryForObject("Test.selectTestNum", getTest_num());

        return SUCCESS;
    }

    // getter & setter
    public TestDTO getTestDTO()
    {
        return testDTO;
    }

    public void setTestDTO(TestDTO testDTO)
    {
        this.testDTO = testDTO;
    }

    public int getTest_num()
    {
        return test_num;
    }

    public void setTest_num(int test_num)
    {
        this.test_num = test_num;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }

    // 여기서는 인터셉터를 사용하지 않고 직접(?) 설정해보자.
    public String getTest_writer_pw()
    {
        return test_writer_pw;
    }

    public void setTest_writer_pw(String test_writer_pw)
    {
        this.test_writer_pw = test_writer_pw;
    }

    public String getTest_subject()
    {
        return test_subject;
    }

    public void setTest_subject(String test_subject)
    {
        this.test_subject = test_subject;
    }

    public String getTest_content()
    {
        return test_content;
    }

    public void setTest_content(String test_content)
    {
        this.test_content = test_content;
    }

    // modalParam
    public String getModalParam_key()
    {
        return modalParam_key;
    }

    public void setModalParam_key(String modalParam_key)
    {
        this.modalParam_key = modalParam_key;
    }
    
}
