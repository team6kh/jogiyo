package user.common.verification.action;

import user.common.verification.dto.EvDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

import common.ConDAOAware;

public class CheckEmailVerification implements Action, ConDAOAware
{
    private SqlMapClient sqlMapper;
    
    //EvDTO evDTO = new EvDTO();  

    //private String buyer_email;
    private String ev_requested;
    private String ev_code;
    
    public void setConDAO(SqlMapClient sqlMapper)
    {
        this.sqlMapper = sqlMapper;
    }

    public String execute() throws Exception
    {       
        //System.out.println("getBuyer_email():"+getBuyer_email());
        System.out.println("getEv_requested():"+getEv_requested());
        System.out.println("getEv_code():"+getEv_code());
        
        EvDTO evDTO = new EvDTO();
        
        evDTO.setEv_requested(getEv_requested());
        evDTO.setEv_code(getEv_code());
        
        evDTO = (EvDTO) sqlMapper.queryForObject("Ev.selectLatestEvCode", evDTO);
        
        if (evDTO != null)
        {
            return SUCCESS;
        }
        
        return ERROR;
    }
    
//    public EvDTO getEvDTO()
//    {
//        return evDTO;
//    }
//
//    public void setEvDTO(EvDTO evDTO)
//    {
//        this.evDTO = evDTO;
//    }

//    public String getBuyer_email()
//    {
//        return buyer_email;
//    }
//
//    public void setBuyer_email(String buyer_email)
//    {
//        this.buyer_email = buyer_email;
//    }

    public String getEv_requested()
    {
        return ev_requested;
    }

    public void setEv_requested(String ev_requested)
    {
        this.ev_requested = ev_requested;
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
