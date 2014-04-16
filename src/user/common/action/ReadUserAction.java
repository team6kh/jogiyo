package user.common.action;

import com.opensymphony.xwork2.Action;

public class ReadUserAction implements Action
{
    private String userType;
    private String userId;
    
    private String actionStatus;

    public String execute() throws Exception
    {
        System.out.println("ReadUserAction execute()");
        
        if (getUserType().equals("buyer"))
        {
            return "buyer";
        } else if (getUserType().equals("seller"))
        {
            return "seller";
        }
        return ERROR;
    }

    // getter & setter
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

    public String getActionStatus()
    {
        return actionStatus;
    }

    public void setActionStatus(String actionStatus)
    {
        this.actionStatus = actionStatus;
    }
}