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
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import board.notice.dto.*;

import java.net.URLEncoder;
import java.sql.SQLException;

public class ReadNoticeAction extends ActionSupport implements ConDAOAware,
		Preparable, ModelDriven {

	public static SqlMapClient sqlMapper;
	private NoticeDTO resultClass = new NoticeDTO(); // 쿼리 결과 값을 저장할 객체
	NoticeDTO noticeDTO;

	private int currentPage;
	private int max_num;
	private int min_num;
	private int next_num;
	private int prev_num;
	private int nrow_num;
	private int prow_num;

	public int getNrow_num() {
		return nrow_num;
	}

	public void setNrow_num(int nrow_num) {
		this.nrow_num = nrow_num;
	}

	public int getProw_num() {
		return prow_num;
	}

	public void setProw_num(int prow_num) {
		this.prow_num = prow_num;
	}

	private InputStream inputStream;
	private String contentDisposition;
	private long contentLength;

	// 추가한것.
	private StringBuffer pagingHtml = new StringBuffer();

	//

	// 상세보기
	public String execute() throws Exception {
		// 해당 글의 조회수 +1
		sqlMapper.update("Notice.updateReadCount", noticeDTO);

		// 게시글의 마지막번호를 받아온다
		resultClass = (NoticeDTO) sqlMapper
				.queryForObject("Notice.selectLastNo");
		max_num = resultClass.getNotice_num();

		// 게시글의 처음번호를 받아온다
		resultClass = (NoticeDTO) sqlMapper
				.queryForObject("Notice.selectFirstNo");
		min_num = resultClass.getNotice_num();
		
		//
		resultClass = (NoticeDTO) sqlMapper.queryForObject(
				"Notice.selectNextNo", noticeDTO.getNotice_num());
		next_num = resultClass.getNotice_num();

		resultClass = (NoticeDTO) sqlMapper.queryForObject(
				"Notice.selectPrevNo", noticeDTO.getNotice_num());
		prev_num = resultClass.getNotice_num();


		// 해당 번호의 글을 가져온다
		resultClass = (NoticeDTO) sqlMapper.queryForObject("Notice.selectOne",
				noticeDTO.getNotice_num());

		pagingHtml.append(resultClass.getNotice_content());

		return SUCCESS;
	}

	public int getNext_num() {
		return next_num;
	}

	public void setNext_num(int next_num) {
		this.next_num = next_num;
	}

	public int getPrev_num() {
		return prev_num;
	}

	public void setPrev_num(int prev_num) {
		this.prev_num = prev_num;
	}

	public int getMax_num() {
		return max_num;
	}

	public void setMax_num(int max_num) {
		this.max_num = max_num;
	}

	public int getMin_num() {
		return min_num;
	}

	public void setMin_num(int min_num) {
		this.min_num = min_num;
	}

	public String checkAction() throws Exception {
		return SUCCESS;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
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

	// 추가
	public StringBuffer getPagingHtml() {
		return pagingHtml;
	}

	public void setPagingHtml(StringBuffer pagingHtml) {
		this.pagingHtml = pagingHtml;
	}

}
