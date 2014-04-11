package board.rest.action;

import board.rest.dto.RestDTO;
import board.restopt.dto.RestoptDTO;
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
	//RestBoard
	private RestDTO paramClass = new RestDTO();
	private RestDTO resultClass = new RestDTO();
	//RestoptBoard
	private RestoptDTO paramClass1 = new RestoptDTO();
	private RestoptDTO resultClass1 = new RestoptDTO();
	
	//폼으로 넘길 넘버
	int temp;
	int virRest_num = 1;
	
	//insertRest.jsp에서 넘긴 히든값
	private int currentPage; //현재 페이지
	private int rest_num;
	
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
	
	//insertRest.jsp에서 사용자가 입력한 옵션명 파라미터
	private String restopt_subject1;
	private String restopt_subject2;
	private String restopt_subject3;
	private String restopt_subject4;
	private String restopt_subject5;
	private String restopt_subject6;
	private String restopt_subject7;
	private String restopt_subject8;
	private String restopt_subject9;
	private String restopt_subject10;
	private String restopt_subject11;
	private String restopt_subject12;
	private String restopt_subject13;
	private String restopt_subject14;
	private String restopt_subject15;
	
	//insertRest.jsp에서 사용자가 입력한 옵션가 파라미터
	private int restopt_priceplus1;
	private int restopt_priceplus2;
	private int restopt_priceplus3;
	private int restopt_priceplus4;
	private int restopt_priceplus5;
	private int restopt_priceplus6;
	private int restopt_priceplus7;
	private int restopt_priceplus8;
	private int restopt_priceplus9;
	private int restopt_priceplus10;
	private int restopt_priceplus11;
	private int restopt_priceplus12;
	private int restopt_priceplus13;
	private int restopt_priceplus14;
	private int restopt_priceplus15;
	
	
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
		//가상 Rest_num을 만들어 virRest_num 이라는 이름으로 넘김
		Integer count = (Integer)sqlMapper.queryForObject("Rest.selectCount");
		if( count != 0){
			resultClass = (RestDTO) sqlMapper.queryForObject("Rest.selectLastNo");
			temp = (int)(resultClass.getRest_num());
			virRest_num = temp+1; // 앞으로 만들어질 시퀀스 넘버
		}
		
		
		return SUCCESS;
	}
	
	

	public String cancel() throws Exception{
		return SUCCESS;
	}
	

	//사용자가 글 등록(submit)했을시
	public String execute() throws Exception {
		
		//아래의 파라미터 insert
		paramClass.setRest_subject(getRest_subject());
		paramClass.setRest_price(getRest_price());
		paramClass.setRest_localcategory(getRest_localcategory());
		paramClass.setRest_typecategory(getRest_typecategory());
		paramClass.setRest_writer_name(getRest_writer_name());
		paramClass.setRest_writer_telnum(getRest_writer_telnum());
		paramClass.setRest_writer_mobilenum(getRest_writer_mobilenum());
		paramClass.setRest_writer_address(getRest_writer_address());
		paramClass.setRest_reg_date(today.getTime());
		
		sqlMapper.insert("Rest.insertRest_board", paramClass);
		
		
		
		// num, resnum, 옵션명, 옵션가 insert
		if(getRestopt_subject1() != null && getRestopt_priceplus1() != 0){
			paramClass1.setRestopt_rest_num(getVirRest_num());
			paramClass1.setRestopt_subject(restopt_subject1);
			paramClass1.setRestopt_priceplus(restopt_priceplus1);
			
			sqlMapper.insert("Rest.insertRestopt", paramClass1);
		}
		if(getRestopt_subject2() != null && getRestopt_priceplus2() != 0){
			paramClass1.setRestopt_rest_num(getVirRest_num());
			paramClass1.setRestopt_subject(restopt_subject2);
			paramClass1.setRestopt_priceplus(restopt_priceplus2);
			
			sqlMapper.insert("Rest.insertRestopt", paramClass1);
		}
		if(getRestopt_subject3() != null && getRestopt_priceplus3() != 0){
			paramClass1.setRestopt_rest_num(getVirRest_num());
			paramClass1.setRestopt_subject(restopt_subject3);
			paramClass1.setRestopt_priceplus(restopt_priceplus3);
			
			sqlMapper.insert("Rest.insertRestopt", paramClass1);
		}
		if(getRestopt_subject4() != null && getRestopt_priceplus4() != 0){
			paramClass1.setRestopt_rest_num(getVirRest_num());
			paramClass1.setRestopt_subject(restopt_subject4);
			paramClass1.setRestopt_priceplus(restopt_priceplus4);
			
			sqlMapper.insert("Rest.insertRestopt", paramClass1);
		}
		if(getRestopt_subject5() != null && getRestopt_priceplus5() != 0){
			paramClass1.setRestopt_rest_num(getVirRest_num());
			paramClass1.setRestopt_subject(restopt_subject5);
			paramClass1.setRestopt_priceplus(restopt_priceplus5);
			
			sqlMapper.insert("Rest.insertRestopt", paramClass1);
		}
		if(getRestopt_subject6() != null && getRestopt_priceplus6() != 0){
			paramClass1.setRestopt_rest_num(getVirRest_num());
			paramClass1.setRestopt_subject(restopt_subject6);
			paramClass1.setRestopt_priceplus(restopt_priceplus6);
			
			sqlMapper.insert("Rest.insertRestopt", paramClass1);
		}
		if(getRestopt_subject7() != null && getRestopt_priceplus7() != 0){
			paramClass1.setRestopt_rest_num(getVirRest_num());
			paramClass1.setRestopt_subject(restopt_subject7);
			paramClass1.setRestopt_priceplus(restopt_priceplus7);
			
			sqlMapper.insert("Rest.insertRestopt", paramClass1);
		}
		if(getRestopt_subject8() != null && getRestopt_priceplus8() != 0){
			paramClass1.setRestopt_rest_num(getVirRest_num());
			paramClass1.setRestopt_subject(restopt_subject8);
			paramClass1.setRestopt_priceplus(restopt_priceplus8);
			
			sqlMapper.insert("Rest.insertRestopt", paramClass1);
		}
		if(getRestopt_subject9() != null && getRestopt_priceplus9() != 0){
			paramClass1.setRestopt_rest_num(getVirRest_num());
			paramClass1.setRestopt_subject(restopt_subject9);
			paramClass1.setRestopt_priceplus(restopt_priceplus9);
			
			sqlMapper.insert("Rest.insertRestopt", paramClass1);
		}
		if(getRestopt_subject10() != null && getRestopt_priceplus10() != 0){
			paramClass1.setRestopt_rest_num(getVirRest_num());
			paramClass1.setRestopt_subject(restopt_subject10);
			paramClass1.setRestopt_priceplus(restopt_priceplus10);
			
			sqlMapper.insert("Rest.insertRestopt", paramClass1);
		}
		if(getRestopt_subject11() != null && getRestopt_priceplus11() != 0){
			paramClass1.setRestopt_rest_num(getVirRest_num());
			paramClass1.setRestopt_subject(restopt_subject11);
			paramClass1.setRestopt_priceplus(restopt_priceplus11);
			
			sqlMapper.insert("Rest.insertRestopt", paramClass1);
		}
		if(getRestopt_subject12() != null && getRestopt_priceplus12() != 0){
			paramClass1.setRestopt_rest_num(getVirRest_num());
			paramClass1.setRestopt_subject(restopt_subject12);
			paramClass1.setRestopt_priceplus(restopt_priceplus12);
			
			sqlMapper.insert("Rest.insertRestopt", paramClass1);
		}
		if(getRestopt_subject13() != null && getRestopt_priceplus13() != 0){
			paramClass1.setRestopt_rest_num(getVirRest_num());
			paramClass1.setRestopt_subject(restopt_subject13);
			paramClass1.setRestopt_priceplus(restopt_priceplus13);
			
			sqlMapper.insert("Rest.insertRestopt", paramClass1);
		}
		if(getRestopt_subject14() != null && getRestopt_priceplus14() != 0){
			paramClass1.setRestopt_rest_num(getVirRest_num());
			paramClass1.setRestopt_subject(restopt_subject14);
			paramClass1.setRestopt_priceplus(restopt_priceplus14);
			
			sqlMapper.insert("Rest.insertRestopt", paramClass1);
		}
		if(getRestopt_subject15() != null && getRestopt_priceplus15() != 0){
			paramClass1.setRestopt_rest_num(getVirRest_num());
			paramClass1.setRestopt_subject(restopt_subject15);
			paramClass1.setRestopt_priceplus(restopt_priceplus15);
			
			sqlMapper.insert("Rest.insertRestopt", paramClass1);
		}
		
		
		// 첨부파일을 선택했다면 파일을 업로드한다.
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
		
		return SUCCESS;
	}

	//form으로 넘길 가상 seq넘버(for opt)
	public int getVirRest_num() {
		return virRest_num;
	}
	public void setVirRest_num(int virRest_num) {
		this.virRest_num = virRest_num;
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
	
	
	
	//옵션명에 대한 메서드
	public String getRestopt_subject1() {
		return restopt_subject1;
	}
	public void setRestopt_subject1(String restopt_subject1) {
		this.restopt_subject1 = restopt_subject1;
	}
	public String getRestopt_subject2() {
		return restopt_subject2;
	}
	public void setRestopt_subject2(String restopt_subject2) {
		this.restopt_subject2 = restopt_subject2;
	}
	public String getRestopt_subject3() {
		return restopt_subject3;
	}
	public void setRestopt_subject3(String restopt_subject3) {
		this.restopt_subject3 = restopt_subject3;
	}
	public String getRestopt_subject4() {
		return restopt_subject4;
	}
	public void setRestopt_subject4(String restopt_subject4) {
		this.restopt_subject4 = restopt_subject4;
	}
	public String getRestopt_subject5() {
		return restopt_subject5;
	}
	public void setRestopt_subject5(String restopt_subject5) {
		this.restopt_subject5 = restopt_subject5;
	}
	public String getRestopt_subject6() {
		return restopt_subject6;
	}
	public void setRestopt_subject6(String restopt_subject6) {
		this.restopt_subject6 = restopt_subject6;
	}
	public String getRestopt_subject7() {
		return restopt_subject7;
	}
	public void setRestopt_subject7(String restopt_subject7) {
		this.restopt_subject7 = restopt_subject7;
	}
	public String getRestopt_subject8() {
		return restopt_subject8;
	}
	public void setRestopt_subject8(String restopt_subject8) {
		this.restopt_subject8 = restopt_subject8;
	}
	public String getRestopt_subject9() {
		return restopt_subject9;
	}
	public void setRestopt_subject9(String restopt_subject9) {
		this.restopt_subject9 = restopt_subject9;
	}
	public String getRestopt_subject10() {
		return restopt_subject10;
	}
	public void setRestopt_subject10(String restopt_subject10) {
		this.restopt_subject10 = restopt_subject10;
	}
	public String getRestopt_subject11() {
		return restopt_subject11;
	}
	public void setRestopt_subject11(String restopt_subject11) {
		this.restopt_subject11 = restopt_subject11;
	}
	public String getRestopt_subject12() {
		return restopt_subject12;
	}
	public void setRestopt_subject12(String restopt_subject12) {
		this.restopt_subject12 = restopt_subject12;
	}
	public String getRestopt_subject13() {
		return restopt_subject13;
	}
	public void setRestopt_subject13(String restopt_subject13) {
		this.restopt_subject13 = restopt_subject13;
	}
	public String getRestopt_subject14() {
		return restopt_subject14;
	}
	public void setRestopt_subject14(String restopt_subject14) {
		this.restopt_subject14 = restopt_subject14;
	}
	public String getRestopt_subject15() {
		return restopt_subject15;
	}
	public void setRestopt_subject15(String restopt_subject15) {
		this.restopt_subject15 = restopt_subject15;
	}
	
	
	
	//옵션가에 대한 메서드
	public int getRestopt_priceplus1() {
		return restopt_priceplus1;
	}
	public void setRestopt_priceplus1(int restopt_priceplus1) {
		this.restopt_priceplus1 = restopt_priceplus1;
	}
	public int getRestopt_priceplus2() {
		return restopt_priceplus2;
	}
	public void setRestopt_priceplus2(int restopt_priceplus2) {
		this.restopt_priceplus2 = restopt_priceplus2;
	}
	public int getRestopt_priceplus3() {
		return restopt_priceplus3;
	}
	public void setRestopt_priceplus3(int restopt_priceplus3) {
		this.restopt_priceplus3 = restopt_priceplus3;
	}
	public int getRestopt_priceplus4() {
		return restopt_priceplus4;
	}
	public void setRestopt_priceplus4(int restopt_priceplus4) {
		this.restopt_priceplus4 = restopt_priceplus4;
	}
	public int getRestopt_priceplus5() {
		return restopt_priceplus5;
	}
	public void setRestopt_priceplus5(int restopt_priceplus5) {
		this.restopt_priceplus5 = restopt_priceplus5;
	}
	public int getRestopt_priceplus6() {
		return restopt_priceplus6;
	}
	public void setRestopt_priceplus6(int restopt_priceplus6) {
		this.restopt_priceplus6 = restopt_priceplus6;
	}
	public int getRestopt_priceplus7() {
		return restopt_priceplus7;
	}
	public void setRestopt_priceplus7(int restopt_priceplus7) {
		this.restopt_priceplus7 = restopt_priceplus7;
	}
	public int getRestopt_priceplus8() {
		return restopt_priceplus8;
	}
	public void setRestopt_priceplus8(int restopt_priceplus8) {
		this.restopt_priceplus8 = restopt_priceplus8;
	}
	public int getRestopt_priceplus9() {
		return restopt_priceplus9;
	}
	public void setRestopt_priceplus9(int restopt_priceplus9) {
		this.restopt_priceplus9 = restopt_priceplus9;
	}
	public int getRestopt_priceplus10() {
		return restopt_priceplus10;
	}
	public void setRestopt_priceplus10(int restopt_priceplus10) {
		this.restopt_priceplus10 = restopt_priceplus10;
	}
	public int getRestopt_priceplus11() {
		return restopt_priceplus11;
	}
	public void setRestopt_priceplus11(int restopt_priceplus11) {
		this.restopt_priceplus11 = restopt_priceplus11;
	}
	public int getRestopt_priceplus12() {
		return restopt_priceplus12;
	}
	public void setRestopt_priceplus12(int restopt_priceplus12) {
		this.restopt_priceplus12 = restopt_priceplus12;
	}
	public int getRestopt_priceplus13() {
		return restopt_priceplus13;
	}
	public void setRestopt_priceplus13(int restopt_priceplus13) {
		this.restopt_priceplus13 = restopt_priceplus13;
	}
	public int getRestopt_priceplus14() {
		return restopt_priceplus14;
	}
	public void setRestopt_priceplus14(int restopt_priceplus14) {
		this.restopt_priceplus14 = restopt_priceplus14;
	}
	public int getRestopt_priceplus15() {
		return restopt_priceplus15;
	}
	public void setRestopt_priceplus15(int restopt_priceplus15) {
		this.restopt_priceplus15 = restopt_priceplus15;
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
}
