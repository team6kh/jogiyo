package board.rest.action;

import board.rest.dto.RestDTO;
import board.restopt.dto.RestoptDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;

import common.ConDAOAware;

public class DeleteRestAction extends ActionSupport implements ConDAOAware{
	
	public static SqlMapClient sqlMapper;
	private RestDTO paramClass = new RestDTO();
	private RestoptDTO paramClass1 = new RestoptDTO();
	
	private int rest_num;
	private String session_id;
	
		
	public void setConDAO(SqlMapClient sqlMapper) { 
	    this.sqlMapper = sqlMapper;
	}
	
	public String execute() throws Exception {
		
		//상품레코드 제거
		paramClass.setRest_num(getRest_num());
		paramClass.setRest_writer_id(getSession_id());
		sqlMapper.delete("Rest.deleteRestBoard", paramClass);
		
		//옵션레코드 제거
		sqlMapper.delete("Rest.deleteRestoptBoard", getRest_num());
		
		return SUCCESS; // 액션-> listRest.jsp
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
