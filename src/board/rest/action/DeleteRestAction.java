package board.rest.action;

import java.util.ArrayList;
import java.util.List;
import board.rest.dto.RestDTO;
import board.restopt.dto.RestOptDTO;
import board.review.action.FileUpload;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;
import common.ConDAOAware;
import common.Constants;

public class DeleteRestAction extends ActionSupport implements ConDAOAware{
	
	public static SqlMapClient sqlMapper;
	private RestDTO paramClass = new RestDTO();
	private RestDTO resultClass = new RestDTO();
	private RestOptDTO paramClass1 = new RestOptDTO();
	private List<RestOptDTO> list = new ArrayList<RestOptDTO>();
	private int rest_num;
	private String session_id;

	//인터셉터
	public void setConDAO(SqlMapClient sqlMapper) { 
	    this.sqlMapper = sqlMapper;
	}
	
	public String execute() throws Exception {
		FileUpload fileUpload = new FileUpload();
		//상품 매인,컨텐트 사진 삭제
		resultClass = (RestDTO)sqlMapper.queryForObject("Rest.selectRestOne", getRest_num());
		String main_fileName = resultClass.getRest_main_savname();
		String content_fileName = resultClass.getRest_content_savname();
		String main_fileUploadPath = Constants.COMMON_FILE_PATH + Constants.REST_MAIN_FILE_PATH;
		String content_fileUploadPath = Constants.COMMON_FILE_PATH + Constants.REST_CONTENT_FILE_PATH;
		fileUpload.deleteFiles(main_fileName, main_fileUploadPath);
		fileUpload.deleteFiles(content_fileName, content_fileUploadPath);
		
		//상품레코드 제거
		paramClass.setRest_num(getRest_num());
		paramClass.setRest_writer_id(getSession_id());
		sqlMapper.delete("Rest.deleteRestBoard", paramClass);
		
		//옵션사진 제거
		//옵션을 불러옴
		list = (List<RestOptDTO>) sqlMapper.queryForList("Rest.selectRestoptOne", getRest_num());
		
		//옵션사진 삭제
		for(int i=0; i<list.size(); i++){
			if (list.get(i).getRestopt_orgname() != null) {
				String filesName = list.get(i).	getRestopt_savname();
				String rest_optfileUploadPath = Constants.COMMON_FILE_PATH + Constants.REST_MENU_FILE_PATH;
				fileUpload.deleteFiles(filesName, rest_optfileUploadPath);
			}
		}
		//옵션레코드 제거
		sqlMapper.delete("Rest.deleteRestoptBoard", getRest_num());
		
		//카트레코드 제거
		sqlMapper.delete("Rest.deleteCartBoard", paramClass);
		
		/*
		식당이 없어져도 리뷰글은 존재한다.
		만약, 리뷰글을 삭제하고 싶을 경우 주석을 해제하세요.  
		sqlMapper.delete("Rest.deleteReviewtBoard", getRest_num());
		*/
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
