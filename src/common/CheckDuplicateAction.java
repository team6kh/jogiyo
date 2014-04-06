package common;

import com.opensymphony.xwork2.Action;

public class CheckDuplicateAction implements Action {
	
	private String reg_id;
	private StringBuffer sb;
	private String isDup;

	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public String getSb() {
		return sb.toString();
	}
	public String getIsDup() {
		return isDup;
	}
	
	public String execute() throws Exception {
		
		sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='euc-kr'?>");
		sb.append("<root>");
		if (!(reg_id.equals("admin") || reg_id.equals("test"))) {
			sb.append("true");
			isDup = "true";
		} else {
			sb.append("false");
			isDup = "false";
		}
		
		sb.append("<id>" + reg_id + "</id>");
		sb.append("</root>");

		// response.setContentType("text/xml;charset=euc-kr");
		// response.getWriter().write(sb.toString());
		
		System.out.println("isDup:"+isDup);
		
		return SUCCESS;
		
	}

}
