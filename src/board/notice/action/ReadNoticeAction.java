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
import java.util.ArrayList;
import java.util.List;

public class ReadNoticeAction extends ActionSupport implements ConDAOAware,Preparable, ModelDriven {

	public static SqlMapClient sqlMapper;
	private NoticeDTO resultClass = new NoticeDTO(); // 쿼리 결과 값을 저장할 객체
	NoticeDTO noticeDTO;
	private int n_count;
	private int currentPage;

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
		
		// 게시글의 총 갯수를 가져온다
		resultClass = (NoticeDTO) sqlMapper.queryForObject("Notice.selectNCount");
        n_count = resultClass.getNotice_num();
		
		// 해당 번호의 글을 가져온다
		resultClass = (NoticeDTO) sqlMapper.queryForObject("Notice.selectOne",noticeDTO.getNotice_num());
		pagingHtml.append(resultClass.getNotice_content());

		return SUCCESS;
	}

	public String rnum() throws Exception {
	    // 게시글의 총 갯수를 가져온다
	    resultClass = (NoticeDTO) sqlMapper.queryForObject("Notice.selectNCount");
        n_count = resultClass.getNotice_num();
	    
	    List<NoticeDTO> list = ListNoticeAction.list;
	        for(NoticeDTO dto : list){
	            if(dto.getRnum() == noticeDTO.getRnum()){
	                resultClass = dto;
	            }
	        }
	    	   
        // 해당 글의 조회수 +1
        sqlMapper.update("Notice.updateReadCount", resultClass);
        pagingHtml.append(resultClass.getNotice_content());

        return SUCCESS;
    }

	public int getN_count() {
        return n_count;
    }

    public void setN_count(int n_count) {
        this.n_count = n_count;
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
