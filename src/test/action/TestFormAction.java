package test.action;

import com.opensymphony.xwork2.Action;

public class TestFormAction implements Action {
	
	private String sb;

	public String execute() throws Exception {
		
		System.out.println("here:"+getSb());
		
		return SUCCESS;
	}

	public String getSb() {
		return sb;
	}

	public void setSb(String sb) {
		this.sb = sb;
	}

}
