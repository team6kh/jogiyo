package board.qna.action;

import java.util.ArrayList;
import java.util.List;

import board.qna.dto.QnaDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

import common.ConDAOAware;
import common.PagingAction;


public class ListQnaAction implements Action, ConDAOAware{

	public static SqlMapClient sqlMapper;
	
	private QnaDTO paramClass = new QnaDTO();
	
	private QnaDTO resultClass = new QnaDTO();
	
	private int qna_num;
	
	private String qna_category;
	
	private List<QnaDTO> list = new ArrayList<QnaDTO>();
	private List<QnaDTO> topList = new ArrayList<QnaDTO>();
	
	private int currentPage = 1;			// 현재 페이지
	private int totalCount;					// 총 게시물의 수
	private int blockCount = 10;			// 한 페이지의 게시물의 수
	private int blockPage = 5;				// 한 화면에 보여줄 페이지 수
	private String pagingHtml;				// 페이징을 구현한 HTML
	private PagingAction page;				// 페이징 클래스
	
	private String actionName = "listQna";	// 페이징액션과 로그인액션에서 쓰인다...
	
	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;
	}
	
	public String execute() throws Exception {
		QnaDTO qnaDTO = new QnaDTO();
		
		topList = sqlMapper.queryForList("Qna.qnaTopList");
		
		list = sqlMapper.queryForList("Qna.qnaList", getQna_category());
		totalCount = list.size(); // 전체 글 갯수를 구한다.
		page = new PagingAction(actionName, currentPage, totalCount, blockCount, blockPage); // pagingAction 객체 생성.
		pagingHtml = page.getPagingHtml().toString(); // 페이지 HTML 생성.

		// 현재 페이지에서 보여줄 마지막 글의 번호 설정.
		int lastCount = totalCount;

		// 현재 페이지의 마지막 글의 번호가 전체의 마지막 글 번호보다 작으면 lastCount를 +1 번호로 설정.
		if (page.getEndCount() < totalCount)
			lastCount = page.getEndCount() + 1;

		// 전체 리스트에서 현재 페이지만큼의 리스트만 가져온다.
		list = list.subList(page.getStartCount(), lastCount);
		
		return SUCCESS;
				
		
	}
	
	public String detail() throws Exception {
		
		//조회수 증가
		sqlMapper.update("Qna.updateReadCount", getQna_num());
		
		//상세보기 조회
		resultClass = (QnaDTO)sqlMapper.queryForObject("Qna.qnaDetail", getQna_num());
		
		return SUCCESS;
	}

	public List<QnaDTO> getList() {
		return list;
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

	public int getQna_num() {
		return qna_num;
	}

	public void setQna_num(int qna_num) {
		this.qna_num = qna_num;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
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
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public void setList(List<QnaDTO> list) {
		this.list = list;
	}

	public List<QnaDTO> getTopList() {
		return topList;
	}

	public void setTopList(List<QnaDTO> topList) {
		this.topList = topList;
	}

	public String getQna_category() {
		return qna_category;
	}

	public void setQna_category(String qna_category) {
		this.qna_category = qna_category;
	}
	
}