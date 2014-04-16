package user.common.logout.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.Action;

public class LogoutAction implements Action, SessionAware
{
    private Map sessionMap;

    public void setSession(Map sessionMap)
    {
        this.sessionMap = sessionMap;
    }

    public String execute() throws Exception
    {
        sessionMap.clear(); // 세션을 지운다.
        return SUCCESS;
    }
    
}
