package common.registration.action;

import com.opensymphony.xwork2.Action;

public class RegistrationFormAction implements Action {
	
	private String isDup;
	
	public String execute() throws Exception {
		
		System.out.println("isDup:"+isDup);
		
		return SUCCESS;
	}

	public String getIsDup() {
		return isDup;
	}

	public void setIsDup(String isDup) {
		this.isDup = isDup;
	}	

}
