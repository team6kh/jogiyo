 package user.seller.action;

import java.util.ArrayList;
import java.util.List;




import board.pay.dto.PaidDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import common.ConDAOAware;

public class DashSellerAction implements Action, Preparable, ModelDriven, ConDAOAware
{
    private String actionName = "dashSeller"; // 페이징액션과 로그인액션에서 쓰인다.
    
    private SqlMapClient sqlMapper;
    
    private PaidDTO paidDTO;
    private List<PaidDTO> paidRes = new ArrayList<PaidDTO>();
    private String session_id;
    
    
    public String execute() throws Exception
    {
         
        
        paidRes = sqlMapper.queryForList("Pay.payList", session_id);
        return SUCCESS;
    }
        
    
    
    
    
    public String getSession_id()
    {
        return session_id;
    }
    public void setSession_id(String session_id)
    {
        this.session_id = session_id;
    }

    public void setActionName(String actionName)
    {
        this.actionName = actionName;
    }
    public String getActionName()
    {
        return actionName;
    }

    public List<PaidDTO> getPaidRes()
    {
        return paidRes;
    }


    public void setPaidRes(List<PaidDTO> paidRes)
    {
        this.paidRes = paidRes;
    }


    // ConDAOAware 인터페이스 : DB 쿼리
    public void setConDAO(SqlMapClient sqlMapper)
    {
        this.sqlMapper = sqlMapper;
    }

    // ModelDriven 인터페이스
    public Object getModel()
    {
        return paidDTO;
    }

    // Preparable 인터페이스
    public void prepare() throws Exception
    {
        paidDTO = new PaidDTO();        
    }

    
    
}
