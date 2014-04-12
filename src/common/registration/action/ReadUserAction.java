package common.registration.action;

import com.opensymphony.xwork2.ActionSupport;

public class ReadUserAction extends ActionSupport {
	
	private String userType;
	private String userId;
	
	public String execute() throws Exception {		
		
		if (getUserType().equals("buyer")) {
			return "buyer";
		} else if(getUserType().equals("seller")) {
			return "seller";
		}
		
		return ERROR;		
	}

	// getter & setter
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}	

}