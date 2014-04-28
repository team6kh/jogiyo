package user.seller.action;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.struts2.interceptor.SessionAware;

import board.pay.dto.MenuDTO;
import board.pay.dto.PaidDTO;
import board.pay.dto.SearchConditionDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

import common.ConDAOAware;

public class DashSellerAction implements Action, ConDAOAware, SessionAware
{
    private String actionName = "dashSeller"; // 페이징액션과 로그인액션에서 쓰인다.
    
    private SqlMapClient sqlMapper; // db 쿼리
    
    private Map sessionMap;
    
    private String session_id;
    
    private String startDate;
    
    private String endDate;
    
 // db 검색 조건값을 담을 DTO
    private SearchConditionDTO searchDTO = new SearchConditionDTO(); 
 // 쿼리문 실행 후 결제 내역 결과를 담는 List
    private List<PaidDTO> paidRes = new ArrayList<PaidDTO>(); 
 // 쿼리문 실행 후 인기 메뉴결과를 담는 List

    private List<MenuDTO> menuRes = new ArrayList<MenuDTO>(); 
    public String execute() throws Exception
    {
        // 판매자의 session_id 정보를 받아온다
        session_id = (String) sessionMap.get("session_id");
        
        // session_id 값이 있는지 확인한다.
        if (session_id != null)
        {
            // 검색조건 DTO인 searchDTO에 session_id 값 세팅
            searchDTO.setSession_id(session_id);
            
            // 날짜값을 일정한 패턴으로 표시하기 위한 format
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         
            // 검색 조건 값이 없는 경우에는 검색 기간을 어제로 설정
            if ((getStartDate() == null) && (getEndDate() == null))
            {
                Calendar cal = Calendar.getInstance();
                
                // 어제 날짜 값을 searchDTO에 세팅
                cal.add(cal.DATE, -1);
                searchDTO.setEndDate(sdf.format(cal.getTime()));
                searchDTO.setStartDate(sdf.format(cal.getTime()));
                
            }
            // 사용자가 검색 조건을 설정한 경우
            else
            {
                searchDTO.setStartDate(sdf.format(sdf.parse(getStartDate())));
                searchDTO.setEndDate(sdf.format(sdf.parse(getEndDate())));
            }
            
            // 판매자가 등록한 상품의 결제 내역을 가져온다. (추출해내는 레코드 개수 제한 설정 필요)
            paidRes = sqlMapper.queryForList("Pay.payList", searchDTO);
            if (!paidRes.isEmpty())
            {
                // 가져온 결제내역에서 식당코드를 꺼내어 searchDTO에 넣는다
                searchDTO.setRest_num(paidRes.get(0).getRest_num());
                
                // 판매자가 등록한 상품의 인기 메뉴 내역을 가져온다. (추출해내는 레코드 개수 제한 설정 필요)
                menuRes = sqlMapper.queryForList("Pay.hotmenu", searchDTO);
                
            }
            return SUCCESS;
        }
        // session_id 값이 없는 경우에는 ERROR 리턴
        else
        {
            return ERROR;
        }
    }
    
    // Session_id setter
    public void setSession_id(String session_id)
    {
        this.session_id = session_id;
    }
    
    public String getActionName()
    {
        return actionName;
    }
    
    // startDate getter & setter
    public String getStartDate()
    {
        return startDate;
    }
    
    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }
    
    // endDate getter & setter
    public String getEndDate()
    {
        return endDate;
    }
    
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
    
    public SearchConditionDTO getSearchDTO()
    {
        return searchDTO;
    }
    
    // paidRes setter & getter
    public List<PaidDTO> getPaidRes()
    {
        return paidRes;
    }
    
    public void setPaidRes(List<PaidDTO> paidRes)
    {
        this.paidRes = paidRes;
    }
    
    // menuRes setter & getter
    public List<MenuDTO> getMenuRes()
    {
        return menuRes;
    }
    
    public void setMenuRes(List<MenuDTO> menuRes)
    {
        this.menuRes = menuRes;
    }
    
    // ConDAOAware 인터페이스 : DB 쿼리
    public void setConDAO(SqlMapClient sqlMapper)
    {
        this.sqlMapper = sqlMapper;
    }
    
    // SessionAware 인터페이스 : 세션 정보 받기
    public void setSession(Map sessionMap)
    {
        this.sessionMap = sessionMap;
        
    }
    
}