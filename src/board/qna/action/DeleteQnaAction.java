package board.qna.action;

import board.qna.dto.QnaDTO;

import com.opensymphony.xwork2.ActionSupport;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import common.ConDAOAware;

import java.io.File;
import java.io.Reader;
import java.io.IOException;

public class DeleteQnaAction extends ActionSupport implements ConDAOAware {
	
	public static SqlMapClient sqlMapper;

	private QnaDTO paramClass = new QnaDTO();		// 파라미터를 저장할 객체
	private QnaDTO resultClass = new QnaDTO();	// 쿼리 결과 값을 저장할 객체
	
	private int currentPage;	// 현재 페이지		
	private int qna_num;		// 현재 글 고유넘버	
		
	public void setConDAO(SqlMapClient sqlMapper){
		this.sqlMapper = sqlMapper;
	}

	// 게시글 DELETE 액션.
	public String execute() throws Exception {
		
		// 해당 번호의 글을 가져온다.
		resultClass = (QnaDTO) sqlMapper.queryForObject("Qna.selectWhereQnaNum", getQna_num());

		// 삭제할 항목 설정.
		paramClass.setQna_num(getQna_num());
				
		// 삭제 쿼리 수행.
		sqlMapper.update("Qna.deleteBoard", paramClass);

		return SUCCESS;
	}

	public QnaDTO getParamClass() {
		return paramClass;
	}

	public void setParamClass(QnaDTO paramClass) {
		this.paramClass = paramClass;
	}
	
	public QnaDTO getResultClass() {
		return resultClass;
	}

	public void setResultClass(QnaDTO resultClass) {
		this.resultClass = resultClass;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getQna_num() {
		return qna_num;
	}

	public void setQna_num(int qna_num) {
		this.qna_num = qna_num;
	}			

}