package user.common.verification.action;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

import common.ConDAOAware;

public class CheckEmailVerification implements Action, ConDAOAware
{
    private SqlMapClient sqlMapper;
    
    private String buyer_email;
    private String ev_code;
    
    public void setConDAO(SqlMapClient sqlMapper)
    {
        this.sqlMapper = sqlMapper;
    }

    public String execute() throws Exception
    {
        System.out.println("getBuyer_email():"+getBuyer_email());
        System.out.println("getEv_code():"+getEv_code());
        
        return SUCCESS;
    }

    public String getBuyer_email()
    {
        return buyer_email;
    }

    public void setBuyer_email(String buyer_email)
    {
        this.buyer_email = buyer_email;
    }

    public String getEv_code()
    {
        return ev_code;
    }

    public void setEv_code(String ev_code)
    {
        this.ev_code = ev_code;
    }
   
}
