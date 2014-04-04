package board.rest.action;

import board.rest.dto.RestDTO;
import com.opensymphony.xwork2.ActionSupport;
import com.ibatis.sqlmap.client.SqlMapClient;
import common.ConDAOAware;
import common.Constants;
import java.util.*;
import java.io.Reader;
import java.io.File;

import org.apache.commons.io.FileUtils;

public class InsertRestAction extends ActionSupport implements ConDAOAware{

	public static Reader reader; 
	public static SqlMapClient sqlMapper;
	private RestDTO paramClass;
	private RestDTO resultClass;
	
	
	//insertRest.jsp에서 넘긴 히든값
	private int currentPage; //현재 페이지
	private int no;
	
	
	//insertRest.jsp에서 사용자가 입력하여 보낸 파라미터
	private String rest_subject;
	private int rest_price;
	private int rest_amount;
	private String rest_localcategory;
	private String rest_typecategory;
	private String rest_writer_name;//임시히든
	private String rest_writer_telnum;//임시히든
	private String rest_writer_mobilenum;//임시히든
	private String rest_writer_address;//임시히든
	Calendar today = Calendar.getInstance();
	
	
	//매인사진
	private File rest_destFile1;
	private String rest_main_orgname;
	private String rest_main_savname;
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

	
	//ConDAOAware 인터페이스의 메서드(인터셉터에서 호출)
	public void setConDAO(SqlMapClient sqlMapper) { 
	    this.sqlMapper = sqlMapper;
	}
	
	//최초 insert액션 호출시 (폼제공)
	public String form() throws Exception {
		return SUCCESS;
	}

	//사용자가 글 등록(submit)했을시
	public String execute() throws Exception {
		
		//파라미터와 리절트 객체 생성.
		paramClass = new RestDTO();
		resultClass = new RestDTO();
		
		//insertRest.jsp에서 사용자가 입력한 파라미터를 get후 DTO에 set함.
		paramClass.setRest_subject(getRest_subject());
		paramClass.setRest_price(getRest_price());
		paramClass.setRest_localcategory(getRest_localcategory());
		paramClass.setRest_typecategory(getRest_typecategory());
		paramClass.setRest_writer_name(getRest_writer_name());
		paramClass.setRest_writer_telnum(getRest_writer_telnum());
		paramClass.setRest_writer_mobilenum(getRest_writer_mobilenum());
		paramClass.setRest_writer_address(getRest_writer_address());
		paramClass.setRest_reg_date(today.getTime());
		
		// [iBatis] insertRestBoard 실행
		sqlMapper.insert("Rest.insertRestBoard", paramClass);
		
		// 첨부파일을 선택했다면 파일을 업로드한다.
		if (getUpload1() != null && getUpload2() != null) {
			//등록한 글 번호 가져오기.
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
			paramClass.setRest_destFile1(Constants.REST_MAIN_FILE_PATH+file_name1+"."+file_ext1);
			paramClass.setRest_main_orgname(getUpload1FileName());
			paramClass.setRest_main_savname(file_name1 + "." + file_ext1);
			//컨텐트사진파일 DTO에 set
			paramClass.setRest_destFile2(Constants.REST_CONTENT_FILE_PATH+file_name2+"."+file_ext2);
			paramClass.setRest_content_orgname(getUpload2FileName()); 
			paramClass.setRest_content_savname(file_name2 + "." + file_ext2);
			
			//파일 정보 업데이트.
			sqlMapper.update("Rest.updateFile", paramClass);
		}
		
		return SUCCESS;
	}

	
	
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
	
	
	//입력된 파라미터
	public String getRest_subject() {
		return rest_subject;
	}
	public void setRest_subject(String rest_subject) {
		this.rest_subject = rest_subject;
	}
	public int getRest_price() {
		return rest_price;
	}
	public void setRest_price(int rest_price) {
		this.rest_price = rest_price;
	}
	public int getRest_amount() {
		return rest_amount;
	}
	public void setRest_amount(int rest_amount) {
		this.rest_amount = rest_amount;
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
	public String getRest_writer_name() {
		return rest_writer_name;
	}
	public void setRest_writer_name(String rest_writer_name) {
		this.rest_writer_name = rest_writer_name;
	}
	public String getRest_writer_telnum() {
		return rest_writer_telnum;
	}
	public void setRest_writer_telnum(String rest_writer_telnum) {
		this.rest_writer_telnum = rest_writer_telnum;
	}
	public String getRest_writer_mobilenum() {
		return rest_writer_mobilenum;
	}
	public void setRest_writer_mobilenum(String rest_writer_mobilenum) {
		this.rest_writer_mobilenum = rest_writer_mobilenum;
	}
	public String getRest_writer_address() {
		return rest_writer_address;
	}
	public void setRest_writer_address(String rest_writer_address) {
		this.rest_writer_address = rest_writer_address;
	}
	public Calendar getToday() {
		return today;
	}
	public void setToday(Calendar today) {
		this.today = today;
	}
	
	
	//매인사진파일
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
	public File getRest_destFile1(){
		return rest_destFile1;
	}
	
	
	//컨텐트사진파일
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
	public File getRest_destFile2(){
		return rest_destFile2;
	}
	
	
	//수정시 매인사진파일이름 구분
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
	//수정시 컨텐트사진파일이름 구분
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

	
	//insertRest.jsp에서 넘긴 히든값
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
