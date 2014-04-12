package board.test.action;

import board.test.dto.TestDTO;

import com.opensymphony.xwork2.Action;
import com.ibatis.sqlmap.client.SqlMapClient;

import common.ConDAOAware;

public class DeleteTestAction implements Action, ConDAOAware
{
    private SqlMapClient sqlMapper; // SqlMapClient API를 사용하기 위한 sqlMapper 객체

    private TestDTO testDTO = new TestDTO();

    private String modalParam_key;
    private int test_num; // 해당 글 고유넘버
    private int currentPage; // 현재 페이지

    public void setConDAO(SqlMapClient sqlMapper)
    {
        this.sqlMapper = sqlMapper;
    }

    // 게시글 DELETE 액션
    public String execute() throws Exception
    {
        // modalParam_key를 Test_num에...
        setTest_num(Integer.parseInt(getModalParam_key()));

        // 삭제할 항목 설정
        testDTO.setTest_num(getTest_num());

        // 삭제 쿼리 수행
        sqlMapper.update("Test.deleteBoard", testDTO);

        return SUCCESS;
    }

    // getter & setter
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