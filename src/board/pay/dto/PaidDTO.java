package board.pay.dto;

import java.util.Date;

public class PaidDTO
{
    private int rest_num; // 식당 코드
    private String rest_writer_id; // 판매자id
    private int paid_num; // 결제 코드
    private String paid_rest_subject; // 식당명
    private String paid_restopt_subject; // 메뉴명
    private int paid_restopt_priceplus; // 메뉴 가격
    private String session_id; // 구매자id
    private Date paid_reg_date; // 주문결제일
  
    public int getRest_num()
    {
        return rest_num;
    }
    public void setRest_num(int rest_num)
    {
        this.rest_num = rest_num;
    }
    public String getRest_writer_id()
    {
        return rest_writer_id;
    }
    public void setRest_writer_id(String rest_writer_id)
    {
        this.rest_writer_id = rest_writer_id;
    }
    public int getPaid_num()
    {
        return paid_num;
    }
    public void setPaid_num(int paid_num)
    {
        this.paid_num = paid_num;
    }
    public String getPaid_rest_subject()
    {
        return paid_rest_subject;
    }
    public void setPaid_rest_subject(String paid_rest_subject)
    {
        this.paid_rest_subject = paid_rest_subject;
    }
    public String getPaid_restopt_subject()
    {
        return paid_restopt_subject;
    }
    public void setPaid_restopt_subject(String paid_restopt_subject)
    {
        this.paid_restopt_subject = paid_restopt_subject;
    }
    public int getPaid_restopt_priceplus()
    {
        return paid_restopt_priceplus;
    }
    public void setPaid_restopt_priceplus(int paid_restopt_priceplus)
    {
        this.paid_restopt_priceplus = paid_restopt_priceplus;
    }
    public String getSession_id()
    {
        return session_id;
    }
    public void setSession_id(String session_id)
    {
        this.session_id = session_id;
    }
    public Date getPaid_reg_date()
    {
        return paid_reg_date;
    }
    public void setPaid_reg_date(Date paid_reg_date)
    {
        this.paid_reg_date = paid_reg_date;
    }
    
    
}