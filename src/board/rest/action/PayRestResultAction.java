package board.rest.action;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;

import common.ConDAOAware;

public class PayRestResultAction  extends ActionSupport implements ConDAOAware{
	public static SqlMapClient sqlMapper;
	
	private int rest_num;
	private String session_id;
	
	
	
	public void setConDAO(SqlMapClient sqlMapper) { 
	    this.sqlMapper = sqlMapper;
	}
	
	
	public String execute() throws Exception {
		
		
		
		return SUCCESS;
	}

	
	
	public int getRest_num() {
		return rest_num;
	}
	public void setRest_num(int rest_num) {
		this.rest_num = rest_num;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	
}
