package board.home.action;

import com.opensymphony.xwork2.Action;

public class HomeAction implements Action
{
    private String actionName = "home";

    // 환영 페이지
    public String welcome() throws Exception
    {
        return SUCCESS;
    }

    public String execute() throws Exception
    {
        return SUCCESS;
    }

    public String getActionName()
    {
        return actionName;
    }

    // no setter
}