package user.common.verification.action;

import java.util.Calendar;

import user.common.verification.dto.EvDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

import common.ConDAOAware;

public class InsertEmailVerification implements Action, ConDAOAware
{
    private SqlMapClient sqlMapper;
    
    private EvDTO evDTO = new EvDTO();
    private Calendar today = Calendar.getInstance(); // 오늘 날짜 구하기
    
    // private String to;
    private String ev_requested;
    private String ev_code;

    public void setConDAO(SqlMapClient sqlMapper)
    {
        this.sqlMapper = sqlMapper;       
    }

    public String execute() throws Exception
    {
        //System.out.println("getEv_requested():"+getEv_requested());
        //System.out.println("getEv_code():"+getEv_code());
        
        evDTO.setEv_requested(getEv_requested());        
        evDTO.setEv_code(getEv_code());
        evDTO.setEv_reg_date(today.getTime());
            
        sqlMapper.insert("Ev.insertEv", evDTO);
            
        return SUCCESS;
    }
    
    public EvDTO getEvDTO()
    {
        return evDTO;
    }

    public void setEvDTO(EvDTO evDTO)
    {
        this.evDTO = evDTO;
    }

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