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

	//게시글 수정
	public String execute() throws Exception {
		
		//일단 항목만 수정한다
		sqlMapper.update("Notice.updateNotice", noticeDTO);

		// 수정이 끝나면 view 페이지로 이동.
		resultClass = (NoticeDTO) sqlMapper.queryForObject("Notice.selectOne", noticeDTO.getNotice_num());

		return SUCCESS;
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

