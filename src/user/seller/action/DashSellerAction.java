package user.seller.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import board.pay.dto.MenuDTO;
import board.pay.dto.PaidDTO;
import board.pay.dto.SearchConditionDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import common.ConDAOAware;

public class DashSellerAction implements Action, Preparable,
        ModelDriven<SearchConditionDTO>, ConDAOAware, SessionAware
{
    private String actionName = "dashSeller"; // 페이징액션과 로그인액션에서 쓰인다.
    
    private SqlMapClient sqlMapper;
    
    private Map sessionMap;
    
    private String session_id;
    
    private SearchConditionDTO searchDTO;
    
    private List<PaidDTO> paidRes = new ArrayList<PaidDTO>();
    
    private List<MenuDTO> menuRes = new ArrayList<MenuDTO>();
    
    public String execute() throws Exception
    {
        // 판매자의 session_id 정보를 받아온다
        session_id = (String) sessionMap.get("session_id");
        
        // session_id 값이 있는지 확인한다.
        if (session_id != null)
        {
            // 파라메터DTO에 session_id 값 세팅
            searchDTO.setSession_id(session_id);
            
            // 날짜 검색 조건 값이 없는 경우에는 오늘로부터 하루 전 날짜로 설정
            if ((searchDTO.getStartDay() == null) && (searchDTO.getEndDay() == null))          
            {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(cal.getTime());
                // EndDay에 오늘 날짜 값 설정
                searchDTO.setEndDay(sdf.parse(date));
            
                // StartDay에 어제 날짜 값 설정 
                cal.add(cal.DATE, -1);
                searchDTO.setStartDay(sdf.parse(sdf.format(cal.getTime())));                
            }
            
            // 판매자가 등록한 상품의 결제 내역을 가져온다. (추출해내는 레코드 개수 제한 설정 필요)
            paidRes = sqlMapper.queryForList("Pay.payList", searchDTO);
            if(!paidRes.isEmpty()) {
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
    
    // ModelDriven 인터페이스
    public SearchConditionDTO getModel()
    {
        return searchDTO;
    }
    
    // preparable 인터페이스
    public void prepare() throws Exception
    {
        searchDTO = new SearchConditionDTO();
        
    }
    
}
