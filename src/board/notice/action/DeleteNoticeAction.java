package board.notice.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import common.ConDAOAware;
import board.notice.dto.*;

import java.io.File;
import java.io.Reader;
import java.io.IOException;

public class DeleteNoticeAction extends ActionSupport implements ConDAOAware,
	Preparable, ModelDriven {

	public static SqlMapClient sqlMapper;
	private NoticeDTO resultClass = new NoticeDTO(); //쿼리 결과 값을 저장할 객체
	NoticeDTO noticeDTO;

	private int currentPage;	//현재 페이지

	// 게시글 삭제
	public String execute() throws Exception {
		
		//해당 번호의 글을 가져온다
		resultClass = (NoticeDTO) sqlMapper.queryForObject("Notice.selectOne", noticeDTO.getNotice_num());
				
		//삭제 쿼리 수행
		sqlMapper.update("Notice.deleteNotice", noticeDTO);

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

	public NoticeDTO getResultClass() {
		return resultClass;
	}

	public void setResultClass(NoticeDTO resultClass) {
		this.resultClass = resultClass;
	}

}

