package user.common.action;

import com.opensymphony.xwork2.Action;

public class DashboardAction implements Action
{
	private String user_type;
    private String user_id;

	public String execute() throws Exception
	{
        if (getUser_type().equals("buyer"))
        {
            return "buyer";
        } else if (getUser_type().equals("seller"))
        {
            return "seller";
        } else if (getUser_type().equals("admin"))
        {
            return "admin";
        }
        
        return ERROR;
	}
	
    // getter & setter
    public String getUser_type()
    {
        return user_type;
    }

    public void setUser_type(String user_type)
    {
        this.user_type = user_type;
    }

    public String getUser_id()
    {
        return user_id;
    }

    public void setUser_id(String user_id)
    {
        this.user_id = user_id;
    }

}
