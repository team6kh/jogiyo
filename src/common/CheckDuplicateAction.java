package common;

import com.opensymphony.xwork2.Action;

public class CheckDuplicateAction implements Action {
	
	private String reg_id;
	private StringBuffer sb;
	private String isDup;
	private int check;	
	
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	
	public int getCheck() {
		return check;
	}

	public String execute() throws Exception {
		
		System.out.println("aaa");
		
		sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='euc-kr'?>");
		sb.append("<root>");
		if (!(reg_id.equals("adminadmin") || reg_id.equals("test"))) {
			sb.append("true");
			isDup = "false";
			check = 1;
		} else {
			sb.append("false");
			isDup = "true";
			check = 0;
		}
		
		sb.append("<id>" + reg_id + "</id>");
		sb.append("</root>");

		// response.setContentType("text/xml;charset=euc-kr");
		// response.getWriter().write(sb.toString());
		
		System.out.println("reg_id:"+reg_id);
		System.out.println("isDup:"+isDup);
		System.out.println("check:"+check);
		
		return SUCCESS;
		
	}
	
}
