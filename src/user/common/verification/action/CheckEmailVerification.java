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
    private String ev_code_input; // 사용자가 입력한 ev_code
    
    // 액션 실행 후 redirect-action을 위한 파라미터
    private String userType;
    private String userId;
    private int isSuccess;
    
    public void setConDAO(SqlMapClient sqlMapper)
    {
        this.sqlMapper = sqlMapper;
    }

    public String execute() throws Exception
    {       
        //System.out.println("getBuyer_email():"+getBuyer_email());
        System.out.println("getEv_requested():"+getEv_requested());
        System.out.println("getEv_code():"+getEv_code_input());
        
        EvDTO evDTO = new EvDTO();
        
        evDTO.setEv_requested(getEv_requested());
        evDTO.setEv_code(getEv_code_input());
        
        evDTO = (EvDTO) sqlMapper.queryForObject("Ev.selectLatestEvCode", evDTO);
        
        if (evDTO != null)
        {
            //return SUCCESS;
            isSuccess = 1;
        } else {
            isSuccess = 0;
        }
        
        //return ERROR;
        return SUCCESS;
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
    
    public String getEv_code_input()
    {
        return ev_code_input;
    }

    public void setEv_code_input(String ev_code_input)
    {
        this.ev_code_input = ev_code_input;
    }

    public String getUserType()
    {
        return userType;
    }

    public void setUserType(String userType)
    {
        this.userType = userType;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public int getIsSuccess()
    {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess)
    {
        this.isSuccess = isSuccess;
    }
   
}
