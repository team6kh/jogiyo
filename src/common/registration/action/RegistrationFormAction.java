package common.registration.action;

import com.opensymphony.xwork2.Action;

public class RegistrationFormAction implements Action {
	
	private String reg_type = "";
	
	public String execute() throws Exception {
		
		if (reg_type.equals("buyer")){
			return "buyer";
		} else if (reg_type.equals("seller")){
			return "seller";
		}
		
		return SUCCESS; // 최초 로딩시에는 BUYER 로
	}

	
	public void setReg_type(String reg_type) {
		this.reg_type = reg_type;
	}	
	public String getReg_type() {
		return reg_type;
	}	

}
