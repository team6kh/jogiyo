package board.test.action;

import board.test.dto.TestDTO;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.ibatis.sqlmap.client.SqlMapClient;

import common.ConDAOAware;

import java.util.*;

import org.apache.struts2.interceptor.SessionAware;

public class InsertTestAction implements Action, Preparable, ModelDriven, ConDAOAware, SessionAware
{
    private SqlMapClient sqlMapper; // SqlMapClient API를 사용하기 위한 sqlMapper 객체

    private TestDTO testDTO;

    private int test_num; // 해당 글 고유넘버
    private int currentPage; // 현재 페이지
    private Calendar today = Calendar.getInstance(); // 오늘 날짜 구하기

    private Map sessionMap = null;

    public void setConDAO(SqlMapClient sqlMapper)
    {
        this.sqlMapper = sqlMapper;
    }

    public void setSession(Map sessionMap)
    {
        this.sessionMap = sessionMap;
    }

    // 게시글 INSERT 폼
    public String insertForm() throws Exception
    {
        return SUCCESS;
    }

    // Preparable 인터페이스의 prepare
    public void prepare() throws Exception
    {
        testDTO = new TestDTO();
    }

    // ModelDriven 인터페이스의 getModel 구현
    public Object getModel()
    {
        return testDTO;
    }

    // 게시글 INSERT 액션
    public String execute() throws Exception
    {
        // 등록할 항목 설정
        testDTO.setTest_reg_date(today.getTime());

        // 등록 쿼리 수행
        sqlMapper.insert("Test.insertBoard", testDTO);

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
}