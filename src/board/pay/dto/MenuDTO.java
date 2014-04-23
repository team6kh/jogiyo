package board.pay.dto;

public class MenuDTO
{
    private int rest_num; // 식당 코드 
    private String rest_writer_id; // 판매자id
     private String paid_restopt_subject; // 메뉴명
    private String paid_sales_volume;  // 메뉴 매출 건수
    private int paid_sales_price; //메뉴 총 매출금액
   
    
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
    public String getPaid_restopt_subject()
    {
        return paid_restopt_subject;
    }
    public void setPaid_restopt_subject(String paid_restopt_subject)
    {
        this.paid_restopt_subject = paid_restopt_subject;
    }
    public String getPaid_sales_volume()
    {
        return paid_sales_volume;
    }
    public void setPaid_sales_volume(String paid_sales_volume)
    {
        this.paid_sales_volume = paid_sales_volume;
    }
    public int getPaid_sales_price()
    {
        return paid_sales_price;
    }
    public void setPaid_sales_price(int paid_sales_price)
    {
        this.paid_sales_price = paid_sales_price;
    }
    
    
    
}
