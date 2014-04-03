//////////////////////
package board.rest.action;

import board.rest.dto.RestDTO;

import com.opensymphony.xwork2.ActionSupport;
import com.ibatis.sqlmap.client.SqlMapClient;
import common.ConDAOAware;

import java.util.*;
import java.io.Reader;
import java.io.File;

import org.apache.commons.io.FileUtils;

public class InsertRestAction extends ActionSupport implements ConDAOAware{

	public static Reader reader; //���� ��Ʈ���� ���� reader.
	public static SqlMapClient sqlMapper; //SqlMapClient API�� ����ϱ� ���� sqlMapper ��ü.
	private RestDTO paramClass; //�Ķ���͸� ������ ��ü
	private RestDTO resultClass; //���� ��� ���� ������ ��ü
	
	
	//insertRest.jsp���� �ѱ� ���簪
	private int currentPage; //���� ������
	private int no;
	
	
	//insertRest.jsp���� ����ڰ� �Է��Ͽ� ���� �Ķ����
	private String rest_subject;
	private int rest_price;
	private int rest_amount;
	private String rest_localcategory;
	private String rest_typecategory;
	private String rest_writer_name;//�ӽ�����
	private String rest_writer_telnum;//�ӽ�����
	private String rest_writer_mobilenum;//�ӽ�����
	private String rest_writer_address;//�ӽ�����
	Calendar today = Calendar.getInstance(); //���� ��¥ ���ϱ�.
	
	
	//���λ���
	private String rest_main_orgname;
	private String rest_main_savname;
	private File upload1; //���� ��ü
	private String upload1ContentType; //������ Ÿ��
	private String upload1FileName; //���� �̸�
	private String fileUploadPath1 = "D:\\upload\\main\\"; //���ε� ���.
										
	
	//����Ʈ����
	private String rest_content_orgname;
	private String rest_content_savname;
	private File upload2; //����2 ��ü
	private String upload2ContentType; //������2 Ÿ��
	private String upload2FileName; //����2 �̸�
	private String fileUploadPath2 = "D:\\upload\\content\\"; //���ε�2 ���.

	
	//ConDAOAware �������̽��� �޼���(���ͼ��Ϳ��� ȣ��)
	public void setConDAO(SqlMapClient sqlMapper) { 
	    this.sqlMapper = sqlMapper;
	}
	
	//���� insert�׼� ȣ��� (������)
	public String form() throws Exception {
		return SUCCESS;
	}

	//����ڰ� �� ���(submit)������
	public String execute() throws Exception {
		
		//�Ķ���Ϳ� ����Ʈ ��ü ��.
		paramClass = new RestDTO();
		resultClass = new RestDTO();
		
		
		//insertRest.jsp���� ����ڰ� �Է��� �Ķ���͸� get�� DTO�� set��.
		paramClass.setRest_subject(getRest_subject());
		paramClass.setRest_price(getRest_price());
		paramClass.setRest_amount(0); //������ 0���� ���� 
		paramClass.setRest_localcategory(getRest_localcategory());
		paramClass.setRest_typecategory(getRest_typecategory());
		paramClass.setRest_writer_name(getRest_writer_name());
		paramClass.setRest_writer_telnum(getRest_writer_telnum());
		paramClass.setRest_writer_mobilenum(getRest_writer_mobilenum());
		paramClass.setRest_writer_address(getRest_writer_address());
		paramClass.setRest_reg_date(today.getTime());
		
		// [iBatis] insertRestBoard ����
		sqlMapper.insert("Rest.insertRestBoard", paramClass);
		
		// ÷�������� �����ߴٸ� ������ ���ε��Ѵ�.
		if (getUpload1() != null && getUpload2() != null) {
			//����� �� ��ȣ ��������.
			resultClass = (RestDTO) sqlMapper.queryForObject("Rest.selectLastNo");
			
			//���λ��� ���� �̸��� Ȯ���� ����.
			String file_name1 = "main_" + resultClass.getRest_num(); // main_112
			String file_ext1 = getUpload1FileName().substring(getUpload1FileName().lastIndexOf('.') + 1, getUpload1FileName().length());
			//����Ʈ���� ���� �̸��� Ȯ���� ����.
			String file_name2 = "content_" + resultClass.getRest_num(); // content_112
			String file_ext2 = getUpload2FileName().substring(getUpload2FileName().lastIndexOf('.') + 1, getUpload2FileName().length());
			
			//���λ������� ����.
			File destFile1 = new File(fileUploadPath1 + file_name1 + "."+ file_ext1); // ���ϰ��/�����̸�.Ȯ����
			FileUtils.copyFile(getUpload1(), destFile1); //����,��� // ������ ����� ���缭 ��ο� �ٿ�����
			//����Ʈ�������� ����.
			File destFile2 = new File(fileUploadPath2 + file_name2 + "."+ file_ext2); // ���ϰ��/�����̸�.Ȯ����
			FileUtils.copyFile(getUpload2(), destFile2); //����,��� // ������ ����� ���缭 ��ο� �ٿ�����

			//���λ������� DTO�� set
			paramClass.setRest_num(resultClass.getRest_num());  //����� �۹�ȣ��
			paramClass.setRest_main_orgname(getUpload1FileName()); //�� ���� �̸�
			paramClass.setRest_main_savname(file_name1 + "." + file_ext1); //������ ������ ���� �̸�
			//����Ʈ�������� DTO�� set
			paramClass.setRest_content_orgname(getUpload2FileName()); //�� ���� �̸�
			paramClass.setRest_content_savname(file_name2 + "." + file_ext2); //������ ������ ���� �̸�
			
			//���� ���� ������Ʈ.
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
	
	
	//�Էµ� �Ķ����
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
	
	
	//���λ�������
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
	
	
	//����Ʈ��������
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
	
	
	//������ ���λ��������̸� ����
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
	//������ ����Ʈ���������̸� ����
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

	
	//insertRest.jsp���� �ѱ� ���簪
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
