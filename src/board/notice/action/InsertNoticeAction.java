package board.notice.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import common.ConDAOAware;

import java.util.*;
import java.io.Reader;
import java.io.IOException;

import board.notice.dto.*;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class InsertNoticeAction extends ActionSupport implements ConDAOAware,
		Preparable, ModelDriven {

	public static SqlMapClient sqlMapper; // SqlMapClient API를 사용하기 위한 sqlMapper 객체
	
	NoticeDTO noticeDTO;

	private int currentPage; //현재 페이지

	Calendar today = Calendar.getInstance(); //오늘 날짜 구하기

	private File upload; //파일 객체
	private String uploadContentType; //컨텐츠 타입
	private String uploadFileName; //파일 이름
	private String fileUploadPath = "D:\\오택근\\upload\\"; //업로드 경로

	public String form() throws Exception {
		//등록 폼
		return SUCCESS;
	}

	//게시판 WRITE 액션
	public String execute() throws Exception {

		//등록할 항목 설정
		noticeDTO.setNotice_reg_date(today.getTime());

		//등록 쿼리 수행
		sqlMapper.insert("Notice.insertNotice", noticeDTO);

		//첨부파일을 선택했다면 파일을 업로드 한다
		if (getUpload() != null) {

			//등록한 글 번호 가져오기
			noticeDTO = (NoticeDTO) sqlMapper.queryForObject("Notice.selectLastNo");

			//실제 서버에 저장될 파일 이름과 확장자 설정
			String file_name = "file_" + noticeDTO.getNotice_num();
			String file_ext = getUploadFileName().substring(
					getUploadFileName().lastIndexOf('.') + 1,
					getUploadFileName().length());

			//서버에 파일 저장
			File destFile = new File(fileUploadPath + file_name + "."
					+ file_ext);
			FileUtils.copyFile(getUpload(), destFile);

			noticeDTO.setNotice_file(file_name + "." + file_ext); 

			//파일 정보 업데이트
			sqlMapper.update("Notice.updateFile", noticeDTO);
		}

		return SUCCESS;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getFileUploadPath() {
		return fileUploadPath;
	}

	public void setFileUploadPath(String fileUploadPath) {
		this.fileUploadPath = fileUploadPath;
	}

	
		public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;
	}

	public Object getModel() {

		return noticeDTO;
	}

	public void prepare() throws Exception {
		noticeDTO = new NoticeDTO();
	}

}
