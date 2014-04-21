package board.rest.action;

import java.util.ArrayList;
import java.util.List;

import board.rest.dto.RestDTO;
import board.restopt.dto.RestoptDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;

import common.ConDAOAware;

public class UpdateRestAction extends ActionSupport implements ConDAOAware {
	public static SqlMapClient sqlMapper;
	private RestDTO resultClass = new RestDTO();
	private List<RestoptDTO> list = new ArrayList<RestoptDTO>();
	
	
	private int rest_num;
	private int currentPage;
	private String session_id;
	
	
	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;
	}	
	
	public String form() throws Exception {
		//수정에 뿌려줄 레코드1개 select
		resultClass = (RestDTO)sqlMapper.queryForObject("Rest.selectRestOne", getRest_num());
		
		//옵션에 뿌려줄 레코드들 select
		list = (List<RestoptDTO>) sqlMapper.queryForList("Rest.selectRestoptOne", getRest_num());
		
	
		return SUCCESS; //updateRest.jsp
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
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public RestDTO getResultClass() {
		return resultClass;
	}
	public void setResultClass(RestDTO resultClass) {
		this.resultClass = resultClass;
	}
	public List<RestoptDTO> getList() {
		return list;
	}
	public void setList(List<RestoptDTO> list) {
		this.list = list;
	}
	
}
