package home.action;

import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport {
	
	private String currentActionName = "home";

	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String getCurrentActionName() {
		return currentActionName;
	}
	
}
