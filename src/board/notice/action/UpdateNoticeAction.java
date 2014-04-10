package board.notice.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import common.ConDAOAware;

import java.io.Reader;
import java.io.File;
import java.io.IOException;

import board.notice.dto.*;

import org.apache.commons.io.FileUtils;

public class UpdateNoticeAction extends ActionSupport implements ConDAOAware,
	Preparable, ModelDriven {

	public static SqlMapClient sqlMapper; //SqlMapClient API를 사용하기 위한 sqlMapper 객체
	private NoticeDTO resultClass; //쿼리 결과 값을 저장할 객체
	NoticeDTO noticeDTO;
	private int currentPage; //현재 페이지

	private File upload; //파일 객체
	private String uploadContentType; //컨텐츠 타입
	private String uploadFileName; //파일 이름
	private String fileUploadPath = "D:\\오택근\\upload\\"; //업로드 경로

	//게시글 수정
	public String execute() throws Exception {
		
		//일단 항목만 수정한다
		sqlMapper.update("Notice.updateNotice", noticeDTO);

		//수정할 파일이 업로드 되었다면 파일을 업로드하고 DB의 file 항목을 수정함.
		if (getUpload() != null) {
			
			//실제 서버에 저장될 파일 이름과 확장자 설정.
			String file_name = "file_" + noticeDTO.getNotice_num();
		           String file_ext = getUploadFileName().substring(getUploadFileName().lastIndexOf('.')+1,getUploadFileName().length());
			
			//이전 파일 삭제
			File deleteFile = new File(fileUploadPath + noticeDTO.getNotice_file());
			deleteFile.delete();
			
			//새 파일 업로드
			File destFile = new File(fileUploadPath + file_name + "." + file_ext);
			FileUtils.copyFile(getUpload(), destFile);
	
			//파일 정보 업데이트.
			sqlMapper.update("Notice.updateFile", noticeDTO);
		}

		// 수정이 끝나면 view 페이지로 이동.
		resultClass = (NoticeDTO) sqlMapper.queryForObject("Notice.selectOne", noticeDTO.getNotice_num());

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

