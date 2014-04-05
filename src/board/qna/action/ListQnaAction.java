package board.qna.action;

import java.util.ArrayList;
import java.util.List;

import board.qna.dto.QnaDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;

import common.ConDAOAware;
import common.PagingAction;

public class ListQnaAction extends ActionSupport implements ConDAOAware{
	
	public static SqlMapClient sqlMapper;
	
	private List<QnaDTO> list = new ArrayList<QnaDTO>();
	
	private int currentPage = 1;
	private int totalCount;
	private int blockCount = 10;
	private int blockPage = 5;
	private String pagingHtml;
	private PagingAction page;
	
	private String actionName = "listQna";
	


	public void setConDAO(SqlMapClient sqlMapper) {
		
		this.sqlMapper = sqlMapper;
		
		
	}

}
