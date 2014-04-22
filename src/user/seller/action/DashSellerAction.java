package user.seller.action;

import java.util.ArrayList;
import java.util.List;

import table.sales.dto.SalesDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import common.ConDAOAware;

public class DashSellerAction implements Action, Preparable, ModelDriven, ConDAOAware
{
    private String actionName = "dashSeller"; // 페이징액션과 로그인액션에서 쓰인다.
    
    private SqlMapClient sqlMapper;
    private SalesDTO salesDTO;
    private List salesRes = new ArrayList();
 
    
    
    public String execute() throws Exception
    {
        System.out.println(sqlMapper);
        
            
        salesDTO.setGroupColumn("paid_restopt_subject");
        salesDTO.setSortColumn("paid_sales_volume");
        
        salesRes = sqlMapper.queryForList("Pay.selectMenuSales", salesDTO);
       
        return SUCCESS;
    }
    
    
    
    
    
    
    
    
    public String getActionName()
    {
        return actionName;
    }

    public List getSalesRes()
    {
        return salesRes;
    }

    public void setSalesRes(List salesRes)
    {
        this.salesRes = salesRes;
    }








    // ConDAOAware 인터페이스 : DB 쿼리
    public void setConDAO(SqlMapClient sqlMapper)
    {
        this.sqlMapper = sqlMapper;
    }

    // ModelDriven 인터페이스
    public Object getModel()
    {
        return salesDTO;
    }

    // Preparable 인터페이스
    public void prepare() throws Exception
    {
        salesDTO = new SalesDTO();        
    }

    
    
}
