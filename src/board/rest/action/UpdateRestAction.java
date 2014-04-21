package board.rest.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import board.rest.dto.RestDTO;
import board.restopt.dto.RestoptDTO;
import board.review.action.FileUpload;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;

import common.ConDAOAware;
import common.Constants;

public class UpdateRestAction extends ActionSupport implements ConDAOAware {
	public static SqlMapClient sqlMapper;
	private List<RestoptDTO> list = new ArrayList<RestoptDTO>();
	
	private int rest_num;
	private int currentPage;
	private String session_id;
	
	
	
	
	//상품글 update
	private RestDTO paramClass = new RestDTO();
	private RestDTO resultClass = new RestDTO();
	
	//상품글 일반항목
	private String rest_subject;
	private String  rest_localcategory;
	private String  rest_typecategory;
	//매인사진
	private File rest_destFile1; //dto
	private String rest_main_orgname; // dto
	private String rest_main_savname; // dto
	private File upload1;
	private String upload1ContentType;
	private String upload1FileName;
	private String fileUploadPath1 = Constants.COMMON_FILE_PATH + Constants.REST_MAIN_FILE_PATH;
	//컨텐트사진
	private File rest_destFile2;
	private String rest_content_orgname;
	private String rest_content_savname;
	private File upload2;
	private String upload2ContentType;
	private String upload2FileName;
	private String fileUploadPath2 = Constants.COMMON_FILE_PATH + Constants.REST_CONTENT_FILE_PATH;
	//상품글 update 끝
	
	
	
	
	
	
	//옵션레코드 update
	private RestoptDTO paramClass1 = new RestoptDTO();
	private RestoptDTO resultClass1 = new RestoptDTO();
	
	
	//옵션레코드 update 끝
	
	
	
	
	
	
	
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
		
		//상품글 업데이트 시작
			//상품글 일반항목 업데이트
			paramClass.setRest_num(getRest_num());
			paramClass.setRest_subject(getRest_subject());
			paramClass.setRest_localcategory(getRest_localcategory());
			paramClass.setRest_typecategory(getRest_typecategory());
			sqlMapper.update("Rest.updateRestone", paramClass);
			
			
			//상품글 파일 업데이트
			if (getUpload1() != null && getUpload2() != null) {
				resultClass = (RestDTO) sqlMapper.queryForObject("Rest.selectLastNo");
	
				//매인사진 파일 이름과 확장자 설정.
				String file_name1 = "main_" + resultClass.getRest_num();
				String file_ext1 = getUpload1FileName().substring(getUpload1FileName().lastIndexOf('.') + 1, getUpload1FileName().length());
				//컨텐트사진 파일 이름과 확장자 설정.
				String file_name2 = "content_" + resultClass.getRest_num();
				String file_ext2 = getUpload2FileName().substring(getUpload2FileName().lastIndexOf('.') + 1, getUpload2FileName().length());
	
				//매인사진파일 저장.
				rest_destFile1 = new File(fileUploadPath1 + file_name1 + "."+ file_ext1); 
				FileUtils.copyFile(getUpload1(), rest_destFile1);
				//컨텐트사진파일 저장.
				rest_destFile2 = new File(fileUploadPath2 + file_name2 + "."+ file_ext2); 
				FileUtils.copyFile(getUpload2(), rest_destFile2); 
	
				//글넘버
				paramClass.setRest_num(resultClass.getRest_num());
				//매인사진파일 DTO에 set
				paramClass.setRest_destFile1(rest_destFile1.getPath().replace('\\', '/').substring(27));
				paramClass.setRest_main_orgname(getUpload1FileName());
				paramClass.setRest_main_savname(file_name1 + "." + file_ext1);
				//컨텐트사진파일 DTO에 set
				paramClass.setRest_destFile2(rest_destFile2.getPath().replace('\\', '/').substring(27));
				paramClass.setRest_content_orgname(getUpload2FileName()); 
				paramClass.setRest_content_savname(file_name2 + "." + file_ext2);
	
				//파일 정보 업데이트.
				sqlMapper.update("Rest.updateFile", paramClass);
			}
		//상품글 업데이트 완료
		
		
		
		
		
		//옵션글 업데이트 시작
			//옵션을 불러옴
			list = (List<RestoptDTO>) sqlMapper.queryForList("Rest.selectRestoptOne", getRest_num());
			
			//옵션사진 삭제
			FileUpload fileUpload = new FileUpload();
			
			for(int i=0; i<list.size(); i++){
				if (list.get(i).getRestopt_orgname() != null) {
					String filesName = list.get(i).	getRestopt_orgname();
					String optfileUploadPath = Constants.COMMON_FILE_PATH + Constants.REST_MENU_FILE_PATH;
					fileUpload.deleteFiles(filesName, optfileUploadPath);
				}
			}
			
			
			//옵션 레코드들 모두 삭제
			sqlMapper.delete("Rest.deleteRestoptBoard", getRest_num());
			
			
			//
			
			
			
		//옵션글 업데이트 완료
		
		
		
		return SUCCESS;  // preRead.jsp
	}
	
	

	
	
	
	public RestDTO getParamClass() {
		return paramClass;
	}

	public void setParamClass(RestDTO paramClass) {
		this.paramClass = paramClass;
	}

	public String getRest_subject() {
		return rest_subject;
	}

	public void setRest_subject(String rest_subject) {
		this.rest_subject = rest_subject;
	}

	public String getRest_localcategory() {
		return rest_localcategory;
	}

	public void setRest_localcategory(String rest_localcategory) {
		this.rest_localcategory = rest_localcategory;
	}

	public String getRest_typecategory() {
		return rest_typecategory;
	}

	public void setRest_typecategory(String rest_typecategory) {
		this.rest_typecategory = rest_typecategory;
	}

	public File getRest_destFile1() {
		return rest_destFile1;
	}

	public void setRest_destFile1(File rest_destFile1) {
		this.rest_destFile1 = rest_destFile1;
	}

	public String getRest_main_orgname() {
		return rest_main_orgname;
	}

	public void setRest_main_orgname(String rest_main_orgname) {
		this.rest_main_orgname = rest_main_orgname;
	}

	public String getRest_main_savname() {
		return rest_main_savname;
	}

	public void setRest_main_savname(String rest_main_savname) {
		this.rest_main_savname = rest_main_savname;
	}

	public File getUpload1() {
		return upload1;
	}

	public void setUpload1(File upload1) {
		this.upload1 = upload1;
	}

	public String getUpload1ContentType() {
		return upload1ContentType;
	}

	public void setUpload1ContentType(String upload1ContentType) {
		this.upload1ContentType = upload1ContentType;
	}

	public String getUpload1FileName() {
		return upload1FileName;
	}

	public void setUpload1FileName(String upload1FileName) {
		this.upload1FileName = upload1FileName;
	}

	public String getFileUploadPath1() {
		return fileUploadPath1;
	}

	public void setFileUploadPath1(String fileUploadPath1) {
		this.fileUploadPath1 = fileUploadPath1;
	}

	public File getRest_destFile2() {
		return rest_destFile2;
	}

	public void setRest_destFile2(File rest_destFile2) {
		this.rest_destFile2 = rest_destFile2;
	}

	public String getRest_content_orgname() {
		return rest_content_orgname;
	}

	public void setRest_content_orgname(String rest_content_orgname) {
		this.rest_content_orgname = rest_content_orgname;
	}

	public String getRest_content_savname() {
		return rest_content_savname;
	}

	public void setRest_content_savname(String rest_content_savname) {
		this.rest_content_savname = rest_content_savname;
	}

	public File getUpload2() {
		return upload2;
	}

	public void setUpload2(File upload2) {
		this.upload2 = upload2;
	}

	public String getUpload2ContentType() {
		return upload2ContentType;
	}

	public void setUpload2ContentType(String upload2ContentType) {
		this.upload2ContentType = upload2ContentType;
	}

	public String getUpload2FileName() {
		return upload2FileName;
	}

	public void setUpload2FileName(String upload2FileName) {
		this.upload2FileName = upload2FileName;
	}

	public String getFileUploadPath2() {
		return fileUploadPath2;
	}

	public void setFileUploadPath2(String fileUploadPath2) {
		this.fileUploadPath2 = fileUploadPath2;
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
