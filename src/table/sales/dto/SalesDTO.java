package table.sales.dto;

public class SalesDTO
{
    private int rest_num; // 식당 코드
    private String paid_restopt_subject; // 메뉴명
    private int paid_sales_volume;  // 매출 건수
    private int paid_sales_price; // 매출금
    private String groupColumn; //그룹 기준
    private String sortColumn; // 정렬 기준
    
        
    
    public int getRest_num()
    {
        return rest_num;
    }
    public void setRest_num(int rest_num)
    {
        this.rest_num = rest_num;
    }
    public String getPaid_restopt_subject()
    {
        return paid_restopt_subject;
    }
    public void setPaid_restopt_subject(String paid_restopt_subject)
    {
        this.paid_restopt_subject = paid_restopt_subject;
    }
    public int getPaid_sales_volume()
    {
        return paid_sales_volume;
    }
    public void setPaid_sales_volume(int paid_sales_volume)
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
    public String getGroupColumn()
    {
        return groupColumn;
    }
    public void setGroupColumn(String groupColumn)
    {
        this.groupColumn = groupColumn;
    }
    public String getSortColumn()
    {
        return sortColumn;
    }
    public void setSortColumn(String sortColumn)
    {
        this.sortColumn = sortColumn;
    }
    
    
    
}
