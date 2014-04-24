package board.pay.dto;

import java.util.Date;

public class SearchConditionDTO
{
    private String session_id; // 판매자의 세션id
    private int rest_num; // 식당 코드
    private Date startDay; // 검색 날짜 조건
    private Date endDay; // 검색 날짜 조건
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
    public Date getStartDay()
    {
        return startDay;
    }
    public void setStartDay(Date startDay)
    {
        this.startDay = startDay;
    }
    public Date getEndDay()
    {
        return endDay;
    }
    public void setEndDay(Date endDay)
    {
        this.endDay = endDay;
    }
    
     
    
}
