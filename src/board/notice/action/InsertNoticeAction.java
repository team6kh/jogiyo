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
