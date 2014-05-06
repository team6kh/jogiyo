package user.seller.action;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.struts2.interceptor.SessionAware;

import board.paid.dto.MenuDTO;
import board.paid.dto.SearchConditionDTO;
import board.paid.dto.PaidDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

import common.ConDAOAware;

public class DashSellerAction implements Action, ConDAOAware, SessionAware
{
    
    
    private String actionName = "dashSeller"; // 페이징액션과 로그인액션에서 쓰인다.
    
    private SqlMapClient sqlMapper; // db 쿼리
    
    
    private Map sessionMap; // session 정보를 꺼내기 위한 sessionMap
    private int[] requestPaid_num; //  쿠폰사용 승인을 하기 위해 필요한 결제번호들을 저장하는 배열
    private String startDate; // 검색 조건 - 조회할 기간의 시작 날짜    
    private String endDate; // 검색 조건 - 조회할 기간의 마지막 날짜
    
    private String seller_id; // 판매자id 값을 저장하기 위한 변수
    
   
    
 // 검색 조건값들을 담을 DTO
    private SearchConditionDTO searchDTO = new SearchConditionDTO(); 
    
    
    
 // 쿼리문 실행 후 쿠폰 사용 요청 내역을 담을 List   
    private List<PaidDTO> cpnRes  = new ArrayList<PaidDTO>();
 // 쿼리문 실행 후 결제 내역 결과를 담는 List
    private List<PaidDTO> paidRes = new ArrayList<PaidDTO>(); 
 // 쿼리문 실행 후 인기 메뉴결과를 담는 List

    private List<MenuDTO> menuRes = new ArrayList<MenuDTO>(); 
   
    public String execute() throws Exception
    {
        // 판매자의 session_id 정보를 받아온다
        seller_id = (String) sessionMap.get("session_id");
        
        
        // session_id 값이 있는 경우
        if (seller_id != null)
        {
         // 검색조건 DTO인 searchDTO에 seller_id 값 세팅
            searchDTO.setSession_id(seller_id);
            
            /* dashSeller.jsp 중단에 띄울 결과물을 위한 코드 */
            
            cpnRes = sqlMapper.queryForList("Paid.selectRequestedCpn", searchDTO);
           
         
            /* dashSeller.jsp 하단에 띄울 결과물을 위한 코드 */
            
            
            
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
            paidRes = sqlMapper.queryForList("Paid.selectPaidList", searchDTO);
            if (!paidRes.isEmpty())
            {
                // 가져온 결제내역에서 식당코드를 꺼내어 searchDTO에 넣는다
                searchDTO.setRest_num(paidRes.get(0).getPaid_rest_num());
                
                // 판매자가 등록한 상품의 인기 메뉴 내역을 가져온다. (추출해내는 레코드 개수 제한 설정 필요)
                menuRes = sqlMapper.queryForList("Paid.selectHotMenu", searchDTO);
                
            }
            return SUCCESS;
        }
        // seller_id 값이 없는 경우에는 ERROR 리턴 -> 로그인 페이지로 
        else
        {
            return ERROR;
        }
    }
    
    
    public String responseCpn() throws Exception{
        
        for(int i=0; i< requestPaid_num.length; i++) {
            sqlMapper.update("Paid.updateResponseCpn", requestPaid_num[i]);
        }
        return SUCCESS;
    }
    
    
    
    
    
    
    public String getActionName()
    {
        return actionName;
    }
    
    // int[] requestPaid_num setter
    
    public void setRequestPaid_num(int[] requestPaid_num)
    {
        this.requestPaid_num = requestPaid_num;
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
    
    // cpnRes setter & getter
    public List<PaidDTO> getCpnRes()
    {
        return cpnRes;
    }

    public void setCpnRes(List<PaidDTO> cpnRes)
    {
        this.cpnRes = cpnRes;
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