package board.pay.dto;

import java.util.Date;

public class SearchConditionDTO
{
    private String session_id; // 판매자의 세션id
    private int rest_num; // 식당 코드
    private Date startDate; // 검색 날짜 조건
    private Date endDate; // 검색 날짜 조건
    public String getSession_id()
    {
        return session_id;
    }
    public void setSession_id(String session_id)
    {
        this.session_id = session_id;
    }
    public int getRest_num()
    {
        return rest_num;
    }
    public void setRest_num(int rest_num)
    {
        this.rest_num = rest_num;
    }
    public Date getStartDate()
    {
        return startDate;
    }
    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }
    public Date getEndDate()
    {
        return endDate;
    }
    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }
  
     
    
}
