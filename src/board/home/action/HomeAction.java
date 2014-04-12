package board.home.action;

import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport {
	
	private String actionName = "home";
	
	public String welcome() throws Exception {
		return SUCCESS;
	}

	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String getActionName() {
		return actionName;
	}
	
	// no setter
	
}
