package user.common.action;

import java.util.ArrayList;
import java.util.List;

import board.qna.dto.QnaDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;

import common.ConDAOAware;
import common.action.PagingAction;

public class ListMyQnaAction extends ActionSupport implements
		ConDAOAware {
	public static SqlMapClient sqlMapper;
	private List<QnaDTO> list = new ArrayList<QnaDTO>();

/*//	private int currentPage = 1;
//	private int totalCount;
//	private int blockCount = 10; // 한 페이지의 게시물의 수
//	private int blockPage = 5; // 한 화면에 보여줄 페이지
	private String pagingHtml;
	private PagingAction page;
	private String actionName = "myAllListPageQna";*/
	private String session_id;

	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;

	}

	public String execute() throws Exception {
		list = sqlMapper.queryForList("Qna.myListQna", session_id);
	/*	totalCount = list.size(); // qna 전체 글 갯수를 구한다.
		page = new PagingAction(actionName, currentPage, totalCount,
				blockCount, blockPage);
		pagingHtml = page.getPagingHtml().toString();

		int lastCount = totalCount;

		if (page.getEndCount() < totalCount)
			lastCount = page.getEndCount() + 1;

		list = list.subList(page.getStartCount(), lastCount);
*/
		return SUCCESS;

	}

	public List<QnaDTO> getList() {
		return list;
	}

	public void setList(List<QnaDTO> list) {
		this.list = list;
	}

	/*public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getBlockCount() {
		return blockCount;
	}

	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}

	public int getBlockPage() {
		return blockPage;
	}

	public void setBlockPage(int blockPage) {
		this.blockPage = blockPage;
	}

	public String getPagingHtml() {
		return pagingHtml;
	}

	public void setPagingHtml(String pagingHtml) {
		this.pagingHtml = pagingHtml;
	}

	public PagingAction getPage() {
		return page;
	}

	public void setPage(PagingAction page) {
		this.page = page;
	}*/

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

/*	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}*/

}
