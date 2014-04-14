package board.rest.action;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import common.ConDAOAware;
import board.rest.dto.RestDTO;
import board.restopt.dto.RestoptDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;

import common.Constants;

public class ReadRestAction extends ActionSupport implements ConDAOAware{
	
	//listRest.jsp에서 보낸 히든값
	private int currentPage;
	private int rest_num;
	
	public static Reader reader;
	public static SqlMapClient sqlMapper;
	private RestDTO paramClass = new RestDTO();
	private RestDTO resultClass = new RestDTO();
	private RestoptDTO paramClass1 = new RestoptDTO();
	private RestoptDTO resultClass1 = new RestoptDTO();
	private List<RestoptDTO> list = new ArrayList<RestoptDTO>();

	//수정시(파일관련)
	private String fileUploadPath1 = Constants.COMMON_FILE_PATH + Constants.REST_MAIN_FILE_PATH;
	private String fileUploadPath2 = Constants.COMMON_FILE_PATH + Constants.REST_CONTENT_FILE_PATH;
	
	
	
	public void setConDAO(SqlMapClient sqlMapper) { 
	    this.sqlMapper = sqlMapper;
	}
	
	
	public String execute() throws Exception {
		//글 조회수 +1
		paramClass.setRest_num(getRest_num());
		sqlMapper.update("Rest.updateRest_readcount", paramClass);
		
		//해당글번호의 레코드를 가져옴(상품테이블, 옵션테이블)
		resultClass = (RestDTO)sqlMapper.queryForObject("Rest.selectRestOne", getRest_num());
		//옵션들
		list = (List<RestoptDTO>) sqlMapper.queryForList("Rest.selectRestoptOne", getRest_num());
		
		return SUCCESS;
	}
	
	
	//옵션 테이블 list
	
	public List<RestoptDTO> getList() {
		return list;
	}
	public void setList(List<RestoptDTO> list) {
		this.list = list;
	}


	//listRest.jsp에서 보낸 히든값
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getRest_num() {
		return rest_num;
	}
	public void setRest_num(int rest_num) {
		this.rest_num = rest_num;
	}


	//paramClass,1 resultClass,1
	public RestDTO getParamClass() {
		return paramClass;
	}
	public void setParamClass(RestDTO paramClass) {
		this.paramClass = paramClass;
	}
	public RestDTO getResultClass() {
		return resultClass;
	}
	public void setResultClass(RestDTO resultClass) {
		this.resultClass = resultClass;
	}
	public RestoptDTO getParamClass1() {
		return paramClass1;
	}
	public void setParamClass1(RestoptDTO paramClass1) {
		this.paramClass1 = paramClass1;
	}
	public RestoptDTO getResultClass1() {
		return resultClass1;
	}
	public void setResultClass1(RestoptDTO resultClass1) {
		this.resultClass1 = resultClass1;
	}
	
}